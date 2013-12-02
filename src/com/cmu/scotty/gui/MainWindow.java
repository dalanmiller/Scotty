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

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class MainWindow {

	private JFrame frame;
	private JPanel jpStatic = new JPanel();
	private JPanel jpImport = new JPanel();
	private JPanel jpFilter = new JPanel();
	private JPanel jpExport = new JPanel();
	private JButton jbtImport;
	private JButton jbtFilter;
	private JButton jbtExport;
	

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
