package com.cmu.scotty.gui;

import com.cmu.scotty.model.*;
import com.cmu.scotty.controller.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;

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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
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
		        MainWindow window = new MainWindow();
		        
				window.frame.setVisible(true);
		      }
		 });
	
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 556, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Top Side-Buttons	
		initializeTopButton();
		//Import Panel
		initializeImportPanel();
		//Export Panel
		initializeExportPanel();
	
		
		//Panels
		jpStatic.setLayout(new GridLayout(1, 3, 0, 0));
		jpStatic.add(jbtImport);
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
		jbtImport.setEnabled(true);
		jbtImport.setSelected(true);
		jbtFilter.setEnabled(false);
		jbtExport.setEnabled(false);
		
		//MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.NORTH);
	    frame.getContentPane().add(jpExport, BorderLayout.CENTER);
	//    jpFilter.setVisible(false);
	//	jpExport.setVisible(false);
		
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
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
		jbtImportNext.setBounds(449, 33, 69, 23);
		//PanelNextImport
		jpImportNext.add(jbtImportNext);
	}
	public void initializeFilterPanel(){
		
	}
	public void initializeExportPanel(){
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
		
	}
}