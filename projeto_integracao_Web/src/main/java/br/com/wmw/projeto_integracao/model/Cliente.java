package br.com.wmw.projeto_integracao.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod;
	private String nome;
	private String email;
	private String telefone;
	private String tipo_de_Pessoa;
	@Column(unique = true)
	private String cpf_Cnpj;
	private String status;
	
	
	public Cliente() {
		
	}
	
	public Cliente(Long cod, String nome, String email, String telefone, String tipo_de_Pessoa, String cpf_Cnpj, String status ) {
		this.cod = cod;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.tipo_de_Pessoa = tipo_de_Pessoa;
	    this.cpf_Cnpj = cpf_Cnpj;
	    this.status = status;
		
	}


	public Long getCod() {
		return cod;
	}


	public void setCod(Long cod) {
		this.cod = cod;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public String getTipo_de_Pessoa() {
		return tipo_de_Pessoa;
	}


	public void setTipo_de_Pessoa(String tipo_de_Pessoa) {
		this.tipo_de_Pessoa = tipo_de_Pessoa;
	}


	public String getCpf_Cnpj() {
		return cpf_Cnpj;
	}


	public void setCpf_Cnpj(String cpf_Cnpj) {
		this.cpf_Cnpj = cpf_Cnpj;
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	
}
