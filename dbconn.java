package BankAtm;

import java.sql.*;


public class dbconn {
	Connection c;
    Statement s;
    public dbconn() {
    	try {
            c = DriverManager.getConnection("jdbc:mysql://localhost/bankingapplication","root", "Romeo@1715");
            s = c.createStatement();
            System.out.println("Connection Established Successfull and the DATABASE NAME IS:"
                    + c.getMetaData().getDatabaseProductName());
    	}
            catch (Exception e) 
        	{
                e.printStackTrace();
            }
           
    	    
    	}
    	
    }
    


        
    	
	
        
