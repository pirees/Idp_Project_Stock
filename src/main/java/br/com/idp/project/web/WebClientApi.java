package br.com.idp.project.web;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import br.com.idp.project.model.Stock;

public class WebClientApi {
	
	private static WebClient webClient = WebClient.create();
	
	public static List<Stock> getAllStock(String url){
		return webClient.get().uri(url + ":8080/stock")
				.retrieve()
				.bodyToFlux(Stock.class)
				.collectList()
				.block();
	}
	
	public static Stock insertStockIntoAPI(Stock stock, String url){
		return webClient.post().uri( url + ":8080/stock")
				.bodyValue(stock)
				.retrieve()
				.bodyToMono(Stock.class)
				.block();
	}

}
