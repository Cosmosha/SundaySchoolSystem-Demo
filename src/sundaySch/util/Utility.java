package sundaySch.util;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Utility {
    
     private static Utility util = null;
    
    public static boolean connectionStatus = false;
    private static Connection con = null;
    private static Statement stmt = null;  
    
         String username = "admin";
         String password = "12345";
    
     private Utility(){
         getConnection(new sundaySch.util.IPAddressing().getAddress());
        seupTeachTable();
       seupStudTable();
       loginTable();
       eventTable();
    }
     
     
      public static Utility getInstance() {
      if(util==null)
      {
          util = new Utility();
      }
       return util;  
    }

       
    public static void getConnection(String sIP){
       
        try{
            String url = "jdbc:mysql://"+sIP+"/sundayschool";
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, "root", "");
            stmt = con.createStatement();
            connectionStatus = true;
            System.out.println("Connection successful");
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(
                    null,"Database Server not found!");
        }
    }
    
    public static Statement getStatement(){
        return stmt;
    }
    
    
    final void seupTeachTable() {
        try {
            String TABLE_NAME = "Teacher";
            stmt = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase() ,null);
            if(tables.next()){
                System.out.println(""+TABLE_NAME+" already exists. Ready to go..");
            }else{
                String mytable = "Create Table "+TABLE_NAME+"("
                        + "teacher_id varchar(10) primary key,\n"
                        + "name varchar(200),\n"
                        + "gender varchar(10),\n"
                        + "DOB varchar(10),\n"
                        + "maritalStatus varchar (50),\n"
                        + "residence varchar(200),\n"
                        + "occupation varchar(200),\n"
                        + "class varchar(20),\n"
                        + "status varchar(100),\n"
                        + "phone varchar(10),\n"
                        + "email varchar (200)"
                        + ")";
                stmt.execute(mytable);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
       final void seupStudTable() {
        try {
            String TABLE_NAME = "Student";
            stmt = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase() ,null);
            if(tables.next()){
                System.out.println(""+TABLE_NAME+" already exists. Ready to go..");
            }else{
                String mytable = "Create Table "+TABLE_NAME+"("
                        + "student_id varchar(10) primary key,\n"
                        + "name varchar(200),\n"
                        + "gender varchar(10),\n"
                        + "dob varchar(10),\n"
                        + "mother_Name varchar (200),\n"
                        + "mother_Number varchar(10),\n"
                        + "father_Name varchar (200),\n"
                        + "father_Number varchar(10),\n"
                        + "residence varchar(200),\n"
                        + "edu_level varchar(20),\n"
                        + "school varchar(100),\n"
                        + "class varchar(100),\n"
                        + "phone varchar (10)"
                        + ")";
                stmt.execute(mytable);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       
   
     
     public ResultSet execQuery(String query){
      ResultSet result;
      
        try {
            stmt= con.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error: " +ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
           System.out.println("Exception at execQuery: Utility "+ex.getLocalizedMessage());
           return null;
        }finally{           
        }
          return result;  
   }
     
     
     public boolean execAction(String qu){
      try{
          stmt =con.createStatement();
          stmt.execute(qu);
          return true;
          
      } catch (SQLException ex) {
          ex = ex.getNextException();
          JOptionPane.showMessageDialog(null, "Error: " +ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
          System.out.println("Exception at execQuery: Utility "+ex.getLocalizedMessage());
          return false; 
      }finally{
      }
      
  }
     
     
     
       final void loginTable() {
        
        try {
            String TABLE_NAME = "Login";
            stmt = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase() ,null);
            if(tables.next()){
                System.out.println(""+TABLE_NAME+" already exists. Ready to go..");
            }else{
                String mytable = "Create Table "+TABLE_NAME+"("
                        + "username varchar(200),\n"
                        + "password varchar (100)"
                        + ")";
                stmt.execute(mytable);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
       
       
      final void eventTable() {
        
        try {
            String TABLE_NAME = "Event";
            stmt = con.createStatement();
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase() ,null);
            if(tables.next()){
                System.out.println(""+TABLE_NAME+" already exists. Ready to go..");
            }else{
                String mytable = "Create Table "+TABLE_NAME+"("
                        + "eventID varchar(10) primary key,\n"
                        + "eventName varchar(100),\n"
                        + "venue varchar(100),\n"
                        + "eventDate varchar(100),\n"
                        + "eventTime varchar(100),\n"
                        + "eventDescription varchar (200)"
                        + ")";
                stmt.execute(mytable);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
    
}
