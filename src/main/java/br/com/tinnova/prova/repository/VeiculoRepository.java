package br.com.tinnova.prova.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.tinnova.prova.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	@Query("SELECT COUNT(v) AS quantidade FROM Veiculo v WHERE v.vendido = :vendido")
	List<Object> exibirQuantidadeVendidos(@Param("vendido")  boolean vendido);
	
	@Query("SELECT v.marca AS marca, COUNT(v) AS quantidade FROM Veiculo v GROUP BY v.marca")
	List<Object> exbirAgrupadoPorFabricante();
	
	@Query("SELECT v FROM Veiculo v WHERE v.dataCriacao > sysdate - 7 and v.vendido = true")
	List<Object> exibirVeiculosSemana();
	
}
