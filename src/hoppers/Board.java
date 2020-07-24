package hoppers;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * {@code Board} is a class that abstracts a checker board and allows Squares to be placed on.
 * 
 * @author	Hao Yukun	(GitHub: <a href="https://github.com/Night-Voyager">Night-Voyager</a>)
 * @author	Huo Jiaxin	(GitHub: <a href="https://github.com/Huo614">Huo614</a>)
 * 
 * @version 2020/5/24
 */
public class Board extends JFrame {
	
	/** The level of the game. */
	private int level = 1;
	
	/**
	 * The state of the game.
	 * 
	 * <p>- State.WIN, or 1, if the player wins a level of the game
	 * <p>- State.IN_PROGRESS, or 0, if the game is in progress
	 * <p>- State.LOSE, or -1, if the player loses a level of the game
	 * 
	 * @see State
	 */
	private int state = State.IN_PROGRESS;
	
	/** The stream of the audio to be played. */
	private static Clip clip;
	
	/**
	 * Contains all the states of the game.
	 * @author Hao Yukun (GitHub: <a href="https://github.com/Night-Voyager">Night-Voyager</a>)
	 */
	public class State {
		
		/** Constant for the state that the game is lost, the value is {@value}. */
		public static final int LOSE = -1;
		
		/** Constant for the state that the game is in progress, the value is {@value}. */
		public static final int IN_PROGRESS = 0;
		
		/** Constant for the state that the game is successfully finished, the value is {@value}. */
		public static final int WIN = 1;
	}
	
	/**
	 * An ArrayList that stores all the Square objects of the Board.
	 * @see Square
	 */
	ArrayList<Square> squares = new ArrayList<>();
	
	/** The timer that counts the time that the player uses to win a level. */
	static Timer timer=new Timer();
	
	/**
	 * The TimerTask object that used to control the tasks of the timer.
	 * @see Timing
	 */
	static Timing timing;
	
	/**
	 * Non-parameter constructor of Board.
	 */
	public Board() {
		this(1);
	}
	
	/**
	 * Constructor of Board with one parameter.
	 * @param level The desired level of the game.
	 */
	public Board(int level) {
		this(level, State.IN_PROGRESS);
	}
	
