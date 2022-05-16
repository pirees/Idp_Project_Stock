package br.com.idp.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.idp.project.model.Quote;
import br.com.idp.project.service.QuoteService;


@RestController
public class QuoteController {

	private QuoteService quotesService;

	public QuoteController(QuoteService quotesService) {
		super();
		this.quotesService = quotesService;
	}
	
	@PostMapping(value = "quote")
	public ResponseEntity<Quote> saveQuote(@RequestBody @Valid Quote quote, BindingResult result){
			
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(quote);
		}
		
		 quotesService.save(quote);

		return ResponseEntity.status(HttpStatus.CREATED).body(quote);
		
	}
	
	@GetMapping(value = "quote")
	public ResponseEntity<List<Quote>> findQuote (){
		
		List<Quote> quoteList = quotesService.listQuotes();
		
		if(quoteList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(quoteList);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(quoteList);
		
	}
	
	
}
