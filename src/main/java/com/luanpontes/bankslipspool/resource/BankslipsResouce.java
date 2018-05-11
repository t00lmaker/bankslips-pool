package com.luanpontes.bankslipspool.resource;

import static com.luanpontes.bankslipspool.model.StatusBankslip.CANCELED;
import static com.luanpontes.bankslipspool.model.StatusBankslip.PAID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luanpontes.bankslipspool.exception.ResourceNotFoundException;
import com.luanpontes.bankslipspool.model.Bankslip;
import com.luanpontes.bankslipspool.repository.BankslipsRepository;
import com.luanpontes.bankslipspool.service.BankslipService;
import com.luanpontes.bankslipspool.service.BankslipStatusService;

@RestController
public class BankslipsResouce {
	
	@Autowired
	BankslipStatusService banklipStatusService;
	
	@Autowired
	BankslipService banklipService;
	
	@Autowired
	BankslipsRepository bankslipRep;
	
	@PostMapping("/bankslips")
	public ResponseEntity<Bankslip> create(@Valid @RequestBody(required=true) Bankslip bankslip) {
		Bankslip newBanklip = bankslipRep.save(bankslip);
		return new ResponseEntity<Bankslip>(newBanklip, CREATED);
	}


	@GetMapping("/bankslips")
	public ResponseEntity<List<Bankslip>> all(){
		return new ResponseEntity<List<Bankslip>>((List<Bankslip>) bankslipRep.findAll(), OK);
	}

	@GetMapping("/bankslips/{id}")
	public ResponseEntity<Bankslip> get(@PathVariable long id) {
		Optional<Bankslip> bankslipOp = bankslipRep.findById(id);
		if(!bankslipOp.isPresent())
			throw new ResourceNotFoundException(id, "Bankslip");
			
		Bankslip bankslip = bankslipOp.get();
		Integer valueFine = banklipService.calcFine(bankslip, LocalDate.now());
		bankslip.setFine(valueFine);
		return new ResponseEntity<Bankslip>(bankslip, OK);
	}

	@DeleteMapping("/bankslips/{id}/cancel")
	public ResponseEntity<Bankslip> delete(@PathVariable Long id) {
		Optional<Bankslip> optional = bankslipRep.findById(id);
		if(!optional.isPresent())
			throw new ResourceNotFoundException(id, "Bankslip");
		
		Bankslip bankslip = optional.get();
		if(banklipStatusService.isValidChangeStatus(bankslip, CANCELED)) {
			banklipStatusService.changeStatus(bankslip, CANCELED);
			bankslipRep.save(bankslip);
		}
		
		return new ResponseEntity<>(NO_CONTENT);
	}

	@PutMapping("/bankslips/{id}/pay")
	public ResponseEntity<Bankslip> update(@PathVariable Long id) {
		Optional<Bankslip> optional = bankslipRep.findById(id);
		if(!optional.isPresent())
			throw new ResourceNotFoundException(id, "Bankslip");
		
		Bankslip bankslip = optional.get();
		if(banklipStatusService.isValidChangeStatus(bankslip, PAID)) {
			banklipStatusService.changeStatus(bankslip, PAID);
			bankslipRep.save(bankslip);
		}
		
		bankslip.setId(id);
		bankslipRep.save(bankslip);
		return new ResponseEntity<>(NO_CONTENT);
	}
}
