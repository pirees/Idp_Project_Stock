package br.com.idp.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.idp.project.controller.dto.StockDTO;
import br.com.idp.project.model.Stock;
import br.com.idp.project.service.StockService;

@RestController
public class StockController {

	private StockService stockService;

	public StockController(StockService stockService) {
		super();
		this.stockService = stockService;
	}
	
	@PostMapping(value = "stock")
	@CacheEvict(value = "listStock", allEntries = true)
	public ResponseEntity<Stock> saveStock(@RequestBody @Valid Stock stock, BindingResult result){
		if(result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(stock);
		}	
		stockService.save(stock);
		return ResponseEntity.status(HttpStatus.CREATED).body(stock);
		
	}
	
	@GetMapping(value = "/stock")
	@Cacheable(value = "listStock")
	public ResponseEntity<List<StockDTO>> findAllStock (){
		
		List<StockDTO> stockList = stockService.listAllStockAndQuote();
		
		if(stockList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stockList);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(stockList);
		
	}
	
	
	@GetMapping(value = "stock/{stockId}")
	public ResponseEntity<List<StockDTO>> findStockById (@PathVariable("stockId") String stockId){
		
		List<StockDTO> stockList = stockService.listStocksByStockId(stockId);
		
		if(stockList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stockList);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(stockList);
		
	}
	
	@DeleteMapping(value = "stock/{stockId}")
	@CacheEvict(value = "listStock", allEntries = true)
	public ResponseEntity<List<Stock>> removeStock (@PathVariable String stockId){
		List<Stock> stockList = stockService.removeAll(stockId);
		
		if(stockList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(stockList);
		}
			
		return ResponseEntity.status(HttpStatus.OK).body(stockList);
	}
	
	
}
