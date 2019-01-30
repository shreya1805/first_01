package com.lti.training.maven.jdbc.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.lti.training.maven.model.Product;
import com.lti.training.maven.jdbc.DataAccessException;

public class ProductDao {
	public List<Product> fetchProducts(int from,int to) throws DataAccessException{
		Connection conn = null;
		PreparedStatement pstmt = null; //Precompiled SQL Comments
		ResultSet rs = null;
	
		
		try {
			
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("production-dev-db.properties");
			Properties dbProperties = new Properties(); //Loading the properties using Properties Class
			
			dbProperties.load(is);
			
			String driverClassName = dbProperties.getProperty("driverClassName");
			String url = dbProperties.getProperty("url");
			String username = dbProperties.getProperty("username");
			String password = dbProperties.getProperty("password");
			
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url,username,password);

			//Selecting 3 records specifically within a range
			String sql = "Select * from (Select p.*, rownum r from Product p) where r between ? and ?;"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, from);
			pstmt.setInt(2, to);
			rs = pstmt.executeQuery();
			
			List<Product> products= new ArrayList<Product>();
			while(rs.next()) {
				Product prod = new Product();
				prod.setId(rs.getInt("id"));
				prod.setName(rs.getString("name"));
				prod.setPrice(rs.getDouble("price"));
				prod.setQuantity(rs.getInt("quantity"));
			}
			return products;
		
			
		}
		catch(ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			//if it running on a remote server and its executed and errors occur .. we wont be able to understand the error
			return null;
		}

		finally {
			try {
				pstmt.close();
			}catch(Exception e){
				
			}
			try {
				conn.close();
			}
			catch(Exception e) {
				
			}
			
		}
	}

}