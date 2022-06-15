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
import dal.dao.RoomDAO;
import pojo.Room;

public class ManageRooms extends JFrame {

	private JPanel contentPane;
	private JTextField txtRoomid;
	private JTextField txtRoomnumber;
	private JTextField txtHotelid;
	private JTextField txtReservationId;
	private JTable table;
	
	BaseDAO<Room> rooms = new RoomDAO();
	
	Room room;
	private JTextField txtYatak;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageRooms frame = new ManageRooms();
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
	public ManageRooms() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 545);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblManageRooms = new JLabel("Manage Rooms");
		lblManageRooms.setFont(new Font("Dialog", Font.BOLD, 16));
		lblManageRooms.setHorizontalAlignment(SwingConstants.CENTER);
		lblManageRooms.setBounds(245, 6, 260, 43);
		contentPane.add(lblManageRooms);
		
		JLabel lblRoomId = new JLabel("Room ID :");
		lblRoomId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoomId.setBounds(47, 51, 111, 35);
		contentPane.add(lblRoomId);
		
		JLabel lblRoomNumber = new JLabel("Room Number :");
		lblRoomNumber.setFont(new Font("Dialog", Font.BOLD, 16));
		lblRoomNumber.setBounds(12, 111, 192, 29);
		contentPane.add(lblRoomNumber);
		
		JLabel lblHotel_Id = new JLabel("Hotel ID :");
		lblHotel_Id.setFont(new Font("Dialog", Font.BOLD, 16));
		lblHotel_Id.setBounds(326, 61, 141, 15);
		contentPane.add(lblHotel_Id);
		
		JLabel lblReservationId = new JLabel("Reservation ID : ");
		lblReservationId.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservationId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblReservationId.setBounds(290, 173, 177, 15);
		contentPane.add(lblReservationId);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setForeground(Color.RED);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(47, 200, 406, 35);
		contentPane.add(lblMessage);
		
		JLabel lblYatakSays = new JLabel("Yatak Sayısı :");
		lblYatakSays.setFont(new Font("Dialog", Font.BOLD, 16));
		lblYatakSays.setHorizontalAlignment(SwingConstants.CENTER);
		lblYatakSays.setBounds(31, 166, 127, 22);
		contentPane.add(lblYatakSays);
		
		txtYatak = new JTextField();
		txtYatak.setBounds(153, 170, 127, 26);
		contentPane.add(txtYatak);
		txtYatak.setColumns(10);
		
		txtRoomid = new JTextField();
		txtRoomid.setBounds(153, 59, 127, 27);
		contentPane.add(txtRoomid);
		txtRoomid.setColumns(10);
		
		txtRoomnumber = new JTextField();
		txtRoomnumber.setBounds(153, 114, 127, 25);
		contentPane.add(txtRoomnumber);
		txtRoomnumber.setColumns(10);
		
		txtHotelid = new JTextField();
		txtHotelid.setBounds(441, 59, 137, 27);
		contentPane.add(txtHotelid);
		txtHotelid.setColumns(10);
		
		txtReservationId = new JTextField();
		txtReservationId.setBounds(451, 167, 141, 29);
		contentPane.add(txtReservationId);
		txtReservationId.setColumns(10);
		
		JButton btnAllRooms = new JButton("Show Rooms");
		btnAllRooms.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAllRooms.setForeground(new Color(240, 255, 255));
		btnAllRooms.setBackground(new Color(70, 130, 180));
		btnAllRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				Iterator<Room> itr = rooms.findAll().iterator();
				
				while(itr.hasNext()) {
					
					room = itr.next();
					
					Object [] add = { room.getId(),room.getNumber(),room.getHotelId(),room.getBedQuantity(),room.getReservationId(),room.getPrice()};
					
					model.addRow(add);
					
					
				}
				
				
			}
		});
		btnAllRooms.setBounds(634, 10, 111, 61);
		contentPane.add(btnAllRooms);
		
		JButton btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setFont(new Font("Dialog", Font.BOLD, 10));
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
		btnDeleteRoom.setBounds(634, 146, 111, 50);
		contentPane.add(btnDeleteRoom);
		
		JButton btnUpdateRoom = new JButton("Update Room");
		btnUpdateRoom.setFont(new Font("Dialog", Font.BOLD, 10));
		btnUpdateRoom.setForeground(new Color(240, 255, 255));
		btnUpdateRoom.setBackground(new Color(70, 130, 180));
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					try {
					
					if(txtRoomnumber.getText().isEmpty() || txtHotelid.getText().isEmpty() || txtYatak.getText().isEmpty()) {
						
						lblMessage.setText("values cannot be empty!");
					}
					else {
						int id = Integer.parseInt(txtRoomid.getText());
						int number = Integer.parseInt(txtRoomnumber.getText());
						int hotelId= Integer.parseInt(txtHotelid.getText());
						int bedQuantity= Integer.parseInt(txtYatak.getText());
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
		btnUpdateRoom.setBounds(634, 208, 111, 50);
		contentPane.add(btnUpdateRoom);
		
		JButton btnAddRoom = new JButton("Add Room");
		btnAddRoom.setFont(new Font("Dialog", Font.BOLD, 10));
		btnAddRoom.setForeground(new Color(240, 255, 255));
		btnAddRoom.setBackground(new Color(70, 130, 180));
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(txtRoomnumber.getText().isEmpty() || txtHotelid.getText().isEmpty() || txtYatak.getText().isEmpty() ) {
						
						lblMessage.setText("values cannot be empty!");
					}
					else {
						
						int number = Integer.parseInt(txtRoomnumber.getText());
						int hotelId= Integer.parseInt(txtHotelid.getText());
						int bedQuantity= Integer.parseInt(txtYatak.getText());
						int price = Integer.parseInt(txtPrice.getText());
						
						room = new Room(number,hotelId,bedQuantity,price);
						
						if(rooms.insert(room)) {
							
							lblMessage.setText("Added Successfully!");
							
						}
						else {
							lblMessage.setText("failed to add room!");
						}

					}
					
				} catch(Exception e ) {

					e.printStackTrace();

				}
				
			}
		});
		btnAddRoom.setBounds(634, 79, 111, 55);
		contentPane.add(btnAddRoom);
		
	
		
		JButton btnMove = new JButton("Move");
		btnMove.setForeground(new Color(240, 255, 255));
		btnMove.setBackground(new Color(70, 130, 180));
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtRoomid.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtRoomnumber.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtHotelid.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtYatak.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtReservationId.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				txtPrice.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
				
				
			}
		});
		btnMove.setBounds(511, 233, 111, 25);
		contentPane.add(btnMove);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(new Color(240, 255, 255));
		btnClear.setBackground(new Color(70, 130, 180));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				model.setRowCount(0);
				
				
				
			}
		});
		btnClear.setBounds(511, 206, 111, 25);
		contentPane.add(btnClear);
		
		table = new JTable();
		table.setForeground(new Color(240, 255, 255));
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Room ID", "Room Number", "Hotel ID", "Yatak Sayısı","Reservation ID","Price"},
			},
			new String[] {
				"Room ID", "Room Number", "Hotel ID","Yatak Sayısı","Reservation ID","Price"
			}
		));
		table.setBounds(47, 270, 691, 213);
		contentPane.add(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(240, 255, 255));
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ManageRooms.this.dispose();
				new adminPanel().setVisible(true);
			}
		});
		btnBack.setBounds(12, 3, 117, 25);
		contentPane.add(btnBack);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(325, 118, 70, 15);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(441, 112, 137, 29);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		
	}
}
