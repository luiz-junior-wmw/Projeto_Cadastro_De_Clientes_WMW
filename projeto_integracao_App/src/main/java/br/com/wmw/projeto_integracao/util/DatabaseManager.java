package br.com.wmw.projeto_integracao.util;

import java.sql.SQLException;

import totalcross.db.sqlite.SQLiteUtil;
import totalcross.sql.Connection;
import totalcross.sql.Statement;
import totalcross.sys.Settings;

public class DatabaseManager {
	
	public static final String SQL_DATABASE = "create table if not exists cliente (cod INTEGER , "
            + "nome varchar not null, email varchar, telefone varchar not null, tipoPessoa varchar not null, "
            + "cpfCnpj varchar primary key not null unique, status varchar)";

	public static SQLiteUtil sqliteUtil;

	static {

		try {

			sqliteUtil = new SQLiteUtil(Settings.appPath, "test.db");

			Statement st = sqliteUtil.con().createStatement();
			st.execute(SQL_DATABASE);
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return sqliteUtil.con();
	}
}
