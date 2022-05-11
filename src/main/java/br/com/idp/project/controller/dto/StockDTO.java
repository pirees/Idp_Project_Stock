package br.com.idp.project.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import br.com.idp.project.model.Quote;
import br.com.idp.project.model.Stock;


public class StockDTO {
	
	private UUID id;
	
	private String stockId;
	
	@NotEmpty
	private Map<LocalDate, String> quote;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	public Map<LocalDate, String> getQuote() {
		return quote;
	}

	public void setQuote(Map<LocalDate, String> quote) {
		this.quote = quote;
	}
	
	public StockDTO(Stock stock) {
		this.id = stock.getId();
		this.stockId = stock.getStockId();
		this.quote = stock.getQuote().stream().collect(Collectors.toMap(Quote::getDate, Quote::getPrice));
	}
	
	public StockDTO() {
		super();
	}

	public static List<StockDTO> converter(List<Stock> stocks) {
		return stocks.stream().map(StockDTO::new).collect(Collectors.toList());
	}
	

	
}
