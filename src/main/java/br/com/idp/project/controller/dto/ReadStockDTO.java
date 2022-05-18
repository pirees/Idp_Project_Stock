package br.com.idp.project.controller.dto;


import br.com.idp.project.model.Stock;

public class ReadStockDTO {

	private String id;
	
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ReadStockDTO() {
		super();
	}
	
	public ReadStockDTO(Stock stock) {
		this.id = stock.getStockId();
		this.description = stock.getDescription();
	}
}
