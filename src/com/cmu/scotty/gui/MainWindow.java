package com.cmu.scotty.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

<<<<<<< HEAD
import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants; //similar to the last one, nice 
=======
import org.eclipse.wb.swing.FocusTraversalOnArray;
>>>>>>> 1891ea21debce5b2f989ae6306e04f27364dd1d4

public class MainWindow {

	private JFrame frame;
	private JPanel jpStatic = new JPanel();
	private JPanel jpImport = new JPanel();
	private JPanel jpFilter = new JPanel();
	private JPanel jpExport = new JPanel();
	private JButton jbtImport;
	private JButton jbtFilter;
	private JButton jbtExport;
	

<<<<<<< HEAD
//	private FileNameExtensionFilter filterTxt = new FileNameExtensionFilter("txt");
	
	//Panel Import
	private JPanel jpImportExcel = new JPanel();
	private JPanel jpImportTxt = new JPanel();
	private JPanel jpImportImg = new JPanel(); 
	private JPanel jpImportNext = new JPanel();
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
	private JFileChooser jfcImportImgFile = new JFileChooser();
	//Panel Next Import
	private JButton jbtImportNext = new JButton("Next>>");
	
=======
>>>>>>> 1891ea21debce5b2f989ae6306e04f27364dd1d4
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		frame.setFont(new Font("Helvetica", Font.PLAIN, 12));
		frame.setForeground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//	TEST CODE
		JLabel lblThisIsPanel = new JLabel("Import File");
		jpImport.setBorder(null);
		jpImport.setBackground(Color.WHITE);
		jpImport.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jpImport.add(lblThisIsPanel);
		
		JLabel lblThisIsPane2 = new JLabel("PanelFilter");
		jpFilter.add(lblThisIsPane2);
		
		JLabel lblThisIsPane3 = new JLabel("PanelExport");
		jpExport.add(lblThisIsPane3);
		jpStatic.setBorder(null);
		jpStatic.setForeground(Color.WHITE);
		jpStatic.setBackground(Color.WHITE);

		// MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.NORTH);
		jbtExport = new JButton("Export");
		jbtExport.setBackground(Color.WHITE);
		jbtExport.setForeground(Color.DARK_GRAY);
		jbtExport.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		jbtExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(false);
				jpExport.setVisible(true);
				frame.getContentPane().add(jpExport, BorderLayout.CENTER);
			}
		});
		
		
		
		//Left Side-Buttons
		jbtImport = new JButton("Import");
		jbtImport.setForeground(Color.DARK_GRAY);
		jbtImport.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		jbtImport.setBackground(Color.WHITE);
		jbtImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(true);
				jpFilter.setVisible(false);
				jpExport.setVisible(false);
				frame.getContentPane().add(jpImport, BorderLayout.CENTER);
			}
		});
		jpStatic.setLayout(new GridLayout(1, 3, 0, 0));
		jpStatic.add(jbtImport);
		jbtFilter = new JButton("Filter");
		jbtFilter.setForeground(Color.DARK_GRAY);
		jbtFilter.setFont(new Font("Helvetica Neue", Font.PLAIN, 13));
		jbtFilter.setBackground(Color.WHITE);
		jbtFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(true);
				jpExport.setVisible(false);
				frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
			}
		});
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
<<<<<<< HEAD
		
		
		
		//PanelImport
		jpImport.setLayout(new GridLayout(4, 1, 0, 0));
		jpImport.add(jpImportExcel);
		jpImport.add(jpImportTxt);
		jpImport.add(jpImportImg);
		jpImport.add(jpImportNext);
		//PanelExcelImport
		jpImportExcel.setLayout(null);
		lblImportExcel.setBounds(28, 31, 69, 15);
		jpImportExcel.add(lblImportExcel);
		jtfExcelPath.setBounds(107, 27, 167, 23);
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
		jbtBrowseExcel.setBounds(286, 27, 69, 23);
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
		jpImportNext.setLayout(null);
		jpImportNext.setLayout(null);
		jbtImportNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		jbtImportNext.setBounds(286, 27, 69, 23);
		jpImportNext.add(jbtImportNext);
		
		//MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.WEST);
=======
>>>>>>> 1891ea21debce5b2f989ae6306e04f27364dd1d4
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		jpFilter.setVisible(false);
		jpFilter.setVisible(false);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);
		frame.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { frame.getContentPane(), jpStatic, jbtImport,
						jbtFilter, jbtExport, jpFilter, lblThisIsPane2,
						jpExport, lblThisIsPane3, jpImport, lblThisIsPanel }));
		
	}

}
