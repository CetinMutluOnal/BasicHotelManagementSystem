package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setBackground(new Color(255, 160, 122));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 530);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPleaseChooseA = new JLabel("Please Choose a Login Type");
		lblPleaseChooseA.setForeground(new Color(70, 130, 180));
		lblPleaseChooseA.setFont(new Font("Dialog", Font.BOLD, 20));
		lblPleaseChooseA.setBounds(153, 40, 387, 39);
		contentPane.add(lblPleaseChooseA);
		
		JButton btnUser = new JButton("User");
		btnUser.setForeground(new Color(240, 255, 255));
		btnUser.setBackground(new Color(70, 130, 180));
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login.this.dispose();
				
				new UserLogin().setVisible(true);
			}
		});
		btnUser.setBounds(126, 204, 201, 92);
		contentPane.add(btnUser);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setForeground(new Color(240, 255, 255));
		btnCustomer.setBackground(new Color(70, 130, 180));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Login.this.dispose();
				
				new CustomerLogin().setVisible(true);
			}
		});
		btnCustomer.setBounds(383, 202, 201, 92);
		contentPane.add(btnCustomer);
	}

}
