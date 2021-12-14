package com.todos.models;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private Long Id;

	private String username;
	private String description;
	private Date targetDate;
	private boolean isDone;

	// Constructors
	public Todo() {

	}

	public Todo(Long id, String username, String description, Date targetDate, boolean isDone) {
		super();
		Id = id;
		this.username = username;
		this.description = description;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	// Getters and Setters

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Todo [Id=" + Id + ", username=" + username + ", description=" + description + ", targetDate="
				+ targetDate + ", isDone=" + isDone + "]";
	}
	
	

}
