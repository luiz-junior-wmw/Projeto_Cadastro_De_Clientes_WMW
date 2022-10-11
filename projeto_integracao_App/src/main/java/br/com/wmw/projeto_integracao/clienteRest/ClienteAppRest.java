package br.com.wmw.projeto_integracao.clienteRest;

import java.sql.SQLException;
import java.util.List;

import br.com.wmw.projeto_integracao.dao.ClienteDao;
import br.com.wmw.projeto_integracao.model.Cliente;
import br.com.wmw.projeto_integracao.util.Mensagens_Vm;
import totalcross.io.ByteArrayStream;
import totalcross.io.IOException;
import totalcross.json.JSONObject;
import totalcross.net.HttpStream;
import totalcross.net.URI;
import totalcross.net.UnknownHostException;
import totalcross.sys.Vm;

public class ClienteAppRest {

	public static ClienteAppRest instance;

	public static ClienteAppRest getInstance() {
		if (instance == null) {
			instance = new ClienteAppRest();
		}
		return instance;
	}

	public void EnviarDados() throws UnknownHostException, IOException, SQLException {

		List<Cliente> clienteList = ClienteDao.getInstance().findAllClientesByStatusApp();

		try {

			for (Cliente cliente : clienteList) {

				HttpStream.Options options = new HttpStream.Options();
				options.httpType = HttpStream.POST;
				options.setContentType("application/json");

				JSONObject jsonObject = new JSONObject(cliente);
				options.data = jsonObject.toString();

				HttpStream httpStream = new HttpStream(new URI("http://localhost:8081/projeto_integracao/salvar"),
						options);

				try (ByteArrayStream byteArrayStream = new ByteArrayStream(4096)) {
					byteArrayStream.readFully(httpStream, 10, 2048);

					if (httpStream.responseCode == 201) {
							Vm.debug(Mensagens_Vm.UPLOAD_SUCESSO);
					}
				}
			}
		} catch (Exception e) {
			Vm.debug(Mensagens_Vm.FALHA_CONEXAO_SERVIDOR_ENVIAR);
		}

	}

	public void AtualizarDados(Cliente cliente) throws UnknownHostException, IOException {

		HttpStream.Options options = new HttpStream.Options();
		options.httpType = HttpStream.PUT;
		options.setContentType("application/json");

		JSONObject jsonObject = new JSONObject(cliente);
		options.data = jsonObject.toString();

		HttpStream httpStream = new HttpStream(new URI("http://localhost:8081/projeto_integracao/atualizar"), options);

		try (ByteArrayStream byteArrayStream = new ByteArrayStream(4096)) {
			byteArrayStream.readFully(httpStream, 10, 2048);

		}
	}
}
