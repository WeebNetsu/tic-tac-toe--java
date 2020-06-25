import java.util.Scanner;

public class Board{
	private char[] arrBlocks = {
		'1', '2', '3',
		'4', '5', '6', 
		'7', '8', '9'
	};

	private boolean[] arrTakenBlocks = {
		true, true, true,
		true, true, true, 
		true, true, true
	};

	private int turn;
	private boolean playing;

	Board(){ //Constructor
		turn = 0;
		playing = true;
	}

	boolean isPlaying(){
		return this.playing;
	}

	int getWinner(){
		char player; 
		int winner;

		for (int i = 0; i < 2; i++) {
			if(i == 0){
				player = 'X'; //player 1
			}else{
				player = 'O'; //player 2
			}

			winner = i + 1; //1 or 2 (for player 1/2)

			//Checks Horizontally
			if(arrBlocks[0] == player && arrBlocks[1] == player && arrBlocks[2] == player)	{
				return winner;
			}	

			if(arrBlocks[3] == player && arrBlocks[4] == player && arrBlocks[5] == player)	{
				return winner;
			}	

			if(arrBlocks[6] == player && arrBlocks[7] == player && arrBlocks[8] == player)	{
				return winner;
			}	

			//Checks Vertically
			if(arrBlocks[0] == player && arrBlocks[3] == player && arrBlocks[6] == player)	{
				return winner;
			}	

			if(arrBlocks[1] == player && arrBlocks[4] == player && arrBlocks[7] == player)	{
				return winner;
			}	

			if(arrBlocks[2] == player && arrBlocks[5] == player && arrBlocks[8] == player)	{
				return winner;
			}	

			//Checks Sideways
			if(arrBlocks[0] == player && arrBlocks[4] == player && arrBlocks[8] == player)	{
				return winner;
			}	

			if(arrBlocks[2] == player && arrBlocks[4] == player && arrBlocks[6] == player)	{
				return winner;
			}	
		}

		return -1;
	}

	void drawBoard(){ //draws the game board
		System.out.println("-------------");

		for (int i = 0; i < 9; i++) {
			if(i % 3 == 0 && i != 0){
				System.out.println("\n-------------");
				System.out.print("| ");
			}else if(i == 0){
				System.out.print("| ");
			}

			System.out.print(arrBlocks[i] + " | ");
		}

		System.out.println("\n-------------");

		turn++;
	}

	char getTurn(){
		if(turn % 2 == 0){
			return 'O'; //player 2 (0) turn
		}

		return 'X'; //player 1 (X) turn
	}

	int indexOf(char c){ //Java has a trashy .indexOf(), so I made my own version
		for(int i = 0; i < arrBlocks.length; i++){
			if(arrBlocks[i] == c){
				return i;
			}
		}

		return -1;
	}

	boolean getIngame(){
		if(getWinner() == 1 || getWinner() == 2){
			return false;
		}

		for(int i = 0; i < arrTakenBlocks.length; i++){
			if(arrTakenBlocks[i]){
				return true;
			}
		}

		System.out.println("It's a draw!");
		return false;
	}

	int getNextInput(){ //gets user input
		System.out.print("Enter you desired value: ");

		Scanner inp = new Scanner(System.in);
		
		int num = -1;

		try{
			num = inp.nextInt(); //has to be an integer
		}catch(Exception e){
			System.out.println("You entered an invalid value!");
			return getNextInput();
		}

		if(num < 1 && num > 9){ //only from 1-9 may be entered
			System.out.println("You entered an invalid value!");
			return getNextInput();
		}
		
		//if thet already chose that number before
		if(indexOf(Character.forDigit(num, 10)) == -1){ 
			System.out.println("You entered an invalid value!");
			return getNextInput();
		}

		//sets that newly chosen block as not open
		arrTakenBlocks[indexOf(Character.forDigit(num, 10))] = false;
		return num;
	}

	void insertValue(int pos, char player){
		arrBlocks[pos] = getTurn(); //Makes it 'X' or 'O'

		System.out.print("\033[H\033[2J"); //clears the screen
	}

	void init(){
		drawBoard();
		insertValue(getNextInput() - 1, getTurn());

		if(getWinner() == 1){
			System.out.println("Player 1 Won!");
		}else if(getWinner() == 2){
			System.out.println("Player 2 Won!");
		}

		if(getIngame() ==  false){
			playing = false;
		}
	}
}