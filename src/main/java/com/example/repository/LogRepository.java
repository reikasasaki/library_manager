package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Log;

public interface LogRepository extends JpaRepository<Log, Integer> {

	public Log findByLibraryIdAndUserIdOrderByRentDateDesc(Integer libraryId,Integer userId);
	
	public List<Log> findByUserId(Integer userId);

}
