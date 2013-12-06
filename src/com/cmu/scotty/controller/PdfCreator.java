package com.cmu.scotty.controller;


import java.awt.Color;
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
import com.itextpdf.text.FontFactory;
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

public class PdfCreator {
 
    /** The resulting PDF. 
     * @throws DocumentException 
     * @throws IOException */
	public static PdfCreator c;

	public static void test() throws IOException, DocumentException{
		ArrayList<Student> s=new<Student> ArrayList();
		Student a=new Student("ruiw","Rui","Wang",
				"MISM","F/T","China", "13F","");
		//a.setPhotoPath("/Users/ruiwang/Desktop/Project6-Resources/testImages/t1.jpg");
		Student b= new Student("a","asdf","asdf",
				"MISM","F/T","China", "13F","");
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(b);
		s.add(a);
		s.add(a);
		s.add(b);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		s.add(a);
		 c= new PdfCreator(s);
		c.printTable3();
	}

	
    public static String RESULT
        = "src/Student_List1.pdf";
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
    ="lib/logo/logo.png";
    public static final String UNAVAILABILE
    ="lib/logo/nopic.png";
    public static String TITLE
    ="Master of Information Systems Management Student List for F13 Semester (August 2013)";
    public static Document document;

    public PdfCreator(ArrayList<Student> s){

    	for(int i=0;i<s.size();i++){
    		image.add(s.get(i).getPhotoPath());
    		program.add(s.get(i).getProgramTrack());
    		firstname.add(s.get(i).getFirstName());
    		lastname.add(s.get(i).getLastName().toUpperCase());
    		email.add(s.get(i).getAndrewID()+"@andrew.cmu.edu");
    		time.add(s.get(i).getFullTime());
    		country.add(s.get(i).getCountry());
    		intake.add(s.get(i).getSemester());
    	}
    }
    
    public void setExportLocation(String path){
    	RESULT=path;
    }
    
    public void setTitle(String t){
    	TITLE = t;
    }
    public void printTable3()
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
      
           
            createThirdTable();
       
            document.close();
    }
 

    public void printTablePreview()
            throws IOException, DocumentException {
        	// step 1
        
            document
                = new Document(PageSize.A4, 30, 30, 30, 30);
            // step 2
            setTitle("Preview File");
            PdfWriter writer
                = PdfWriter.getInstance(document, new FileOutputStream("preview.pdf"));
            writer.setCompressionLevel(0);
            // step 3
            document.setMargins(20,20,0,30);
            document.open();
            // step 4
      
           
            createThirdTable();
       
            document.close();
    }
   

   
    
    public void createThirdTable() throws DocumentException, IOException {
    	int n=3-(image.size())%3;
    	
    	// a table with three columns
        PdfPTable table = new PdfPTable(3);
       // PdfPTable table1 =new PdfPTable(7);
	    table.setTotalWidth(PageSize.A4.getWidth() - 45);
	    table.setLockedWidth(true);

        PdfPCell cell;

        Font font = FontFactory.getFont("Impact",12,Font.UNDERLINE);
        Font font1= FontFactory.getFont("Impact",12,Font.BOLD);
      
        font.setColor(30, 144, 255);;
        Font fontbold = FontFactory.getFont("Times-Roman", 12, Font.BOLD);
        // the cell object
        for(int i=0; i<image.size();i++){
        	
        	if(i%9==0){
        	    cell = new PdfPCell();
        	    
                cell.setBorder(0);
                Image logo = Image.getInstance(LOGO);
               
                logo.scaleAbsoluteHeight(20);
                logo.scaleAbsoluteWidth(20);
                logo.scalePercent(23);
                cell.setColspan(2);
                cell.addElement(logo);
                table.addCell(cell);
             
                cell = new PdfPCell();
          
                cell.setBorder(0);
                cell.addElement(new Phrase("\n \n"));
                cell.addElement(new Phrase(TITLE,fontbold));
                
                table.addCell(cell);
               cell.setBorder(0);
               PdfPCell cell1 = new PdfPCell();
               cell1.setColspan(3);  
               cell1.setBorder(0);
               Paragraph p = new Paragraph();
               p.add(new Phrase(" "));
               cell1.addElement(p);
                table.addCell(cell1);
               

                
        	}
        
        		int[] cellWidth = {100, 100,100};
        
        		table.setWidths(cellWidth);
        		cell = new PdfPCell();
            	cell.setBorder(0);
        	    Paragraph p = new Paragraph();
        	  
        
        	    
        	    Phrase ph = new Phrase();
        	    if(image.get(i)!=null && !image.get(i).isEmpty()){
        	    Image img = Image.getInstance(image.get(i));
        	    img.scaleAbsolute(130f, 130f);
            
            	

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }else{
        	    	
        	    	Image img= Image.getInstance(UNAVAILABILE);
        	    	//img.scaleToFit(300, 135);
        	    	img.scaleAbsolute(130f, 130f);
                	
                	ph.add(new Chunk(img, 0, 0, true));
        	    }
   	         ph.add(new Phrase("\n"+firstname.get(i)+" ",font1));
   	         ph.add(new Phrase(lastname.get(i)+" ",font1));
   	         ph.add(new Phrase("\n"+email.get(i)+" ",font));
   	         ph.add(new Phrase("\nPROGRAM: "+program.get(i)+" "));
   	         ph.add(new Phrase("\n"+time.get(i)+" "));
   	         ph.add(new Phrase("|"+country.get(i)+" "));
   	         ph.add(new Phrase("|"+intake.get(i)+" "));
   	      ph.add(new Phrase("\n \n "));
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
