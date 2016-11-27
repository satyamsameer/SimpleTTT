/*
 * This SimpleBoard Game on Tic-Tc-Toe is ceated by Sameer Satyam.
 * General public don not use any code copied directly from the source without any permissions.
 */
package TTT;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class SimpleBoard.
 * 
 * A Simple Tic-Tc-Toe Java based GUI game implementation
	 * - Two players one player is User and other one is computer.
	 * - Have three modes Easy, Medium and Hard (Unbeatable)
	 * - Fixed Priority and Rule based logic is implemented in this software
 */
public class SimpleBoard extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The win combinations. */
	private int[][] winCombinations = new int[][] {
			{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, //horizontal wins
			{0, 4, 8}, {2, 4, 6},             //diagonal wins
			{0, 3, 6}, {1, 4, 7}, {2, 5, 8} //vertical wins
	};

	/** The buttons. */
	private JButton buttons[]=new JButton[9]; // GUI buttons array
	
	/** The btn new button 2. */
	JButton btnNewButton_2 = new JButton("X"); //For computer turn button
	
	/** The letter. */
	private String letter = "O";
	
	/** The count. */
	private int count = 0;
	
	/** The win. */
	private boolean win = false;
	
	/** The lbl new label. */
	JLabel lblNewLabel = new JLabel("");
	
	/** The choice. */
	String[] choice = {"Easy", "Medium", "Hard"};  // Easy-random Medium-counter measures Hard-Unbeatable
	
	/** The combox. */
	JComboBox<String> combox=new JComboBox<String>(choice);  // Combobox for changing mode

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
					SimpleBoard frame = new SimpleBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Instantiates a new simple board. - Frame for the Game as Constructor
	 */
	public SimpleBoard() {
		setTitle("Tic-Tac-Toe");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 291, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*Above Panel*/
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
		lblNewLabel.setText("Start - Player-O Computer-X.");
		panel_1.add(lblNewLabel);

		/*Tool buttons*/
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttons=null;
				System.exit(0);
			}
		});
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.setForeground(new Color(0, 204, 0));
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		panel_1.add(btnNewButton_1);

		btnNewButton_2.setForeground(Color.white);
		btnNewButton_2.setBackground(Color.red);
		btnNewButton_2.setToolTipText("Computer turn first!");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runTurn();
			}
		});
		panel_1.add(btnNewButton_2);

		/*Hard Checkbox*/
		combox.setBackground(Color.WHITE);
		combox.setForeground(Color.BLACK);
		combox.setSelectedIndex(0);
		panel_1.add(combox);

		JLabel lblNewLabel_1 = new JLabel("Created by - Sameer Satyam");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 11));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(58, 371, 167, 14);
		contentPane.add(lblNewLabel_1);
	}

	/**
	 * Checks if is win.
	 */
	public void isWin(){
		/*Determine who won*/
		for(int i=0; i<=7; i++){
			if( buttons[winCombinations[i][0]].getText().equals(buttons[winCombinations[i][1]].getText()) && buttons[winCombinations[i][1]].getText().equals(buttons[winCombinations[i][2]].getText()) && buttons[winCombinations[i][0]].getText() != ""){
				win = true;
				break;
			}
		}
	}

	/**
	 * Update label.
	 *
	 * @param sw the sw
	 */
	public void updateLabel(int sw){
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setText("Start The Game- Player O turn.");
		switch(sw){
		case 0: lblNewLabel.setText("Computer X move top-left.");break;
		case 1: lblNewLabel.setText("Computer X move top.");break;
		case 2: lblNewLabel.setText("Computer X move top-right.");break;
		case 3: lblNewLabel.setText("Computer X move left.");break;
		case 4: lblNewLabel.setText("Computer X move middle.");break;
		case 5: lblNewLabel.setText("Computer X move right.");break;
		case 6: lblNewLabel.setText("Computer X move bottom-left.");break;
		case 7: lblNewLabel.setText("Computer X move bottom.");break;
		case 8: lblNewLabel.setText("Computer X move bottom-right.");break;
		}
	}

	/**
	 * Update B.
	 *
	 * @param inde the inde
	 * @param c the c
	 */
	public void updateB(int inde,String c){
		buttons[inde].setFont(new Font("Tahoma", Font.PLAIN, 65));
		if(buttons[inde].getText().equals("")){
			buttons[inde].setText(c);
			buttons[inde].setForeground(Color.WHITE);
			if(buttons[inde].getText().equalsIgnoreCase("X")){
				buttons[inde].setBackground(Color.RED);
			}else{
				buttons[inde].setBackground(Color.BLUE);
			}
			buttons[inde].setEnabled(false);
			count++;
			//System.out.println("count -"+count+" button -"+inde);
			updateLabel(inde);
		}
	}

	/**
	 * Run turn.
	 */
	public void runTurn(){
		boolean checkings=true;
		int x2=0;
		if(combox.getSelectedIndex()==0){ //For easy mode
			while(checkings){  //For random at any empty position
				x2 = (new Random()).nextInt(9-0) +0;
				if(buttons[x2].getText().equals("")){
					updateB(x2,"X");
					checkings=false;
				}
			}
		}else{  //For medium and hard mode
			int[] corner={0,2,6,8};   //For corner empty buttons
			while (checkings){
				x2 = (new Random()).nextInt(4-0) +0;
				if (buttons[corner[x2]].getText().equals("")) {  // is empty
					checkings = false;  // if so, set to false
				}
			}
			updateB(corner[x2],"X");
		}
		updateLabel(x2);
		btnNewButton_2.setEnabled(false);
		combox.setEnabled(false);
	}

	/**
	 * Form line. - Check for winning lines
	 *
	 * @param symbol the symbol
	 * @return the int
	 */
	public int formLine(String symbol){  //-1 if no button is found and button number if one is found
		for(int i=0;i<winCombinations.length;i++){
			if(	buttons[winCombinations[i][0]].getText().equals("") && buttons[winCombinations[i][1]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][2]].getText().equalsIgnoreCase(symbol)){
				return winCombinations[i][0];
			}
			else if (buttons[winCombinations[i][0]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][1]].getText().equals("") && buttons[winCombinations[i][2]].getText().equalsIgnoreCase(symbol)){
				return winCombinations[i][1];
			}
			else if(buttons[winCombinations[i][0]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][1]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][2]].getText().equals("")){
				return winCombinations[i][2];
			}
		}
		return -1;
	}

	/**
	 * Form fork. - Create an opportunity where you have two threats to win
	 *
	 * @param symbol the symbol
	 * @return the int
	 */
	public int formFork(String symbol){  //-1 if no button is found and button number if one is found
		for(int k=0;k<9;k++){
			if(buttons[k].getText().equals("")){
				buttons[k].setText(symbol);
				int cl=0;
				for(int i=0;i<winCombinations.length;i++){
					if(	buttons[winCombinations[i][0]].getText().equals("") && buttons[winCombinations[i][1]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][2]].getText().equalsIgnoreCase(symbol)){
						cl++;
					}
					else if (buttons[winCombinations[i][0]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][1]].getText().equals("") && buttons[winCombinations[i][2]].getText().equalsIgnoreCase(symbol)){
						cl++;
					}
					else if(buttons[winCombinations[i][0]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][1]].getText().equalsIgnoreCase(symbol) && buttons[winCombinations[i][2]].getText().equals("")){
						cl++;
					}
				}
				if(cl>=2){
					buttons[k].setText("");
					return k;
				}
				buttons[k].setText("");
			}
		}
		return -1;
	}

	/**
	 * Draw random.
	 */
	public void drawRandom(){  //Main automation function
		boolean checking = true;
		int x=0;
		if(combox.getSelectedIndex()==0){  //for Easy mode
			while(checking){ 
				x = (new Random()).nextInt(9-0) +0;  //For random at any empty position
				if(buttons[x].getText().equals("")){
					updateB(x,"X");
					checking=false;
				}
			}
		}else{  // For both Medium and Hard mode
			if(formLine("X")!=-1){   //For computer perfect win
				updateB(formLine("X"),"X");
				//System.out.println("1");
			}else if(formLine("O")!=-1){  //Counter's Opponents win
				updateB(formLine("O"),"X");
				//System.out.println("2");
			}else if(formFork("X")!=-1&&combox.getSelectedIndex()==2){ // For only Hard mode
				updateB(formFork("X"),"X");
				//System.out.println("Fork 1");
			}else if(formFork("O")!=-1&&combox.getSelectedIndex()==2){  //Counter's Opponents Fork & For only Hard mode
				int comp=0;
				for(int k=0;k<9;k++){
					if(buttons[k].getText().equals("")){
						buttons[k].setText("X");
						int line=formLine("X");
						if(line!=-1){
							if(line!=formFork("O")){buttons[k].setText("");comp=k;/*System.out.println("Fork 2");*/break;}
						}
						buttons[k].setText("");
					}
				}
				updateB(comp,"X");
			}
			else if(buttons[4].getText()==""){  //If middle is empty
				updateB(4,"X");
			}else{
				if(combox.getSelectedIndex()==2){  // For only Hard mode
					Set<Integer> set = new HashSet<Integer>();
					Integer[] corner={0,2,6,8};  //For corner empty buttons
					Set<Integer> targetSet = new HashSet<Integer>(Arrays.asList(corner));
					while (checking){
						x = (new Random()).nextInt(4-0) +0;
						set.add(corner[x]);
						if (buttons[corner[x]].getText().equals("")) {   // is empty
							checking = false; // if so, set to false
							//System.out.println("Hard Corner Move");
							updateB(corner[x],"X");
						}
						else if(set.containsAll(targetSet)){ //When no corner is empty then put at random position
							boolean checking1=true;
							while(checking1){  //For random at any empty position
								x = (new Random()).nextInt(9-0) +0;
								if(buttons[x].getText().equals("")){
									checking=false;
									checking1=false;
									//System.out.println("Hard random move");
									updateB(x,"X");
								}
							}
						}
					}
				}else{
					while(checking){  //For random at any empty position
						x = (new Random()).nextInt(9-0) +0;
						if(buttons[x].getText().equals("")){
							updateB(x,"X");
							checking=false;
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/*For Actions performed on button click*/
	@Override
	public void actionPerformed(ActionEvent a) {
		letter = "O";
		for(int i=0;i<buttons.length;i++){
			if(a.getSource()==buttons[i]){
				updateB(i,letter);
				isWin();  //check for letter "O"
				if(win==true)
					break;
				letter = "X";
				if(count<9&&win==false){
					drawRandom();
					isWin();   //check for letter "X"
					if(win==true)
						break;
				}
				break;
			}
			btnNewButton_2.setEnabled(false);
			combox.setEnabled(false);
		}
		/*Message*/
		if(win == true){
			JOptionPane.showMessageDialog(null, letter + " wins the game!","Congratulations !",JOptionPane.INFORMATION_MESSAGE);
			reset();
			combox.setEnabled(true);
			btnNewButton_2.setEnabled(true);
		} else if(count == 9 && win == false){
			JOptionPane.showMessageDialog(null,"Game draw!","Oops !",JOptionPane.PLAIN_MESSAGE);
			reset();
			combox.setEnabled(true);
			btnNewButton_2.setEnabled(true);
		}

	}

	/**
	 * Reset.
	 */
	public void reset(){
		letter="O";
		win=false;
		count=0;
		for(int i=0;i<9;i++){
			buttons[i].setText("");
			buttons[i].setEnabled(true);
			buttons[i].setBackground(SystemColor.menu);
		}
		combox.setEnabled(true);
		btnNewButton_2.setEnabled(true);
		lblNewLabel.setForeground(Color.white);
		lblNewLabel.setText("Start - Player-O Computer-X.");
	}
}