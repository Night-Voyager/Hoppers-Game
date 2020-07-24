package hoppers;

/**
 * This class {@code HoppersGame} contains the main method of the whole game.
 * 
 * @author	Hao Yukun	(GitHub: <a href="https://github.com/Night-Voyager">Night-Voyager</a>)
 * @author	Li Yuhang	(GitHub: <a href="https://github.com/KiloCN">KiloCN</a>)
 * @author	Huo Jiaxin	(GitHub: <a href="https://github.com/Huo614">Huo614</a>)
 * @author	Dai Zeyi	(GitHub: <a href="https://github.com/daizeyi0109">daizeyi0109</a>)
 * @author	Guo Jie		(GitHub: <a href="https://github.com/Emily634">Emily634</a>)
 * 
 * @version	2020/5/23
 */
public class HoppersGame {
	
	/**
	 * A String of the path of all static files, 
	 * including the image files and audio files.
	 */
	public static String STATIC = "static/";
	
	/** A String of the path of all image files. */
	public static String IMAGE = STATIC + "image/";
	
	/** A String of the path of all audio files. */
	public static String AUDIO = STATIC + "audio/";
	
	/**
	 * An static object of {@code Board} class, 
	 * which makes it easier to call methods in {@code Board} class in special situations.
	 * @see Board
	 */
	static Board b = new Board();
	
	/**
	 * Get the Board object of the game.
	 * @return The Board object
	 */
	public Board getBoard() {
		return b;
	}
	
	/**
	 * The main method of the program.
	 * @param args The array of String input, which is not needed for this program.
	 */
	public static void main(String[] args) {
		new Face();
	}
	
}

