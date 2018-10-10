
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {
    Doctor doctor;
 

    /**
     *
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    private Board board;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        //create a 12 x 12 board
        board = new Board(12, 12);
        doctor = new Doctor (1,2);
        

    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        board.putPeg(Color.green, doctor.getRow(), doctor.getCol());
        
//        //put down peg
//        board.putPeg(Color.yellow, 0, 0);
//        //starting game loop
//        board.displayMessage("y");
//        Coordinate click2 = board.getClick();
//        int row2 = click2.getRow();
//        int col2 = click2.getCol();
//        //remove peg
//        board.removePeg(row2, col2);

        while (true) {

            //wait for user to click on a spot
            Coordinate click = board.getClick();
            //getting row and collumn
            int row = click.getRow();
            int col = click.getCol();
            
            board.removePeg( doctor.getRow(), doctor.getCol());
            doctor.move(row, col);
            
            board.putPeg(Color.green, doctor.getRow(), doctor.getCol());
        }
    }
}
