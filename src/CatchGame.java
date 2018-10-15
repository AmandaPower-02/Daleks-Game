
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    private Doctor doctor;
    private Dalek [] dalek;
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

        //place doctor at their origional spot on the board
        doctor = new Doctor(1, 2);



        //place 3 dalek
        dalek = new Dalek [3];
        
        for (int i = 0; i < 3; i++) {
            int row = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            int col = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            dalek[i] = new Dalek (row, col);
        }
         //put 3 dalek on the board
        for (int i = 0; i < 3; i++) {
            board.putPeg(Color.black ,dalek[i].getRow(), dalek[i].getCol());
            
        }


    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        //put the doctor on the board
        board.putPeg(Color.green, doctor.getRow(), doctor.getCol());
        
        while (true) {
            //move the doctor
            //wait for user to click on a spot
            Coordinate click = board.getClick();

            //getting row and collumn
            int row = click.getRow();
            int col = click.getCol();

            //rmove peg after spot on board is cicked
            board.removePeg(doctor.getRow(), doctor.getCol());
            //move the doctor
            doctor.move(row, col);
            // place doctor in new spotd
            board.putPeg(Color.green, doctor.getRow(), doctor.getCol());
            
            //move the daleks
            for (int i = 0; i < dalek.length ; i++) {
//                remove the daleks from place
                board.removePeg(dalek[i].getRow(), dalek[i].getCol());
//                move the daleks
                dalek[i].advanceTowards(doctor);
//                place peg in new place
                board.putPeg(Color.black, dalek[i].getRow(), dalek[i].getCol());
            } 
            
            //the daleks have crashed 
            dalek [0].crash(dalek [1]);
            dalek [1].crash(dalek [2]);
            dalek [2].crash(dalek [0]);
            
            for (int i = 0; i <dalek.length; i++) {
                
                //the daleks have crashed 
            dalek [0].crash(dalek [1]);
            dalek [1].crash(dalek [2]);
            dalek [2].crash(dalek [0]);
                
                if(dalek[i].hasCrashed()){
                //remove the daleks
                board.removePeg(dalek[i].getRow(), dalek[i].getCol());
                //place red dot in their place
                board.putPeg(Color.red, dalek[i].getRow(), dalek[i].getCol());
                }
            }
                
            }
        }
    

}
