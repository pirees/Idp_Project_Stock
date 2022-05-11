package br.com.idp.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.idp.project.model.Quote;
import br.com.idp.project.service.QuoteService;

@ExtendWith(MockitoExtension.class)
class QuoteControllerTest {
	
	@Mock
	private QuoteService quoteService;
	
	@Mock
	private QuoteController quoteController;
	
	@Mock
	private BindingResult result;
	
	@BeforeEach
    void init(){
		quoteController = new QuoteController(quoteService);
    }

	@Test 
	public void createOnlyQuote() {

		Quote quote = new Quote();
		quote.setDate(LocalDate.now());
		quote.setPrice("15");
		
		ResponseEntity<?> entity = quoteController.saveQuote(quote,result);
		
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}

}
