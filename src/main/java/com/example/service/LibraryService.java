package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Library;
import com.example.repository.LibraryRepository;

@Service
public class LibraryService {

	private final LibraryRepository libraryRepository;
	
	@Autowired
	public LibraryService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}
	
	public List<Library> findAll(){
		return this.libraryRepository.findAll();
	}
	
	public Library findLibrary(Integer id) {
		Optional<Library> optionalLibrary = this.libraryRepository.findById(id);
		Library libraries = optionalLibrary.get();
		return libraries;
	}
	
	public Library update(Integer id,Library libraraies) {
		Library libraries = this.findLibrary(id);
		libraries.setName(libraries.getName());
		libraries.setUser_id(libraries.getUser_id());
		return this.libraryRepository.save(libraries);
	}
	
	
}
