package br.com.infox.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão com banco de dados
 * @author Natan Oliveira da Silva
 * @version 1.1
 */

public class ModuloConexao {

	/**
	 * Método responsável pela conexão com o banco	
	 * 
	 * @return conexao 
	 */
	public static Connection conector() {

		Connection con = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/dbinfox?useTimezone=true&serverTimezone=UTC"; 
		String user = "dba";
		String password = "Infox007";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			return null;
		}
	}
	
}
