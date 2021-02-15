package br.com.tinnova.prova.controller.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.tinnova.prova.model.Veiculo;
import br.com.tinnova.prova.repository.VeiculoRepository;

public class VeiculoForm {
	
	@NotNull @NotEmpty
	private String veiculo;

	@NotNull @NotEmpty
	private String marca;
	
	@NotNull
	private Integer ano;
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull 
	private boolean vendido;
	public String getVeiculo() {
		return veiculo;
	}
	public String getMarca() {
		return marca;
	}
	public Integer getAno() {
		return ano;
	}
	public String getDescricao() {
		return descricao;
	}
	public boolean isVendido() {
		return vendido;
	}
	
	public Veiculo converter() {
		Veiculo veiculo = new Veiculo();
		veiculo.setVeiculo(this.veiculo);
		veiculo.setMarca(this.marca);
		veiculo.setAno(this.ano);
		veiculo.setDescricao(descricao);
		veiculo.setVendido(this.vendido);
		veiculo.setDataCriacao(new Date());
		veiculo.setDataAtualizacao(new Date());
		return veiculo;
	}
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.getOne(id);
		veiculo.setVeiculo(this.veiculo);
		veiculo.setMarca(this.marca);
		veiculo.setAno(this.ano);
		veiculo.setDescricao(descricao);
		veiculo.setVendido(this.vendido);
		veiculo.setDataAtualizacao(new Date());
		return veiculo;
	}
}
