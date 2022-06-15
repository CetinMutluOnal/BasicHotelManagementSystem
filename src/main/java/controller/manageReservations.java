package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dal.dao.BaseDAO;
import dal.dao.ReservationDAO;
import pojo.Reservation;
import javax.swing.SwingConstants;

public class manageReservations extends JFrame {

	private JPanel contentPane;
	private JTextField txtPrice;
	private JTextField txtStartdate;
	private JTextField txtEnddate;
	private JTextField txtCustomerd;
	private JLabel lblMessage;
	private JTable table;
	
	BaseDAO<Reservation> reservations = new ReservationDAO();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private JButton btnDelete;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageReservations frame = new manageReservations();
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
	public manageReservations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table = new JTable();
		table.setToolTipText("");
		table.setBackground(new Color(230, 230, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Id", "Start Date", "End Date", "Customer ID"},
			},
			new String[] {
				"id", "startdate", "enddate", "customerid"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		table.getColumnModel().getColumn(1).setPreferredWidth(111);
		table.getColumnModel().getColumn(2).setPreferredWidth(101);
		table.getColumnModel().getColumn(3).setPreferredWidth(99);
		table.setBounds(62, 300, 636, 204);
		contentPane.add(table);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Dialog", Font.BOLD, 16));
		lblPrice.setBounds(25, 97, 106, 35);
		contentPane.add(lblPrice);
		
		JLabel lblStartDate = new JLabel("Start Date :");
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartDate.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStartDate.setBounds(296, 40, 124, 35);
		contentPane.add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("End Date : ");
		lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblEndDate.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEndDate.setBounds(306, 97, 106, 35);
		contentPane.add(lblEndDate);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(131, 97, 141, 26);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtStartdate = new JTextField();
		txtStartdate.setBounds(425, 46, 147, 26);
		contentPane.add(txtStartdate);
		txtStartdate.setColumns(10);
		
		txtEnddate = new JTextField();
		txtEnddate.setBounds(425, 103, 147, 26);
		contentPane.add(txtEnddate);
		txtEnddate.setColumns(10);
		
		JLabel lblCustomerId = new JLabel("Customer Id :");
		lblCustomerId.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCustomerId.setBounds(612, 50, 141, 15);
		contentPane.add(lblCustomerId);
		
		txtCustomerd = new JTextField();
		txtCustomerd.setBounds(606, 77, 147, 26);
		contentPane.add(txtCustomerd);
		txtCustomerd.setColumns(10);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Dialog", Font.BOLD, 16));
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(39, 50, 70, 15);
		contentPane.add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(133, 46, 132, 26);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		lblMessage = new JLabel("message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Dialog", Font.BOLD, 16));
		lblMessage.setForeground(Color.RED);
		lblMessage.setBounds(25, 240, 428, 35);
		contentPane.add(lblMessage);
		
		JButton btnEkle = new JButton("Show All");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblMessage.setText("");
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				
				Iterator<Reservation> itr = reservations.findAll().iterator();
				Reservation reservation;
				while(itr.hasNext()) {
					reservation = itr.next();
					
					Object[] add = {reservation.getId(),reservation.getStartDate(),reservation.getEndDate(),reservation.getCustomerId()};
					model.addRow(add);
					
				}
			
			
		}
		});
		btnEkle.setBounds(214, 166, 147, 46);
		contentPane.add(btnEkle);
		
		JButton ekle = new JButton("Add Reservation");
		ekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 if ( txtPrice.getText().trim().equals("") || txtStartdate.getText().trim().equals("") || txtEnddate.getText().trim().equals("")) {
				
				lblMessage.setText("Price and reservation date values cannot be empty !");
				
			}
			else {
				
					try {
						
					
			//	String startDate= dateFormat.format(txtStartdate.getText());
			//	String endDate = dateFormat.format(txtEnddate.getText());
				
				Date date1 = dateFormat.parse(txtStartdate.getText());
				Date date2= dateFormat.parse(txtEnddate.getText());
				
				int customerid= Integer.parseInt(txtCustomerd.getText());
				
				Reservation reservation = new Reservation(date1,date2,customerid);
				
				if(reservations.insert(reservation)) {
					lblMessage.setText("Submit Succesfull");
				}
				else {
					lblMessage.setText("Submit Failed!");
				}
					} catch(ParseException e ) {
						e.printStackTrace();
					}catch(NumberFormatException e)
					{
						e.printStackTrace();
					}
					
				
			}
		
			}
		});
		ekle.setBounds(23, 166, 161, 46);
		contentPane.add(ekle);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id = (int) (table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()));
				if(reservations.delete(id)) {
					lblMessage.setText("Deleted Successfully");
				}
				else {
					lblMessage.setText("Delete Failed!");
				}
			}
		});
		btnDelete.setBounds(387, 166, 153, 46);
		contentPane.add(btnDelete);
		
		JLabel lblHeader = new JLabel("Edit Reservations");
		lblHeader.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setBounds(170, 0, 346, 46);
		contentPane.add(lblHeader);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				 if ( txtPrice.getText().trim().equals("") || txtStartdate.getText().trim().equals("") || txtEnddate.getText().trim().equals("")) {
						
						lblMessage.setText("Price and reservation date values cannot be empty !");
						
					}
					else {
						
							try {
								
							
					//	String startDate= dateFormat.format(txtStartdate.getText());
					//	String endDate = dateFormat.format(txtEnddate.getText());
						
						Date date1 = dateFormat.parse(txtStartdate.getText());
						Date date2= dateFormat.parse(txtEnddate.getText());
						
						int customerid= Integer.parseInt(txtCustomerd.getText());
						int id = Integer.parseInt(txtId.getText());
						
						Reservation reservation = new Reservation(id,date1,date2,customerid);
						
						if(reservations.update(reservation)) {
							lblMessage.setText("Updated Succesfully");
						}
						else {
							lblMessage.setText("Update Failed!");
						}
							} catch(ParseException e ) {
								e.printStackTrace();
							}catch(NumberFormatException e)
							{
								e.printStackTrace();
							}	
						
					}

			}
		});
		btnUpdate.setBounds(577, 166, 153, 49);
		contentPane.add(btnUpdate);
		
		JButton btnMove = new JButton("Move");
		btnMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtStartdate.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtEnddate.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtCustomerd.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				
				
				
			}
		});
		btnMove.setBounds(494, 246, 99, 25);
		contentPane.add(btnMove);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
			}
		});
		btnClear.setBounds(622, 246, 99, 25);
		contentPane.add(btnClear);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manageReservations.this.dispose();
				new adminPanel().setVisible(true);
				
				
			}
		});
		btnBack.setBounds(14, 0, 117, 25);
		contentPane.add(btnBack);
		

	}
}
