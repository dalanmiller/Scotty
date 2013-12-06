package com.cmu.scotty.gui;

import com.cmu.scotty.model.*;
import com.cmu.scotty.controller.*;
import com.cmu.scotty.persistence.*;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color; 

import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.table.DefaultTableModel; 



public class MainWindow {

	//Test for import File
	
	private JFrame frame;
	private JPanel jpStatic = new JPanel();
	private JPanel jpImport = new JPanel();
	private JPanel jpFilter = new JPanel();
	private JPanel jpExport = new JPanel();
	private JButton jbtImport;
	private JButton jbtFilter;
	private JButton jbtExport;
	
	//Panel Import
	private JPanel jpImportExcel = new JPanel();
	private JPanel jpImportTxt = new JPanel();
	private JPanel jpImportImg = new JPanel(); 
	//Panel excel import
	private JLabel lblImportExcel = new JLabel("Excel file*");
	private JTextField jtfExcelPath = new JTextField();
	private JButton jbtBrowseExcel = new JButton("Browse");
	private File importExcelFile = new File(""); 
	private FileNameExtensionFilter filterExcel = new FileNameExtensionFilter("Excel", "xls", "xlsx");
	private JFileChooser jfcImportExcelFile = new JFileChooser();
	//Panel Txt Import
	private JLabel lblImportTxt = new JLabel("Text file");
	private JTextField jtfTxtPath = new JTextField();
	private JButton jbtBrowseTxt = new JButton("Browse");
	private File importTxtFile = new File(""); 
	private FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("TXT", "txt");
	private JFileChooser jfcImportTxtFile = new JFileChooser();
	//Panel Folder Import
	private JLabel lblImportImg = new JLabel("Image folder*");
	private JTextField jtfImgPath = new JTextField();
	private JButton jbtBrowseImg = new JButton("Browse");
	private File importImgFile = new File(""); 
	private JFileChooser jfcImportImgFile = new JFileChooser();
	//Panel Next Import
	private JPanel jpImportNext = new JPanel();
	private JButton jbtImportNext = new JButton("Next>>");
	private final JButton button = new JButton("Next>>");
		
	//Panel Path Export
	private JPanel jpPathExport = new JPanel();
	private JLabel lalPdfExport = new JLabel("Export Folder");
	private JTextField jtfPdfPath = new JTextField();
	private JButton jbtPdfBrowse = new JButton("Browse");
	private JLabel lalPdfName = new JLabel("File Name"); 
	private JTextField jtfFileName = new JTextField();
	private JLabel lal_pdf = new JLabel(".pdf");
	private JButton jbtExportPdf = new JButton("Export Pdf File");
	private File exportPdfFile = new File(""); 
	private JFileChooser jfcExportPdfFile = new JFileChooser();
	
