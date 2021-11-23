package proyecto2;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class Vista2 extends JFrame {

	private JPanel panelContenido2;
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtEdad;
	private JTextField txtEmail;
	private JComboBox<String> cbGenero;
	private JTable tabla;
	private String driverDB = "org.postgresql.Driver";
	private String nombreDB = "usuario_db";
	private String urlDB = "jdbc:postgresql://localhost:5432/" + this.nombreDB;
	private String usuarioDB = "postgres";
	private String passDB = "osman271201";
	private Connection conn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista2 ventana2 = new Vista2();
					ventana2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Vista2() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1045, 420);
		this.panelContenido2 = new JPanel();
		this.panelContenido2.setBackground(Color.GRAY);
		this.panelContenido2.setForeground(new Color(255, 200, 0));
		this.panelContenido2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelContenido2);
		this.panelContenido2.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNombre.setBounds(60, 135, 75, 20);
		this.panelContenido2.add(lblNombre);

		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDireccion.setBounds(60, 165, 75, 20);
		this.panelContenido2.add(lblDireccion);

		JLabel lblEdad = new JLabel("Edad:");
		lblEdad.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEdad.setBounds(60, 200, 75, 20);
		this.panelContenido2.add(lblEdad);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
		lblEmail.setBounds(60, 235, 75, 20);
		this.panelContenido2.add(lblEmail);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Arial", Font.PLAIN, 18));
		lblGenero.setBounds(60, 270, 75, 20);
		panelContenido2.add(lblGenero);

		JLabel lblCedula = new JLabel("Cedula:");
		lblCedula.setFont(new Font("Arial", Font.PLAIN, 18));
		lblCedula.setBounds(60, 105, 75, 20);
		this.panelContenido2.add(lblCedula);

		txtCedula = new JTextField();
		txtCedula.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCedula.setBounds(173, 102, 155, 20);
		this.panelContenido2.add(txtCedula);
		txtCedula.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNombre.setColumns(10);
		txtNombre.setBounds(173, 135, 155, 20);
		this.panelContenido2.add(txtNombre);

		txtDireccion = new JTextField();
		txtDireccion.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(173, 168, 155, 20);
		this.panelContenido2.add(txtDireccion);

		txtEdad = new JTextField();
		txtEdad.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEdad.setColumns(10);
		txtEdad.setBounds(173, 203, 155, 20);
		this.panelContenido2.add(txtEdad);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBounds(173, 236, 155, 20);
		this.panelContenido2.add(txtEmail);

		cbGenero = new JComboBox<String>();
		cbGenero.setModel(new DefaultComboBoxModel<String>(new String[] { "", "M", "F" }));
		cbGenero.setBounds(173, 271, 155, 20);
		this.panelContenido2.add(cbGenero);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String cedula = txtCedula.getText();
				String nombre = txtNombre.getText();
				String direccion = txtDireccion.getText();
				String genero = cbGenero.getSelectedItem().toString();
				String email = txtEmail.getText();
				String edadString = txtEdad.getText();
				
				if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || email.isEmpty() || edadString.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Datos incompletos");
				} else {
					try {

						int edad = Integer.parseInt(edadString);

						if (cedulaExiste(cedula)) {
							JOptionPane.showMessageDialog(null, "Esta cedula ya esta en uso");
						} else {
							insertarUsuario(cedula, nombre, direccion, genero, email, edad);
							reset();
							JOptionPane.showMessageDialog(null, "Se ha agregado el usuario");
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Edad invalida");
					}
				}

			}

		});
		btnAgregar.setBackground(Color.BLACK);
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAgregar.setBounds(60, 320, 100, 25);
		this.panelContenido2.add(btnAgregar);

		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int fila = tabla.getSelectedRow();
				if (fila == -1 || fila > tabla.getRowCount()-1) {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para actualizar");
				} else {
					
					String cedulaAnterior = tabla.getValueAt(fila, 0).toString();
					String cedula = txtCedula.getText();
					String nombre = txtNombre.getText();
					String direccion = txtDireccion.getText();
					String genero = cbGenero.getSelectedItem().toString();
					String email = txtEmail.getText();
					String edadString = txtEdad.getText();
					
					if (cedula.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || email.isEmpty() || edadString.isEmpty()) {
						JOptionPane.showMessageDialog(null, "No se puede actualizar con un campo vacio");
					} else {
						try {

							int edad = Integer.parseInt(edadString);

							if (cedula.equals(cedulaAnterior)) {
								actualizarUsuario(cedulaAnterior, cedula, nombre, direccion, genero, email, edad);
								reset();
								JOptionPane.showMessageDialog(null, "Se ha actualizado el usuario");
							} else {
								if (cedulaExiste(cedula)) {
									JOptionPane.showMessageDialog(null, "La cedula ya esta en uso");
								} else {
									actualizarUsuario(cedulaAnterior, cedula, nombre, direccion, genero, email, edad);
									reset();
									JOptionPane.showMessageDialog(null, "Se ha actualizado el usuario");
								}
							}

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "Edad invalida");
						}
					}
				}
			}
		});
		
		btnActualizar.setForeground(Color.WHITE);
		btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
		btnActualizar.setBackground(Color.BLACK);
		btnActualizar.setBounds(240, 320, 100, 25);
		this.panelContenido2.add(btnActualizar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(370, 105, 630, 185);
		this.panelContenido2.add(scrollPane);

		tabla = new JTable();
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int fila = tabla.getSelectedRow();
				if (fila != -1) {

					txtCedula.setText(tabla.getValueAt(fila, 0).toString());
					txtNombre.setText(tabla.getValueAt(fila, 1).toString());
					txtDireccion.setText(tabla.getValueAt(fila, 2).toString());
					txtEdad.setText(tabla.getValueAt(fila, 3).toString());
					txtEmail.setText(tabla.getValueAt(fila, 4).toString());
					String genero = tabla.getValueAt(fila, 5).toString();
					if (genero.equals("M")) {
						cbGenero.setSelectedIndex(1);
					} else {
						cbGenero.setSelectedIndex(2);
					}


				}

			}
		});
		
		tabla.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Cedula", "Nombre", "Direccion", "Edad", "Email", "Genero" }));
		scrollPane.setViewportView(tabla);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setForeground(Color.WHITE);
		lblUsuarios.setFont(new Font("Arial", Font.BOLD, 30));
		lblUsuarios.setBounds(450, 20, 505, 45);
		this.panelContenido2.add(lblUsuarios);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int fila = tabla.getSelectedRow();
				if (fila != -1 && fila <= tabla.getRowCount() - 1) {

					int op = JOptionPane.showConfirmDialog(null, "Esta seguro de que quiere borrar el usuario seleccionado?");
					if (op == 0) {
						String id = tabla.getValueAt(fila, 0).toString();
						borrarUsuario(id);
						reset();
						JOptionPane.showMessageDialog(null, "Se ha borrado el usuario");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Seleccione un usuario para borrar");
				}

			}
		});
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Arial", Font.BOLD, 12));
		btnEliminar.setBackground(Color.BLACK);
		btnEliminar.setBounds(535, 320, 100, 25);
		this.panelContenido2.add(btnEliminar);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					conn.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				new VentanaPrincipal().setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
		btnVolver.setBackground(Color.BLACK);
		btnVolver.setBounds(775, 320, 100, 25);
		this.panelContenido2.add(btnVolver);

		conectar();
		subirDatos();

	}

	private void reset() {
		subirDatos();
		txtCedula.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtEdad.setText("");
		txtEmail.setText("");
		cbGenero.setSelectedIndex(0);
	}

	private void subirDatos() {

		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.getDataVector().clear();
		tabla.revalidate();

		try {

			String query = "Select * from usuarios";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String cedula = rs.getString("cedula");
				String nombre = rs.getString("nombre");
				String direccion = rs.getString("direccion");
				String genero = rs.getString("genero");
				String email = rs.getString("email");
				int edad = rs.getInt("edad");
				modelo.addRow(new Object[] { cedula, nombre, direccion, edad, email, genero });
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean cedulaExiste(String cedula) {
		try {

			String query = "Select cedula from users Where cedula = '" + cedula + "'";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			return rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void insertarUsuario(String cedula, String nombre, String direccion, String genero, String email, int edad) {
		try {

			String query = "Insert into usuarios (cedula, nombre, direccion, edad, email, genero) " + "values ('" + cedula + "','"
					+ nombre + "','" + direccion + "','" + edad + "','" + email + "','" + genero + "')";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void actualizarUsuario(String cedulaAnterior, String cedula, String nombre, String direccion, String genero, String email,
			int edad) {
		try {
			
			String query = "Update usuarios" + "	Set cedula='" + cedula + "', nombre='" + nombre + "', direccion='" + direccion
					+ "', edad='" + edad + "', email='" + email + "', genero='" + genero + "'" + "	Where cedula = '"
					+ cedulaAnterior + "';";
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void borrarUsuario(String cedula) {
		try {

			String query = "Delete from public.usuarios" 
			+ "  Where cedula = '"+ cedula + "';";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void conectar() {
		try {
			Class.forName(driverDB);	
			this.conn = DriverManager.getConnection(urlDB, usuarioDB, passDB);
			if (conn != null) {
				JOptionPane.showMessageDialog(null, "Conexion exitosa");
			}
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
		}
	}
}