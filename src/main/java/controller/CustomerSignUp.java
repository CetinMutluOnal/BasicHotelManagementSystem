package controller;

import pojo.*;
import dal.dao.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class CustomerSignUp extends JFrame {

	BaseDAO<Customer> customers = new CustomerDAO();
	
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private JTextField txtPhoneNo;
	private JTextField txtIdNo;
	private JTextField txtAdress;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSignUp frame = new CustomerSignUp();
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
	public CustomerSignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHeader = new JLabel("SIGN UP");
		lblHeader.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(261, -17, 177, 71);
		contentPane.add(lblHeader);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(92, 68, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(92, 107, 122, 15);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("E-Mail :");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setBounds(92, 148, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPhoneNo = new JLabel("Phone No :");
		lblPhoneNo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPhoneNo.setBounds(92, 197, 111, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblIdentityNo = new JLabel("TC No :");
		lblIdentityNo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIdentityNo.setBounds(92, 249, 70, 15);
		contentPane.add(lblIdentityNo);
		
		JLabel lblAdress = new JLabel("Adress :");
		lblAdress.setFont(new Font("Dialog", Font.BOLD, 16));
		lblAdress.setBounds(92, 304, 122, 15);
		contentPane.add(lblAdress);
		
		JLabel lblMessage = new JLabel("message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setBounds(129, 364, 450, 33);
		contentPane.add(lblMessage);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(252, 50, 203, 33);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(252, 140, 203, 33);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(252, 95, 203, 33);
		contentPane.add(pwdPassword);
		
		txtPhoneNo = new JTextField();
		txtPhoneNo.setBounds(252, 188, 203, 35);
		contentPane.add(txtPhoneNo);
		txtPhoneNo.setColumns(10);
		
		txtIdNo = new JTextField();
		txtIdNo.setBounds(252, 238, 203, 40);
		contentPane.add(txtIdNo);
		txtIdNo.setColumns(10);
		
		txtAdress = new JTextField();
		txtAdress.setBounds(252, 295, 203, 35);
		contentPane.add(txtAdress);
		txtAdress.setColumns(10);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String name = txtUsername.getText();
				String password = new String (pwdPassword.getPassword());
				String email= txtEmail.getText();
				String phoneNo= txtPhoneNo.getText();
				String IdNo= txtIdNo.getText();
				String adress= txtAdress.getText();

				Customer newCustomer = new Customer(name,password,email,phoneNo,IdNo,adress);
				
				if(customers.insert(newCustomer)) {
					lblMessage.setText("Sign Up Successfull");
				}
				else {
					lblMessage.setText("Sign Up Failed");
				}
				
				
			}
		});
		btnSignUp.setBounds(515, 113, 152, 86);
		contentPane.add(btnSignUp);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				CustomerSignUp.this.dispose();
				
				new CustomerLogin().setVisible(true);
			}
		});
		btnBack.setBounds(26, 7, 117, 25);
		contentPane.add(btnBack);
		
	
	}
}
