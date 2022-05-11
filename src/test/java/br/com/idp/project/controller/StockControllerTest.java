package br.com.idp.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import br.com.idp.project.model.Quote;
import br.com.idp.project.model.Stock;
import br.com.idp.project.service.QuoteService;
import br.com.idp.project.service.StockService;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {

	
	@Mock
	private StockService stockService;
	
	@Mock
	private QuoteService quoteService;
	
	@Mock
	private StockController stockController;
	
	@Mock
	private BindingResult result;
	
	@BeforeEach
    void init(){
		stockController = new StockController(stockService);
    }
	
	@Test 
	public void createOnlyStock() {

		Stock stock = new Stock();
		stock.setStockId("itsa4");
		
		ResponseEntity<?> entity = stockController.saveStock(stock, result);
			
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}
	
	@Test 
	public void createStockAndQuote() {

		Quote quote = new Quote();
		quote.setDate(LocalDate.now());
		quote.setPrice("15");
		
		List<Quote> quoteList = new ArrayList<>();
		
		quoteList.add(quote);
		
		Stock stock = new Stock();
		stock.setStockId("itsa4");
		stock.setQuote(quoteList);
		
		ResponseEntity<?> entity = stockController.saveStock(stock, result);
			
		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
	}

}
