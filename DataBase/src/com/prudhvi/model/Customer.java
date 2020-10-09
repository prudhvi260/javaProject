/*
  create table Customer(
  id int primary key auto_increment,
  name varchar(256),
  phone varchar(256),
  email varchar(256) unique key,
  temperature float,
  entryDateTime varchar(256),
  exitDateTime varchar(256)
  )
  
  
  insert into Customer values(null,'Prudhvi','9059307850','pru@gmail.com',98.8,'18-08-2020 17:20:00','NA')
  
  update Customer set name='Prudhvi Rao',exitDateTime='18-08-2020 17:50:00' where id=1
  
  delete from Customer where id=1;
 */


package com.prudhvi.model;

public class Customer {
    public int id;
	public String name;
	public String phone;
	public String email;
	public double temperature;
	public String entryDateTime;
	public String exitDateTime;

	public Customer()
	{
		
	}

	public Customer(String name, String phone, String email, double temperature, String entryDateTime,
			String exitDateTime) {
		super();
		this.id=id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.temperature = temperature;
		this.entryDateTime = entryDateTime;
		this.exitDateTime = exitDateTime;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", temperature="
				+ temperature + ", entryDateTime=" + entryDateTime + ", exitDateTime=" + exitDateTime + "]";
	}

	
	
}
