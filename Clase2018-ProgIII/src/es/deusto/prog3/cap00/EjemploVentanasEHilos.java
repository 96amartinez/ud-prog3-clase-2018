package es.deusto.prog3.cap00;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EjemploVentanasEHilos {

	private static JFrame f;
	private static Thread t;
	private static boolean pausa;
	public static void main(String[] args) {
		f = new JFrame();
		f.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		f.setSize( 640, 480 );
		f.setLocation( 2000, 100 );
		JButton b = new JButton( "Haz!" );
		f.getContentPane().add( b );
		final int i = 4;
		b.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println( "Hola" + i );
				if (t==null) {
					t = new Thread( new Runnable() {
						@Override
						public void run() {
							// TODO Auto-generated method stub
							pausa = false;
							int i = 0;
							while (true) {
								if (!pausa) {
									System.out.println( "Mens " + i);
									i++;
								}
								try { Thread.sleep( 1000 );
								} catch (InterruptedException e) {
								}
							}
							
						}
					});
					t.start();
				} else if (pausa) {
					pausa = false;
				} else {
					pausa = true;
				}
			}
		});
		f.getContentPane().addKeyListener( new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		f.getContentPane().addMouseListener( new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		f.setVisible( true );
		// System.out.println( "hola" );
		
	}

}
