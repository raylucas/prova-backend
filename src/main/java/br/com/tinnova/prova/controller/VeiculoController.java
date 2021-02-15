package br.com.tinnova.prova.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.tinnova.prova.controller.dto.DetalhesVeiculoDto;
import br.com.tinnova.prova.controller.dto.VeiculoDto;
import br.com.tinnova.prova.controller.form.VeiculoForm;
import br.com.tinnova.prova.model.Veiculo;
import br.com.tinnova.prova.repository.VeiculoRepository;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	private boolean vendido = false;
		
	@GetMapping
	public List<VeiculoDto> exibirTodos() {
		List<Veiculo> veiculos = veiculoRepository.findAll();
		return VeiculoDto.converter(veiculos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesVeiculoDto> exibirPorId(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		if(veiculo.isPresent()) {
			return ResponseEntity.ok(new DetalhesVeiculoDto(veiculo.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<VeiculoDto> cadastrar(@RequestBody @Valid VeiculoForm form, UriComponentsBuilder uriBuilder) {
		Veiculo veiculo = form.converter();
		veiculoRepository.save(veiculo);
		
		URI uri = uriBuilder.path("/veiculo/{id}").buildAndExpand(veiculo.getId()).toUri();
		return ResponseEntity.created(uri).body(new VeiculoDto(veiculo));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VeiculoDto> atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoForm form){
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if(optional.isPresent()) {
			Veiculo veiculo = form.atualizar(id, veiculoRepository);
			return ResponseEntity.ok(new VeiculoDto(veiculo));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Veiculo> optional = veiculoRepository.findById(id);
		if(optional.isPresent()) {
			veiculoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/find")
	public List<Object> exibirPorVendidos(String q) {
		List<Object> objects = new ArrayList<>();
		switch (q) {
			case "nvendido":
				objects = veiculoRepository.exibirQuantidadeVendidos(vendido);
				break;
			case "fabricante":
				objects = veiculoRepository.exbirAgrupadoPorFabricante();
				break;
			case "semana":
				objects = veiculoRepository.exibirVeiculosSemana();
				break;
			default:
	            throw new IllegalArgumentException("parametro indevido");
		}
		return objects;
	}
}
