package br.com.wmw.projeto_integracao.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClienteWebControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void deveriaSalvarOCliente() throws Exception {
		
		URI uri = new URI("/salvar");
		String json = "{\"nome\":\"LUIZ\",\"email\":\"luiz.elias@wmw.com.br\","
				+ "\"telefone\":\"+55 (48) 9 98371596\",\"tipo_de_Pessoa\":\"F\",\"cpf_Cnpj\":\"00338171975\",\"status\":\"WEB\"}";
		mockMvc.perform(MockMvcRequestBuilders
			    .post(uri).content(json)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status()
				.is(201));
	}
	
	@Test
	public void deveriaAtualizarStatusParaExc_APP() throws Exception {
		
		URI uri = new URI("/atualizar");
		String json = "{\"cod\":1,\"nome\":\"LUIZ ELIZIO ELIAS JUNIOR\",\"email\":\"luiz.elias@wmw.com.br\","
				        + "\"telefone\":\"+55 (48) 9 9837-0003\",\"tipo_de_Pessoa\":\"F\",\"cpf_Cnpj\":\"003.381.719-75\",\"status\":\"EXC_APP\"}";
		mockMvc.perform(MockMvcRequestBuilders
			    .put(uri).content(json)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status()
				.is(200));
	}
	
	@Test
	public void deveriaDeletarTodosClientes() throws Exception {
		
		URI uri = new URI("/deleteTodos");
		mockMvc.perform(MockMvcRequestBuilders
			    .delete(uri)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status()
				.is(200));
	}
	
	@Test
	public void deveriaBuscarTodosOsClientes() throws Exception {
	
		URI uri = new URI("/listaTodos");
		mockMvc.perform(MockMvcRequestBuilders
			    .get(uri)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(MockMvcResultMatchers.status()
				.is(200));
	}

}
