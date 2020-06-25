import java.util.Scanner;

public class Game{
	public static void main(String[] args) {
		Board game = new Board();
		System.out.println("Choose a number to start playing!");

		while(game.isPlaying()){
			game.init();
		}

		game.drawBoard();
	}
}