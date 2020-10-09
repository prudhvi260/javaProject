package com.prudhvi.dao;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.prudhvi.model.Customer;

public class DB {
	
	Connection connection;
	Statement statement; //to execute sql command in mysql db;
	PreparedStatement ps;  //to execute sql command in mysql db;
	CallableStatement callsmt ; //to execute stored procedures in MYDQL DB;
  public DB()
  {
	  try {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  System.out.println("1. Driver Loaded");
	  }catch(Exception e)
	  {
		  System.out.println("Driver Not Found");
		  System.out.println(e);
	  }
  }
  //create connection
  public void creatconnection()
  {
	  try {
		  String url="jdbc:mysql://localhost/customer";
		  String user="root";
		  String password="";
		  
		 connection= DriverManager.getConnection(url,user,password);
		 System.out.println("2. Connection Created");
	  }catch(Exception e)
	  {
		  
	  }
  }
  public String AddCustomer(Customer customer)
  {
	 /* try {
		  String sql="insert into Customer values(null,'"+customer.name+"','"+customer.phone+"','"+customer.email+"',"+customer.temperature+",'"+customer.entryDateTime+"','"+customer.exitDateTime+"')";
		  //statement=(Statement) connection.createStatement();
		  statement=connection.createStatement();
		 int i= statement.executeUpdate(sql);//will perform sql crude operations
		 if(i>0)
		 {
			 return customer.name;
		 }else {
			 return customer.name+"Not Added in DataBase";
		 }
		  
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  */
	  try {
		  String sql="insert into Customer values(null,?,?,?,?,?,?)";
		  ps=connection.prepareStatement(sql);
		  ps.setString(1, customer.name);
		  ps.setString(2, customer.phone);
		  ps.setString(3, customer.email);
		  ps.setFloat(4, (float) customer.temperature);
		  ps.setString(5, customer.entryDateTime);
		  ps.setString(6, customer.exitDateTime);
		 int i= ps.executeUpdate();//will perform sql crude operations
		 if(i>0)
		 {
			 return customer.name;
		 }else {
			 return customer.name+"Not Added in DataBase";
		 }
		  
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  
	return "";	  
  }
  public void executeBatchProcess()
  {
	  try {
		String q= "update Customer set name='Fionna Flynn', email='fionna.flynn@example.com' where id = 2";
		String l="delete from customer where cid=8";
		statement=connection.createStatement();
		connection.setAutoCommit(false);
		statement.addBatch(q);
		statement.addBatch(l);
		statement.executeBatch();
		connection.commit();
		System.out.println("Batch is Executed");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		try {
			System.out.println("Batch Process Failed some Error Occurred");
			connection.rollback();
		}catch(Exception e1)
		{
			e1.printStackTrace();
		}
		
	}
  }
  public void executeProceduer(Customer customer)
  {
	  try {
		  String sql="{call addCustomer(?,?,?,?,?,?)}";
		  callsmt=connection.prepareCall(sql);
		  
		  callsmt.setString(1, customer.name);
		  callsmt.setString(2, customer.phone);
		  callsmt.setString(3, customer.email);
		  callsmt.setFloat(4, (float) customer.temperature);
		  callsmt.setString(5, customer.entryDateTime);
		  callsmt.setString(6, customer.exitDateTime);
		  callsmt.execute();
		  System.out.println("Procedure Executed"); 
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
  public ArrayList<Customer> showAllCustomers()
  {
	  try {
		  String s="select * from customer";
		  ps=connection.prepareStatement(s);
		  ResultSet result=ps.executeQuery();
		  ArrayList<Customer>c=new ArrayList<>();
		  while(result.next())
		  {
			  Customer customer=new Customer();
			  customer.id=result.getInt(1);
			  customer.name = result.getString(2);
				customer.phone = result.getString(3);
				customer.email = result.getString(4);
				customer.temperature = result.getFloat(5);
				customer.entryDateTime = result.getString(6);
				customer.exitDateTime = result.getString(7);
				c.add(customer);
		  }
		  return c;
	  }catch(Exception e)
	  {
		  
	  }
	  return null;
  }
  public void showbyphone(String phone)
  {
	  try {
		  String q="select * from customer where phone=?";
		  ps=connection.prepareStatement(q);
		  ps.setString(1, phone);
		  ResultSet result=ps.executeQuery();
		  while(result.next())
		  {
			  Customer customer=new Customer();
			  customer.id=result.getInt(1);
			  customer.name = result.getString(2);
			  customer.phone = result.getString(3);
				customer.email = result.getString(4);
				customer.temperature = result.getFloat(5);
				customer.entryDateTime = result.getString(6);
				customer.exitDateTime = result.getString(7);
				System.out.println(customer);
		  }
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
  public void closeConnection()
  {
	  try {
		  connection.close();
	  }catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
  
}
