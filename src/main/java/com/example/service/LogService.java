package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;

@Service
public class LogService {
	
	private LogRepository logRepository;
	
	@Autowired
	public LogService(LogRepository logRepository) {
		this.logRepository = logRepository;
	}

	public Log save(Log log) {
		return this.logRepository.save(log);
	}
	
	public Log findLatestLibrary(Integer libraryId,Integer userId) {
		return this.logRepository.findByLibraryIdAndUserIdOrderByRentDateDesc(libraryId, userId);
	}
	
	public List<Log> findLog(Integer userId) {
		return this.logRepository.findByUserId(userId);
	}
}
