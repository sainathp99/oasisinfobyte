package com.reservationsystem.model;

import java.awt.EventQueue;
import java.util.*;
import java.sql.*;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.reservationsystem.service.ReservationService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.Date;
import javax.swing.border.BevelBorder;


public class Reservation extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JTextField passenger;
	private static JTextField phone;
	private static JTextField trainno;
	private static JTextField trainName;
	private static JTextField source;
	private static JTextField destination;
	private JTextField dob;
	private JTextField doj;

	private static String getSelectedGender(ButtonGroup group) {
        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

	public Reservation(String uid) {
		super("Reservation Portal");
		setResizable(false);
		setForeground(new Color(0, 255, 204));
		setBackground(new Color(0, 204, 204));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(670,484);
		setLocationRelativeTo(null);
		setBounds(100, 100, 736, 581);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 255, 153));
		contentPane.setForeground(new Color(51, 255, 153));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 204, 204), new Color(0, 255, 51), new Color(0, 51, 255), new Color(0, 204, 102)));

		setContentPane(contentPane);
		
		JLabel lblPassenger = new JLabel("Passenger:");
		
		JLabel lblNewLabel = new JLabel("Gender:");
		
		JLabel lblPhoneNo = new JLabel("Phone no:");
		
		JLabel lblBearthPreference = new JLabel("Date of Journey:");
		
		JLabel lblTrainNo = new JLabel("Train no:");
		
		JLabel lblTrainName = new JLabel("Train Name:");
		
		JLabel lblNewLabel_1 = new JLabel("Source:");
		
		JLabel lblDestination = new JLabel("Destination:");
		
		JLabel lblAddress = new JLabel("Address:");
		
		passenger = new JTextField();
		passenger.setColumns(10);
		
		
		phone = new JTextField();
		phone.setColumns(11);
		
		
		trainno = new JTextField();
		trainno.setColumns(10);

		
		trainName = new JTextField();
		trainName.setEditable(false);
		trainName.setColumns(15);
		trainName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Train","root","");
			    int tno=Integer.parseInt(trainno.getText());
			    PreparedStatement st=con.prepareStatement("select train_name,source,destination from trains where trains.train_no=?");
				st.setInt(1, tno);
			    ResultSet rs=st.executeQuery();
			    if(rs.next()) {
			    	trainName.setText(rs.getString(1));
			    	source.setText(rs.getString(2));
			    	destination.setText(rs.getString(3));
			    }
			    else {
			    	JOptionPane.showInternalMessageDialog(contentPane,"Invalid Train number");
			    }
			    con.close();
				}
				catch(Exception ex) {JOptionPane.showInternalMessageDialog(contentPane,ex); }
			}
		});
		
	
		JRadioButton rdbtnM = new JRadioButton("M");
		
		JRadioButton rdbtnF = new JRadioButton("F");
		ButtonGroup gender=new ButtonGroup();
		gender.add(rdbtnM);
		gender.add(rdbtnF);
		
		JTextArea address = new JTextArea();
		address.setColumns(30);
		
		JButton btnBook = new JButton("Book");
		
		btnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pphone=phone.getText();
				int tno=Integer.parseInt(trainno.getText());
				String pname=passenger.getText();
				String DOB=dob.getText();
				//isValidDate(DOB);
				String DOJ=doj.getText();
				//dilipisValidDate(DOJ);
				String Add=address.getText();
				String sex=getSelectedGender(gender);
				
				try {
				System.out.println("hello");
				ReservationService.makeReservation(pphone,tno,sex,uid,pname,DOB,Add,DOJ);
				JOptionPane.showInternalMessageDialog(contentPane, "booking Success");
				}
				catch(Exception ex) {}
			}
		});
		
		ImageIcon homeIcon = new ImageIcon("/home/dilipkumar/eclipse-workspace/Online_Reservation_System/src/icons/icons8-home-48.png");
		
		JLabel lblHome = new JLabel(homeIcon);
		lblHome.setForeground(new Color(0, 0, 153));
		lblHome.setBackground(new Color(0, 0, 0));
		lblHome.setToolTipText("Home");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		
		source = new JTextField();
		source.setEditable(false);
		source.setColumns(10);
		
		destination = new JTextField();
		destination.setEditable(false);
		destination.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date of Birth:");
		
		dob = new JTextField();
		dob.setToolTipText("YYYY-MM-DD");
		dob.setColumns(10);
		
		doj = new JTextField();
		doj.setToolTipText("YYYY-MM-DD");
		doj.setColumns(10);
		
		JLabel lblFillPassengerDetails = new JLabel("Fill Passenger Details.");
		lblFillPassengerDetails.setFont(new Font("Dialog", Font.BOLD, 22));
		lblFillPassengerDetails.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(72)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassenger)
								.addComponent(lblPhoneNo)
								.addComponent(lblNewLabel_2)
								.addComponent(lblTrainNo)
								.addComponent(lblNewLabel_1)
								.addComponent(lblAddress))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(source, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(trainno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(passenger, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(37)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addComponent(lblNewLabel)
														.addComponent(lblBearthPreference)
														.addComponent(lblTrainName))
													.addGap(2)
													.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPane.createSequentialGroup()
															.addComponent(rdbtnM)
															.addGap(18)
															.addComponent(rdbtnF))
														.addComponent(destination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(trainName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(doj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
												.addComponent(lblDestination))
											.addGap(76))
										.addComponent(btnBook, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
								.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblHome)
							.addGap(18)
							.addComponent(lblFillPassengerDetails, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(27)
									.addComponent(lblFillPassengerDetails, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblHome)))
							.addGap(39)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassenger)
								.addComponent(passenger, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(rdbtnM)
								.addComponent(rdbtnF))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPhoneNo)
								.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBearthPreference)
								.addComponent(doj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblTrainNo)
									.addComponent(trainno, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblTrainName))
								.addComponent(trainName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_2))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(source, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDestination)
										.addComponent(destination, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(33)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(21)
									.addComponent(btnBook, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAddress)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(230)
							.addComponent(dob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(226, Short.MAX_VALUE))
		);
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Home home=new Home(uid);
				home.setVisible(true);
			}
		});

		contentPane.setLayout(gl_contentPane);
	}
}
