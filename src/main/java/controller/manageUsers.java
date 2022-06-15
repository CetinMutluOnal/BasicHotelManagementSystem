package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dal.dao.*;
import pojo.*;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class manageUsers extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JTextField txtEmail;
	private JTextField txtRole;
	private JTextField txtHotel;
	private JTable table;

	UserDAO users = new UserDAO();
	BaseDAO<userRole> roles = new userRoleDAO();
	BaseDAO<userHotel> hotels = new userHotelDAO();
	BaseDAO<Roles> roleTable = new RolesDAO();
	BaseDAO<Hotel> hotelTable = new HotelDAO();
	
	User user;
	userRole userRole;
	userHotel userHotel;
	Roles role;
	Hotel hotel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageUsers frame = new manageUsers();
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
	public manageUsers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("User Id : ");
		lblUserId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblUserId.setBounds(39, 38, 117, 15);
		contentPane.add(lblUserId);
		
		JLabel lblName = new JLabel("Username : ");
		lblName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblName.setBounds(24, 101, 117, 15);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPassword.setBounds(24, 164, 117, 15);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEmail.setBounds(303, 38, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblRole = new JLabel("Role :");
		lblRole.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRole.setBounds(303, 164, 70, 15);
		contentPane.add(lblRole);
		
		txtId = new JTextField();
		txtId.setBounds(131, 34, 142, 25);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(131, 97, 142, 25);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(131, 160, 142, 25);
		contentPane.add(pwdPassword);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(373, 34, 142, 25);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtRole = new JTextField();
		txtRole.setEditable(false);
		txtRole.setBounds(373, 160, 142, 25);
		contentPane.add(txtRole);
		txtRole.setColumns(10);
		txtRole.setText("2");
		
		JLabel lblHotel = new JLabel("Hotel :");
		lblHotel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotel.setBounds(303, 101, 70, 15);
		contentPane.add(lblHotel);
		
		txtHotel = new JTextField();
		txtHotel.setBounds(373, 96, 142, 25);
		contentPane.add(txtHotel);
		txtHotel.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(64, 210, 340, 38);
		contentPane.add(lblMessage);
		lblMessage.setText("");
		
		JButton btnAddUser = new JButton("add user");
		btnAddUser.setBackground(new Color(70, 130, 180));
		btnAddUser.setForeground(new Color(240, 255, 255));
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			String username = txtUsername.getText();
			String mail = txtEmail.getText();
			String password = pwdPassword.getText();
			int hotelId = Integer.parseInt(txtHotel.getText());
			int roleId=Integer.parseInt(txtRole.getText());
			
			user = new User(username,password,mail);
			users.insert(user);
			int id = users.findbyEmail(mail).getId();
			userRole = new userRole(id,roleId);
			roles.insert(userRole);
			userHotel=new userHotel(id,hotelId);
			hotels.insert(userHotel);
			
			
		
			
		}
		});
		btnAddUser.setBounds(579, 82, 109, 52);
		contentPane.add(btnAddUser);
		
		JButton btnUpdateUser = new JButton("update user");
		btnUpdateUser.setFont(new Font("Dialog", Font.BOLD, 10));
		btnUpdateUser.setBackground(new Color(70, 130, 180));
		btnUpdateUser.setForeground(new Color(240, 255, 255));
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				String username = txtUsername.getText();
				String mail = txtEmail.getText();
				String password = pwdPassword.getText();
				int id = Integer.parseInt(txtId.getText());
				
				user = new User(id,username,password,mail);
				users.update(user);

				
				
				
				
			}
		});
		btnUpdateUser.setBounds(579, 210, 109, 52);
		contentPane.add(btnUpdateUser);
		
		JButton btnDeleteUser = new JButton("delete user");
		btnDeleteUser.setFont(new Font("Dialog", Font.BOLD, 11));
		btnDeleteUser.setBackground(new Color(70, 130, 180));
		btnDeleteUser.setForeground(new Color(240, 255, 255));
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessage.setText("");
				int id =(int) table.getValueAt(table.getSelectedRow(),0);
				
				if(roles.delete(id) && hotels.delete(id) && users.delete(id)  ) {
					lblMessage.setText("Deleted Successfully");
				}
				else {
					lblMessage.setText("Failed to Delete");
				}
				
				
			}
		});
		btnDeleteUser.setBounds(577, 146, 111, 52);
		contentPane.add(btnDeleteUser);
		
		JButton btnShowUsers = new JButton("Show Users");
		btnShowUsers.setFont(new Font("Dialog", Font.BOLD, 11));
		btnShowUsers.setBackground(new Color(70, 130, 180));
		btnShowUsers.setForeground(new Color(240, 255, 255));
		btnShowUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				String hotelName=null;
				
				Iterator<User> itr = users.findAll().iterator();
				
				while(itr.hasNext()) {
				
				user = itr.next();
				if((roles.findById(user.getId())).getRoleID() == 2) {
				userRole= roles.findById(user.getId());
				String rolename = (roleTable.findById(userRole.getRoleID())).getRoleName();
				
					userHotel = hotels.findById(user.getId());
					hotelName= (hotelTable.findById(userHotel.getHotelId())).getName();
					Object[] add = {user.getId(),user.getUsername(),user.getPassword(),user.getMail(),rolename,hotelName};
					
					model.addRow(add);
				}
				
				}	
			}
		});
		btnShowUsers.setBounds(579, 12, 109, 60);
		contentPane.add(btnShowUsers);
		
		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{
					"User ID", "Username", "Password", "Email", "Role", "Hotel"
				}
			},
			new String[] {
				"User ID", "Username", "Password", "Email", "Role", "Hotel"
			}
		));
		table.setBounds(69, 279, 587, 202);
		contentPane.add(table);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(70, 130, 180));
		btnClear.setForeground(new Color(240, 255, 255));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model =  (DefaultTableModel) table.getModel();
				model.setRowCount(1);
				
				txtId.setText("");
				txtUsername.setText("");
				pwdPassword.setText("");
				txtEmail.setText("");
				txtRole.setText("2");
				txtRole.setEditable(false);
				txtHotel.setText("");
				txtHotel.setEditable(true);
				
			}
		});
		btnClear.setBounds(454, 210, 117, 25);
		contentPane.add(btnClear);
		
		JButton btnMove = new JButton("Move");
		btnMove.setBackground(new Color(70, 130, 180));
		btnMove.setForeground(new Color(240, 255, 255));
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				txtHotel.setEditable(false);
				txtRole.setEditable(false);
				
				txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtUsername.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				pwdPassword.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtEmail.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtRole.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txtHotel.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				
				
				
			}
		});
		btnMove.setBounds(454, 237, 117, 25);
		contentPane.add(btnMove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(240, 255, 255));
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manageUsers.this.dispose();
				new adminPanel().setVisible(true);
			}
		});
		btnBack.setBounds(24, 1, 117, 25);
		contentPane.add(btnBack);
		
		
	}
}
