package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dal.dao.BaseDAO;
import dal.dao.CustomerDAO;
import dal.dao.ReservationDAO;
import dal.dao.RoomDAO;
import dal.dao.userHotelDAO;
import pojo.Customer;
import pojo.Reservation;
import pojo.Room;
import pojo.userHotel;

public class userReservation extends JFrame {

	private JPanel contentPane;
	BaseDAO<userHotel> userhotel = new userHotelDAO();
	CustomerDAO customers = new CustomerDAO();
	RoomDAO rooms = new RoomDAO();
	ReservationDAO reservations = new ReservationDAO();
	Room room ;
	Reservation reservation;
	Customer customer;
	listener listener;
	
	int hotelId = userhotel.findById(listener.getId()).getHotelId();
	private JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userReservation frame = new userReservation();
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
	public userReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{"Room ID", "Room Number","Yatak Sayisi","Price","Reservation Status"}
				},
				new String[] {
						"Room ID", "Room Number","Yatak Sayisi","Price","Reservation Status"
				}
		));
		table.setBounds(62, 12, 578, 224);
		table.setBackground(new Color(230, 230, 250));
		contentPane.add(table);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(300, 267, 148, 27);
		contentPane.add(dateChooser);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(492, 267, 148, 27);
		contentPane.add(dateChooser_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new Object[] {""}));
		comboBox.setBounds(62, 267, 189, 27);
		contentPane.add(comboBox);

		Iterator<Customer> ite= customers.findAll().iterator();
		while(ite.hasNext()) {
			comboBox.addItem(ite.next().getMail());
		}
		
		
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		
		String status;
		
		Iterator<Room> itr = rooms.findAll().iterator();
		
		while(itr.hasNext()) {
			room=itr.next();
			
			if(hotelId == room.getHotelId()) {
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
				
				
				customer = customers.findbyEmail(comboBox.getSelectedItem().toString());
				int id = customer.getId();
				Date startDate = dateChooser.getDate();
				Date endDate = dateChooser.getDate();
				
				reservation = new Reservation(startDate,endDate,id);
				int reservationId= reservations.insertt(reservation);
				int roomId= (int)table.getValueAt(table.getSelectedRow(), 0);
				
				
				
				rooms.updateReservation(reservationId, roomId);
				
				
			}
		});
		btnAddReservation.setFont(new Font("Dialog", Font.BOLD, 11));
		btnAddReservation.setBounds(335, 354, 142, 39);
		contentPane.add(btnAddReservation);
		
		JButton btnDeleteReservation = new JButton("Delete Reservation");
		btnDeleteReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				room = rooms.findById((int)table.getValueAt(table.getSelectedRow(), 0));
				int reservationId= room.getReservationId();
				
				
				rooms.updateReservationNull(null, room.getId());
				reservations.delete(reservationId);
				
				
				
			}
		});
		btnDeleteReservation.setFont(new Font("Dialog", Font.BOLD, 10));
		btnDeleteReservation.setBounds(506, 354, 142, 39);
		contentPane.add(btnDeleteReservation);
		
		
		
		
		
	}
}
