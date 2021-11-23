package proyecto2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel panelContenido;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal ventana1 = new VentanaPrincipal();
					ventana1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 420);
		this.panelContenido = new JPanel();
		this.panelContenido.setBackground(Color.GRAY);
		this.panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido);
		this.panelContenido.setLayout(null);
		
		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setFont(new Font("Arial", Font.BOLD, 30));
		lblMenuPrincipal.setForeground(Color.WHITE);
		lblMenuPrincipal.setBounds(420, 40, 275, 45);
		this.panelContenido.add(lblMenuPrincipal);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Vista2().setVisible(true);
				dispose();
				
			}
		});
		
		btnEntrar.setFont(new Font("Arial", Font.PLAIN, 20));
		btnEntrar.setBackground(Color.BLACK);
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBounds(420, 185, 175, 35);
		this.panelContenido.add(btnEntrar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial", Font.PLAIN, 20));
		btnSalir.setBackground(Color.BLACK);
		btnSalir.setBounds(420, 250, 175, 35);
		this.panelContenido.add(btnSalir);
	}
}
