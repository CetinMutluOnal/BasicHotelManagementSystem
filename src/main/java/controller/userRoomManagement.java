package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import dal.dao.DBConnection;
import dal.dao.HotelDAO;
import dal.dao.RoomDAO;
import dal.dao.userHotelDAO;
import pojo.Hotel;
import pojo.Room;
import pojo.userHotel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class userRoomManagement extends JFrame {

	private JPanel contentPane;
		
	private PreparedStatement statement = null;
	
	private ResultSet result = null;
	
	private Connection con = DBConnection.getConnection();
	
	

	BaseDAO<userHotel> userhotels = new userHotelDAO();
	BaseDAO<Hotel> hotels = new HotelDAO();
	RoomDAO rooms = new RoomDAO();
	userHotel userhotel =null;
	Hotel hotel= null;
	Room room = null;
	listener listener;
	
	private JTextField txtRoomid;
	private JTextField txtRoomnumber;
	private JTextField txtHotelid;
	private JTextField txtBed;
	private JTextField txtPrice;
	private JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userRoomManagement frame = new userRoomManagement();
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
	public userRoomManagement() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblYourHotel = new JLabel("Your Hotel");
		lblYourHotel.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYourHotel.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourHotel.setBounds(20, 35, 167, 47);
		contentPane.add(lblYourHotel);
		
		JLabel lblDescription = new JLabel("description");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDescription.setBounds(20, 182, 167, 54);
		contentPane.add(lblDescription);
		
		JLabel lblProvince = new JLabel("province");
		lblProvince.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvince.setFont(new Font("Dialog", Font.BOLD, 16));
		lblProvince.setBounds(20, 132, 167, 54);
		contentPane.add(lblProvince);
		
		JLabel lblCity = new JLabel("city");
		lblCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCity.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCity.setBounds(20, 79, 167, 54);
		contentPane.add(lblCity);
		
		
		userhotel = userhotels.findById(listener.getId());
		
		hotel =hotels.findById(userhotel.getHotelId());
		
		
	
		lblYourHotel.setText(hotel.getName());
		
		lblCity.setText(hotel.getSehir());
		
		lblProvince.setText(hotel.getIlce());
		
		lblDescription.setText(hotel.getAciklama());
		
		JLabel lblRoomId = new JLabel("Room Id :");
		lblRoomId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoomId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoomId.setBounds(276, 35, 97, 15);
		contentPane.add(lblRoomId);
		
		JLabel lblRoomNumber = new JLabel("Room Number :");
		lblRoomNumber.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoomNumber.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoomNumber.setBounds(220, 67, 153, 15);
		contentPane.add(lblRoomNumber);
		
		JLabel lblHotelId = new JLabel("Hotel Id :");
		lblHotelId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHotelId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotelId.setBounds(264, 99, 109, 15);
		contentPane.add(lblHotelId);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrice.setBounds(303, 171, 70, 15);
		contentPane.add(lblPrice);
		
		JLabel lblBedQuantity = new JLabel("Bed Quantity :");
		lblBedQuantity.setHorizontalAlignment(SwingConstants.TRAILING);
		lblBedQuantity.setFont(new Font("Dialog", Font.BOLD, 16));
		lblBedQuantity.setBounds(230, 138, 143, 15);
		contentPane.add(lblBedQuantity);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(113, 202, 408, 34);
		contentPane.add(lblMessage);
		
		txtRoomid = new JTextField();
		txtRoomid.setBounds(379, 27, 176, 27);
		contentPane.add(txtRoomid);
		txtRoomid.setColumns(10);
		
		txtRoomnumber = new JTextField();
		txtRoomnumber.setBounds(379, 63, 176, 25);
		contentPane.add(txtRoomnumber);
		txtRoomnumber.setColumns(10);
		
		txtHotelid = new JTextField();
		txtHotelid.setEditable(false);
		txtHotelid.setBounds(379, 94, 176, 27);
		contentPane.add(txtHotelid);
		txtHotelid.setColumns(10);
		txtHotelid.setText(Integer.toString(hotel.getId()));;
		
		txtBed = new JTextField();
		txtBed.setBounds(379, 133, 176, 27);
		contentPane.add(txtBed);
		txtBed.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(379, 170, 176, 27);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnAddRooms = new JButton("Add Rooms");
		btnAddRooms.setForeground(new Color(240, 255, 255));
		btnAddRooms.setBackground(new Color(70, 130, 180));
		btnAddRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(txtRoomnumber.getText().isEmpty()  || txtBed.getText().isEmpty() ) {
						
						lblMessage.setText("values cannot be empty!");
					}
					else {
						
						int number = Integer.parseInt(txtRoomnumber.getText());
						int hotelId= Integer.parseInt(txtHotelid.getText());
						int bedQuantity= Integer.parseInt(txtBed.getText());
						int price = Integer.parseInt(txtPrice.getText());
						
						room = new Room(number,hotelId,bedQuantity,price);
						
						if(rooms.insert(room)) {
							
							lblMessage.setText("Added Successfully!");
							
						}
						else {
							lblMessage.setText("failed to add room!");
						}

					}
					
				} catch(NullPointerException e ) {

					e.printStackTrace();

				}

			}
		});
		btnAddRooms.setBounds(567, 12, 121, 63);
		contentPane.add(btnAddRooms);
		
		JButton btnShowRooms = new JButton("Show Rooms");
		btnShowRooms.setFont(new Font("Dialog", Font.BOLD, 11));
		btnShowRooms.setForeground(new Color(240, 255, 255));
		btnShowRooms.setBackground(new Color(70, 130, 180));
		btnShowRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				Iterator<Room> itr = rooms.findHotelsAll(hotel.getId()).iterator();
				
				
				while(itr.hasNext()) {
					
					room = itr.next();
					
					Object [] add = { room.getId(),room.getNumber(),room.getHotelId(),room.getBedQuantity(),room.getReservationId(),room.getPrice()};
					
					model.addRow(add);
					
					
				}
		
			
		}
		});
		btnShowRooms.setBounds(567, 76, 121, 63);
		contentPane.add(btnShowRooms);
		
		JButton btnUpdateRoom = new JButton("Update Room");
		btnUpdateRoom.setFont(new Font("Dialog", Font.BOLD, 11));
		btnUpdateRoom.setForeground(new Color(240, 255, 255));
		btnUpdateRoom.setBackground(new Color(70, 130, 180));
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		try {
					
					if(txtRoomnumber.getText().isEmpty() || txtBed.getText().isEmpty()) {
						
						lblMessage.setText("values cannot be empty!");
					}
					else {
						int id = Integer.parseInt(txtRoomid.getText());
						int number = Integer.parseInt(txtRoomnumber.getText());
						int hotelId= Integer.parseInt(txtHotelid.getText());
						int bedQuantity= Integer.parseInt(txtBed.getText());
						int price = Integer.parseInt(txtPrice.getText());
						
						room = new Room(id,number,hotelId,bedQuantity,price);
						
						if(rooms.update(room)) {
							
							lblMessage.setText("Updated Successfully!");
							
						}
						else {
							lblMessage.setText("failed to update room!");
						}

					}
					
				} catch(Exception e ) {

					e.printStackTrace();

				}
				
				
			}
		});
		btnUpdateRoom.setBounds(567, 143, 121, 63);
		contentPane.add(btnUpdateRoom);
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setFont(new Font("Dialog", Font.BOLD, 11));
		btnDeleteRoom.setForeground(new Color(240, 255, 255));
		btnDeleteRoom.setBackground(new Color(70, 130, 180));
		btnDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = (int) table.getValueAt(table.getSelectedRow(),table.getSelectedColumn());
				
				if(rooms.delete(id)) {
					lblMessage.setText("Deleted Successfully");
				}
				else {
					lblMessage.setText("Failed to delete ! ");
				}
			}
		});
		btnDeleteRoom.setBounds(567, 210, 121, 63);
		contentPane.add(btnDeleteRoom);
		
		JButton btnClear = new JButton("clear");
		btnClear.setForeground(new Color(240, 255, 255));
		btnClear.setBackground(new Color(70, 130, 180));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				model.setRowCount(1);
				
				txtRoomid.setText("");
				txtRoomnumber.setText("");
				txtHotelid.setText("");
				txtBed.setText("");
				txtPrice.setText("");
				
				
			}
		});
		btnClear.setBounds(65, 248, 117, 25);
		contentPane.add(btnClear);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(240, 255, 255));
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				userRoomManagement.this.dispose();
				new UserLogin().setVisible(true);
			}
		});
		btnBack.setBounds(20, 12, 117, 25);
		contentPane.add(btnBack);
		

		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtRoomid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtRoomnumber.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtHotelid.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtBed.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtPrice.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				
			}
		});
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Room ID", "Room Number", "Hotel ID","Yatak Sayısı","Reservation ID","Price"}
			},
			new String[] {
					"Room ID", "Room Number", "Hotel ID","Yatak Sayısı","Reservation ID","Price"
			}
		));
		table.setBounds(65, 290, 605, 191);
		contentPane.add(table);
		
		JButton btnManageReservations = new JButton("Manage Reservations");
		btnManageReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			userRoomManagement.this.setVisible(false);
			new userReservation().setVisible(true);
			}
		});
		btnManageReservations.setFont(new Font("Dialog", Font.BOLD, 11));
		btnManageReservations.setBounds(367, 248, 188, 25);
		btnManageReservations.setForeground(new Color(240, 255, 255));
		btnManageReservations.setBackground(new Color(70, 130, 180));
		contentPane.add(btnManageReservations);
		
		
		
		
		
	}
}
