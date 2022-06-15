package controller;
import java.awt.EventQueue;
import dal.dao.*;
import pojo.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserSignUp extends JFrame {

	private JPanel contanePane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	
	BaseDAO<User> users = new UserDAO();
	
	User user;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignUp frame = new UserSignUp();
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
	public UserSignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contanePane = new JPanel();
		contanePane.setBackground(new Color(230, 230, 250));
		contanePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contanePane);
		contanePane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUsername.setBounds(100, 128, 108, 15);
		contanePane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(100, 178, 119, 15);
		contanePane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(100, 229, 70, 15);
		contanePane.add(lblEmail);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(275, 117, 181, 28);
		contanePane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(275, 218, 181, 28);
		contanePane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(275, 167, 181, 28);
		contanePane.add(pwdPassword);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setBounds(141, 286, 439, 28);
		contanePane.add(lblMessage);
		
		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(txtUsername.getText().isEmpty() || pwdPassword.getText().isEmpty() || txtEmail.getText().isEmpty()) {
						
					lblMessage.setText("Values cannot be empty!");
					
				}
				else {
					
				
				
				String name= txtUsername.getText();
				String password = pwdPassword.getText();
				String email = txtEmail.getText();
				
				user= new User(name,password,email);
				
				if(users.insert(user)) {
					lblMessage.setText("Signed Up Successfully!");
				}
				else {
					lblMessage.setText("Sign Up Failed!");
				}
				
				}
				
				
				
				
			}
		});
		btnSignup.setBounds(275, 382, 142, 44);
		contanePane.add(btnSignup);
		
		JLabel lblSignup = new JLabel("SignUp");
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setFont(new Font("Dialog", Font.BOLD, 20));
		lblSignup.setBounds(246, 12, 181, 44);
		contanePane.add(lblSignup);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserSignUp.this.dispose();
				
				new UserLogin().setVisible(true);
			}
		});
		btnBack.setBounds(34, 12, 117, 25);
		contanePane.add(btnBack);
	}
}
