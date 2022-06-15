package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.exceptions.DataTruncationException;

import dal.dao.BaseDAO;
import dal.dao.CustomerDAO;
import pojo.Customer;

public class manageCustomers extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private JTextField txtPhone;
	private JTextField txtIdno;
	private JTextField txtAdress;
	private JTable table;
	
	BaseDAO<Customer> customers = new CustomerDAO();
	Customer customer;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageCustomers frame = new manageCustomers();
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
	public manageCustomers() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 531);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageCustomer = new JLabel("Manage Customer");
		lblManageCustomer.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblManageCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageCustomer.setBounds(296, 0, 182, 25);
		contentPane.add(lblManageCustomer);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(46, 78, 70, 15);
		contentPane.add(lblName);
		
		JLabel lblId = new JLabel("ID :");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(36, 51, 70, 15);
		contentPane.add(lblId);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(12, 105, 111, 15);
		contentPane.add(lblPassword);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(46, 132, 70, 15);
		contentPane.add(lblEmail);
		
		JLabel lblPhoneNo = new JLabel("Phone No : ");
		lblPhoneNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNo.setBounds(358, 37, 111, 15);
		contentPane.add(lblPhoneNo);
		
		JLabel lblIdentityNo = new JLabel("Identity No:");
		lblIdentityNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdentityNo.setBounds(358, 64, 111, 15);
		contentPane.add(lblIdentityNo);
		
		JLabel lblAdress = new JLabel("Adress :");
		lblAdress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdress.setBounds(368, 94, 70, 15);
		contentPane.add(lblAdress);
		
		txtId = new JTextField();
		txtId.setBounds(124, 47, 201, 19);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(124, 76, 201, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(124, 132, 201, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(124, 103, 199, 19);
		contentPane.add(pwdPassword);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(487, 35, 167, 19);
		contentPane.add(txtPhone);
		txtPhone.setColumns(10);
		
		txtIdno = new JTextField();
		txtIdno.setBounds(487, 62, 167, 19);
		contentPane.add(txtIdno);
		txtIdno.setColumns(10);
		
		txtAdress = new JTextField();
		txtAdress.setBounds(487, 92, 172, 19);
		contentPane.add(txtAdress);
		txtAdress.setColumns(10);
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(65, 233, 373, 39);
		contentPane.add(lblMessage);
		
		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtName.getText().trim().equals("") || pwdPassword.getText().trim().equals("") || txtEmail.getText().trim().equals("") || txtPhone.getText().trim().equals("") || txtIdno.getText().trim().equals("") || txtAdress.getText().trim().equals("")) {
					lblMessage.setText("Values can not be empty !");
				}
				else {
					
				
				
				String name = txtName.getText();
				String password = pwdPassword.getText();
				String email = txtEmail.getText();
				String phoneno= txtPhone.getText();
				String idNo = txtPhone.getText();
				String adress = txtAdress.getText();
				
				customer = new Customer(name,password,email,phoneno,idNo,adress);
				
				if(customers.insert(customer)) {
					lblMessage.setText("Added successfully");
				}
				else {
					lblMessage.setText("Failed to add Customer");
				}
				}
				
				
				
			}
		});
		btnAddCustomer.setBounds(31, 174, 167, 61);
		contentPane.add(btnAddCustomer);
		
		JButton btnShowCustomers = new JButton("Show Customers");
		btnShowCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				Iterator<Customer> itr = customers.findAll().iterator();
				
				while(itr.hasNext()) {
					customer= itr.next();
					
					Object [] add = {customer.getId(),customer.getName(),customer.getPassword(),customer.getMail(),customer.getPhoneNo(),customer.getIdNo(),customer.getAdress()};
					
					model.addRow(add);

					
				}
				
				
				
			}
		});
		btnShowCustomers.setBounds(210, 174, 153, 61);
		contentPane.add(btnShowCustomers);
		
		JButton btnDeleteCustomers = new JButton("Delete Customer");
		btnDeleteCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = (int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				
				if(customers.delete(id)) {
					lblMessage.setText("Deleted Successfully.");
					
				}
				else {
					lblMessage.setText("Delete customer failed.");
				}
			}
		});
		btnDeleteCustomers.setBounds(385, 174, 153, 61);
		contentPane.add(btnDeleteCustomers);
		
		JButton btnUpdateCustomer = new JButton("Update Customer");
		btnUpdateCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(txtName.getText().trim().equals("") || pwdPassword.getText().trim().equals("") || txtEmail.getText().trim().equals("") || txtPhone.getText().trim().equals("") || txtIdno.getText().trim().equals("") || txtAdress.getText().trim().equals("")) {
					lblMessage.setText("Values can not be empty !");
				}
				else {
					
				try {
					
				
					
				int id = Integer.parseInt(txtId.getText());
				String name = txtName.getText();
				String password = pwdPassword.getText();
				String email = txtEmail.getText();
				String phoneno= txtPhone.getText();
				String idNo = txtPhone.getText();
				String adress = txtAdress.getText();
				
				customer = new Customer(id,name,password,email,phoneno,idNo,adress);
				
				if(customers.update(customer)) {
					lblMessage.setText("Updated Succesfully");
				}
				else {
					lblMessage.setText("Customer update failed.");
				}
				
				} catch(DataTruncationException e ) {
					e.printStackTrace();
				}	catch(NumberFormatException e ) {
					e.printStackTrace();
				}
				}
				
				
				
				
				
			}
		});
		btnUpdateCustomer.setBounds(567, 174, 153, 61);
		contentPane.add(btnUpdateCustomer);
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtName.setText(table.getValueAt(table.getSelectedRow(),1).toString());
				pwdPassword.setText(table.getValueAt(table.getSelectedRow(),2).toString());
				txtEmail.setText(table.getValueAt(table.getSelectedRow(),3).toString());
				txtPhone.setText(table.getValueAt(table.getSelectedRow(),4).toString());
				txtIdno.setText(table.getValueAt(table.getSelectedRow(),5).toString());
				txtAdress.setText(table.getValueAt(table.getSelectedRow(), 6).toString());
				
				
				
				
				
			}
		});
		btnMove.setBounds(461, 127, 117, 25);
		contentPane.add(btnMove);
		
		JButton btnClearTable = new JButton("Clear Table");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				
				model.setRowCount(0);
				
			}
		});
		btnClearTable.setBounds(603, 127, 117, 25);
		contentPane.add(btnClearTable);
		
		
		
		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{
					"Id", "Name", "Password", "E-Mail", "Phone No", "Identity No", "Adress"
				}
				
			},
			new String[] {
				"Id", "Name", "Password", "E-Mail", "Phone No", "Identity No", "Adress"
			}
		));
		table.setBounds(31, 271, 717, 211);
		contentPane.add(table);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				manageCustomers.this.dispose();
				new adminPanel().setVisible(true);
			}
		});
		btnBack.setBounds(12, 1, 117, 25);
		contentPane.add(btnBack);
	}
}
