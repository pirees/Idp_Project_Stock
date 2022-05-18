package br.com.idp.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.idp.project.controller.dto.ReadStockDTO;
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

	public ReadStockDTO save(Stock stock) {

		ReadStockDTO dto = new ReadStockDTO();
		
		dto.setId(stock.getStockId());
		dto.setDescription(stock.getDescription());
		
		stockRepository.save(stock);
		
		return WebClientApi.insertStockIntoAPI(dto, urlStockManager);
	}
	
	public List<StockDTO> listStocksByStockId(String stockId){
		
		List<Stock> stockList = stockRepository.findByStockId(stockId);
					
		return StockDTO.converter(stockList);
		
	}
	
	public List<StockDTO> listAllStockAndQuote(){
		
		List<Stock> stockList = stockRepository.findAll();
		
		return StockDTO.converter(stockList);
		
	}
	
	public List<Stock> removeAll(String stockId){
	List <Stock> stockList = stockRepository.findByStockId(stockId);
		/*if(!stockList.isEmpty()) {
			stockRepository.deleteByStockId(stockId);
		}*/
	
		stockRepository.deleteByStockId(stockId);
		
		return stockList;
	}
	
	

	

}
