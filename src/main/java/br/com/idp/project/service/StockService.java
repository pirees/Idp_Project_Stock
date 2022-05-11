package br.com.idp.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.idp.project.controller.dto.StockDTO;
import br.com.idp.project.model.Stock;
import br.com.idp.project.repository.StockRepository;
import br.com.idp.project.web.WebClientApi;

@Service
@Transactional
public class StockService {
	
	@Value("${app.stock-manager.url}")
	private String urlStockManager;
	
	private StockRepository stockRepository;

	
	public StockService(StockRepository stockRepository ) {
		super();
		this.stockRepository = stockRepository;
	}

	public Stock save(Stock stock) {

		stockRepository.save(stock);
		
		return WebClientApi.insertStockIntoAPI(stock, urlStockManager);
	}
	
	public List<StockDTO> listStocksByStockId(String stockId){
		
		List<Stock> stocks = stockRepository.findByStockId(stockId);
					
		return StockDTO.converter(stocks);
		
	}
	
	public List<StockDTO> listAllStockAndQuote(){
		
		List<Stock> stocks = stockRepository.findAll();
		
		return StockDTO.converter(stocks);
		
	}
	
	public List<Stock> removeAll(@PathVariable String stockId){
	List <Stock> stock = stockRepository.findByStockId(stockId);
		if(!stock.isEmpty()) {
			stockRepository.deleteByStockId(stockId);
		}
		
		return stock;
	}
	
	

	

}
