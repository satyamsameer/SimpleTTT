/*
 * This SimpleBoard2P Game on Tic-Tc-Toe is ceated by Sameer Satyam [satyamsameer].
 * Licensed under
 * GNU GENERAL PUBLIC LICENSE   Version 3, 29 June 2007
 * General public do not use any code copied directly from the source without the permissions.
 */
package TTT;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.border.*;
import javax.swing.*;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleBoard.
 * 
 * A Simple Tic-Tc-Toe Java based GUI game implementation
	 * - In Single Player one player is user and other one is computer.
	 * - In two player both players have the their turns one by one
	 * - Have three modes Easy, Medium and Hard (Unbeatable)
	 * - Fixed Priority and Rule based logic is implemented in this software
 */
public class SimpleBoard2P extends JFrame implements ActionListener{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The win combinations. */
	private int[][] winCombinations = new int[][] {
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
			{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, //vertical wins
			{0, 4, 8}, {2, 4, 6}             //diagonal wins
	};

	/** The buttons. */
	private JButton buttons[]=new JButton[9];
	
	/** The letter. */
	private String letter = "O";
	
	/** The count. */
	private int count = 0;
	
	/** The win. */
	private boolean win = false;
	
	/** The lbl new label. */
	JLabel lblNewLabel = new JLabel("New label");

	/**
	 * Instantiates a new simple board 2 P.
	 */
	/*Frame for the Game*/
	public SimpleBoard2P() {
		setTitle("Tic-Tac-Toe: Two P");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(291, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 264, 264);
		contentPane.add(panel);


		panel.setLayout(new GridLayout(3, 3));
		for(int i=0;i<=8;i++){
			buttons[i]=new JButton("");
			panel.add(buttons[i]);
			buttons[i].setBackground(SystemColor.menu);
			buttons[i].addActionListener(this);
		}

		/*Below Panel*/
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 286, 264, 74);
		contentPane.add(panel_1);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBackground(new Color(255, 102, 0));

		lblNewLabel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lblNewLabel.setPreferredSize(new Dimension(250, 25));
		lblNewLabel.setMinimumSize(new Dimension(270, 30));
		lblNewLabel.setMaximumSize(new Dimension(270, 30));
		lblNewLabel.setText("Start The Game- Player X turn.");
		panel_1.add(lblNewLabel);

		/*Tool buttons*/
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.setToolTipText("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setForeground(new Color(0, 204, 0));
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.setToolTipText("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_1 = new JLabel("Copyright \u00a9 2016 Sameer Satyam");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 371, 200, 14);
		contentPane.add(lblNewLabel_1);

		/*JMenu*/
		JMenuBar mb = new JMenuBar();
		JMenu switchg = new JMenu(" Gameplay");
		switchg.setMnemonic('g');
		switchg.setToolTipText("Switch Gameplay");
		JMenuItem onep=new JMenuItem("Single player");
		onep.setToolTipText("Single player Gameplay");
		onep.setMnemonic('s');
		onep.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK | ActionEvent.SHIFT_MASK));//Open PDF
		onep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttons=null;
				contentPane=null;
				new SimpleBoard();
				dispose();
			}
		});
		switchg.add(onep);
		mb.add(switchg);
		setJMenuBar(mb);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/*For Actions performed on button click*/
	public void actionPerformed(ActionEvent a) {
		count++;

		/*Calculate whose turn it is*/
		if(count % 2 == 0){
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setText("Player "+letter+" turn.");
			letter = "O";
		} else {
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setText("Player "+letter+" turn.");
			letter = "X";
		}

		/*Write the letter to the button and deactivate it*/
		JButton pressedButton = (JButton)a.getSource();
		pressedButton.setFont(new Font("Tahoma", Font.PLAIN, 65));
		pressedButton.setText(letter);
		pressedButton.setForeground(Color.WHITE);
		if(letter=="X"){
			pressedButton.setBackground(Color.RED);
		}
		else{
			pressedButton.setBackground(Color.BLUE);
		}
		pressedButton.setEnabled(false);


		/*Determine who won*/
		for(int i=0; i<=7; i++){
			if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) && buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) && buttons[winCombinations[i][0]].getText() != ""){
				win = true;
			}
		}
		/*Message*/
		if(win == true){
			JOptionPane.showMessageDialog(null, letter + " wins the game!");
			reset();
		} else if(count == 9 && win == false){
			JOptionPane.showMessageDialog(null, "Game draw!");
			reset();
		}
	}
	
	/**
	 * Reset.
	 */
	/*Rest the Game*/
	public void reset(){
		letter="O";
		win=false;
		count=0;
		for(int i=0;i<9;i++){
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(SystemColor.menu);
			//buttons[i].setForeground(Color.BLACK);
			lblNewLabel.setForeground(Color.white);
			lblNewLabel.setText("Start The Game- Player X turn.");
		}
	}
}
