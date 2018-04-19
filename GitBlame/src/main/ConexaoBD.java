package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Início da classe de conexão//

public class ConexaoBD {
	public static String status = "Não conectou...";

	public ConexaoBD() {
	}

	public static java.sql.Connection getConnection() {
		Connection conn = null; // pro compilador ficar feliz
		try {
			// Carrega o driver JDBC
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);
			// Configuração da conexão com um banco de dados//
			// troque por seu ip, senha, user, etc
			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "scam"; // nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String user = "root"; // nome de um usuário de seu BD
			String key = ""; // sua senha de acesso
			conn = DriverManager.getConnection(url, user, key);

			// Testa sua conexão//
			if (conn != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->Não foi possivel realizar conexão");
			}
			return conn;

		} catch (ClassNotFoundException e) { // Driver não encontrado
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			// Não conseguindo se conectar ao banco
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}

	// Método que retorna o status da sua conexão//
	public static String statusConection() {
		return status;
	}

	// Método que fecha sua conexão//
	public static boolean closeConnection() {
		try {
			ConexaoBD.getConnection().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// Método que reinicia sua conexão//
	public static java.sql.Connection restartConnection() {
		closeConnection();
		return ConexaoBD.getConnection();
	}
}