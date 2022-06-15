package controller;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class adminPanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPanel frame = new adminPanel();
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
	public adminPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAdminControlPanel = new JLabel("Admin Control Panel");
		lblAdminControlPanel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblAdminControlPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminControlPanel.setBounds(161, 12, 364, 54);
		contentPane.add(lblAdminControlPanel);
		
		JButton btnManageHotels = new JButton("Manage Hotels");
		btnManageHotels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				adminPanel.this.dispose();
				new manageHotels().setVisible(true);
				
			}
		});
		btnManageHotels.setBounds(74, 90, 236, 96);
		contentPane.add(btnManageHotels);
		
		JButton btnManageRooms = new JButton("Manage Rooms");
		btnManageRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				adminPanel.this.dispose();
				new ManageRooms().setVisible(true);
			}
		});
		btnManageRooms.setBounds(375, 90, 220, 96);
		contentPane.add(btnManageRooms);
		
		JButton btnManageReservations = new JButton("Manage Reservations");
		btnManageReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				adminPanel.this.dispose();
				new manageReservations().setVisible(true);
			}
		});
		btnManageReservations.setBounds(74, 227, 236, 96);
		contentPane.add(btnManageReservations);
		
		JButton btnManageCustomers = new JButton("Manage Customers");
		btnManageCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				adminPanel.this.dispose();
				new manageCustomers().setVisible(true);
			}
		});
		btnManageCustomers.setBounds(241, 365, 220, 96);
		contentPane.add(btnManageCustomers);
		
		JButton btnManageUsers = new JButton("Manage Users");
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				adminPanel.this.dispose();
				new manageUsers().setVisible(true);
			}
		});
		btnManageUsers.setBounds(375, 227, 220, 96);
		contentPane.add(btnManageUsers);
	}
}
