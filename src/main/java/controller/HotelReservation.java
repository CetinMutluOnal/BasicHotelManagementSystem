package controller;


import java.awt.Color;
import java.awt.EventQueue;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import dal.dao.*;
import pojo.*;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;



public class HotelReservation extends JFrame {

	private PreparedStatement statement = null;
	private ResultSet result = null;
	private JPanel contentPane;
	RoomDAO rooms = new RoomDAO();
	ReservationDAO reservations = new ReservationDAO();
	Room room;
	Reservation reservation;
	
	listener listener;
	private JTable table;

	/**
	 * Launch the application.
	 * 
	 */
	
	public static void showRooms() {
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HotelReservation frame = new HotelReservation();
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
	public HotelReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 701, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Room ID", "Room Number", "Bed Quantity","Price","Reservation Status",},
			},
			new String[] {
				"Room ID", "Room Number","Bed Quantity","Price","Reservation Status"
			}
		));
		table.setBounds(12, 37, 677, 226);
		contentPane.add(table);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(75, 275, 182, 27);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(382, 275, 182, 27);
		contentPane.add(dateChooser_1);
		

		
		Integer customerId=listener.getCustomerId();
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String status;
		
		Iterator<Room> itr = rooms.findAll().iterator();
		
		while(itr.hasNext()) {
			room=itr.next();
			
			if(listener.getId() == room.getHotelId()) {
				int roomId=room.getId();
				int roomNumber= room.getNumber();
				int bedQuantity = room.getBedQuantity();
				int price = room.getPrice();
				int reservationId=room.getReservationId();
				
				if(reservationId == 0) {
					status = "";
				}
				else {
					status="Reserved";
				}
				
				Object[] add = {roomId,roomNumber,bedQuantity,price,status};
				
				model.addRow(add);
				
			}
			
		}
		
		
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Date startDate=dateChooser.getDate();
				Date endDate= dateChooser_1.getDate();
				int customerID= listener.getCustomerId();
				
				reservation = new Reservation(startDate,endDate,customerID);

				int roomId= (int)table.getValueAt(table.getSelectedRow(),0);
				int reservationId=reservations.insertt(reservation);
				
				rooms.updateReservation(reservationId, roomId);
				
				table.revalidate();
				
			}
		});
		btnAddReservation.setBounds(258, 432, 150, 27);
		contentPane.add(btnAddReservation);
		
		
	}
}
