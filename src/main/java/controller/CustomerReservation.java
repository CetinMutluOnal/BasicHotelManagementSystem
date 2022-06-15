package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dal.dao.*;
import pojo.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class CustomerReservation extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	listener listener;
	
	BaseDAO<Hotel> hotels = new HotelDAO();
	
	Hotel hotel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReservation frame = new CustomerReservation();
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
	public CustomerReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setBackground(new Color(230, 230, 250));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					
				
				
				int id =(int) table.getValueAt(table.getSelectedRow(), 0);
				
				listener.setId(id);
				
				CustomerReservation.this.setVisible(false);
				
				new HotelReservation().setVisible(true);
				
				}catch(ArrayIndexOutOfBoundsException a) {
					a.printStackTrace();
				}
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{
					"ID", "Hotel Name", "City", "Town","Description"
				}
			},
			new String[] {
					"ID", "Hotel Name", "City", "Town","Description"
			}
		));
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		Iterator<Hotel> itr = hotels.findAll().iterator();
		
		while(itr.hasNext()) {
			hotel = itr.next();
			
			Object [] add = {hotel.getId(),hotel.getName(),hotel.getSehir(),hotel.getIlce(),hotel.getAciklama()};
			
			model.addRow(add);
			
		}
		table.setBounds(33, 22, 614, 435);
		contentPane.add(table);
	}
}
