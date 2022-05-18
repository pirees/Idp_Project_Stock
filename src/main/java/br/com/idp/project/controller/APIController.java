package br.com.idp.project.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idp.project.web.WebClientApi;

@RestController
@RequestMapping("/")
public class APIController {

	WebClientApi web = new WebClientApi();

	@Value("${app.stock-quote.url}")
	private String urlStockQuote;
	
	@Value("${app.stock-manager.url}")
	private String urlStockManager;
	
	@PostConstruct
	public void registerApi(){
		web.registerAPI(urlStockQuote, urlStockManager);
			
	}

	@DeleteMapping("/stockcache")
	@CacheEvict(cacheNames = "listStock")
	public void removeCache() {
		
	}
}
