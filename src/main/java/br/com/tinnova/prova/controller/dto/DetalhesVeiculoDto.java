package br.com.tinnova.prova.controller.dto;

import br.com.tinnova.prova.model.Veiculo;

public class DetalhesVeiculoDto {
	private Long id;
	private String veiculo;
	private String marca;
	private Integer ano;
	private String descricao;
	private boolean vendido;
	
	public Long getId() {
		return id;
	}
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

	public DetalhesVeiculoDto(Veiculo veiculo) {
		this.id = veiculo.getId();
		this.veiculo = veiculo.getVeiculo();
		this.marca = veiculo.getMarca();
		this.ano =  veiculo.getAno();
		this.descricao = veiculo.getDescricao();
		this.vendido = veiculo.isVendido();
	}
}