	/**
	 * Constructor of Board with two parameters.
	 * @param level The desired level of the game.
	 * @param state The desired state of the game.
	 */
	public Board(int level, int state) {
		super("Hoppers Game by Team 3");
		
		this.setSize(750, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false); 
		this.setVisible(false);
		
		this.level = level;
		this.state = state;
		
		for (int i=1; i<=5; i++) {
			for (int j=1; j<=5; j++) {
				squares.add(new Square(i, j));
				this.add(squares.get(squares.size()-1));
			}
		}
		this.setLayout(new GridLayout(5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menu1 = new JMenu("       Select level       ");
		menuBar.add(menu1);
		JMenu menu2 = new JMenu("       Tips       ");
		menuBar.add(menu2);
		JMenu menu3 = new JMenu("       Music control       ");
		menuBar.add(menu3);
		JMenu menu4 = new JMenu("       Quit       ");
		menuBar.add(menu4);
		
		/* Implement the functions in the menu bar */
		JMenuItem jMenuItem1=new JMenuItem("previous level");
		JMenuItem jMenuItem2=new JMenuItem("next level");
		JMenuItem jMenuItem3=new JMenuItem("Jump to specified level");
		JMenuItem jMenuItem4=new JMenuItem("Restart current level");
		JMenuItem jMenuItem5=new JMenuItem("exit");
		JMenuItem jMenuItem6=new JMenuItem("stop the music");
		JMenuItem jMenuItem7=new JMenuItem("View the tip for the current level");
		JMenuItem jMenuItem8=new JMenuItem("Background music 1");
		JMenuItem jMenuItem9=new JMenuItem("Background music 2");
		 
		menu1.add(jMenuItem1);
		menu1.add(jMenuItem2);
		menu1.add(jMenuItem3);
		menu1.add(jMenuItem4);
		menu2.add(jMenuItem7);
		menu4.add(jMenuItem5);
		menu3.add(jMenuItem8);
		menu3.add(jMenuItem9);
		menu3.add(jMenuItem6);
		 
		jMenuItem1.addActionListener(new ActionListener() {//Select the previous level
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getLevel()==1) {
					Object[] options = {"OK"}; 
					JOptionPane.showOptionDialog(Board.this, "This is already the first level. ", "Warning ", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE,null, options, options[0]);
				}
				else
					prevLevel();
			}
			
		});
		
		jMenuItem2.addActionListener(new ActionListener() {//Select the next level
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getLevel()==4) {
					Object[] options = { "OK"};
					JOptionPane.showOptionDialog(Board.this, "This is already the last level. ", "Warning ", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE,null, options, options[0]);
				}
				else
					nextLevel();
			}
			
		});
		
		jMenuItem3.addActionListener(new ActionListener() {//jump to any level
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] obj2 ={"1","2","3","4"};  
				String s = (String) JOptionPane.showInputDialog(Board.this,"Please enter the level you want to play:\n", "level", JOptionPane.PLAIN_MESSAGE, null, obj2, "1"); 
				if (s.equals("1"))
				{
					setLevel(1);
				}
				else if(s.equals("2"))
				{
					setLevel(2);
				}
				else if(s.equals("3"))
				{
					setLevel(3);
				}
				else if(s.equals("4"))
				{
					setLevel(4);
				}
			}
			
		});
		
		
		jMenuItem4.addActionListener(new ActionListener() {//Restart the current level
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setLevel(getLevel());
			}
			
		});
		
		jMenuItem5.addActionListener(new ActionListener() {//Exit the program
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		jMenuItem6.addActionListener(new ActionListener() {//stop the music
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Board.this.stopBackgroundMusic();
			}
			
		});
		
		jMenuItem7.addActionListener(new ActionListener() {//View the tip for the current level
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tips();
			}
			
		});
		
		jMenuItem8.addActionListener(new ActionListener() {//change the background music
			
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundMusic1();
			}
			
		});
		
		jMenuItem9.addActionListener(new ActionListener() {//change the background music
			
			@Override
			public void actionPerformed(ActionEvent e) {
				backgroundMusic2();
			}

		});
		
		this.refresh();
	}
	
	/**
	 * Accessor of level.
	 * @return The number of current game level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Mutator of level.
	 * @param level The desired level of the game.
	 */
	public void setLevel(int level) {
		timing.cancel();
		setState(State.LOSE);
		if (level==1||level==2||level==3||level==4) {
			this.level = level;
			refresh();
		}
		else
			JOptionPane.showMessageDialog(this, "Error", "This level is not included in the game.", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Back to the previous level.
	 */
	public void prevLevel() {
		if (level>1)
			setLevel(level-1);
		else
			JOptionPane.showMessageDialog(this, "This is the first level of this game.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Go to the next level.
	 */
	public void nextLevel() {
		if (level<4)
			setLevel(level+1);
		else
			JOptionPane.showMessageDialog(this, "This is the last level of this game.", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Accessor of state.
	 */
	public int getState() {
		int redFrogNum = Square.getNumOfRedFrog();
		int greenFrogNum = Square.getNumOfGreenFrog();
		
		if (redFrogNum==0)
			this.setState(State.LOSE);
		else if (greenFrogNum==0)
			this.setState(State.WIN);
		else
			this.setState(State.IN_PROGRESS);
		
		return state;
	}
	
	/**
	 * Mutator of state.
	 * @param state The desired state of the game.
	 */
	public void setState(int state) {
		this.state = state;
	}
	
	/**
	 * Refresh the Board, then all Squares will be set to their original place of the current level.
	 */
	public void refresh() {
		
		setState(State.IN_PROGRESS);
		timing = new Timing();
		try {
			timer.schedule(timing,2);
		}
		catch (Exception e) {
			;
		}
		
		for (int i=0; i<25; i++) {
			if (i%2==0)
				squares.get(i).setType(Square.Type.LilyPad);
			else
				squares.get(i).setType(Square.Type.Water);
		}
		
		switch (level) {
		case 1:
			squares.get(0).setType(Square.Type.RedFrog);
			squares.get(6).setType(Square.Type.GreenFrog);
			squares.get(18).setType(Square.Type.GreenFrog);
			break;
		case 2:
			squares.get(2).setType(Square.Type.RedFrog);
			squares.get(6).setType(Square.Type.GreenFrog);
			squares.get(8).setType(Square.Type.GreenFrog);
			squares.get(16).setType(Square.Type.GreenFrog);
			squares.get(18).setType(Square.Type.GreenFrog);
			break;
		case 3:
			squares.get(2).setType(Square.Type.GreenFrog);
			squares.get(10).setType(Square.Type.GreenFrog);
			squares.get(14).setType(Square.Type.GreenFrog);
			squares.get(16).setType(Square.Type.GreenFrog);
			squares.get(24).setType(Square.Type.GreenFrog);
			squares.get(22).setType(Square.Type.RedFrog);
			break;
		case 4:
			squares.get(0).setType(Square.Type.GreenFrog);
			squares.get(2).setType(Square.Type.GreenFrog);
			squares.get(8).setType(Square.Type.GreenFrog);
			squares.get(16).setType(Square.Type.GreenFrog);
			squares.get(18).setType(Square.Type.GreenFrog);
			squares.get(22).setType(Square.Type.GreenFrog);
			squares.get(10).setType(Square.Type.RedFrog);
			break;
		}
		
	}
	
	/**
	 * Get a square by its index.
	 * @param index The index of the desired square.
	 * @return The desired square.
	 */
	public Square getSquare(int index) {
		return squares.get(index);
	}
	
	/**
	 * Get a square by its position.
	 * @param positionX The position x of the desired square.
	 * @param positionY The position y of the desired square.
	 * @return The desired square.
	 */
	public Square getSquare(int positionX, int positionY) {
		return squares.get((positionX-1)*5+(positionY-1));
	}
	
	/**
	 * Judge whether a square is movable to another square or not.
	 * @param sq1 The first chosen square
	 * @param sq2 The second chosen square
	 * @return The boolean value of whether the first square is movable or not
	 */
	public boolean isMovable(Square sq1, Square sq2) {
		if (sq1.getType()==Square.Type.GreenFrog2 || sq1.getType()==Square.Type.RedFrog2) {
			if (sq2.getType()==Square.Type.LilyPad) {
				int x1 = sq1.getPositionX();
				int y1 = sq1.getPositionY();
				int x2 = sq2.getPositionX();
				int y2 = sq2.getPositionY();
				
				try {
					Square sq_mid = this.getSquare((x1+x2)/2, (y1+y2)/2);
					
					if (sq_mid.getType()==Square.Type.GreenFrog || sq_mid.getType()==Square.Type.RedFrog) {
						return true;
					}
				}
				catch (Exception ex) {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * To give tips of the current level for the player.
	 */
	public void tips() {
		JFrame tipsFrame = new JFrame("Tip");
		ImageIcon tipsImage = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"tipsImages/null.png"));
		JLabel tipsLabel = new JLabel(tipsImage);
		
		tipsLabel.setBounds(0, 0, tipsImage.getIconWidth(), tipsImage.getIconHeight());
		tipsFrame.setSize(tipsImage.getIconWidth()+20, tipsImage.getIconHeight()+40);
		tipsFrame.setResizable(false);
		tipsFrame.add(tipsLabel);
		tipsFrame.setVisible(true);
		
		switch (level) {
		case 1:
			tipsLabel.setIcon(new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"tipsImages/level1.gif")));
			break;
		case 2:
			tipsLabel.setIcon(new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"tipsImages/level2.gif")));
			break;
		case 3:
			tipsLabel.setIcon(new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"tipsImages/level3.gif")));
			break;
		case 4:
			tipsLabel.setIcon(new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"tipsImages/level4.gif")));
			break;
		}
		/*
		switch (level) {
		case 1:
			this.getSquare(1, 1).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(1, 1).moveTo(this.getSquare(3, 3));
			sleep(1000);
			
			this.getSquare(3, 3).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(3, 3).moveTo(this.getSquare(5, 5));
			sleep(1000);
			
			break;
			
		case 2:
			this.getSquare(1, 3).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(1, 3).moveTo(this.getSquare(3, 1));
			sleep(1000);
			
			this.getSquare(3, 1).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(3, 1).moveTo(this.getSquare(5, 3));
			sleep(1000);
			
			this.getSquare(5, 3).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(5, 3).moveTo(this.getSquare(3, 5));
			sleep(1000);
			
			this.getSquare(3, 5).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(3, 5).moveTo(this.getSquare(1, 3));
			sleep(1000);
			
			break;
			
		case 3:
			this.getSquare(5, 5).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(5, 5).moveTo(this.getSquare(1, 5));
			sleep(1000);
			
			this.getSquare(1, 5).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(1, 5).moveTo(this.getSquare(1, 1));
			sleep(1000);
			
			this.getSquare(1, 1).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(1, 1).moveTo(this.getSquare(5, 1));
			sleep(1000);
			
			this.getSquare(5, 1).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(5, 1).moveTo(this.getSquare(3, 3));
			sleep(1000);
			
			this.getSquare(5, 3).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(5, 3).moveTo(this.getSquare(1, 3));
			sleep(1000);
			
			break;
			
		case 4:
			this.getSquare(1, 1).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(1, 1).moveTo(this.getSquare(1, 5));
			sleep(1000);
			
			this.getSquare(1, 5).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(1, 5).moveTo(this.getSquare(3, 3));
			sleep(1000);
			
			this.getSquare(3, 3).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(3, 3).moveTo(this.getSquare(5, 1));
			sleep(1000);
			
			this.getSquare(5, 1).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(5, 1).moveTo(this.getSquare(5, 5));
			sleep(1000);
			
			this.getSquare(5, 5).setType(Square.Type.GreenFrog2);
			sleep(1000);
			this.getSquare(5, 5).moveTo(this.getSquare(3, 3));
			sleep(1000);
			
			this.getSquare(3, 1).setType(Square.Type.RedFrog2);
			sleep(1000);
			this.getSquare(3, 1).moveTo(this.getSquare(3, 5));
			sleep(1000);
			
			break;
		}
		refresh();
		*/
	}
	
	/**
	 * Causes the currently executing game to sleep (temporarily cease execution) for the specified number of milliseconds.
	 * @param millis The length of time to sleep in milliseconds.
	 */
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Control the process of the whole game.
	 */
	public void gameProcess() {
		switch (this.getState()) {
		case State.WIN:
			break;
		case State.LOSE:
			JOptionPane.showMessageDialog(this, "The red frog should not be removed.", "Oops!", JOptionPane.ERROR_MESSAGE);
			refresh();
			break;
		}
	}
	
	/**
	 * To play the default background music.
	 */
	public void backgroundMusic1()
	{
		try
		{
			try {
				clip.close();
			} catch (Exception e) { ; }
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(HoppersGame.class.getResource(HoppersGame.AUDIO+"backgroundMusic.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();	
		}
		catch(Exception ex)
		{
			System.out.println("Error");
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * To play another background music.
	 */
	public void backgroundMusic2()
	{
		try
		{
			try {
			clip.close();
			} catch (Exception e) { ; }
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(HoppersGame.class.getResource(HoppersGame.AUDIO+"music2.wav"));
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
				
		}
		catch(Exception ex)
		{
			System.out.println("Error");
			ex.printStackTrace();
		}
	
	}
	
	/**
	 * To stop the background music.
	 */
	public void stopBackgroundMusic() {
		clip.stop();
	}
	
	/**
	 * Time the game and show the result
	 * @author Huo Jiaxin (GitHub: <a href="https://github.com/Huo614">Huo614</a>)
	 */
	class Timing extends TimerTask {
		
		@Override
		public void run() {
			int i=0;
			while(getState()!=State.WIN) {
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
				i++;
			}
			if(getState()==State.WIN) {
				Object [] options = {"Stay at current level", "Go to next level"};
				int option = JOptionPane.showOptionDialog(Board.this, "You win this level!\nThe time you spent is "+i+" seconds", "Congratulations!", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				timing.cancel();
				if (option!=1) { ; }
				else
					nextLevel();
			}
		}
	}
}