package com.cmu.scotty.controller;
/**
 * @author Rui Wang
 * @version 1.0
 * 9 Dec, 2013
 */

/**
 * PdfCreator Class takes a Student ArrayList as input and print out a pdf file to the selected 
 * location.
 * 
 * printTable3() method could export the pdf file to the location
 * 
 * createThirdTable() put all the elements to the pdf document
 * 
 * printPreview() method could export the pdf file in the relative path
 *
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.cmu.scotty.model.*;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfCreator {
 
    /** The resulting PDF. 
     * @throws DocumentException 
     * @throws IOException */


    public static String RESULT
        = "src/Student_List2.pdf";
    /** The movie poster. */
    public ArrayList <String> image=new ArrayList<String>();
    public ArrayList <String> program=new ArrayList<String>();
    public ArrayList <String> firstname=new ArrayList<String>();
    public ArrayList <String> lastname=new ArrayList<String>();
    public ArrayList <String> email=new ArrayList<String>();
    public ArrayList <String> time=new ArrayList<String>();
    public ArrayList <String> country=new ArrayList<String>();
    public ArrayList <String> intake=new ArrayList<String>();
    
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
    		if(s.get(i).getProgramTrack().trim().length()>10){
    		program.add(s.get(i).getProgramTrack().trim().substring(0,10));
    		}
    		else{
    			program.add(s.get(i).getProgramTrack().trim());	
    		}
    		if(s.get(i).getFirstName().trim().length()>10){
    		firstname.add(s.get(i).getFirstName().trim().substring(0,10));
    		}else{
    			firstname.add(s.get(i).getFirstName().trim());
    		}
    		if(s.get(i).getLastName().trim().length()>10){
        		lastname.add(s.get(i).getLastName().trim().substring(0,10));
        		}
    		else {
    		lastname.add(s.get(i).getLastName().toUpperCase().trim());
    		} 
    		if(s.get(i).getAndrewID().trim().length()>8){
    		email.add(s.get(i).getAndrewID().trim().substring(0,8)+"@andrew.cmu.edu");
    		}else{
    			email.add(s.get(i).getAndrewID().trim()+"@andrew.cmu.edu");
    		}
    		if(s.get(i).getFullTime().length()>3){
    			time.add(s.get(i).getFullTime().trim().substring(0,3));
    		}
    		else{
    		
    		time.add(s.get(i).getFullTime().trim());
    		}
    		if(s.get(i).getCountry().length()>10){
    		country.add(s.get(i).getCountry().trim().substring(0,10));
    		}else{
    			country.add(s.get(i).getCountry().trim());
    		
    		}
    		if(s.get(i).getSemester().length()>5){
    			intake.add(s.get(i).getSemester().trim().substring(0,5));
    		}
    		else{
    		intake.add(s.get(i).getSemester().trim());
    		}
    	}
    }
    /*
     * setExportLocation() could set the exportation path
     */
    public void setExportLocation(String path){
    	RESULT=path;
    }
    /*
     * setTitle could set the document Title
     */
    public void setTitle(String t){
    	TITLE = t;
    }
    
    /*
     * printTable3()export pdf to the selected path
     */
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
        //    writer.close();
            document.close();
    }
 /*
  * printTablePreview()generate a preview version of pdf in the software
  */

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
            writer.close();
    }
   
    /*
     * createThirdTable create a pdf file with desired elements
     */
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
        	   
        	    try{
        	 
        	    Image img = Image.getInstance(image.get(i));
        	    img.scaleAbsolute(130f, 130f);
            
            	

   	         ph.add(new Chunk(img, 0, 0, true));
        	    }catch(IOException e){
        	    	 if(!image.get(i).isEmpty()){
        	    	Image img= Image.getInstance(UNAVAILABILE);
        	    	//img.scaleToFit(300, 135);
        	    	img.scaleAbsolute(130f, 130f);
                	
                	ph.add(new Chunk(img, 0, 0, true));
        	    	 }
        	    }finally{
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
           
      
    }
       for(int j=0; j<n;j++){
    	   cell = new PdfPCell();
       	cell.setBorder(0);
        	table.addCell(cell);
        }
      document.add(table);
    }
 

}
