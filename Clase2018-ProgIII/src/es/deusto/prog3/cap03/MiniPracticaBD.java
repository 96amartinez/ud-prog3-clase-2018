package es.deusto.prog3.cap03;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MiniPracticaBD {

	private static Connection con;
	private static Statement s;
	private static ResultSet rs;
	public static void main(String[] args) {
		String com = "";
		try {
			Class.forName( "org.sqlite.JDBC" );
			con = DriverManager.getConnection( "jdbc:sqlite:test.db" );
			s = con.createStatement();
			try {
				com = "create table Usuario( nick STRING, pass STRING )";
				s.executeUpdate( com );
			} catch (SQLException e) {} // Se lanza si la tabla ya existe - no hay problema
			// Ver si existe admin
			com = "select * from Usuario where nick = 'admin'";
			rs = s.executeQuery( com );
			if (!rs.next()) { // Añadirlo si no existe
				com = "insert into Usuario ( nick, pass ) values ('admin', 'admin')";
				s.executeUpdate( com );
			}
			anyadirUsuarios();
			/*
			rs.close();
			s.close();
			con.close();
			*/
		} catch (SQLException|ClassNotFoundException e) {
			System.out.println( "Último comando: " + com );
			e.printStackTrace();
		}
	}
	
	private static JTextField tfUsuario = new JTextField( "", 10 );
	private static JTextField tfPassword = new JTextField( "", 10 );
	private static void anyadirUsuarios() {
		JFrame f = new JFrame( "Añadir usuarios" );
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		JPanel p = new JPanel();
		p.add( new JLabel( "Nick: ") ); p.add( tfUsuario );
		f.add( p, BorderLayout.NORTH );
		p = new JPanel();
		p.add( new JLabel( "Password: ") ); p.add( tfPassword );
		f.add( p, BorderLayout.CENTER );
		p = new JPanel();
		JButton b = new JButton( "Añadir usuario" );
		p.add( b );
		JButton b2 = new JButton( "Borrar nick" );
		p.add( b2 );
		f.add( p, BorderLayout.SOUTH );
		f.pack();
		f.setVisible( true );
		b.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfUsuario.getText().isEmpty() && !tfPassword.getText().isEmpty()) {
					String com = "";
					try {
						// Ver si existe usuario
						// "select * from Usuario where nick = 'admin'";
						// Si queremos asegurar el string habría que hacer algo así...
						// String nick = tfUsuario.getText().replaceAll( "'", "''" );
						// ...si no, cuidado con lo que venga en el campo de entrada.
						com = "select * from Usuario where nick = '" + tfUsuario.getText() + "'";
						rs = s.executeQuery( com );
						if (!rs.next()) {
							// "insert into Usuario ( nick, pass ) values ('admin', 'admin')";
							com = "insert into Usuario ( nick, pass ) values ('"+ 
									tfUsuario.getText() +"', '" + tfPassword.getText() + "')";
							s.executeUpdate( com );
						} else {
							JOptionPane.showMessageDialog( null, "Usuario ya existe" );
						}
					} catch (SQLException e2) {
						System.out.println( "Último comando: " + com );
						e2.printStackTrace();
					}
				}
			}
		});
		b2.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!tfUsuario.getText().isEmpty() && !tfPassword.getText().isEmpty()) {
					String com = "";
					try {
						// Borrar usuario
						com = "delete from Usuario where nick = '"+ tfUsuario.getText() +"'";
						s.executeUpdate( com );
					} catch (SQLException e2) {
						System.out.println( "Último comando: " + com );
						e2.printStackTrace();
					}
				}
			}
		});
		f.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					rs.close();
					s.close();
					con.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
	}

}


// OJO CON SQL INJECTION!!!!!
//
//  Por ejemplo si se mete en la casilla de borrado...
//   a'; drop table usuario; --
//
