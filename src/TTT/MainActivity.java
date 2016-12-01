/*
 * This MainActivity on Tic-Tc-Toe is created by Sameer Satyam [satyamsameer].
 * Licensed under
 * GNU GENERAL PUBLIC LICENSE   Version 3, 29 June 2007
 * General public do not use any code copied directly from the source without the permissions.
 */
package TTT;

import java.awt.EventQueue;

import javax.swing.UIManager;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity {
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					new SimpleBoard();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}