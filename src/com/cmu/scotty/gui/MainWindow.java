/**
 * @version 1.0
 * @author Daniel Alan Miller
 * @author Tania Dasgupta
 * @author Rijia 'Rebecca' Hou
 */

package com.cmu.scotty.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import jxl.read.biff.BiffException;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

import com.cmu.scotty.controller.PdfCreator;
import com.cmu.scotty.controller.ScottyController;
import com.cmu.scotty.exception.ArrayListDoesNotMatch;
import com.cmu.scotty.exception.WrongExcelException;
import com.cmu.scotty.exception.WrongTextException;
import com.cmu.scotty.model.Student;
import com.itextpdf.text.DocumentException;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;




//This class MainWindow is responsible for the entirety of our little application. 
// It handles the tabs located along the top of the application and swtiching between enabled windows.
// It also handles rendering and positioning of all elements which are present on all the pages of our application. 
// In some instances we tried to move as much functionality as we could into the ScottyController class, but some of the PDFCreator code
// had to go here because of dependencies on the pdfPreview window. 
// Ultimately this class handles everything you can see and then some of the things that we had to put in here. 

public class MainWindow {

	//Test for import File
	
	private JFrame frame;
	private JPanel jpStatic = new JPanel();
	private JPanel jpImport = new JPanel();
	private JPanel jpFilter = new JPanel();
	private JPanel jpExport = new JPanel();
	private JButton jbtImport;
	private JButton jbtExport;
	private JButton jbtFilter;

	
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
	private JLabel lblImportTxt = new JLabel("Class Roster(.txt)");
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
	
	// Controller 
	private ScottyController controller = new ScottyController();

	
	// FILTER
	private final JPanel jpFilterList = new JPanel();
	private final JPanel jpFilterCtrl = new JPanel();
	private final JLabel lblNewLabel = new JLabel("Country:");
	private JComboBox programSelector;
	private JTable studentsList;
	
	private JComboBox countrySelector;

	//EXPORT
	private JTextField jtfpdfTitle = new JTextField();

	
	private final ArrayList<String> dbColumns = new ArrayList<String>(){{add("PROGRAMTRACK"); add("COUNTRY");}};
	private ArrayList<String> filters = new ArrayList<String>(){{add(null); add(null);}};
	
	private JTable studentsTable = new JTable();

	
	private final ArrayList columnNames = new ArrayList();
	private final ArrayList columnValues = new ArrayList();
	
