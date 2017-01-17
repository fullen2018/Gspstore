package initdata.excel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCForMySql
{
	private static Connection conn;
	/*private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://118.123.253.143:55304/miraclefireworks";
	private static final String USER = "admin";
	private static final String PASSWORD = "admin";*/
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/gspstore";
	private static final String USER = "root";
	private static final String PASSWORD = "123321";
	
	private  JDBCForMySql(){
		try
		{
			// ������
			Class.forName(JDBC_DRIVER);

			// l����ݿ�
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	// ����Connection
	public static Connection getConnection()
	{
		 if (conn!=null){
			 return conn;
		 }else{
			new JDBCForMySql();
		 }
		return conn;
	}

}