	//Panel Preview Export
	private JPanel jpPreviewExport = new JPanel();
	private JPanel jpPreviewWindow = new JPanel();
	private final JPanel jpFilterList = new JPanel();
	private final JPanel jpFilterCtrl = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Country:");
	private JComboBox programSelector;
	private JTable studentsList;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		        try {
		          	UIManager.setLookAndFeel(new SubstanceRavenGraphiteLookAndFeel());
		        //	UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		        } catch (Exception e) {
		        	e.printStackTrace();
		        }
		        MainWindow window;
				try {
					window = new MainWindow();
					window.frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			
		      }
		 });
		
		
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public MainWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 556, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Top Side-Buttons	
		initializeTopButton();
		//Import Panel
		initializeImportPanel();
		//Filter Panel
		initializeFilterPanel();
		//Export Panel
		initializeExportPanel();
	

		//Panels
		jpStatic.setLayout(new GridLayout(1, 3, 0, 0));
		jpStatic.add(jbtImport);
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
		jbtImport.setEnabled(true);
		jbtImport.setSelected(true);

		
		//MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.NORTH);
	    frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		
		
		frame.setLocationRelativeTo(null);
		ImageIcon ico = new ImageIcon("img/scottie-dog.jpg");
		frame.setIconImage(ico.getImage());

	
	}
	public void initializeImportPanel(){
		
		/*PanelImport*/

		jpImport.setLayout(new GridLayout(4, 1, 0, 0));
		jpImport.add(jpImportExcel);
		jpImport.add(jpImportImg);
		jpImport.add(jpImportTxt);
		jpImport.add(jpImportNext);
		jpImportExcel.setLayout(null);
		lblImportExcel.setBounds(30, 18, 66, 59);
		jpImportExcel.add(lblImportExcel);
		jtfExcelPath.setBounds(127, 36, 283, 23);
		jpImportExcel.add(jtfExcelPath);
		jfcImportExcelFile.setFileFilter(filterExcel);
		jbtBrowseExcel.setBounds(449, 36, 69, 23);
		jbtBrowseExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcImportExcelFile.showOpenDialog(jbtBrowseExcel);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					importExcelFile = jfcImportExcelFile.getSelectedFile();
					jtfExcelPath.setText(importExcelFile.getAbsolutePath());
				}
			}
		});
		jpImportExcel.add(jbtBrowseExcel);
		
		//PanelTxtImport
		jpImportTxt.setLayout(null);
		lblImportTxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportTxt.setBounds(33, 19, 61, 59);
		jpImportTxt.add(lblImportTxt);
		jtfTxtPath.setBounds(127, 37, 282, 23);
		jpImportTxt.add(jtfTxtPath);
		jfcImportTxtFile.setFileFilter(filterTxt);
		jbtBrowseTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcImportTxtFile.showOpenDialog(jbtBrowseTxt);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					importTxtFile = jfcImportTxtFile.getSelectedFile();
					jtfTxtPath.setText(importTxtFile.getAbsolutePath());
				}
			}
		});
		jbtBrowseTxt.setBounds(449, 37, 69, 23);
		jpImportTxt.add(jbtBrowseTxt);
		//PanelImgPath
		jfcImportImgFile.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		jpImportImg.setLayout(null);
		lblImportImg.setBounds(22, 11, 82, 62);
		jpImportImg.add(lblImportImg);
		jtfImgPath.setBounds(127, 31, 283, 23);
		jpImportImg.add(jtfImgPath);
		jbtBrowseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcImportImgFile.showOpenDialog(jbtBrowseImg);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					importImgFile = jfcImportImgFile.getSelectedFile();
					jtfImgPath.setText(importImgFile.getAbsolutePath());
				}
			}
		});
		jbtBrowseImg.setBounds(449, 31, 69, 23);
		jpImportImg.add(jbtBrowseImg);
		jpImportNext.setLayout(null);
		jbtImportNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfExcelPath.getText().trim().length()<1 || jtfImgPath.getText().trim().length()<1){
					if(jtfExcelPath.getText().trim().length()<1)
						JOptionPane.showMessageDialog(null,"Please choose the Excel Path!");
					else if(jtfImgPath.getText().trim().length()<1)
						JOptionPane.showMessageDialog(null,"Please choose the Image Source Folder!");
				}
				else{					
					jpImport.setVisible(false);
					jbtFilter.setEnabled(true);
					jpFilter.setVisible(true);
					jpExport.setVisible(false);
					
					jbtImport.setSelected(false);
					jbtFilter.setSelected(true);
					jbtExport.setSelected(false);
					frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
				}
				
			}
		});
		
				
		jbtImportNext.setBounds(352, 10, 69, 23);

		//PanelNextImport
		jpImportNext.add(jbtImportNext);
	}
	public void initializeFilterPanel(){
		ArrayList<Student> students = new ArrayList<Student>();
		
     	Student student1 = new Student();
     	
	     	student1.setAndrewID("44435slkf");
	     	student1.setFirstName("Tania");
	     	student1.setLastName("Dasgupta");
	     	student1.setCountry("India");
	     	student1.setFullTime("Yes");
	     	student1.setProgramTrack("MISM");
	     	student1.setSemester("1st");
	     	student1.setPhotoPath("jndj\\bsdfsd");
			
		Student student2 = new Student();
			student2.setAndrewID("0078");
			student2.setFirstName("Daniel");
			student2.setLastName("Miller");
			student2.setCountry("U.S");
			student2.setFullTime("Yes");
			student2.setProgramTrack("MISM");
			student2.setSemester("1st");
			student2.setPhotoPath("j\\ndj\\bsdfsd");
			
		students.add(student1);
		students.add(student2);
		
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		
		for(Student s: students){
			System.out.println(s.toString());
			dlm.addElement(s.toString());
		}
		jpFilter.setLayout(null);
		jpFilterList.setBounds(189, 11, 351, 329);
		jpFilterList.setPreferredSize(new Dimension(50, 50));
		jpFilterList.setMinimumSize(new Dimension(50, 50));
		
		jpFilter.add(jpFilterList);
		
		jpFilterList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 351, 329);
