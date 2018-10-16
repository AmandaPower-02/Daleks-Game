
/**
 * This class models a Delek in the game. A Delek has a position and can advance
 * towards the Doctor.
 */
public class Dalek {

    private int row, col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
        this.row = theRow;
        this.col = theCol;
        this.hasCrashed = false;
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doctor) {
        if(!hasCrashed){
            
        //move dalek row
        if (doctor.getRow()== this.row){
            this.row = this.row;
        }
        if (doctor.getRow()> this.row){
            this.row = this.row + 1;
        }
        if (doctor.getRow()< this.row){
            this.row = this.row - 1;
        }
        
        //move dalek colum
        if (doctor.getCol()== this.col){
            this.col = this.col;
        }
        if (doctor.getCol()> this.col){
            this.col = this.col + 1;
        }
        if (doctor.getCol()< this.col){
            this.col = this.col - 1;
        }
    }
        
        
     
        
    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash (Dalek d1) {
        if(this.row == d1.row && this.col == d1.col){
            d1.hasCrashed = true;
            this.hasCrashed = true;
        }
        
        
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed() {
        return this.hasCrashed;
    }
}
