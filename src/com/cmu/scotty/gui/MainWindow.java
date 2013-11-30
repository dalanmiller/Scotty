package com.cmu.scotty.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints; //similar to the last one, nice 

public class MainWindow {

	private JFrame frame;
	private JPanel jpStatic = new JPanel();
	private JPanel jpImport = new JPanel();
	private JPanel jpFilter = new JPanel();
	private JPanel jpExport = new JPanel();
	private JButton jbtImport;
	private JButton jbtFilter;
	private JButton jbtExport;
	private JMenuBar jmb = new JMenuBar();

	

//	private FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("txt");
	
	//Panel Import
	private JPanel jpImportExcel = new JPanel();
	private JPanel jpImportTxt = new JPanel();
	private JPanel jpImportImg = new JPanel(); 
	//Panel excel import
	private JLabel lblImportExcel = new JLabel("Excel file");
	private JTextField jtfExcelPath = new JTextField();
	private JButton jbtBrowseExcel = new JButton("Browse");
	private File importExcelFile = new File(""); 
	private FileNameExtensionFilter filterExcel = new FileNameExtensionFilter("Excel", "xls", "xlsx");
	private JFileChooser jfcImportExcelFile = new JFileChooser();
	//Panel Txt Import
	private JLabel lblImportTxt = new JLabel("Txt file");
	private JTextField jtfTxtPath = new JTextField();
	private JButton jbtBrowseTxt = new JButton("Browse");
	private File importTxtFile = new File(""); 
	private FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("TXT", "txt");
	private JFileChooser jfcImportTxtFile = new JFileChooser();
	//Panel Folder Import
	private JLabel lblImportImg = new JLabel("Image folder");
	private JTextField jtfImgPath = new JTextField();
	private JButton jbtBrowseImg = new JButton("Browse");
	private File importImgFile = new File(""); 
//	private FileNameExtensionFilter filterImg = new FileNameExtensionFilter("Directory", "");
	private JFileChooser jfcImportImgFile = new JFileChooser();
	
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
		        	e.printStackTrace();
		          //System.out.println("Substance Graphite failed to initialize");
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//menu
		JMenu jmSkin = new JMenu("Skins");
		jmb.add(jmSkin);
		
		
		//	TEST CODE
	//	JLabel lblThisIsPanel = new JLabel("Import File");
	//	jpImport.add(lblThisIsPanel);
		
		JLabel lblThisIsPane2 = new JLabel("PanelFilter");
		jpFilter.add(lblThisIsPane2);
		
		JLabel lblThisIsPane3 = new JLabel("PanelExport");
		jpExport.add(lblThisIsPane3);
		
		
		
		//Left Side-Buttons
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
		
		
		//Panels
		jpStatic.setLayout(new GridLayout(3, 1, 0, 0));
		jpStatic.add(jbtImport);
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
		
		
		
		//PanelImport
		jpImport.setLayout(new GridLayout(3, 1, 0, 0));
		jpImport.add(jpImportExcel);
		jpImport.add(jpImportTxt);
		jpImport.add(jpImportImg);
		//PanelExcelImport
		jpImportExcel.setLayout(null);
		lblImportExcel.setBounds(28, 34, 69, 15);
		jpImportExcel.add(lblImportExcel);
		jtfExcelPath.setBounds(107, 30, 167, 23);
		jpImportExcel.add(jtfExcelPath);
		jfcImportExcelFile.setFileFilter(filterExcel);
		jbtBrowseExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcImportExcelFile.showOpenDialog(jbtBrowseExcel);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					importExcelFile = jfcImportExcelFile.getSelectedFile();
					jtfExcelPath.setText(importExcelFile.getAbsolutePath());
				}
			}
		});
		jbtBrowseExcel.setBounds(286, 30, 69, 23);
		jpImportExcel.add(jbtBrowseExcel);
		
		//PanelTxtImport
		jpImportTxt.setLayout(null);
		lblImportTxt.setBounds(28, 34, 69, 15);
		jpImportTxt.add(lblImportTxt);
		jtfTxtPath.setBounds(107, 30, 167, 23);
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
		jbtBrowseTxt.setBounds(286, 30, 69, 23);
		jpImportTxt.add(jbtBrowseTxt);
		//PanelImgPath
		jfcImportImgFile.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		jpImportImg.setLayout(null);
		lblImportImg.setBounds(28, 34, 69, 15);
		jpImportImg.add(lblImportImg);
		jtfImgPath.setBounds(107, 30, 167, 23);
		jpImportImg.add(jtfImgPath);
	//	jfcImportTxtFile.setFileFilter(filterImg);
		jbtBrowseImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = jfcImportImgFile.showOpenDialog(jbtBrowseImg);			
				if(returnVal == JFileChooser.APPROVE_OPTION){
					importImgFile = jfcImportImgFile.getSelectedFile();
					jtfImgPath.setText(importImgFile.getAbsolutePath());
				}
			}
		});
		jbtBrowseImg.setBounds(286, 30, 69, 23);
		jpImportImg.add(jbtBrowseImg);
		
		
		//MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.WEST);
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		jpFilter.setVisible(false);
		jpFilter.setVisible(false);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);
		frame.setJMenuBar(jmb);
		
	}

}
