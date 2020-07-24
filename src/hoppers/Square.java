package hoppers;

import javax.swing.JButton;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * {@code Square} class controls the behavior of pieces
 * 
 * @author	Li Yuhang	(GitHub: <a href="https://github.com/KiloCN">KiloCN</a>)
 * @author	Dai Zeyi	(GitHub: <a href="https://github.com/daizeyi0109">daizeyi0109</a>)
 * @author	Guo Jie		(GitHub: <a href="https://github.com/Emily634">Emily634</a>)
 * @version 2020/5/8
 */
public class Square extends JButton{

	/**The x-axis coordinate of the Square */
	private int x;

	/**The y-axis coordinate of the Square */     
	private int y;

	/**The type of the square
	 *
     * <p>- 1, if the type of square is GreenFrog
     * <p>- 2, if the type of square is GreenFrog2
     * <p>- 3, if the type of square is LilyPad
     * <p>- 4, if the type of square is RedFrog
     * <p>- 5, if the type of square is RedFrog2
     * <p>- 6, if the type of square is Water
	 */
	private int squareType;
	
	/**The number of GreenFrog on the board*/  
	private static int numOfGreenFrog;

	/**The number of RedFrog on the board*/  
	private static int numOfRedFrog;

	/**The number of Squares in the selected state*/
	public static int number = 0;

	/**The first Square object selected*/
	private static Square selectOne;

	/**The second Square object selected*/
	private static Square selectTwo;
	
	/**
	  * Contains all the Type of the Square.
	  * @author	Li Yuhang	(GitHub: <a href="https://github.com/KiloCN">KiloCN</a>)
	  */
	public class Type {

		/** Constant for the type of Square that Square type is GreenFrog, the value is {@value} */
		public static final int GreenFrog = 1;

		/** Constant for the type of Square that Square type is GreenFrog2, the value is  {@value} */
		public static final int GreenFrog2 = 2;

		/** Constant for the type of Square that Square type is LilyPad, the value is  {@value} */
		public static final int LilyPad = 3;

		/** Constant for the type of Square that Square type is RedFrog, the value is  {@value} */
		public static final int RedFrog = 4;

		/** Constant for the type of Square that Square type is RedFrog2, the value is  {@value} */
		public static final int RedFrog2 = 5;

		/** Constant for the type of Square that Square type is Water, the value is {@value} */
		public static final int Water = 6;
	}
	
	/**
	 * Constructor of Square.
	 * @param positionX The x-axis coordinate of Square.
	 * @param positionY The y-axis coordinate of Square.
	 */
	Square(int positionX, int positionY) {
		this(positionX, positionY, Type.Water);
	}
	
