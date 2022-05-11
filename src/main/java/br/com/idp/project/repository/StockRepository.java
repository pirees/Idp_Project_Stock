package br.com.idp.project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.idp.project.model.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {

	List<Stock> findByStockId(String stockId);

	String deleteByStockId(String stockId);

}
