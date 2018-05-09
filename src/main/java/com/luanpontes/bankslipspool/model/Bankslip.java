package com.luanpontes.bankslipspool.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Bankslip implements Serializable{
	
	private static final long serialVersionUID = 5686025464282714459L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty("due_date")
	@NotNull
	private String dueDate;
	
	@NotNull
	@JsonProperty("total_in_cents")
	private Integer totalInCents;
	
	@NotNull
	private String customer;
	
	@NotNull
	private String status;
	
	@Transient
	private Integer fine;

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getTotalInCents() {
		return totalInCents;
	}

	public void setTotalInCents(Integer totalInCents) {
		this.totalInCents = totalInCents;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String custumer) {
		this.customer = custumer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFine() {
		return fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}
}
