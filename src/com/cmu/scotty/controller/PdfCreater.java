package com.cmu.scotty.controller;


import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import com.cmu.scotty.model.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfCreater {
 
    /** The resulting PDF. 
     * @throws DocumentException 
     * @throws IOException */

	public static void main(String[]args) throws IOException, DocumentException{
		ArrayList<Student> s=new<Student> ArrayList();
		Student a=new Student("ruiw","Rui","Wang",
				"MISM","F/T","China", "13F");
		a.setPhotoPath("/Users/ruiwang/Desktop/Project6-Resources/testImages/t1.jpg");
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		PdfCreater c= new PdfCreater(s);
		c.printTable();
	}
	
	
    public static final String RESULT
        = "src/Student_List.pdf";
    /** The movie poster. */
    public ArrayList <String> image=new<String> ArrayList();
    public ArrayList <String> program=new<String> ArrayList();
    public ArrayList <String> firstname=new<String> ArrayList();
    public ArrayList <String> lastname=new<String> ArrayList();
    public ArrayList <String> email=new<String> ArrayList();
    public ArrayList <String> time=new<String> ArrayList();
    public ArrayList <String> country=new<String> ArrayList();
    public ArrayList <String> intake=new<String> ArrayList();
    
    public static final String LOGO
    ="/Users/ruiwang/Pictures/logo.jpg";
    public static final String TITLE
    ="Master of Information Systems Management Student List for F13 Semester (August 2013)";
    public static Document document;
    PdfCreater(ArrayList<Student> s){
    	for(int i=0;i<s.size();i++){
    		image.add(s.get(i).getPhotoPath());
    		program.add(s.get(i).getProgramTrack());
    		firstname.add(s.get(i).getFirstName());
    		lastname.add(s.get(i).getLastName());
    		email.add(s.get(i).getAndrewID()+"@andrew.cmu.edu");
    		time.add(s.get(i).getFullTime());
    		country.add(s.get(i).getCountry());
    		intake.add(s.get(i).getSemester());
    	}
    }
    public void printTable()
        throws IOException, DocumentException {
    	// step 1
    
        document
            = new Document(PageSize.A4, 30, 30, 30, 30);
        // step 2
        
        PdfWriter writer
            = PdfWriter.getInstance(document, new FileOutputStream(RESULT));
        writer.setCompressionLevel(0);
        // step 3
        document.setMargins(20,20,0,30);
        document.open();
        // step 4
  
       //createFirstTable();
       // createSecondTable();
        createThirdTable();
       // createForthTable();
       // createFifthTable();
       // createSixthTable();
        document.close();
        }
        // step 5

   
    public void createFirstTable() throws DocumentException, IOException {
    	int n=1-(image.size())%1;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(1);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(23);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {200};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
            	cell.setPaddingLeft(200);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        
        	      Phrase ph = new Phrase();
        	      if(image!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
            	img.scalePercent(100);
    	  
    
    	         ph.add(new Chunk(img, 0, 0, true));
        	      }
    	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
    	         ph.add(new Phrase(lastname.get(i)+" "));
    	         ph.add(new Phrase("\n"+email.get(i)+" "));
    	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
    	         ph.add(new Phrase("\n"+time.get(i)+" "));
    	         ph.add(new Phrase("|"+country.get(i)+" "));
    	         ph.add(new Phrase("|"+intake.get(i)+" "));
    	         p.add(ph);
    	         p.setAlignment(Element.ALIGN_CENTER);
    	         
    	           cell.addElement(p);
    	           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	       	table.addCell(cell);
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
    
    public void createSecondTable() throws DocumentException, IOException {
    	int n=2-(image.size())%2;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(2);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(23);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {150, 150};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    Phrase ph = new Phrase();
        
        	    if(image.get(i)!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
            	img.scalePercent(60);
    	      
    

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
   	         ph.add(new Phrase(lastname.get(i)+" "));
   	         ph.add(new Phrase("\n"+email.get(i)+" "));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	         p.add(ph);
   	         p.setAlignment(Element.ALIGN_CENTER);
    	           cell.addElement(p);
    	           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	       	table.addCell(cell);
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
    
    public void createThirdTable() throws DocumentException, IOException {
    	int n=3-(image.size())%3;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(3);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(23);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {100, 100,100};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        	    Phrase ph = new Phrase();
        	    if(image.get(i)!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
            	img.scalePercent(90);
    	     

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
   	         ph.add(new Phrase(lastname.get(i)+" "));
   	         ph.add(new Phrase("\n"+email.get(i)+" "));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	         p.add(ph);
   	         p.setAlignment(Element.ALIGN_CENTER);
 
           cell.addElement(p);
           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       	table.addCell(cell);
           	
            	
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
 
    public void createForthTable() throws DocumentException, IOException {
    	int n=4-(image.size())%4;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(4);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(25);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {150, 150,150,150};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        	    Phrase ph = new Phrase();
        	    if(image.get(i)!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
    	      

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
   	         ph.add(new Phrase(lastname.get(i)+" "));
   	         ph.add(new Phrase("\n"+email.get(i)+" "));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	         p.add(ph);
   	         p.setAlignment(Element.ALIGN_CENTER);
          
  
    	         p.setAlignment(Element.ALIGN_CENTER);
    	         
    	           cell.addElement(p);
    	           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	       	table.addCell(cell);
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
    public void createFifthTable() throws DocumentException, IOException {
    	int n=5-(image.size())%5;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(5);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(23);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {100, 100,100,100,100};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        
        	      Phrase ph = new Phrase();
        	      if(image.get(i)!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
            	img.scalePercent(100);
    	  
    

   	         ph.add(new Chunk(img, 0, 0, true));
        	      }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
   	         ph.add(new Phrase(lastname.get(i)+" "));
   	         ph.add(new Phrase("\n"+email.get(i)+" "));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	         p.add(ph);
   	         p.setAlignment(Element.ALIGN_CENTER);
    	           cell.addElement(p);
    	           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	       	table.addCell(cell);	
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
    public void createSixthTable() throws DocumentException, IOException {
    	int n=6-(image.size())%6;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(6);
        PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell,cell1;
        cell = new PdfPCell();
        cell.setBorder(0);
        Image logo = Image.getInstance(LOGO);
        logo.scaleAbsoluteHeight(20);
        logo.scaleAbsoluteWidth(20);
        logo.scalePercent(23);
        cell.setColspan(4);
        cell.addElement(logo);
        table1.addCell(cell);
     
        cell = new PdfPCell();
        cell.setColspan(3);
        cell.setBorder(0);
        cell.addElement(new Phrase(TITLE));
        table1.addCell(cell);
        document.add(table1);
    	
    	
        // the cell object
        for(int i=0; i<image.size();i++){
        
        		int[] cellWidth = {100, 100,100,100,100,100};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        
        	    Phrase ph = new Phrase();
        	    if(image.get(i)!=null){
        	    Image img = Image.getInstance(image.get(i));
       
            	img.scaleToFit(300, 135);
            	img.scalePercent(50);
    	      
    

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" "));
   	         ph.add(new Phrase(lastname.get(i)+" "));
   	         ph.add(new Phrase("\n"+email.get(i)+" "));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	        p.add(ph);
   	         p.setAlignment(Element.ALIGN_CENTER);
    	         
    	           cell.addElement(p);
    	           cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	       	table.addCell(cell);	
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
    

}
