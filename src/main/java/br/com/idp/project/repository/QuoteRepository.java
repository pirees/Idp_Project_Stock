package br.com.idp.project.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idp.project.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote, UUID> {

	
}
