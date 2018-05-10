package com.luanpontes.bankslipspool.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luanpontes.bankslipspool.model.Bankslip;
import com.luanpontes.bankslipspool.repository.BankslipsRepository;

@RestController
public class BankslipsResouce {
	
	@Autowired
	BankslipsRepository bankslipRep;
	
	@PostMapping("/bankslips")
	public ResponseEntity<Bankslip> create(@Valid @RequestBody(required=true) Bankslip bankslip) {
		Bankslip newBanklip = bankslipRep.save(bankslip);
		return new ResponseEntity<Bankslip>(newBanklip, HttpStatus.CREATED);
	}


	@GetMapping("/bankslips")
	public ResponseEntity<List<Bankslip>> all(){
		return new ResponseEntity<List<Bankslip>>((List<Bankslip>) bankslipRep.findAll(), HttpStatus.OK);
	}

	@GetMapping("/bankslips/{id}")
	public ResponseEntity<Bankslip> get(@PathVariable long id) {
		Optional<Bankslip> bankslipOp = bankslipRep.findById(id);
		if(!bankslipOp.isPresent())
			return new ResponseEntity<Bankslip>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Bankslip>(bankslipOp.get(), HttpStatus.OK);
	}

	@DeleteMapping("/bankslips/{id}/cancel")
	public ResponseEntity<Bankslip> delete(@PathVariable Long id) {
		Optional<Bankslip> optional = bankslipRep.findById(id);
		if(!optional.isPresent())
			return new ResponseEntity<Bankslip>(HttpStatus.NOT_FOUND);
		
		bankslipRep.delete(optional.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/bankslips/{id}")
	public ResponseEntity<Bankslip> update(@RequestBody Bankslip bankslip, @PathVariable Long id) {
		Optional<Bankslip> bankslipOp = bankslipRep.findById(id);
		if(!bankslipOp.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		bankslip.setId(id);
		bankslipRep.save(bankslip);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
