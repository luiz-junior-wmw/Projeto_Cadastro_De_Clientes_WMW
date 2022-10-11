package br.com.wmw.projeto_integracao.Repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.wmw.projeto_integracao.model.Cliente;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)

public class ClienteRepositoryTest {

	static final String SOBRENOME = "DEODATO";
	static final String STATUS_ATIVO = "WEB";
	static final String STATUS_INATIVO = "EXC_APP";

	@Autowired
	private ClienteRepository repository;

	@Test
	public void deveriaBuscarClientePorNome() {
		
		
		List<String> nome =  new ArrayList<String>();
		nome.add("IVONE MIGUEL DEODATO");
		nome.add("ANA BEATRIZ DEODATO ELIAS");
		nome.add("BRENDHA DEODATO ELIAS");
		
		List <String> clienteList = new ArrayList<String>();
		List<Cliente> buscarPorNome = repository.buscarPorNome(SOBRENOME);
		
		for (Cliente cliente : buscarPorNome) {
			clienteList.add(cliente.getNome());
		}
		Assert.assertEquals(nome, clienteList);
		Assert.assertNotNull(nome);
	}
	
	@Test
	public void deveriaBuscarPorStatusWeb() {
		
		
	    List<String> nome = new ArrayList<String>();	
	    nome.add("LUIZ ELIZIO ELIAS JUNIOR");
	    nome.add("IVONE MIGUEL DEODATO");
	    nome.add("ANA BEATRIZ DEODATO ELIAS");
	    nome.add("BRENDHA DEODATO ELIAS");
	    nome.add("LEDA MARIA COSTA ELIAS");
	    nome.add("LUCILIA MARIA COSTA ELIAS");
	    nome.add("LILIANA MARIA ELIAS CONSTANTINO");
	    nome.add("LUCIANA APARECIDA COSTA ELIAS PREVE");
	    nome.add("LUIZ ELIZIO ELIAS NETO");
		
        List <String> clienteList = new ArrayList<String>();
		List<Cliente> buscarPorWEB = repository.buscarPorStatusClienteAtivo_Test(STATUS_ATIVO);
		
		for (Cliente cliente : buscarPorWEB) {
			clienteList.add(cliente.getNome());
		}
		Assert.assertEquals(nome, clienteList);
		Assert.assertNotNull(nome);
	}
	
	@Test
	public void deveriaBuscarPorStatusEXC_APP() {
		
		
		  List<String> nome = new ArrayList<String>();	
		    nome.add("TESTE EXCLUÍDO");
			
	        List <String> clienteList = new ArrayList<String>();
			List<Cliente> buscarPorWEB = repository.buscarPorStatusClienteInativo_Test(STATUS_INATIVO);
			
			for (Cliente cliente : buscarPorWEB) {
				clienteList.add(cliente.getNome());
			}
			Assert.assertEquals(nome, clienteList);
			Assert.assertNotNull(nome);
		}
}