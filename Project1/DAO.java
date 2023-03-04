package study;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {

	public Connection myGetConnection()  {

		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/ajdb1";
			String uname = "root";
			String pwd = "root";

			con = DriverManager.getConnection(url,uname,pwd);
			System.out.println("connection successful...");
		}
		catch(SQLException e)
		{
			System.out.println("The connection could not be made "+e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public boolean addRecord(String username, String name, long contact, String gender, String password) throws ClassNotFoundException, SQLException {
		//get a connection
		Connection con = myGetConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("insert into userdata values(?,?,?,?,?)");
			pstmt.setString(1, name);  //1 is que mark 1
			pstmt.setString(2, gender);  //internally single quote added or not if null
			pstmt.setLong(3, contact);
			pstmt.setString(4, username);
			pstmt.setString(5, password);
			int u = pstmt.executeUpdate();
			if(u==1)
			System.out.println("inserted");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public ResultSet getRows(String query) {
		Connection con = myGetConnection();
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public void showResultSet(ResultSet rs) {
		try {
			while(rs.next()) {
				String username = rs.getString("username");
				String name = rs.getString("name");
				long contact = rs.getLong("contact");
				String password = rs.getString("password");
				String gender = rs.getString("gender");
				System.out.println("row: "+username+", "+name+", "+contact+", "+gender+", "+password+", ");
			}
		}catch(SQLException e) {
			System.out.println("problem showing resultset: "+e);
		}
	}
	
	public void update(String username, long contact, String name, String password) throws ClassNotFoundException, SQLException {
		Connection con = myGetConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("update userdata set contact = ?,name = ?, password = ? where username = ?");
			pstmt.setLong(1, contact);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.setString(4, username);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRecord(String username) throws SQLException {
		Connection con = myGetConnection();

		try {
			PreparedStatement pstmt = con.prepareStatement("delete from userdata where username=?");
			pstmt.setString(1, username);
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePwd(String username, String password) throws ClassNotFoundException, SQLException {
		Connection con = myGetConnection();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("update userdata set password = ? where username = ?");
			pstmt.setString(1, password);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validate(String username, String password) {
		Connection con = myGetConnection();
		
		ResultSet rs;
		try {
			Statement stmt = con.createStatement();
			rs =stmt.executeQuery("Select username, password from userdata");
			while(rs.next()) {
				String str1=rs.getString("username");
				String str2=rs.getString("password");
				if(username.equals(str1) && password.equals(str2)) {
					return true;
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
}
