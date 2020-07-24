package hoppers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**{@code Face} is a class that shows the cover before the game starts 
 * including the function to start and exit the game
 * 
 * @author	Huo Jiaxin	(GitHub: <a href="https://github.com/Huo614">Huo614</a>)
 * 
 * @version 2020/5/23
 */
public class Face {
	/** The window used to display the start interface before entering the game interface. */
	private JFrame frame = new JFrame("Start Page");
	
	/** The background image of the start interface. */
	private ImageIcon background;
	
	/**
	 * The constructor of Face.
	 */
	public Face() {
		frame.setResizable(false); 
        background = new ImageIcon(HoppersGame.class.getResource(HoppersGame.IMAGE+"face.jpg"));
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        
        JButton jb=new JButton("Start game");
        jb.setBounds(300,300,200,50);  //Set the position and size of the start button
        JButton jb2=new JButton("Exit game");
        jb2.setBounds(300,400,200,50);  //Set the position and size of the exit button
        label.add(jb);
        label.add(jb2);
        jb.setBackground(Color.pink);
        jb2.setBackground(Color.orange);
        jb.setBorderPainted(true);
        jb2.setBorderPainted(true);
        
        frame.getLayeredPane().add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(background.getIconWidth(), background.getIconHeight());
        frame.validate();
        frame.setVisible(true);
        
        jb.addActionListener(new ActionListener(){ //start the game
        	
        	public void actionPerformed(ActionEvent e) {
        		HoppersGame h = new HoppersGame();
        		Board b = h.getBoard();
        		frame.setVisible(false);
        		b.setVisible(true);
        		b.backgroundMusic1();
        	}
        	
        });
        
        jb2.addActionListener(new ActionListener(){ //exit the game
        	
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        	
        });
        
	}
	
}