package br.com.wmw.projeto_integracao.model;

public class Cliente {
	
	private int cod;
	private String nome;
	private String email;
	private String telefone;
	private String tipo_de_Pessoa;
	private String cpf_Cnpj;
	private String status ;
	
	public Cliente() {

	}

	public Cliente(String nome, String email, String telefone, String tipoPessoa, String cpfCnpj, String status) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.tipo_de_Pessoa = tipoPessoa;
		this.cpf_Cnpj = cpfCnpj;
		this.status = status;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
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
