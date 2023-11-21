package com.example.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Log;

@Repository
public interface LogController extends JpaRepository <Log, Integer>{

}