	private PdfCreator pdfCreator; 
	
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
		        } catch (Exception e) {
		        	JOptionPane.showMessageDialog(null,"Error: Failed Generating skins for the window!");
		        }
		        MainWindow window;
				try {
					window = new MainWindow();
					window.frame.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error: Failed import/export files!");
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
	//	initializeFilterPanel();
	//	initializeImportPanel();

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

		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);

		
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
		lblImportExcel.setBounds(36, 18, 66, 59);
		jpImportExcel.add(lblImportExcel);
		jtfExcelPath.setEditable(false);
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
		lblImportTxt.setBounds(10, 19, 118, 59);
		jpImportTxt.add(lblImportTxt);
		jtfTxtPath.setEditable(false);
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
		lblImportImg.setBounds(28, 11, 82, 62);
		jpImportImg.add(lblImportImg);
		jtfImgPath.setEditable(false);
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
		
		//THIS IS THE NEXT BUTTON ON THE IMPORT PAGE AND THE ACTION BELOW WHICH HAPPENS AFTER YOU CLICK IT
		jbtImportNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfExcelPath.getText().trim().length()<1 || jtfImgPath.getText().trim().length()<1){
					if(jtfExcelPath.getText().trim().length()<1)
						JOptionPane.showMessageDialog(null,"Please choose the Excel Path!");
					else if(jtfImgPath.getText().trim().length()<1)
						JOptionPane.showMessageDialog(null,"Please choose the Image Source Folder!");
				}
				else{					
				

					
					controller.setImgPath(jtfImgPath.getText());
					
					try
					{
						ArrayList<Student> studentD = new ArrayList<Student>();
						controller.readExcel(jtfExcelPath.getText().trim());
						
						// Read the text file if it is not empty
						if(jtfTxtPath.getText().trim().length()>=1)
						{
							controller.insertText(jtfTxtPath.getText().trim());
						}
						//studentD = controller.selectStudent("andrewId", "abmd3");
						//System.out.println("Hi");
						jpImport.setVisible(false);
						jbtFilter.setEnabled(true);
						jpFilter.setVisible(true);
						jpExport.setVisible(false);
						
						jbtImport.setSelected(false);
						jbtFilter.setSelected(true);
						jbtExport.setSelected(false);
						frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
					}
					catch(WrongExcelException wrongExcelException)
					{
					//	wrongExcelException.printStackTrace();
						JOptionPane.showMessageDialog(null, wrongExcelException.getMessage());
						
					}
					catch(IOException ioException)
					{
					//	ioException.printStackTrace();
						JOptionPane.showMessageDialog(null, ioException.getMessage());
					}
					catch(BiffException biffException)
					{
					//	biffException.printStackTrace();
						JOptionPane.showMessageDialog(null, "Wrong file type/format,please check again!");
					}
					
					catch(WrongTextException wrongTextException)
					{
				//		wrongTextException.printStackTrace();
						JOptionPane.showMessageDialog(null, wrongTextException.getMessage());
					}
					catch(SQLException sqlException)
					{
				//		sqlException.printStackTrace();
						JOptionPane.showMessageDialog(null, sqlException.getMessage());
					}
					catch(Exception except)
					{
				//		except.printStackTrace();
						JOptionPane.showMessageDialog(null, except.getMessage());
					}
					
					//GET ALL STUDENTS FROM MAIN CONTROLLER, CREATE SET OF THEIR COUNRIES, CREATE LIST FROM SET OF COUNTRIES
					//FOR COUNTRY IN STUDENTS ADD ITEMS TO countrySelector
					ArrayList<String> countryOptions = new ArrayList<String>();
					countryOptions.add("Global");
						
					
					try {
						ArrayList<String> inputCountries= controller.selectCountries();
						
						Collections.sort(inputCountries,
								new Comparator<String>() {
						    @Override
						    public int compare(String arg0, String arg1) {
										System.out.println("arg0:" + arg0
												+ " | arg1" + arg1);
								return arg0.compareTo(arg1);
							}
						});
						
						for (String country: inputCountries){
							countryOptions.add(country);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
					}
					
					String[] countryOptionsArray = countryOptions.toArray(new String[countryOptions.size()]);
					countrySelector = new JComboBox(countryOptionsArray);
					countrySelector.setBounds(0,86,163,20);
					countrySelector.addPropertyChangeListener(new PropertyChangeListener(){
						public void propertyChange(PropertyChangeEvent arg0){

							String country = countrySelector.getSelectedItem().toString();
							if (country.equals("Global")){
								filters.set(1, null);
							} else {
								filters.set(1, country);
							}
							try {
								redoStudentTable();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Error!");
							} catch (Exception e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null,"Error!");
							}
						}
					});
					jpFilterCtrl.add(countrySelector);
					
					try {
						redoStudentTable();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Error!");
					}
					
					//Filter Panel
					initializeFilterPanel();
				}
				
			}
		});
		
				
		jbtImportNext.setBounds(449, 22, 69, 23);

		//PanelNextImport
		jpImportNext.add(jbtImportNext);
	}
	
	public void pdfPreview(){
	  	String path= System.getProperty("java.io.tmpdir");

		File file = new File(path, "preview.pdf"); 
		RandomAccessFile raf;
	    FileChannel channel;
	    ByteBuffer buf;
	    PDFFile pdffile; 
		try {			
		    
			raf = new RandomAccessFile(file, "r");			
			channel = raf.getChannel();  
			buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			pdffile = new PDFFile(buf);  
		    
	     int pages = pdffile.getNumPages();
	     Image img;

	     PDFPage page = pdffile.getPage(1);
	     Rectangle rect =
             new Rectangle(0, 0, (int)page.getBBox().getWidth(), (int)page.getBBox().getHeight());
	     
	           img = page.getImage(220, (int)(220.0/rect.width*rect.height),
	                 rect, // clip rect
	                 null, // null for the ImageObserver
	                 true, // fill background with white
	                 true) // block until drawing is done
	         ;
	         jpPreviewWindow.removeAll();
	         jpPreviewWindow.add(new JLabel(new ImageIcon(img)));
	         raf.close();
	         channel.close();
	         buf.clear();
	         
	         
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage());
		}  catch (IOException e){
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		
	}

	public void initializeFilterPanel(){
		
		jpFilter.setLayout(null);
		jpFilterList.setBounds(189, 11, 351, 329);
		jpFilterList.setPreferredSize(new Dimension(50, 50));
		jpFilterList.setMinimumSize(new Dimension(50, 50));
		
		jpFilter.add(jpFilterList);
		
		jpFilterList.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 351, 329);
