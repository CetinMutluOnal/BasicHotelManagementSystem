package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import dal.dao.DBConnection;
import dal.dao.UserDAO;
import pojo.User;

public class UserLogin extends JFrame  implements IListener{

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private PreparedStatement statement = null;
	
	private ResultSet result= null;
	
	private Connection con = DBConnection.getConnection();
	
	UserDAO users = new UserDAO();

	User user ;
	
		void newFrame(User user) {
			new userRoomManagement();
		}
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
public boolean userCheck( String mail)  {
		
		String findByEmailQuery = ("SELECT * from kullanici where email = ? ");
		
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

		@Override
		public User userListener(User user) {
			
			String name = txtEmail.getText();
			
			user=users.findbyEmail(name);
			
			return user;
			
		
			}
	
	/**
	 * Create the frame.
	 */
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(127, 102, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(113, 161, 124, 15);
		contentPane.add(lblPassword);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(40, 197, 647, 27);
		contentPane.add(lblMessage);
		lblMessage.setText("");
		
		
		txtEmail = new JTextField();
		txtEmail.setBounds(282, 97, 165, 27);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(282, 156, 165, 27);
		contentPane.add(pwdPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String kullaniciAdi= txtEmail.getText();
				
				String sifre= new String(pwdPassword.getPassword());

				if(userCheck(kullaniciAdi)) {
					
					user = users.findbyEmail(kullaniciAdi);
					
					if(user.getPassword().equals(sifre)){
						
						if(users.findRole(user.getId()).getRoleID() == 1) {
							
							
							
							UserLogin.this.setVisible(false);
							new adminPanel().setVisible(true);
							
						}
						else {
							listener.setId(user.getId());
							UserLogin.this.setVisible(false);
							new userRoomManagement().setVisible(true);
						}
						
					}
					else {
						lblMessage.setText("Wrong Password");
					}
					
				}
				else {
					lblMessage.setText("User Does not exist !");
				}

			}
		});
		btnLogin.setBounds(242, 265, 202, 55);
		contentPane.add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign UP");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserLogin.this.dispose();
				
				new UserSignUp().setVisible(true);
			}
		});
		btnSignUp.setBounds(262, 367, 165, 60);
		contentPane.add(btnSignUp);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				UserLogin.this.dispose();
				
				new Login().setVisible(true);
			}
		});
		btnBack.setBounds(24, 12, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblAdministratorSystem = new JLabel("Administrator & System User Login Panel");
		lblAdministratorSystem.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAdministratorSystem.setBounds(113, 37, 522, 48);
		contentPane.add(lblAdministratorSystem);
	}

	

	

}
