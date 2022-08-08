package br.com.infox.dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

	public static Connection conector() {

		Connection con = null;
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/dbinfox?useTimezone=true&serverTimezone=UTC";
		String user = "root";
		String password = "natan07149";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			return null;
		}
	}
}