//		scrollPane.setDoubleBuffered(true);
		
		studentsTable.setBounds(new Rectangle(100, 10, 50, 50));
		
		
        studentsTable.setFillsViewportHeight(true);		
		studentsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		scrollPane.add(studentsTable);
		scrollPane.setViewportView(studentsTable);
		jpFilterList.add(scrollPane);
				
		jpFilterCtrl.setBounds(10, 11, 173, 329);
		
		jpFilter.add(jpFilterCtrl);
//		jpFilterCtrl.setLayout(null);
		
		
		String[] programOptions = new String[]{"All Programs", "MISMAU", "MSGLOB", "MS3-AU", "MSIT"};
		jpFilterCtrl.setLayout(null);
		
		JLabel lblProgram = new JLabel("Program:");
		lblProgram.setBounds(0, 8, 85, 14);
		jpFilterCtrl.add(lblProgram);
		
		programSelector = new JComboBox(programOptions);
		
		//NEED TO CREATE EVENT TO HANDLE RELOADING THE LIST OF STUDENTS WHEN PROPERTY CHANGES
		programSelector.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				String program = programSelector.getSelectedItem().toString();
				if (program.equals("All Programs")){
					filters.set(0, null);
				} else {
					filters.set(0, program);
				}
				try {
					redoStudentTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error!");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Error!");
				}
			}
		});
		programSelector.setBounds(0, 32, 163, 20);
		jpFilterCtrl.add(programSelector);
	
		lblNewLabel.setBounds(0, 62, 67, 14);
		
		jpFilterCtrl.add(lblNewLabel);
			
		JButton filterNext = new JButton("Next>>");
		filterNext.setBounds(87, 139, 76, 23);
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
				
				try {
					pdfCreator = new PdfCreator( getCurrentStudentSubSet() );
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				try {
					pdfCreator.printTablePreview();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				//Export Panel
				try {
					initializeExportPanel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		ArrayList<Student> stuFrmText = new ArrayList<Student>();
		ArrayList<String> andrewIds = new ArrayList<String>();
		ArrayList<Student> currStuFrmText = new ArrayList<Student>();
		ArrayList<Student> currStudentFrmFilter = new ArrayList<Student>();
		Student studentNew = new Student();
		
		
		try
		{
			if(jtfTxtPath.getText().trim().length()>=1)
			{
				//controller.insertText(jtfTxtPath.getText().trim());
				stuFrmText = controller.readText(jtfTxtPath.getText().trim());
				
				Iterator iterator = stuFrmText.iterator();
				
				columnNames.add("PROGRAMTRACK");
				columnNames.add("MSIT-AU");
				
				while(iterator.hasNext())
				{
					studentNew = (Student)iterator.next();
				}
								
				
				try {
					currStuFrmText = controller.selectStudentOnAndrewIds(andrewIds);
					currStudentFrmFilter = controller.selectStudent(columnNames, columnValues);
				} catch (ArrayListDoesNotMatch e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (SQLException e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
				
			}
			
			//currStudent.add
		}
		catch(IOException ioExc)
		{
		//	ioExc.printStackTrace();
			JOptionPane.showMessageDialog(null, ioExc.getMessage());
			
		} catch (WrongTextException wrgTxtExc) {
			
		//	wrgTxtExc.printStackTrace();
			JOptionPane.showMessageDialog(null, wrgTxtExc.getMessage());
		}
		
		
		
		
	}
	
	public ArrayList<Student> getCurrentStudentSubSet() throws SQLException, Exception{
		
		ArrayList<Object[]> studentsData = new ArrayList<Object[]>();
		
		boolean nonNull = false;
		for (String s : filters){
			if (s != null){
				nonNull = true;
				break;
			}
		}
			
		if (nonNull == false){
			return controller.selectStudent();
		} else {			
			ArrayList<String> specFilters = (ArrayList<String>) filters.clone();
			ArrayList<String> specColNames = (ArrayList<String>) dbColumns.clone();
			
			for (int i = 0; i < specFilters.size(); i++){
				if (specFilters.get(i) == null){
					specFilters.remove(i);
					specColNames.remove(i);
				}
			}
		
			return controller.selectStudent(specColNames, specFilters);
		}
	}

	public void redoStudentTable() throws SQLException, Exception {
		
		ArrayList<Object[]> studentsData = new ArrayList<Object[]>();
		
		boolean nonNull = false;
		for (String s : filters){
			if (s != null){
				nonNull = true;
				break;
			}
		}
			
		ArrayList<Student> stus;
		if (nonNull == false){
			stus = controller.selectStudent(); 
			for(Student s: stus ){
				studentsData.add(s.toRow());
			}
		} else {			
			ArrayList<String> specFilters = (ArrayList<String>) filters.clone();
			ArrayList<String> specColNames = (ArrayList<String>) dbColumns.clone();
			
			for (int i = 0; i < specFilters.size(); i++){
				if (specFilters.get(i) == null){
					specFilters.remove(i);
					specColNames.remove(i);
				}
			}
			stus = controller.selectStudent(specColNames, specFilters);
			for(Student s: stus){
				studentsData.add(s.toRow());
			}
		}
		
		DefaultTableModel studentsTableModel = new DefaultTableModel(	
					studentsData.toArray(new Object[studentsData.size()][]),
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
		};
		
		
		studentsTable.setModel(studentsTableModel);
		
	}
	
	public void initializeExportPanel() throws IOException{
		jpExport.setLayout(null);
		jpPathExport.setBounds(0, 0, 284, 340);
		jpExport.add(jpPathExport);
		jpPreviewExport.setBounds(282, 0, 258, 340);
		jpExport.add(jpPreviewExport);
		//Path Export Panel
		JLabel lblReportTitle = new JLabel("Report Title");
		lblReportTitle.setBounds(20, 21, 78, 15);
		jpPathExport.add(lblReportTitle);
		jpPathExport.setLayout(null);
		jpPathExport.setLayout(null);
		lalPdfExport.setBounds(20, 68, 78, 15);
		jpPathExport.add(lalPdfExport);
		jtfPdfPath.setEditable(false);
		jtfPdfPath.setBounds(20, 86, 175, 22);
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
		
		jbtPdfBrowse.setBounds(205, 86, 69, 23);
		jpPathExport.add(jbtPdfBrowse);
		lalPdfName.setBounds(20, 119, 54, 15);
		jpPathExport.add(lalPdfName);
		jtfFileName.setBounds(20, 136, 195, 22);
		jpPathExport.add(jtfFileName);
		
		
		jtfpdfTitle.setFont(new Font("SimSun", Font.PLAIN, 12));
		jtfpdfTitle.setBounds(20, 39, 254, 20);
		jpPathExport.add(jtfpdfTitle);
		jtfpdfTitle.setColumns(10);
		
		lal_pdf.setBounds(225, 139, 40, 15);
		jpPathExport.add(lal_pdf);
		
		jbtExportPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(jtfpdfTitle.getText().trim().length()<1 || jtfPdfPath.getText().trim().length()<1 || jtfFileName.getText().trim().length()<1){
					if(jtfpdfTitle.getText().trim().length()<1 ){
					//	jtfpdfTitle.setText("Output");
					}
					
						
					else if(jtfPdfPath.getText().trim().length()<1){
						JOptionPane.showMessageDialog(null,"Please choose Folder for the Export File!");
					}
						
					else if(jtfFileName.getText().trim().length()<1)
					{
						JOptionPane.showMessageDialog(null,"Please input the file name!");
					}
						
				}
				else{	
					String path = jtfPdfPath.getText().trim()+"\\"+jtfFileName.getText().trim()+".pdf";
					pdfCreator.setExportLocation(path);
					pdfCreator.setTitle(jtfpdfTitle.getText().trim());
					try {
						pdfCreator.printTable3();
						JOptionPane.showMessageDialog(null,"Done!");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1.getMessage());
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,e1.getMessage());
					}
					
				}
			}
		});
		jbtExportPdf.setBounds(20, 197, 123, 30);
		jpPathExport.add(jbtExportPdf);
	
		
	
		jpPreviewExport.setLayout(null);
		jpPreviewWindow.setBorder(new TitledBorder(null, "File Preview", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		jpPreviewWindow.setBounds(10, 10, 228, 320);
		jpPreviewExport.add(jpPreviewWindow);
		
		// Preview Window
		pdfPreview();
	   
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
		jbtFilter.setEnabled(false);
		jbtExport.setEnabled(false);
		
	}
	public JComboBox getProgramSelector() {
		return programSelector;
	}
}
