package com.creditcard.account.controller;

import com.creditcard.account.domain.Account;
import com.creditcard.account.domain.MessageResponse;
import com.creditcard.account.service.AccountService;
import com.creditcard.account.utils.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/api/account", produces = {"application/json"})
@RestController
public class AccountController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// @Autowired annotation provides the automatic dependency injection.
	@Autowired
	AccountService accountService;

	/**
	* description- Save account entity in the h2 database.
	* @PostMapping annotation handles the http post request matched with the given uri.
	* @RequestBody annotation binds the http request body to the domain object.
	* @Valid annotation validates a model after binding the user input to it.
	* @return the ResponseEntity object
	*/
	@PostMapping(value= "/add" ,consumes = {"application/json"})
	public ResponseEntity save(final @RequestBody @Valid Account account) {

		log.info("Saving account details in the database.");
		Validation validation = new Validation();

		//code to check  Luhn 10 credit card number
		if(!validation.isValidCreditCardNumber(account.getCard()))
			{
				final MessageResponse msg = new MessageResponse();
				msg.setMessage("Card Number is invalid");
				return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			}
		//New cards start with a £0 balance
		if(account.getBalance()>0){
			{
				final MessageResponse msg = new MessageResponse();
					msg.setMessage("New cards start with a £0 balance");
					return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
			}


		}
		//call service
		accountService.save(account);
		return new ResponseEntity<>(account.getId(), HttpStatus.OK);
	}


	/**
	 * description-Get all account details from the h2 database.
	 * @GetMapping annotation handles the http get request matched with the given uri.
	 * @return the ResponseEntity object
	 */
	@GetMapping(value= "/getall", produces = {"application/json"})
	public ResponseEntity getAll() {

		log.info("Getting account details from the database.");

		return new ResponseEntity<>(accountService.getAll(), HttpStatus.OK);
	}
}
