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

    
    public Connection createConnection() throws SQLException , Exception
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        
        catch (SQLException sqlExcept)
        {
        	sqlExcept.printStackTrace();
        	throw sqlExcept;
        }  
        catch (Exception except)
        {
        	except.printStackTrace();
        	throw except;
        }
        
        return conn;
    }
    
    public boolean checkTable() throws SQLException , Exception
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		
    	return true;
    }
    
    public void dropTable() throws SQLException , Exception
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
    }
    
    
    public void createTable() throws SQLException , Exception
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
    }
    
    
    public void insertStudents(ArrayList<Student> studentArr) throws SQLException , Exception
    {
        try
        {
    	   boolean insertStatus = false;
        	Iterator iterator = studentArr.iterator();
        	while(iterator.hasNext())
        	{
        		insertStudent((Student)iterator.next());
        	}
        }
        	 catch (SQLException sqlExcept)
             {
             	sqlExcept.printStackTrace();
             	throw sqlExcept;
             }  
         	catch (Exception except)
             {
         		except.printStackTrace();
         		throw except;
             }  
    }
    
    
    public void updateStudents(ArrayList<Student> studentArr) throws SQLException , Exception
    {
        try
        {
    	    boolean insertStatus = false;
        	Iterator iterator = studentArr.iterator();
        	while(iterator.hasNext())
        	{
        		updateStudent((Student)iterator.next());
        	}
        }
        	 catch (SQLException sqlExcept)
             {
             	sqlExcept.printStackTrace();
             	throw sqlExcept;
             }  
         	catch (Exception except)
             {
         		except.printStackTrace();
         		throw except;
             }  
    }
    
    public void insertStudent(Student student) throws SQLException,Exception
    {
          		
        // Put a check for andrew Id
    	
    	String firstName = student.getFirstName() == null ? " " : student.getFirstName().trim();
        String lastName = student.getLastName()==null ? " " : student.getLastName().trim();
        String programTrack = student.getProgramTrack() == null ? " " : student.getProgramTrack().trim();
        String fullTime = student.getFullTime() == null ? " " : student.getFullTime().trim();
        String country = student.getCountry() == null ? " " : student.getCountry().trim();
        String semester = student.getSemester() == null ? " " : student.getSemester().trim();
        String pathName = student.getPhotoPath() == null ? " " : student.getPhotoPath().trim();
       
        //String andrewId = student.getAndrewID().trim();
    
    	
    	try{
    		
    		if(conn==null)
        	{
        		conn = createConnection();
        	}
            stmt = conn.createStatement();
            String query = "insert into " + tableName + " values (" 
       		     + "'" + student.getAndrewID() + "'," 
       		     + "'" + firstName + "',"
       		     + "'" + lastName + "',"
       		     + "'" + programTrack + "',"
       		     + "'" + fullTime + "',"
       		     + "'" + country + "',"
       		     + "'" + semester + "',"
       		     + "'" + pathName + "')";
            
            System.out.println(query);            
            stmt.execute(query);  
            stmt.close();
    	}
        	
    	 catch (SQLException sqlExcept)
         {
         	sqlExcept.printStackTrace();
         	throw sqlExcept;
         }  
     	catch (Exception except)
         {
     		except.printStackTrace();
     		throw except;
         }  
            
       
            
    }
    
    
    public void updateStudent(Student student) throws SQLException,Exception
    {
        try
        {    
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
            stmt = conn.createStatement();
            
            //String[] studentCol = new String[8];

            int count = 0;
            
           /*  = if(student.getFirstName()!=null) { String firstNamestudent.getFirstName().trim()};
            String lastName = student.getLastName().trim();
            String programTrack = student.getProgramTrack();
            String fullTime = student.getFullTime().trim();
            String country = student.getCountry().trim();
            String semester = student.getSemester().trim();
            String pathName = student.getPhotoPath().trim();
            String andrewId = student.getAndrewID().trim();*/
            
            String query = " update " + tableName + " set " ;
            
            	            	
            	if(student.getFirstName()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " firstname " + " = " + "'" + student.getFirstName().trim() + "'";
            		count++;
            	}
            
            	if(student.getLastName()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " lastName " + " = " + "'" + student.getLastName().trim() + "'";
                    count++;        	
            	}
            	if(student.getProgramTrack()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " programTrack " + " = " + "'" + student.getProgramTrack().trim() + "'";
            	    count++;
            	}
            
            	if(student.getFullTime()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " fullTime " + " = " + "'" + student.getFullTime().trim() + "'";
            		count++;
            	}
            	
            	if(student.getCountry()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " country " + " = " + "'" + student.getCountry().trim() + "'";
            		count++;
            	}
            
            	if(student.getSemester()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " semester " + " = " + "'" + student.getSemester() + "'";
            		count++;
            	}
            	if(student.getPhotoPath()!=null)
            	{
            		if(count>0)
                	{
                		query = query + " , ";
                	}
            		query = query + " pathName " + " = " +  "'" + student.getPhotoPath() +  "'" ;
            		count++;
            	}            
            	            	
            query = query + " where andrewId = '" + student.getAndrewID() + "'" ;        
       		    /* + "'" + student.getAndrewID() + "'," 
       		     + "'" + student.getFirstName().trim() + "')";*/
       		    /* + "'" + student.getLastName().trim().toUpperCase() + "',"
       		     + "'" + student.getProgramTrack().trim().toUpperCase() + "',"
       		     + "'" + student.getFullTime().trim().toUpperCase() + "',"
       		     + "'" + student.getCountry().trim().toUpperCase() + "',"
       		     + "'" + student.getSemester().trim().toUpperCase() + "',"
       		     + "'" + student.getPhotoPath().trim().toUpperCase() + "')";*/
            
            System.out.println(query);
            
            stmt.executeUpdate(query);            		     
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
            throw sqlExcept;
        }
    }
    
    
    public ArrayList<Student> selectStudents(ArrayList<String> andrewIds) throws SQLException , Exception
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	int count = 0;
    	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
        	
        	
        	//Put null checks for column value and column name
        	
        	String query = " select * from " + tableName + " where andrewId in ( " ; 
        	
        	Iterator iterator = andrewIds.iterator();
        	
        	while(iterator.hasNext())
        	{
        		if(count>0){ query = query + " , "; }
        		String andrewId = (String)iterator.next();
        		query = query + " '" + andrewId + "' ";
        		count ++;
        	}
            
        	query = query + " ) ";
        	
        	System.out.println(query);
        	
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	
            	Student student = new Student();
            	
                student.setAndrewID(results.getString(1));
                student.setFirstName(results.getString(2));            
                student.setLastName(results.getString(3));
                student.setProgramTrack(results.getString(4));
                student.setFullTime(results.getString(5));
                student.setCountry(results.getString(6));
                student.setSemester(results.getString(7));
                student.setPhotoPath(results.getString(8));
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return studentsArr;
    }
    
    
    public ArrayList<Student> selectAllStudents() throws SQLException , Exception
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	   	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
        	
        	
        	//Put null checks for column value and column name
        	
        	String query = " select * from " + tableName ;      	
        	System.out.println(query);
        	
            ResultSet results = stmt.executeQuery(query);	                    
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	
            	Student student = new Student();
            	
                student.setAndrewID(results.getString(1));
                student.setFirstName(results.getString(2));            
                student.setLastName(results.getString(3));
                student.setProgramTrack(results.getString(4));
                student.setFullTime(results.getString(5));
                student.setCountry(results.getString(6));
                student.setSemester(results.getString(7));
                student.setPhotoPath(results.getString(8));
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return studentsArr;
    }
    
        
    public ArrayList<Student> selectStudent(String columnName, String columnValue) throws SQLException , Exception
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
        	
        	//Put nulll checks for column value and column name
        	
        	String query = null;
        	
            if(columnName.equalsIgnoreCase("programtrack"))
            {
            	query = "select * from " + tableName + " where " + columnName.trim().toUpperCase() + " likes " + "'%" + columnValue.trim() + "%'"; 	
            }
            else
            {
            	 query = "select * from " + tableName + " where " + columnName.trim().toUpperCase() + " = " + "'" + columnValue.trim() + "'"; 
            }
        	
           
            
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	
            	Student student = new Student();
            	
                student.setAndrewID(results.getString(1));
                student.setFirstName(results.getString(2));            
                student.setLastName(results.getString(3));
                student.setProgramTrack(results.getString(4));
                student.setFullTime(results.getString(5));
                student.setCountry(results.getString(6));
                student.setSemester(results.getString(7));
                student.setPhotoPath(results.getString(8));
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return studentsArr;
    }
    
    public ArrayList<String> selectCountries() throws SQLException , Exception
    {   
    	
    	ArrayList<String> countriesArr = new ArrayList<String>() ;
    	
    	String query = null;
        	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
        	  
        	query = "select distinct(COUNTRY) from " + tableName;        	
        	        	
        	System.out.println(query);
        	
            
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
               	countriesArr.add(results.getString(1));
            }
            
            results.close();
            stmt.close();
           
        }
        catch (SQLException sqlExcept)
        {
        	sqlExcept.printStackTrace();
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return countriesArr;
    }
    
    
    public ArrayList<String> selectAndrewIds() throws SQLException , Exception
    {   
    	
    	ArrayList<String> andrewIdsArr = new ArrayList<String>() ;
    	
    	String query = null;
        	
        try
        {
        	if(conn==null)
        	{
        		conn = createConnection();
        	}
        	
        	stmt = conn.createStatement();
        	  
        	query = "select distinct(andrewId) from " + tableName;        	
        	        	
        	System.out.println(query);
        	
            
            ResultSet results = stmt.executeQuery(query);
            
            
            ResultSetMetaData rsmd = results.getMetaData();
            int numberCols = rsmd.getColumnCount();
            
            while(results.next())
            {
            	andrewIdsArr.add(results.getString(1));
            }
            
            results.close();
            stmt.close();
           
        }
        catch (SQLException sqlExcept)
        {
        	sqlExcept.printStackTrace();
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return andrewIdsArr;
    }
    
    
    public ArrayList<Student> selectStudent(ArrayList<String> columnNameArr, ArrayList<String> columnValueArr) throws ArrayListDoesNotMatch, SQLException , Exception
    {   
    	
    	ArrayList<Student> studentsArr = new ArrayList<Student>() ;
    	
    	int count = 0;
    	
    	String query = null;
    	
    	Iterator colNmeItr = columnNameArr.iterator();
    	Iterator colValItr = columnValueArr.iterator();
    	
    	String columnName = null;
    	String columnValue = null;
    	
    	if(columnNameArr.size()!=columnValueArr.size())
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
        	  
        	query = "select * from " + tableName + " where ";
        	
        	while(colNmeItr.hasNext()){
        		
        		columnName = (String)colNmeItr.next();
        		columnValue = (String)colValItr.next();
        		
        		if(count==0)
        	    {
        			query = query + columnName.trim().toUpperCase() + " = " + " '" + columnValue.trim().toUpperCase()  + "' " ;
        	    }
        		else
        		{
        			query = query + " and " + columnName.trim().toUpperCase() + " = " + " '" + columnValue.trim().toUpperCase()  + "' " ;
        		}
        		
        	    count++;
        	    
        	}
        	
        	System.out.println(query);
        	
            
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
                student.setPhotoPath(results.getString(8).trim().toUpperCase());
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
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  
		return studentsArr;
    }
    
    
    
    public void shutdown() throws SQLException , Exception
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
        	sqlExcept.printStackTrace();
        	throw sqlExcept;
        }  
    	catch (Exception except)
        {
    		except.printStackTrace();
    		throw except;
        }  

    }

}