//		scrollPane.setDoubleBuffered(true);
		
		JTable studentsTable = new JTable();
		studentsTable.setBounds(new Rectangle(100, 10, 50, 50));
		
		studentsTable.setModel(new DefaultTableModel(
				new Object[][] {
					{"danielam", "Daniel", "Miller", "GMISM", "UNITED STATES OF AMERICA"},
				},
				new String[] {
					"Andrew ID", "First Name", "Last Name", "Program", "Country"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
		});
        studentsTable.setFillsViewportHeight(true);		
		studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		scrollPane.add(studentsTable);
		scrollPane.setViewportView(studentsTable);
		jpFilterList.add(scrollPane);
				
		jpFilterCtrl.setBounds(10, 11, 173, 329);
		
		jpFilter.add(jpFilterCtrl);
//		jpFilterCtrl.setLayout(null);
		
		
		String[] programOptions = new String[]{"All Programs", "GMISM", "MSIT", "MSPPM"};
		jpFilterCtrl.setLayout(null);
		
		JLabel lblProgram = new JLabel("Program:");
		lblProgram.setBounds(0, 8, 44, 14);
		jpFilterCtrl.add(lblProgram);
		
		programSelector = new JComboBox(programOptions);
		
		//NEED TO CREATE EVENT TO HANDLE RELOADING THE LIST OF STUDENTS WHEN PROPERTY CHANGES
		programSelector.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		programSelector.setBounds(54, 5, 109, 20);
		jpFilterCtrl.add(programSelector);
		
		ArrayList<String> countryOptions = new ArrayList<String>();
		
		//GET ALL STUDENTS FROM MAIN CONTROLLER, CREATE SET OF THEIR COUNRIES, CREATE LIST FROM SET OF COUNTRIES
		//FOR COUNTRY IN STUDENTS ADD ITEMS TO countrySelector

		countryOptions.add("Global");
	
		lblNewLabel.setBounds(0, 39, 43, 14);
		
		jpFilterCtrl.add(lblNewLabel);
		
		String[] countryOptionsArray = countryOptions.toArray(new String[countryOptions.size()]);
		System.out.println(countryOptionsArray);
		JComboBox countrySelector = new JComboBox(countryOptionsArray);
		countrySelector.setBounds(54,36,109,20);
		jpFilterCtrl.add(countrySelector);
		
		JButton filterNext = new JButton("Next");
		filterNext.setBounds(108, 74, 55, 23);
		jpFilterCtrl.add(filterNext);
		
		//NEED TO CREATE ACTIONS TO SWITCH TO EXPORT WINDOW
		filterNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jpImport.setVisible(false);
				jbtExport.setEnabled(true);
				jpFilter.setVisible(false);
				jpExport.setVisible(true);
				
				jbtImport.setSelected(false);
				jbtFilter.setSelected(false);
				jbtExport.setSelected(true);
				frame.getContentPane().add(jpExport, BorderLayout.CENTER);
			}
		});
		
	}
	public void initializeExportPanel() throws IOException{
		jpExport.setLayout(null);
		jpPathExport.setBounds(0, 0, 284, 340);
		jpExport.add(jpPathExport);
		jpPreviewExport.setBounds(282, 0, 258, 340);
		jpExport.add(jpPreviewExport);
		//Path Export Panel
		jpPathExport.setLayout(null);
		jpPathExport.setLayout(null);
		lalPdfExport.setBounds(20, 42, 78, 15);
		jpPathExport.add(lalPdfExport);
		jtfPdfPath.setBounds(20, 67, 175, 22);
		jpPathExport.add(jtfPdfPath);
		jfcExportPdfFile.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		jbtPdfBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcExportPdfFile.showOpenDialog(jbtPdfBrowse);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					exportPdfFile = jfcExportPdfFile.getSelectedFile();
					jtfPdfPath.setText(exportPdfFile.getAbsolutePath());
				}
				
			}
		});
		
		jbtPdfBrowse.setBounds(205, 66, 69, 23);
		jpPathExport.add(jbtPdfBrowse);
		lalPdfName.setBounds(20, 108, 54, 15);
		jpPathExport.add(lalPdfName);
		jtfFileName.setBounds(20, 136, 175, 22);
		jpPathExport.add(jtfFileName);
		lal_pdf.setBounds(205, 139, 24, 15);
		jpPathExport.add(lal_pdf);
		jbtExportPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		jbtExportPdf.setBounds(20, 197, 123, 30);
		jpPathExport.add(jbtExportPdf);
		jpPreviewExport.setLayout(null);
		jpPreviewWindow.setBorder(new TitledBorder(null, "File Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpPreviewWindow.setBounds(10, 10, 228, 320);
		jpPreviewExport.add(jpPreviewWindow);
		
		// Preview Window
		 File file = new File("Student_List1.pdf");  
	     RandomAccessFile raf = new RandomAccessFile(file, "r");  
	     FileChannel channel = raf.getChannel();  
	     ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());  
	     PDFFile pdffile = new PDFFile(buf);  
	     int pages = pdffile.getNumPages();
	     Image img;
	//   jpPicture.setLayout(new GridLayout(pages, 1, 0, 0));
	//     for(int i=1; i<= pages;i++) {
	     PDFPage page = pdffile.getPage(1);
	     Rectangle rect =
             new Rectangle(0, 0, (int)page.getBBox().getWidth(), (int)page.getBBox().getHeight());

	         //generate the image
	       //  img = page.getImage(rect.width/2, rect.height/2, //width &amp; height
	           img = page.getImage(220, (int)(220.0/rect.width*rect.height),
	                 rect, // clip rect
	                 null, // null for the ImageObserver
	                 true, // fill background with white
	                 true) // block until drawing is done
	         ;
	         jpPreviewWindow.add(new JLabel(new ImageIcon(img)));
	}
	
	public void initializeTopButton(){
		jbtImport = new JButton("Import");
		jbtImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(true);
				jpFilter.setVisible(false);
				jpExport.setVisible(false);
				jbtImport.setSelected(true);
				jbtFilter.setSelected(false);
				jbtExport.setSelected(false);
				frame.getContentPane().add(jpImport, BorderLayout.CENTER);
			}
		});
		jbtFilter = new JButton("Filter");
		jbtFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(true);
				jpExport.setVisible(false);
				jbtImport.setSelected(false);
				jbtFilter.setSelected(true);
				jbtExport.setSelected(false);
				frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
			}
		});
		jbtExport = new JButton("Export");
		jbtExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(false);
				jpExport.setVisible(true);
				jbtImport.setSelected(false);
				jbtFilter.setSelected(false);
				jbtExport.setSelected(true);
				frame.getContentPane().add(jpExport, BorderLayout.CENTER);
			}
		});
		

		/*MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.NORTH);
		
	
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jpFilter.add(button);
//		frame.setJMenuBar(jmb);
		frame.setLocationRelativeTo(null);
		ImageIcon ico = new ImageIcon("img/scottie-dog.jpg");
		frame.setIconImage(ico.getImage());*/
	}
	public JComboBox getProgramSelector() {
		return programSelector;
	}
}