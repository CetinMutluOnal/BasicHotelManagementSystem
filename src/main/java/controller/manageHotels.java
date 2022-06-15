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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dal.dao.BaseDAO;
import dal.dao.HotelDAO;
import pojo.Hotel;

public class manageHotels extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtId;
	private JTable table;

	BaseDAO<Hotel> hotels = new HotelDAO();
	Hotel hotel;
	private JTextField txtCity;
	private JTextField txtProvince;
	private JTextField txtDescription;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageHotels frame = new manageHotels();
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
	public manageHotels() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEditHotels = new JLabel("Manage Hotels");
		lblEditHotels.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblEditHotels.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditHotels.setBounds(309, 14, 176, 37);
		contentPane.add(lblEditHotels);
		
		JLabel lblHotelId = new JLabel("Hotel Id :");
		lblHotelId.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotelId.setBounds(48, 64, 96, 15);
		contentPane.add(lblHotelId);
		
		JLabel lblHotelName = new JLabel("Hotel Name :");
		lblHotelName.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotelName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotelName.setBounds(380, 64, 135, 15);
		contentPane.add(lblHotelName);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.RED);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBackground(Color.WHITE);
		lblMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMessage.setBounds(74, 241, 411, 37);
		contentPane.add(lblMessage);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setBounds(59, 104, 70, 15);
		contentPane.add(lblCity);
		
		JLabel lblProvince = new JLabel("Province :");
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProvince.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvince.setBounds(380, 104, 125, 15);
		contentPane.add(lblProvince);
		
		JLabel lblDescription = new JLabel("Description : ");
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setBounds(27, 147, 133, 25);
		contentPane.add(lblDescription);
		
		
		txtName = new JTextField();
		txtName.setBounds(514, 60, 168, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtId = new JTextField();
		txtId.setBounds(162, 60, 176, 25);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		txtCity = new JTextField();
		txtCity.setBounds(162, 100, 176, 25);
		contentPane.add(txtCity);
		txtCity.setColumns(10);
		
		txtProvince = new JTextField();
		txtProvince.setBounds(514, 100, 176, 25);
		contentPane.add(txtProvince);
		txtProvince.setColumns(10);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(156, 147, 534, 25);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);
		
		
		JButton btnAddHotel = new JButton("Add Hotel");
		btnAddHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtName.getText().trim().equals("")) {
					lblMessage.setText("Hotel Name can not be empty !");
					
				}
				else {
					
					String name = txtName.getText();
					String city = txtCity.getText();
					String province = txtProvince.getText();
					String description = txtDescription.getText();
					
					
					
					
					hotel = new Hotel(name,city,province,description);
					
					if(hotels.insert(hotel)) {
						lblMessage.setText("Added Successfully");
					}
					else {
						lblMessage.setText("Failed to add hotel!");
					}
					
					
					
				}
			}
		});
		btnAddHotel.setBounds(25, 184, 168, 58);
		contentPane.add(btnAddHotel);
		
		JButton btnShowHotels = new JButton("Show Hotels");
		btnShowHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessage.setText("");
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				Iterator<Hotel> itr = hotels.findAll().iterator();
				
				while(itr.hasNext()) {
					hotel = itr.next();
					
					Object [] add = {hotel.getId(),hotel.getName(),hotel.getSehir(),hotel.getIlce(),hotel.getAciklama()};
					
					model.addRow(add);
				}
				
			}
		});
		btnShowHotels.setBounds(232, 184, 154, 58);
		contentPane.add(btnShowHotels);
		
		JButton btnDeleteHotel = new JButton("Delete Hotel");
		btnDeleteHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id= (int) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
				
				if(hotels.delete(id)) {
					lblMessage.setText("Deleted Succesfully");
				}
				else {
					lblMessage.setText("Failed to Delete Otel!");
				}
				
			}
		});
		btnDeleteHotel.setBounds(411, 184, 154, 58);
		contentPane.add(btnDeleteHotel);
		
		JButton btnUpdateHotel = new JButton("Update Hotel");
		btnUpdateHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtName.getText().trim().equals("")) {
					lblMessage.setText("Hotel Name can not be empty !");
					
				}
				else {
					
					String name = txtName.getText();
					String city = txtCity.getText();
					String province = txtProvince.getText();
					String description = txtDescription.getText();
					int id = Integer.parseInt(txtId.getText());
					
					hotel = new Hotel(id,name,city,province,description);
					
					if(hotels.update(hotel)) {
						lblMessage.setText("Updated Successfully");
					}
					else {
						lblMessage.setText("Failed to update hotel!");
					}
					
					
					
				}
				
				
			
				
			}
		});
		btnUpdateHotel.setBounds(591, 184, 154, 58);
		contentPane.add(btnUpdateHotel);
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtCity.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtProvince.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtDescription.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				
				
				 
				
			}
		});
		btnMove.setBounds(514, 247, 117, 25);
		contentPane.add(btnMove);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				model.setRowCount(0);
			}
		});
		btnClear.setBounds(641, 247, 117, 25);
		contentPane.add(btnClear);
		
		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Hotel ID", "Hotel Name", "City", "Province","Description" },
			},
			new String[] {
					"Hotel ID", "Hotel Name", "City", "Province","Description"
			}
		));
		table.setBounds(48, 290, 697, 203);
		contentPane.add(table);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				manageHotels.this.dispose();
				new adminPanel().setVisible(true);
			}
		});
		btnBack.setBounds(12, 0, 117, 25);
		contentPane.add(btnBack);
		

	
		
	}
}
