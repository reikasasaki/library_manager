package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Library;
import com.example.entity.Log;
import com.example.entity.User;
import com.example.service.LibraryService;
import com.example.service.LogService;
import com.example.service.LoginUser;

@Controller
@RequestMapping("library")
public class LibraryController {

	private final LibraryService libraryService;
	private final LogService logService;
	
	@Autowired
	public LibraryController(LibraryService libraryService,LogService logService) {
		this.libraryService = libraryService;
		this.logService = logService;
	}
	
	@GetMapping
	public String index(Model model) {
		List<Library> libraries = this.libraryService.findAll();
		model.addAttribute("libraries", libraries);
		return "library/index";
	}
	
	@GetMapping("/library")
	public String getMain(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		model.addAttribute("user",loginUser.getUser());
		return "library";
	}
	
	@GetMapping("/borrow")
	public String borrowingForm(@RequestParam("id") Integer id, Model model) {
		Library libraries = this.libraryService.findLibrary(id);
		model.addAttribute("libraries",libraries);
		return "library/borrowingForm";
	}
	
	@PostMapping("/borrow")
	public String borrow(@RequestParam("id") Integer id,@RequestParam("return_due_date") String returnDueDate,
			@AuthenticationPrincipal LoginUser loginUser) {
		Library libraries = this.libraryService.findLibrary(id);
		User user = loginUser.getUser();
		Integer userId = user.getId();
		libraries.setUser_id(userId);
		libraryService.update(id, libraries);
		Log log = new Log();
		log.setLibraryId(id);
		log.setUserId(userId);
		log.setRentDate(LocalDateTime.now());
		log.setReturn_due_date(LocalDateTime.parse(returnDueDate+"T00:00:00"));
		log.setReturn_date(null);
		this.logService.save(log);
		return "redirect:/library";
	}
	
	@PostMapping("/return")
	public String returnBook(@RequestParam("id") Integer id, @AuthenticationPrincipal LoginUser loginUser) {
		Library libraries = this.libraryService.findLibrary(id);
		libraries.setUser_id(0);
		libraryService.update(id, libraries);
		User user = loginUser.getUser();
		Integer userId = user.getId();
		Log log = this.logService.findLatestLibrary(id, userId);
		log.setReturn_date(LocalDateTime.now());
		this.logService.save(log);
		return "redirect:/library";
	}
	
	
	@GetMapping("/history")
	public String history(Model model,@AuthenticationPrincipal LoginUser loginuser) {
		User user = loginuser.getUser();
		Integer userId = user.getId();
		List<Log> log = this.logService.findLog(userId);
		model.addAttribute("log",log);
		return "library/borrowHistory";
	}
}
