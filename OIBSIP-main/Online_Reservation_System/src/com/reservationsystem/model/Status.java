package com.reservationsystem.model;

import java.awt.EventQueue;
import java.awt.event.*;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Status extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField PNR;

	/**
	 * Launch the application.
	 */
	/**public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Status frame = new Status();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Status(String uid) {
		super("Reservation Status");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel lblFrom = new JLabel("From:");
		
		JLabel source = new JLabel("source");
		
		JLabel lblTo = new JLabel("To:");
		
		JLabel destination = new JLabel("destination");
		
		JLabel lblPnr = new JLabel("PNR:");
		
		JLabel lblPassengerDetails = new JLabel("Passenger Details:");
		
		JLabel lblName = new JLabel("Name:");
		
		JLabel lblDate = new JLabel("Phone no:");
		
		JLabel lblGender = new JLabel("Gender:");
		
		JLabel lblAddress = new JLabel("Address:");
		
		JLabel lblTrainName = new JLabel("Train name:");
		
		JLabel lblResevationStatus = new JLabel("Resevation Status:");
		
		JLabel name = new JLabel("passenger");
		
		JLabel phone = new JLabel("phone");
		
		JLabel gender = new JLabel("gender");
		
		JLabel address = new JLabel("address");
		
		JLabel train = new JLabel("train");
		
		JLabel status = new JLabel("status");
		
		JButton btnCancelTicket = new JButton("Cancel Ticket");
		
		JLabel lblBoardingOn = new JLabel("Boarding on:");
		
		JLabel doj = new JLabel("bording details");
		
		PNR = new JTextField();
		PNR.setColumns(10);
		
		JButton btnGo = new JButton("Go");
		
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int pnr=Integer.parseInt(PNR.getText());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Train","root","");
				    PreparedStatement st=con.prepareStatement("select * from reservation_log where reservation_log.PNR=?");
					st.setInt(1, pnr);
				    ResultSet rs=st.executeQuery();
				    if(rs.next()) {
				    	train.setText(rs.getString(4)+" / "+rs.getInt(3));
				    	source.setText(rs.getString(5));
				    	destination.setText(rs.getString(6));
				    	name.setText(rs.getString(8));
				    	doj.setText(rs.getString(7));
				    	phone.setText(rs.getString(9));
				    	gender.setText(rs.getString(10));
				    	address.setText(rs.getString(12));
				    	status.setText(rs.getString(13));
				    }
				    else {
				    	JOptionPane.showInternalMessageDialog(contentPane,"invalid PNR");
				    }
				    con.close();
					}
					catch(Exception ex) {JOptionPane.showInternalMessageDialog(contentPane,ex); }
			}
		});
		
		btnCancelTicket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cancel cancel=new Cancel(uid);
				cancel.setVisible(true);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPassengerDetails))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblResevationStatus)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(status))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblName))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblDate))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblGender))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblAddress))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblTrainName)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(train)
								.addComponent(address)
								.addComponent(gender)
								.addComponent(phone)
								.addComponent(name)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCancelTicket)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblPnr)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblFrom)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(source)))
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(112)
											.addComponent(lblTo)
											.addGap(18)
											.addComponent(destination))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(PNR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnGo))))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBoardingOn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(doj)))
					.addContainerGap(346, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFrom)
						.addComponent(source)
						.addComponent(lblTo)
						.addComponent(destination))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPnr)
						.addComponent(PNR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGo))
					.addGap(8)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBoardingOn)
						.addComponent(doj))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPassengerDetails)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(name))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate)
						.addComponent(phone))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender)
						.addComponent(gender))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress)
						.addComponent(address))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTrainName)
						.addComponent(train))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResevationStatus)
						.addComponent(status))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelTicket)
					.addContainerGap(263, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
