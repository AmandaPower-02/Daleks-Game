
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    private Doctor doctor;
    private Dalek[] dalek;
    public boolean playing = true;
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

        //generate row and column where doctor will start
        int docRow = (int) (Math.random() * ((11 - 0) + 1)) + 0;
        int docCol = (int) (Math.random() * ((11 - 0) + 1)) + 0;

        //place doctor at their origional spot on the board
        doctor = new Doctor(docRow, docCol);



        //place 3 dalek
        dalek = new Dalek[3];

        for (int i = 0; i < dalek.length; i++) {
            int row = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            int col = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            dalek[i] = new Dalek(row, col);
            //put dalek on the board
            board.putPeg(Color.black, dalek[i].getRow(), dalek[i].getCol());
        }
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        
        //put the doctor on the board
        board.putPeg(Color.green, doctor.getRow(), doctor.getCol());
        
        //re generate if any daleks are in the same place
        for (int i = 0; i < dalek.length; i++) {
            if(dalek[i].hasCrashed()||dalek[i].getRow()== doctor.getRow()&& dalek[i].getCol()==doctor.getCol()){
             
                board.removePeg(dalek[i].getRow(), dalek[i].getCol());
                board.removePeg(doctor.getRow(), doctor.getCol());
                
                for (int x = 0; x < 3; x++) {
            int row = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            int col = (int) (Math.random() * ((11 - 0) + 1)) + 0;
            dalek[x] = new Dalek(row, col);
        }
        //put 3 dalek on the board
        for (int j = 0; j < 3; j++) {
            
            board.putPeg(Color.black, dalek[j].getRow(), dalek[j].getCol());

        }
        //generate row and column where doctor will start
        int docRow = (int) (Math.random() * ((11 - 0) + 1)) + 0;
        int docCol = (int) (Math.random() * ((11 - 0) + 1)) + 0;

        //place doctor at their origional spot on the board
        doctor = new Doctor(docRow, docCol);
            }
            
        }
        
        while (playing) {
            //move the doctor
            //wait for user to click on a spot
            Coordinate click = board.getClick();
//            System.out.println("Click: " + click.getRow() + "\t" + click.getCol());
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
            for (int i = 0; i < dalek.length; i++) {
//                remove the daleks from place
                board.removePeg(dalek[i].getRow(), dalek[i].getCol());
//                move the daleks
                dalek[i].advanceTowards(doctor);
//                place peg in new place
                board.putPeg(Color.black, dalek[i].getRow(), dalek[i].getCol());
            }

            for(int i = 0; i < dalek.length; i++){
                for(int j = i + 1; j < dalek.length; j++){
                    if(dalek[i].getRow() == dalek[j].getRow() && dalek[i].getCol() == dalek[j].getCol()){
                        board.removePeg(dalek[i].getRow(), dalek[i].getCol());
                        board.removePeg(dalek[j].getRow(), dalek[j].getCol());
                        board.putPeg(Color.RED, dalek[i].getRow(), dalek[i].getCol());
                        dalek[i].crash();
                        dalek[j].crash();
                    }
                }
            }
            
            if (dalek[0].hasCrashed() && dalek[1].hasCrashed() && dalek[2].hasCrashed()) {
                    //tell player they win
                    board.displayMessage("YOU WIN");
                    playing = false;
            }

            //you lose if a dalake catches you
            for (int i = 0; i < dalek.length; i++) {
                if (dalek[i].getRow() == doctor.getRow() && dalek[i].getCol() == doctor.getCol()) {
                    //remove doctor peg
                    board.removePeg(doctor.getRow(), doctor.getCol());
                    //remove dalek peg
                    board.removePeg(dalek[i].getRow(), dalek[i].getCol());
                    //place yellow peg
                    board.putPeg(Color.yellow, dalek[i].getRow(), dalek[i].getCol());
                    //tell player they loose
                     board.displayMessage("YOU LOSE");
                    //make the game stop
                    playing = false;
                }

            }
        }
    }
}
