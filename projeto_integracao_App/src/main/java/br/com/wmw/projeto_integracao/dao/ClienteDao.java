package br.com.wmw.projeto_integracao.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.ui.ListaClienteWindow;
import br.com.wmw.projeto_integracao.util.DatabaseManager;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.sql.Connection;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Vm;

public class ClienteDao {
	
	public static final String SQL_EXCLUI_TODOS = "DELETE FROM CLIENTE";
	public static final String SQL_INSERE = "INSERT INTO CLIENTE(cod, nome,email,telefone,tipoPessoa,cpfCnpj,status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	public static final String SQL_EXCLUI = "UPDATE CLIENTE SET STATUS = 'EXC' WHERE CPFCNPJ = ? ";
	public static final String SQL_ALTERA = "UPDATE CLIENTE SET nome = ?, email = ?, telefone = ?, tipoPessoa = ?, status = ?  WHERE cpfCnpj = ? ";
	public static final String SQL_LISTAR_CLIENTES_POR_STATUS_EXC = "SELECT cod, NOME, EMAIL, TELEFONE, TIPOPESSOA, CPFCNPJ FROM CLIENTE WHERE STATUS = 'EXC'";
	public static final String SQL_LISTAR_CLIENTES_POR_CPF = "SELECT NOME, TELEFONE, EMAIL FROM CLIENTE WHERE CPFCNPJ = ?";
	public static final String SQL_LISTAR_CLIENTES_POR_STATUS_APP = "SELECT cod, NOME, EMAIL, TELEFONE, TIPOPESSOA, CPFCNPJ FROM CLIENTE WHERE STATUS = 'APP'";
	public static final String SQL_LISTAR_CLIENTES_POR_STATUS_ATT = "SELECT NOME, EMAIL, TELEFONE, TIPOPESSOA, CPFCNPJ FROM CLIENTE WHERE STATUS = 'ATT'";
	public static final String SQL_LISTAR_CLIENTES_POR_STATUS_WEB = "SELECT cod, NOME, EMAIL, TELEFONE, TIPOPESSOA, CPFCNPJ FROM CLIENTE WHERE STATUS = 'WEB'";
	public static final String SQL_LISTAR_CLIENTES = "SELECT cod, nome, email, telefone, tipoPessoa, cpfCnpj, status FROM cliente where status != 'EXC' and status != 'EXC_APP' order by nome asc";
	public static final String SQL_LISTAR_CLIENTES_COM_EXC = "SELECT cod, nome, email, telefone, tipoPessoa, cpfCnpj, status FROM cliente order by nome asc";

	public static ClienteDao instance;

	public static ClienteDao getInstance() {
		if (instance == null) {
			instance = new ClienteDao();
		}
		return instance;
	}


	public void InserirCliente(Cliente cliente) throws SQLException {

		Connection con = DatabaseManager.getConnection();

		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_INSERE);

		try {

			ps.setInt(1, cliente.getCod());
			ps.setString(2, cliente.getNome().toUpperCase());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getTipo_de_Pessoa().toUpperCase());
			ps.setString(6, cliente.getCpf_Cnpj());
			ps.setString(7, cliente.getStatus());

