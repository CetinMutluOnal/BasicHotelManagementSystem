package controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dal.dao.*;
import pojo.*;
import java.awt.Color;

public class CustomerLogin extends JFrame {
	private JPasswordField pwdPassword;
	
	private JTextField txtEmail;

	private PreparedStatement statement = null;
	
	private ResultSet result= null;
	
	private Connection con = DBConnection.getConnection();
	
	CustomerDAO customers = new CustomerDAO();
	
	Customer customer;
	
	listener listener;
	
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLogin frame = new CustomerLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
public boolean customerCheck( String mail)  {
		
		String findByEmailQuery = ("SELECT * from musteri where mail = ? ");
		
		try {
			
			statement=con.prepareStatement(findByEmailQuery);
			statement.setString(1, mail);
			result= statement.executeQuery();
		
			if(result.next()) 
				
				return true;
			
		}	catch(SQLException e ){
			e.printStackTrace();
		}
		
		
		return false;
		
		
	}
	
	/**
	 * Create the frame.
	 */
	public CustomerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(230,230,250));
		setBounds(100, 100, 700, 470);
		
		JLabel lblKullancAd = new JLabel("Username");
		lblKullancAd.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullancAd.setFont(new Font("Dialog", Font.BOLD, 16));
		lblKullancAd.setBounds(126, 53, 134, 59);
		
		JLabel lblifre = new JLabel("Password");
		lblifre.setHorizontalAlignment(SwingConstants.CENTER);
		lblifre.setFont(new Font("Dialog", Font.BOLD, 16));
		lblifre.setBounds(102, 124, 154, 19);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setBounds(29, 164, 631, 30);
		getContentPane().add(lblMessage);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(294, 129, 178, 30);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(294, 69, 178, 30);
		txtEmail.setColumns(10);
		
		JButton btnGiriYap = new JButton("Login");
		btnGiriYap.setBounds(294, 219, 138, 45);
		 btnGiriYap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String kullaniciAdi= txtEmail.getText();
				
				String sifre= new String(pwdPassword.getPassword());

				if(customerCheck(kullaniciAdi)) {
					
					customer = customers.findbyEmail(kullaniciAdi);
					
					if(customer.getPassword().equals(sifre)){
						
						listener.setCustomerId(customer.getId());
						
						CustomerLogin.this.dispose();
						new CustomerReservation().setVisible(true);
						
					}
					else {
						lblMessage.setText("Wrong Password");
					}
					
				}
				else {
					lblMessage.setText("Customer Does not exist !");
				}

				
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(lblifre);
		getContentPane().add(lblKullancAd);
		getContentPane().add(txtEmail);
		getContentPane().add(pwdPassword);
		getContentPane().add(btnGiriYap);
		
		JButton btnSignUpFor = new JButton("Sign Up");
		btnSignUpFor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CustomerLogin.this.dispose();
				
				new CustomerSignUp().setVisible(true);
				
			}
		});
		btnSignUpFor.setBounds(245, 320, 227, 58);
		getContentPane().add(btnSignUpFor);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CustomerLogin.this.dispose();
				
				new Login().setVisible(true);
			}
		});
		btnBack.setBounds(29, 16, 117, 25);
		getContentPane().add(btnBack);
		
	
		
		
	}
}
