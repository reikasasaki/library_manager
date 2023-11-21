package com.example.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class Log {

	@Id
	@SequenceGenerator(name = "LOG_ID_GENERATOR", sequenceName = "LOG_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOG_ID_GENERATOR")
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "LIBRARY_ID")
	private Integer library_id;
	
	@Column(name = "USER_ID")
	private Integer user_id;
	
	@Column(name = "RENT_DATE")
	private Date rent_date;
	
	@Column(name = "RETURN_DATE")
	private Date return_date;
	
	@Column(name = "RETURN_DUE_DATE")
	private LocalDateTime return_due_date;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLibrary_id() {
		return this.library_id;
	}

	public void setLibrary_id(Integer library_id) {
		this.library_id = library_id;
	}

	public Integer getUser_id() {
		return this.user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Date getRent_date() {
		return this.rent_date;
	}

	public void setRent_date(Date rent_date) {
		this.rent_date = rent_date;
	}

	public Date getReturn_date() {
		return this.return_date;
	}

	public void setReturn_date(Date return_date) {
		this.return_date = return_date;
	}

	public LocalDateTime getReturn_due_date() {
		return this.return_due_date;
	}

	public void setReturn_due_date(LocalDateTime return_due_date) {
		this.return_due_date = return_due_date;
	}
	
	
}
