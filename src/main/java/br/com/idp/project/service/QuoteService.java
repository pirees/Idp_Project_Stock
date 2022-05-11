package br.com.idp.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idp.project.model.Quote;
import br.com.idp.project.repository.QuoteRepository;


@Service
@Transactional
public class QuoteService {

	private QuoteRepository quotesRepository;

	
	public QuoteService(QuoteRepository quotesRepository) {
		super();
		this.quotesRepository = quotesRepository;
	}
	
	public Quote save( Quote quotes) {
		
		quotesRepository.save(quotes);
		
		return quotes; 
	}
	
	public List<Quote> listQuotes(){
		
		List<Quote> quotes = quotesRepository.findAll();
								
		return quotes;
		
	}
	
}
