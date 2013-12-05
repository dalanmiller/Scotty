package com.cmu.scotty.gui;

import com.cmu.scotty.model.*;
import com.cmu.scotty.controller.*;
import com.cmu.scotty.persistence.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
import java.util.ArrayList;

import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.SwingConstants;
import javax.swing.JList;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component; 



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
	private JMenuBar jmb = new JMenuBar();
	
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
		private JLabel lblImportTxt = new JLabel("Txt file");
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
		
//		JScrollPane listScroller = new JScrollPane(list);
//        listScroller.setPreferredSize(new Dimension(250, 80));
//        listScroller.setAlignmentX(LEFT_ALIGNMENT);
	
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//menu
		JMenu jmSkin = new JMenu("Skins");
		jmb.add(jmSkin);
		
		JLabel lblThisIsPane3 = new JLabel("PanelExport");
		jpExport.add(lblThisIsPane3);
		
		
		
		//Top Side-Buttons
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
		
		jbtFilter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jpImport.setVisible(false);
				jpExport.setVisible(false);
				jpFilter.setVisible(true);
			}
		});
		
		jbtFilter.setEnabled(false);
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
		jpStatic.setLayout(new GridLayout(1, 3, 0, 0));
	//	jpStatic.setLayout(null);
	//	jpStatic.setSize(frame.getWidth(),1000000);
		jpStatic.add(jbtImport);
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
		jbtImport.setEnabled(true);
		jbtImport.setSelected(true);
		jbtExport.setEnabled(false);
		
		//PanelImport
		jpImport.setLayout(new GridLayout(4, 1, 0, 0));
		jpImport.add(jpImportExcel);
		jpImport.add(jpImportTxt);
		jpImport.add(jpImportImg);
		jpImport.add(jpImportNext);
		jpImportExcel.setLayout(null);
		lblImportExcel.setBounds(32, 0, 66, 59);
		jpImportExcel.add(lblImportExcel);
		jtfExcelPath.setBounds(122, 18, 204, 23);
		jpImportExcel.add(jtfExcelPath);
		jfcImportExcelFile.setFileFilter(filterExcel);
		jbtBrowseExcel.setBounds(352, 18, 69, 23);
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
		lblImportTxt.setBounds(31, 0, 69, 59);
		jpImportTxt.add(lblImportTxt);
		jtfTxtPath.setBounds(122, 18, 204, 23);
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
		jbtBrowseTxt.setBounds(352, 18, 69, 23);
		jpImportTxt.add(jbtBrowseTxt);
		//PanelImgPath
		jfcImportImgFile.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
		jpImportImg.setLayout(null);
		lblImportImg.setBounds(24, 0, 82, 62);
		jpImportImg.add(lblImportImg);
		jtfImgPath.setBounds(122, 20, 204, 23);
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
		jbtBrowseImg.setBounds(352, 20, 69, 23);
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
		
		//Panel Filter
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
			dlm.addElement(s.toString());
		}
		
		JList list = new JList(dlm);

		
		jbtImportNext.setBounds(352, 10, 69, 23);
		//PanelNextImport
		jpImportNext.add(jbtImportNext);
		
		//MainWindow layout
		frame.getContentPane().add(jpStatic, BorderLayout.NORTH);
		
		jpFilter.add(list);
		frame.getContentPane().add(jpExport, BorderLayout.CENTER);
		frame.getContentPane().add(jpImport, BorderLayout.CENTER);
		frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
		button.setAlignmentX(Component.RIGHT_ALIGNMENT);
		button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		jpFilter.add(button);
//		frame.setJMenuBar(jmb);
		frame.setLocationRelativeTo(null);
		ImageIcon ico = new ImageIcon("img/scottie-dog.jpg");
		frame.setIconImage(ico.getImage());
	}

}