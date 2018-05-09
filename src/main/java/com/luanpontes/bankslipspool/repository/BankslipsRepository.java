package com.luanpontes.bankslipspool.repository;

import org.springframework.data.repository.CrudRepository;

import com.luanpontes.bankslipspool.model.Bankslip;

public interface BankslipsRepository extends CrudRepository<Bankslip, Long> {

}
