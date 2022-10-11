package br.com.wmw.projeto_integracao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.wmw.projeto_integracao.Repository.ClienteRepository;
import br.com.wmw.projeto_integracao.model.Cliente;

@RestController
public class ClienteWebController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping(value = "listaTodos")
	public ResponseEntity<List<Cliente>> listaClientes() {

		List<Cliente> clientes = clienteRepository.findAll();

		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);

	}

	@PostMapping(value = "salvar")
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		
		cliente.setStatus("WEB");
		String cpf = cliente.getCpf_Cnpj();
		boolean validouCpf = ValidarCpf.validaCpf(cpf);
		boolean validouCnpj = ValidarCnpj.validaCnpj(cpf);
		
		if (validouCpf || validouCnpj) {
			
			cliente = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
		
		} else {
		
			return new ResponseEntity<Cliente>(cliente, HttpStatus.BAD_REQUEST);
		}

	}
	
	@PostMapping(value = "atualizarParaEXC_APP")
	public ResponseEntity<Cliente> atualizarParaEXC_APP(@RequestBody Cliente cliente) {
		
        cliente.setStatus("EXC_APP"); 
		cliente = clienteRepository.save(cliente);
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);

	}

	@PutMapping(value = "atualizar")
	public ResponseEntity<?> atualizar(@RequestBody Cliente cliente) {

		if (cliente.getCod() == null) {
			return new ResponseEntity<String>("Codigo não informado!", HttpStatus.OK);
		}

		cliente = clienteRepository.saveAndFlush(cliente);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        
	}
	
	@PutMapping(value = "atualizarDto")
	public ResponseEntity<?> atualizar(@RequestParam Long cod, @RequestParam String telefone, @RequestParam String email) {

		List<Cliente> lista = clienteRepository.findAllByCod(cod);
		
		if(lista.size() >0) {
			Cliente cliente = lista.get(0);
			cliente.setEmail(email);
			cliente.setTelefone(telefone);

			cliente = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		} 	
		return null;
	}
	
	@DeleteMapping(value = "deleteTodos")
	public ResponseEntity<String> delete() {
		
		clienteRepository.deleteAll();

		return new ResponseEntity<String>("Cliente deletado com sucesso!", HttpStatus.OK);
	}

	@GetMapping(value = "buscarClienteCod")
	public ResponseEntity<Cliente> buscarclientecod(@RequestParam(name = "Cod") Long Cod) {

		Cliente cliente = clienteRepository.findById(Cod).get();

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@GetMapping(value = "buscarTodosClientes")
	public ResponseEntity<List<Cliente>> buscarpornome(@RequestParam(name = "nome") String nome) {

		List<Cliente> cliente = clienteRepository.buscarPorNome(nome.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorStatusClienteAtivo")
	public ResponseEntity<List<Cliente>> buscarporStatusClienteAtivo(@RequestParam(name = "nome") String status) {

		List<Cliente> cliente = clienteRepository.buscarPorStatusClienteAtivo(status.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorStatusClienteAtivoTest")
	public ResponseEntity<List<Cliente>> buscarporStatusClienteAtivo_Test(@RequestParam(name = "nome") String status) {

		List<Cliente> cliente = clienteRepository.buscarPorStatusClienteAtivo(status.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorStatusClienteInativo")
	public ResponseEntity<List<Cliente>> buscarporStatusClienteInativo(@RequestParam(name = "nome") String status) {

		List<Cliente> cliente = clienteRepository.buscarPorStatusClienteInativo(status.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorStatusClienteInativoTest")
	public ResponseEntity<List<Cliente>> buscarporStatusClienteInativo_Test(@RequestParam(name = "nome") String status) {

		List<Cliente> cliente = clienteRepository.buscarPorStatusClienteInativo(status.trim().toUpperCase());

		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "delete")
	public ResponseEntity<String> delete(@RequestParam Long CodUser) {
		
		clienteRepository.deleteById(CodUser);
         
		return new ResponseEntity<String>("Cliente deletado com sucesso!", HttpStatus.OK);
	}

}
