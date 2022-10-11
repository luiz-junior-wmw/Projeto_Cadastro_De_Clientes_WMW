package br.com.wmw.projeto_integracao.clienteRest;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.util.Mensagens_Popup;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONException;
import totalcross.json.JSONFactory;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.net.UnknownHostException;
import totalcross.sys.Vm;

public class ClienteWebRest {

	public static ClienteWebRest instance;

	public static ClienteWebRest getInstance() {
		if (instance == null) {
			instance = new ClienteWebRest();
		}
		return instance;
	}

	public void ReceberDadosDaWeb() {

		HttpStream.Options options = new HttpStream.Options();
		options.httpType = HttpStream.GET;
		options.setContentType("application/json");

		HttpStream httpStream;
		try {
			httpStream = new HttpStream(new URI("http://localhost:8081/projeto_integracao/listaTodos"), options);
			ByteArrayStream byteArrayStream = new ByteArrayStream(4096);
			byteArrayStream.readFully(httpStream, 10, 2048);

			String data = new String(byteArrayStream.getBuffer(), 0, byteArrayStream.available());

			if (httpStream.responseCode == 200) {

				Vm.debug(Mensagens_Vm.DOWNLOAD_SUCESSO);
				Mensagens_Popup.getInstance().SINCRONIZACAO_SUCESSO_DOWNLOAD();

				try {
					List<Cliente> listaDeClientes = (JSONFactory.asList(data, Cliente.class));

					try {
						ClienteDao.getInstance().ExcluirTodosClientes();
					} catch (SQLException e) {

						e.printStackTrace();
					}

					ClienteDao.getInstance().InserirListaDeClientes(listaDeClientes);

				} catch (ArrayIndexOutOfBoundsException | InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException | JSONException | NoSuchMethodException
						| SecurityException e) {

					e.printStackTrace();
				}
			}

			byteArrayStream.close();
		} catch (IOException e) {
			Vm.debug(Mensagens_Vm.FALHA_CONEXAO_SERVIDOR_RECEBER);
			Mensagens_Popup.getInstance().SINCRONIZACAO_FALHA_RECEBER_DADOS();
		}
	}

	public void EnviarDadosParaWeb() throws SQLException {

		List<Cliente> listaDeClientes = null;
		try {
			listaDeClientes = ClienteDao.getInstance().findAllClientes_Com_Exc();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean existeClienteAppOuAtt = false;

		for (Cliente cliente : listaDeClientes) {
			if (cliente.getStatus().equals("APP")) {
				try {
					existeClienteAppOuAtt = true;
					ClienteAppRest.getInstance().EnviarDados();
					cliente.setStatus("WEB");
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (cliente.getStatus().equals("ATT")) {
				try {
					existeClienteAppOuAtt = true;
					cliente.setStatus("WEB");
					ClienteAppRest.getInstance().AtualizarDados(cliente);
				} catch (IOException e1) {
					Vm.debug(Mensagens_Vm.FALHA_CONEXAO_SERVIDOR_ENVIAR);
					Mensagens_Popup.getInstance().SINCRONIZACAO_FALHA_ENVIAR_DADOS();
					return;
				}
			}

			if (cliente.getStatus().equals("EXC")) {
				try {
					existeClienteAppOuAtt = true;
					cliente.setStatus("EXC_APP");
					ClienteAppRest.getInstance().AtualizarDados(cliente);
				} catch (IOException e1) {
					Vm.debug(Mensagens_Vm.FALHA_CONEXAO_SERVIDOR_ENVIAR);
					Mensagens_Popup.getInstance().SINCRONIZACAO_FALHA_ENVIAR_DADOS();
					return;
				}
			}
		}

		if (!existeClienteAppOuAtt) {

			Vm.debug(Mensagens_Vm.SEM_NOVOS_DADOS_ENVIAR);
			Mensagens_Popup.getInstance().SEM_NOVOS_DADOS_PARA_ENVIAR();

		} else if (existeClienteAppOuAtt) {
			Mensagens_Popup.getInstance().SINCRONIZACAO_SUCESSO_UPLOAD();
			ReceberDadosDaWeb();
		}

	}
}
