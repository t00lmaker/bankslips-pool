package com.luanpontes.bankslipspool.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Bankslip implements Serializable{
	
	private static final long serialVersionUID = 5686025464282714459L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@JsonProperty("due_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dueDate;
	
	@NotNull
	@JsonProperty("total_in_cents")
	private Integer totalInCents;
	
	@NotNull
	private String customer;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusBankslip status;
	
	@Transient
	private Integer fine;

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
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

	public StatusBankslip getStatus() {
		return status;
	}

	public void setStatus(StatusBankslip status) {
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
