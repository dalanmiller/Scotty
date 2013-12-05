package com.cmu.scotty.persistence;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.cmu.scotty.exception.ArrayListDoesNotMatch;
import com.cmu.scotty.model.Student;


public class StudentDao {
	
	private static String dbURL = "jdbc:derby:scottyDB;create=true";
    private static String tableName = "students";
    private static String columnName = "andrewId";
    // jdbc Connection
    private static Connection conn = null;
    private static Statement stmt = null;

    
    public Connection createConnection()
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (Exception except)
        {
            except.printStackTrace();
        }
        
        return conn;
    }
    
    public boolean checkTable()
    {
    	ResultSet results = null;
    	
    	try
        {   
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
            
        	DatabaseMetaData databaseMetaData = conn.getMetaData();
        	
        	String[] stuArr = new String[1];
        	stuArr[0] = "TABLE";
        	
        	results = databaseMetaData.getTables(null, null, null, stuArr); 
        	
        	if(!results.next())
        	{
        		return false;
        	}
        	
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            return false;
        }  	 	
		
    	return true;
    }
    
    public void dropTable()
    {
        try
        {   
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
            stmt = conn.createStatement();
            String query = " drop table " + tableName;
 		    System.out.println(query);
 		    stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    
    public void createTable()
    {
        try
        {   
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	conn = createConnection();
            stmt = conn.createStatement();
            String query = "create table " + tableName
 		           + "( ANDREWID varchar(40), "
 		           + "  FIRSTNAME varchar(40), "
 		           + "  LASTNAME varchar(40),  "
 		           + "  PROGRAMTRACK varchar(40), "
 		           + "  FULLTIME varchar(40), "
 		           + "  COUNTRY varchar(40), "
 		           + "  SEMESTER varchar(40), "
 		           + "  PATHNAME varchar(500), PRIMARY KEY (andrewId))";
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    
    public void insertStudents(ArrayList<Student> studentArr)
    {
    	Iterator iterator = studentArr.iterator();
    	while(iterator.hasNext())
    	{
    		insertStudent((Student)iterator.next());
    	}
    }
    
    public void insertStudent(Student student)
    {
        try
        {    
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
            stmt = conn.createStatement();
            String query = "insert into " + tableName + " values (" 
       		     + "'" + student.getAndrewID() + "'," 
       		     + "'" + student.getFirstName().trim().toUpperCase() + "',"
       		     + "'" + student.getLastName().trim().toUpperCase() + "',"
       		     + "'" + student.getProgramTrack().trim().toUpperCase() + "',"
       		     + "'" + student.getFullTime().trim().toUpperCase() + "',"
       		     + "'" + student.getCountry().trim().toUpperCase() + "',"
       		     + "'" + student.getSemester().trim().toUpperCase() + "',"
       		     + "'" + student.getPhotoPath().trim().toUpperCase() + "')";
            
            System.out.println(query);
            
            stmt.execute(query);            		     
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public ArrayList<Student> selectStudent(String columnName, String columnValue)
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
            String query = "select * from " + tableName + " where " + columnName.trim().toUpperCase() + " = " + "'" + columnValue.trim().toUpperCase() + "'"; 
            
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	
            	Student student = new Student();
            	
                student.setAndrewID(results.getString(1));
                student.setFirstName(results.getString(2).trim().toUpperCase());            
                student.setLastName(results.getString(3).trim().toUpperCase());
                student.setProgramTrack(results.getString(4).trim().toUpperCase());
                student.setFullTime(results.getString(5).trim().toUpperCase());
                student.setCountry(results.getString(6).trim().toUpperCase());
                student.setSemester(results.getString(7).trim().toUpperCase());
                student.setPhotoPath(results.getString(6).trim().toUpperCase());
                System.out.println(student.getAndrewID() 
                		           + "\t\t" + student.getFirstName() 
                		           + "\t\t" + student.getLastName()
                		           + "\t\t" + student.getProgramTrack()
                		           + "\t\t" + student.getFullTime()
                		           + "\t\t" + student.getCountry()
                		           + "\t\t" + student.getSemester()
                		           + "\t\t" + student.getPhotoPath());
                studentsArr.add(student);
            }
            
            results.close();
            stmt.close();
           
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		return studentsArr;
    }
    
    
    public ArrayList<Student> selectStudent(ArrayList<String> columnName, ArrayList<String> columnValue) throws ArrayListDoesNotMatch
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	
    	String query = null;
    	
    	Iterator colNmeItr = columnName.iterator();
    	Iterator colValItr = columnValue.iterator();
    	
    	if(columnName.size()!=columnValue.size())
    	{
    		ArrayListDoesNotMatch arrDoesNMat = new ArrayListDoesNotMatch();
    		throw  arrDoesNMat;
    	}
    	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
          
        	query = "select * from " + tableName;
        	
        	/*while(colNmeItr.hasNext()){
        		
        		query = "select * from " + tableName 
           		        + " where " + colNmeItr.next() 
           		        + " = " + "'" + columnValue.trim().toUpperCase() 
           		        + "'"; 
        		
        	}*/
        	
        	
            
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	
            	Student student = new Student();
            	
                student.setAndrewID(results.getString(1));
                student.setFirstName(results.getString(2).trim().toUpperCase());            
                student.setLastName(results.getString(3).trim().toUpperCase());
                student.setProgramTrack(results.getString(4).trim().toUpperCase());
                student.setFullTime(results.getString(5).trim().toUpperCase());
                student.setCountry(results.getString(6).trim().toUpperCase());
                student.setSemester(results.getString(7).trim().toUpperCase());
                student.setPhotoPath(results.getString(6).trim().toUpperCase());
                System.out.println(student.getAndrewID() 
                		           + "\t\t" + student.getFirstName() 
                		           + "\t\t" + student.getLastName()
                		           + "\t\t" + student.getProgramTrack()
                		           + "\t\t" + student.getFullTime()
                		           + "\t\t" + student.getCountry()
                		           + "\t\t" + student.getSemester()
                		           + "\t\t" + student.getPhotoPath());
                studentsArr.add(student);
            }
            
            results.close();
            stmt.close();
           
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
		return studentsArr;
    }
    
    
    
    public void shutdown()
    {
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (conn != null)
            {
                DriverManager.getConnection(dbURL + ";shutdown=true");
                conn.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            
        }

    }

}
