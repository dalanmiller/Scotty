package com.cmu.scotty.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//menu
		JMenu jmSkin = new JMenu("Skins");
		jmb.add(jmSkin);
		
		
		//	TEST CODE
		JLabel lblThisIsPanel = new JLabel("Import File");
		jpImport.add(lblThisIsPanel);
		
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
				frame.getContentPane().add(jpImport, BorderLayout.CENTER);
			}
		});
		jbtFilter = new JButton("Filter");
		jbtFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(true);
				jpExport.setVisible(false);
				frame.getContentPane().add(jpFilter, BorderLayout.CENTER);
			}
		});
		jbtExport = new JButton("Export");
		jbtExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpImport.setVisible(false);
				jpFilter.setVisible(false);
				jpExport.setVisible(true);
				frame.getContentPane().add(jpExport, BorderLayout.CENTER);
			}
		});
		
		
		//Panels
		jpStatic.setLayout(new GridLayout(3, 1, 0, 0));
		jpStatic.add(jbtImport);
		jpStatic.add(jbtFilter);
		jpStatic.add(jbtExport);
		
		
	
		
		
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