			ps.execute();
			Mensagens_Popup.getInstance().CADASTRO_SUCESSO();
			try {

			} finally {

				ps.close();
			}

		} catch (Exception e) {

			Vm.debug(Mensagens_Vm.CPF_JA_EXISTE);
			Mensagens_Popup.getInstance().CPF_JA_EXISTE();

		} finally {
			con.close();
		}
	}

	public boolean ExcluirCliente(String cpf) throws SQLException {

		Connection con = DatabaseManager.getConnection();
		int excluded = 0;
		try {
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_EXCLUI);

			try {
				ps.setString(1, cpf);

				if (cpf != null) {
					excluded = ps.executeUpdate();
					Mensagens_Popup.getInstance().EXCLUIDO_SUCESSO();
				}
			} finally {
				ps.close();
			}

			ListaClienteWindow lista = new ListaClienteWindow();
			lista.popup();
		} finally {

			con.close();
		}
		return excluded > 0;

	}

	public ArrayList<Cliente> findAllClientes()  {

		Statement st = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();
		try {
			st = DatabaseManager.getConnection().createStatement();
			rs = st.executeQuery(SQL_LISTAR_CLIENTES);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod(rs.getInt(1));
				cliente.setNome(rs.getString(2).toUpperCase());
				cliente.setEmail(rs.getString(3));
				cliente.setTelefone(rs.getString(4));
				cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
				cliente.setCpf_Cnpj(rs.getString(6));
				cliente.setStatus(rs.getString(7));

				clientesList.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return clientesList;
	}
	public ArrayList<Cliente> findAllClientes_Com_Exc() throws SQLException {

		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();

		Statement st = DatabaseManager.getConnection().createStatement();

		try {
			ResultSet rs = st.executeQuery(SQL_LISTAR_CLIENTES_COM_EXC);
			try {
				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCod(rs.getInt(1));
					cliente.setNome(rs.getString(2).toUpperCase());
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
					cliente.setCpf_Cnpj(rs.getString(6));
					cliente.setStatus(rs.getString(7));

					clientesList.add(cliente);

				}
			} finally {
				rs.close();
			}
		} finally {

			st.close();

		}

		return clientesList;

	}

	public boolean AlterarCliente(Cliente cliente) throws SQLException {

		int updated;
		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_ALTERA);

		try {
			ps.setString(1, cliente.getNome().toUpperCase());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getTelefone());
			ps.setString(4, cliente.getTipo_de_Pessoa().toUpperCase());
			ps.setString(5, cliente.getStatus());
			ps.setString(6, cliente.getCpf_Cnpj());

			updated = ps.executeUpdate();

		} finally {

			ps.close();
		}

		if (updated > 0) {

			Mensagens_Popup.getInstance().ALTERADO_SUCESSO();

			ListaClienteWindow telaListaClienteWindow = new ListaClienteWindow();
			telaListaClienteWindow.popup();

		}

		return updated > 0;
	}

	public Cliente findAllClientesByCpfCnpj(String cpfCnpj) throws SQLException {

		Cliente cliente = new Cliente();

		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_LISTAR_CLIENTES_POR_CPF);

		try {
			ps.setString(1, cpfCnpj);
			ResultSet rs = ps.executeQuery();

			try {

				if (rs.next()) {
					cliente.setNome(rs.getString(1).toUpperCase());
					cliente.setTelefone(rs.getString(2));
					cliente.setEmail(rs.getString(3));

				} else {

					Vm.debug(Mensagens_Vm.CPF_CNPJ_INEXISTENTE);
					Mensagens_Popup.getInstance().ERRO_BUSCAR_CLIENTE();
				}

			} finally {

				rs.close();
			}

		} finally {

			ps.close();
		}
		return cliente;
	}

	public void InserirClienteWeb(Cliente cliente) throws SQLException {

		Connection con = DatabaseManager.getConnection();

		PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_INSERE);
		try {

			ps.setInt(1, cliente.getCod());
			ps.setString(2, cliente.getNome().toUpperCase());
			ps.setString(3, cliente.getEmail());
			ps.setString(4, cliente.getTelefone());
			ps.setString(5, cliente.getTipo_de_Pessoa().toUpperCase());
			ps.setString(6, cliente.getCpf_Cnpj());
			ps.setString(7, cliente.getStatus());

			ps.execute();
			try {

			} finally {

				con.close();
			}
		} finally {
			ps.close();
		}

	}

	public void InserirListaDeClientes(List<Cliente> listaDeClientes) {

		listaDeClientes.forEach(cliente -> {

			try {
				InserirClienteWeb(cliente);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public void ExcluirTodosClientes() throws SQLException {

		Connection con = DatabaseManager.getConnection();
		try {
			PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(SQL_EXCLUI_TODOS);
			try {

				ps.execute();
			} finally {
				ps.close();
			}
		} finally {

			con.close();
		}

	}

	public ArrayList<Cliente> findAllClientesByStatusApp() throws SQLException {

		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();

		Statement st = DatabaseManager.getConnection().createStatement();

		try {
			ResultSet rs = st.executeQuery(SQL_LISTAR_CLIENTES_POR_STATUS_APP);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod(rs.getInt(1));
				cliente.setNome(rs.getString(2).toUpperCase());
				cliente.setEmail(rs.getString(3));
				cliente.setTelefone(rs.getString(4));
				cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
				cliente.setCpf_Cnpj(rs.getString(6));
				clientesList.add(cliente);
			}
			try {

			} finally {

				rs.close();
			}
		} finally {

			st.close();
		}

		return clientesList;
	}

	public ArrayList<Cliente> findAllClientesByStatusWeb() throws SQLException {

		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();

		Statement st = DatabaseManager.getConnection().createStatement();
		try {
			ResultSet rs = st.executeQuery(SQL_LISTAR_CLIENTES_POR_STATUS_WEB);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod(rs.getInt(1));
				cliente.setNome(rs.getString(2).toUpperCase());
				cliente.setEmail(rs.getString(3));
				cliente.setTelefone(rs.getString(4));
				cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
				cliente.setCpf_Cnpj(rs.getString(6));
				clientesList.add(cliente);

			}

			try {
			} finally {

				rs.close();
			}
		} finally {

			st.close();
		}

		return clientesList;
	}

	public ArrayList<Cliente> findAllClientesByStatusAtt() throws SQLException {

		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();

		Statement st = DatabaseManager.getConnection().createStatement();
		try {
			ResultSet rs = st.executeQuery(SQL_LISTAR_CLIENTES_POR_STATUS_ATT);
			try {

				while (rs.next()) {
					Cliente cliente = new Cliente();
					cliente.setCod(rs.getInt(1));
					cliente.setNome(rs.getString(2).toUpperCase());
					cliente.setEmail(rs.getString(3));
					cliente.setTelefone(rs.getString(4));
					cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
					cliente.setCpf_Cnpj(rs.getString(6));
					clientesList.add(cliente);
				}
			} finally {

				rs.close();
			}
		} finally {

			st.close();

		}
		return clientesList;
	}

	public ArrayList<Cliente> findAllClientesByStatusExc() throws SQLException {

		ArrayList<Cliente> clientesList = new ArrayList<Cliente>();

		Statement st = DatabaseManager.getConnection().createStatement();

		try {
			ResultSet rs = st.executeQuery(SQL_LISTAR_CLIENTES_POR_STATUS_EXC);

			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setCod(rs.getInt(1));
				cliente.setNome(rs.getString(2).toUpperCase());
				cliente.setEmail(rs.getString(3));
				cliente.setTelefone(rs.getString(4));
				cliente.setTipo_de_Pessoa(rs.getString(5).toUpperCase());
				cliente.setCpf_Cnpj(rs.getString(6));
				clientesList.add(cliente);
			}
			try {
			} finally {

				rs.close();
			}
		} finally {

			st.close();

		}
		return clientesList;
	}
}
