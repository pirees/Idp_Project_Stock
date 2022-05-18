package br.com.idp.project.web;

import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.idp.project.controller.dto.ReadStockDTO;

public class WebClientApi {
	
	private static WebClient webClient = WebClient.create();
	
	
	
	public static List<ReadStockDTO> getAllStock(String url){
		return webClient.get().uri(url + ":8080/stock")
				.retrieve()
				.bodyToFlux(ReadStockDTO.class)
				.collectList()
				.block();
	}
	
	public static ReadStockDTO insertStockIntoAPI(ReadStockDTO stock, String url){
		return webClient.post().uri( url + ":8080/stock")
				.bodyValue(stock)
				.retrieve()
				.bodyToMono(ReadStockDTO.class)
				.block();
	}
	
	public void registerAPI(String urlStock, String urlManager) {
		Registration register = new Registration(urlStock ,8081);
		webClient.post().uri(urlManager + ":8080/notification")
		.bodyValue(register)
		.retrieve()
		.bodyToFlux(Registration.class)
		.blockFirst();
	}

}
