package es.deusto.prog3.cap01;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** Ejemplo de utilizaci�n de sockets para comunicar un programa "servidor"
 * con un "cliente" en el mismo equipo. El cliente puede enviar textos
 * al servidor, que env�a un mensaje de confirmaci�n con cada texto.
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class EjemploSockets {

	private static String HOST = "localhost";  // IP de conexi�n para la comunicaci�n
	private static int PUERTO = 4000;          // Puerto de conexi�n
	
	private static VentanaServidor vs;
	private static VentanaCliente vc;
	public static void main(String[] args) {
		vs = new VentanaServidor();
		vs.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vs.lanzaServidor();
			}
		}).start();
		vc = new VentanaCliente();
		vc.setVisible( true );
		(new Thread() {
			@Override
			public void run() {
				vc.lanzaCliente();
			}
		}).start();
	}

	@SuppressWarnings("serial")
	public static class VentanaCliente extends JFrame {
		JLabel lEstado = new JLabel( " " );
		JTextField tfMensaje = new JTextField( "Introduce tu mensaje y pulsa <Enter>" );
        PrintWriter flujoOut;
        boolean finComunicacion = false;
		public VentanaCliente() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			setTitle( "Ventana cliente - 'fin' acaba" );
			getContentPane().add( tfMensaje, BorderLayout.NORTH );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			tfMensaje.addFocusListener( new FocusAdapter() { // Selecciona todo el texto del cuadro en cuanto se le da el foco del teclado
				@Override
				public void focusGained(FocusEvent e) {
					tfMensaje.selectAll();
				}
			});
			tfMensaje.addActionListener( new ActionListener() { // Evento de <enter> de textfield
				@Override
				public void actionPerformed(ActionEvent e) {
					flujoOut.println( tfMensaje.getText() );
					if (tfMensaje.getText().equals("fin"))
						finComunicacion = true;
					tfMensaje.setText( "" );
				}
			});
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					flujoOut.println( "fin" );
					finComunicacion = true;
				}
			});
		}
	    public void lanzaCliente() {
	        try (Socket socket = new Socket( HOST, PUERTO )) {
	            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            flujoOut = new PrintWriter(socket.getOutputStream(), true);
	            do {
	            	String feedback = echoes.readLine();  // Devuelve mensaje de servidor o null cuando se cierra la comunicaci�n
	            	if (feedback!=null) {
	            		lEstado.setText( feedback );
	            	} else {  // Comunicaci�n cortada por el servidor o por error en comunicaci�n
	            		finComunicacion = true;
	            	}
	            } while(!finComunicacion);
	        } catch (IOException e) {
            	lEstado.setText( "Error en cliente: " + e.getMessage());
	        }
	        lEstado.setText( "Fin de proceso de cliente." );
	    }
	}
	    
	@SuppressWarnings("serial")
	public static class VentanaServidor extends JFrame {
		JLabel lEstado = new JLabel( " " );
		JTextArea taMensajes = new JTextArea( 10, 1 );
        boolean finComunicacion = false;
        Socket socket;
		public VentanaServidor() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 400, 0 );
			setTitle( "Ventana servidor" );
			getContentPane().add( new JScrollPane(taMensajes), BorderLayout.CENTER );
			getContentPane().add( lEstado, BorderLayout.SOUTH );
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						socket.close();
					} catch (IOException e1) {
			    		lEstado.setText("Error en servidor: " + e1.getMessage());
					}
					finComunicacion = true;
				}
			});
		}
	    public void lanzaServidor() {
	    	try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {
	    		socket = serverSocket.accept();
	    		lEstado.setText( "Cliente conectado" );
	    		BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    		PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
	    		while(!finComunicacion) {
	    			String textoRecibido = input.readLine();
	    			if(textoRecibido.equals("fin")) {
	    				break;
	    			}
	    			lEstado.setText( "Recibido de cliente: [" + textoRecibido + "]" );
	    			taMensajes.append( textoRecibido + "\n" );
	    			taMensajes.setSelectionStart( taMensajes.getText().length() );
	    			output.println("Recibido: [" + textoRecibido + "]" );
	    		}
	    		lEstado.setText( "El cliente se ha desconectado." );
	    		socket.close();
	    	} catch(IOException e) {
	    		lEstado.setText("Error en servidor: " + e.getMessage());
	    	}
	    }
	}

}