	/**
	 * Constructor of Square.
	 * @param positionX The x-axis coordinate of Square.
	 * @param positionY The y-axis coordinate of Square.
	 * @param type The desired type of Square.
	 */
	Square(int positionX, int positionY, int type){
		this.x = positionX;
		this.y = positionY;
		if(type == 1) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"GreenFrog.png"));
			this.setIcon(icon);
			this.squareType = 1;
			}
		if(type == 2) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"GreenFrog2.png"));
			this.setIcon(icon);
			this.squareType = 2;
			}
		if(type == 3) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"LilyPad.png"));
			this.setIcon(icon);
			this.squareType = 3;
			}
		if(type == 4) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"RedFrog.png"));
			this.setIcon(icon);
			this.squareType = 4;
			}
		if(type == 5) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"RedFrog2.png"));
			this.setIcon(icon);
			this.squareType = 5;
			}
		if(type == 6) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"Water.png"));
			this.setIcon(icon);
			this.squareType = 6;
			}
		
		ButtonActionLisener eAction = new ButtonActionLisener();
		this.addActionListener(eAction);
		
		if(this.squareType == Square.Type.GreenFrog || this.squareType == Square.Type.GreenFrog2) {
				Square.numOfGreenFrog++;
			}
			else if(this.squareType == Square.Type.RedFrog || this.squareType == Square.Type.RedFrog2) {
				Square.numOfRedFrog++;
			}
	}

	/**
	 * Get the x-axis coordinates of Square.
	 * @return The int value of whether x-axis coordinates of Square.
	 */
	public int getPositionX() {
		return this.x;
	}

	/**
	 * Get the y-axis coordinates of Square.
	 * @return The int value of whether y-axis coordinates of Square.
	 */
	public int getPositionY() {
		return this.y;
	}

	/**
	 * Get the type of Square.
	 * @return The int value of the type of Square.
	 */
	public int getType() {
		return squareType;
	}
	
	/**
	 * Modify the type of Square.
	 * @param type of Square change.
	 */
	public void setType(int type) {
		if(this.squareType == Square.Type.GreenFrog || this.squareType == Square.Type.GreenFrog2) {
			Square.numOfGreenFrog--;
		}
		else if(this.squareType == Square.Type.RedFrog || this.squareType == Square.Type.RedFrog2) {
			Square.numOfRedFrog--;
		}
		if(type == 1) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"GreenFrog.png"));
			this.setIcon(icon);
			this.squareType = 1;
			}
		if(type == 2) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"GreenFrog2.png"));
			this.setIcon(icon);
			this.squareType = 2;
			}
		if(type == 3) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"LilyPad.png"));
			this.setIcon(icon);
			this.squareType = 3;
			}
		if(type == 4) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"RedFrog.png"));
			this.setIcon(icon);
			this.squareType = 4;
			}
		if(type == 5) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"RedFrog2.png"));
			this.setIcon(icon);
			this.squareType = 5;
			}
		if(type == 6) {
			ImageIcon icon = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"Water.png"));
			this.setIcon(icon);
			this.squareType = 6;
			}
		if(this.squareType == Square.Type.GreenFrog || this.squareType == Square.Type.GreenFrog2) {
			Square.numOfGreenFrog++;
		}
		else if(this.squareType == Square.Type.RedFrog || this.squareType == Square.Type.RedFrog2) {
			Square.numOfRedFrog++;;
		}
	}
	
	/**
	 * Detect when any of Square are clicked with a mouse.
	 */
	private class ButtonActionLisener implements ActionListener {
		
		public  void actionPerformed(ActionEvent e) {
			
				number++;
				if(number == 1) {
					selectOne = Square.this;
				}
				if(number == 2) {
					selectTwo = Square.this;
				}
				if(Square.this.squareType == Square.Type.GreenFrog) {
					Square.this.setType(Square.Type.GreenFrog2);
					
				}
				else if(Square.this.squareType == Square.Type.RedFrog) {
					Square.this.setType(Square.Type.RedFrog2);
				}
				if(number == 2) {
					number = 0;
					selectOne.moveTo(selectTwo);
				}
				
				HoppersGame h = new HoppersGame();
				Board b = h.getBoard();
				b.gameProcess();
			
			}
		
		}
	
	/**
	 * Moving the pieces.
	 * @param selectTwo The Square which is second select.
	 */
	public void moveTo(Square selectTwo) {
		HoppersGame h = new HoppersGame();
		Board b = h.getBoard();
		int selectOneX = this.getPositionX();
		int selectOneY = this.getPositionY();
		int selectTwoX = selectTwo.getPositionX();
		int selectTwoY = selectTwo.getPositionY();
		Square sq1 = b.getSquare(selectOneX, selectOneY);
		Square sq2 = b.getSquare(selectTwoX, selectTwoY);
		if(b.isMovable(sq1, sq2) == true) {
			playFrog();
			if(sq1.squareType == Square.Type.GreenFrog2) {
				sq2.setType(Square.Type.GreenFrog);
			
			}
			else {
				sq2.setType(Square.Type.RedFrog);
			}
			sq1.setType(Square.Type.LilyPad);
			b.getSquare((selectOneX + selectTwoX)/2, (selectOneY + selectTwoY)/2).setType(Square.Type.LilyPad);
		}
		else if(b.isMovable(sq1, sq2) == false) {
			playNo();
			if(sq1.squareType == Square.Type.GreenFrog2) {
				sq1.setType(Square.Type.GreenFrog);
			}
			if(sq1.squareType == Square.Type.RedFrog2) {
				sq1.setType(Square.Type.RedFrog);
			}
			if(sq2.squareType == Square.Type.GreenFrog2) {
				sq2.setType(Square.Type.GreenFrog);
			}
			if(sq2.squareType == Square.Type.RedFrog2) {
				sq2.setType(Square.Type.RedFrog);
			}
		}
		
	}
		
	/**
	 * Play frog sound effects.
	 */
	public void playFrog() {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(HoppersGame.class.getResource(HoppersGame.AUDIO+"frog.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}
		catch(Exception ex) {
			System.out.println("Error with playing sound");
			ex.printStackTrace();
		}
	}

	/**
	 * Play wrong sound effect.
	 */
	public void playNo() {
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(HoppersGame.class.getResource(HoppersGame.AUDIO+"no.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}
		catch(Exception ex) {
			System.out.println("Error with playing sound");
			ex.printStackTrace();
		}
	}
	
	/**
	 * Get the number of GreenFrog remain.
	 * @return the int value of the number of GreenFrog remain.
	 */
	public static int getNumOfRedFrog() {
		return Square.numOfRedFrog;
	}
	
	/**
	 * Get the number of RedFrog remain.
	 * @return the int value of the number of RedFrog remain.
	 */
	public static int getNumOfGreenFrog() {	
		return Square.numOfGreenFrog;
	}	
}


