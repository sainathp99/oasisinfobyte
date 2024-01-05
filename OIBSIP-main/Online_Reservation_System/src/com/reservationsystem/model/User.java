package com.reservationsystem.model;
import java.awt.EventQueue;
import com.reservationsystem.service.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.border.BevelBorder;

public class User extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel lblWelcomeToRail;

	/**
	 * Create the frame.
	 */
	public User() {
		super("Ticket Reservation Portal");
		setFont(new Font("Liberation Sans Narrow", Font.BOLD, 15));
		setBackground(new Color(51, 204, 255));
		setForeground(new Color(0, 0, 0));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 153));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(51, 204, 255), new Color(0, 153, 0), new Color(51, 204, 204), new Color(0, 153, 0)));

		setContentPane(contentPane);
		
		JLabel lblUesrName = new JLabel("Uesr Name:");
		
		textField = new JTextField();
		textField.setBackground(new Color(153, 255, 255));
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(153, 255, 255));
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Dyuthi", Font.BOLD, 14));
		btnLogin.setBackground(new Color(102, 255, 255));
		
		lblWelcomeToRail = new JLabel("Welcome to Rail Seva");
		lblWelcomeToRail.setFont(new Font("Dialog", Font.BOLD, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(113)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblPassword)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblUesrName)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(171)
							.addComponent(btnLogin))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(86)
							.addComponent(lblWelcomeToRail)))
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblWelcomeToRail, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUesrName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnLogin)
					.addContainerGap(93, Short.MAX_VALUE))
		);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if(LoginService.authenticateUser(textField.getText(),new String(passwordField.getPassword()))) {
					Home home=new Home(textField.getText());
					dispose();
					home.setVisible(true);
				}
				else {
					JOptionPane.showInternalMessageDialog(contentPane,"Invalid username/Password");
				}
				}
				catch(Exception ex) {
					JOptionPane.showInternalMessageDialog(contentPane,ex);
				}
					
			}
		});
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	try {
        				if(LoginService.authenticateUser(textField.getText(),new String(passwordField.getPassword()))) {
        					Home home=new Home(textField.getText());
        					dispose();
        					home.setVisible(true);
        				}
        				else {
        					JOptionPane.showInternalMessageDialog(contentPane,"Invalid username/Password");
        				}
        				}
        				catch(Exception ex) {
        					JOptionPane.showInternalMessageDialog(contentPane,ex);
        				}
                }
            }
        });
	}
}
