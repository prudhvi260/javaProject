package com.prudhvi.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.prudhvi.dao.DB;
import com.prudhvi.model.Customer;

public class CMSApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
         DB db=new DB();
         db.creatconnection();
		while(true) {

			System.out.println("-----------------------------------------");
			System.out.println("Welcome to CMS App");
			System.out.println("1. Add Customer");
			System.out.println("2. Show All Customers");
			System.out.println("3. Mark Exit for Customer");
			System.out.println("4. Show Customer by Phone");
			System.out.println("5. See Customers with time spent > 30 mins");
			System.out.println("6. Execute Batch Process");
			System.out.println("7. Execute Procedures");
			System.out.println("0. Exit the App");
			System.out.println("-----------------------------------------");

			System.out.println();

			System.out.println("Enter Your Choice: ");
			int choice = scanner.nextInt();

			if(choice == 0) {
				System.out.println("Thank You For Using CMS APP :)");
				break;
			}
			scanner.nextLine();
			switch(choice)
			{
			case 1:
				Customer cRef = new Customer();

				System.out.println("Enter Customer Name: ");
				cRef.name=scanner.nextLine();

				System.out.println("Enter Customer Phone: ");
				cRef.phone=scanner.nextLine();

				System.out.println("Enter Customer Email: ");
				cRef.email=scanner.nextLine();

				System.out.println("Enter Customer Temperature: ");
				cRef.temperature=scanner.nextDouble();

				Date date = new Date(); // Date Object which is the time-stamp of our system
				cRef.entryDateTime=date.toString();
				cRef.exitDateTime="NA";

				//System.out.println("customer is:");
				//System.out.println(cRef); // whenever we print reference variable toString method from Object class is executed automatically
				//System.out.println(cRef.toString()); // we have no toString in Customer class and we are executing it ? as it is thr in the Parent Class Object

				scanner.nextLine();
				System.out.println("Would You Like to Add Customer"+cRef.name+" ?(yes/no)");
				String save = scanner.nextLine();
				if(save.equals("yes")) {
					
                    String m=db.AddCustomer(cRef);
                    System.out.println(m);
					//System.out.println("Customer Saved in File");

				}else {
					System.out.println("Thank You For Using CMS App :)");
				}

				break;
				
			case 2:
				  ArrayList<Customer>o=db.showAllCustomers();
				  System.out.println(o);
				  db.closeConnection();
				break;
			case 3:
				
				break;
			case 4:
				    String s=scanner.nextLine();
				    db.showbyphone(s);
				    db.closeConnection();
				    break;
			case 5:
				break;
			case 6:
				   db.executeBatchProcess();
				   db.closeConnection();
				break;
			case 7:
				Customer cRef1 = new Customer();

				System.out.println("Enter Customer Name: ");
				cRef1.name=scanner.nextLine();

				System.out.println("Enter Customer Phone: ");
				cRef1.phone=scanner.nextLine();

				System.out.println("Enter Customer Email: ");
				cRef1.email=scanner.nextLine();

				System.out.println("Enter Customer Temperature: ");
				cRef1.temperature=scanner.nextDouble();

				Date date1 = new Date(); // Date Object which is the time-stamp of our system
				cRef1.entryDateTime=date1.toString();
				cRef1.exitDateTime="NA";

				//System.out.println("customer is:");
				//System.out.println(cRef); // whenever we print reference variable toString method from Object class is executed automatically
				//System.out.println(cRef.toString()); // we have no toString in Customer class and we are executing it ? as it is thr in the Parent Class Object

				scanner.nextLine();
				System.out.println("Would You Like to Add Customer"+cRef1.name+" ?(yes/no)");
				String save1 = scanner.nextLine();
				if(save1.equals("yes")) {
					
                    db.executeProceduer(cRef1);
                    
					//System.out.println("Customer Saved in File");

				}else {
					System.out.println("Thank You For Using CMS App :)");
				}

				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

			scanner.nextLine();
			
		}

	}

}
