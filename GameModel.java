import javax.swing.*;
import java.util.Random;


/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the followiung information:
 * - the current location of the blue dot
 * - the state of all the dots on the board (available, selected or 
 *  occupied by the blue dot
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameModel {

    /**
     * predefined values to capture the state of a point
     */

    public static final int AVAILABLE   = 0;
    public static final int SELECTED    = 1;
    public static final int DOT         = 2;
    private int[][] states ;

    private int Size ;
    private int NumberOfSteps;
    // ADD YOUR INSTANCE VARIABLES HERE

    /**
     * Constructor to initialize the model to a given size of board.
     *
     * @param size
     *            the size of the board
     */

    public GameModel(int size) {
    this.Size=size;
        int j,i,z=0,w=0;
        Random d = new Random();
        Random s = new Random();
        Random h = new Random();
    this.NumberOfSteps=0;
       this.states = new int[this.Size][this.Size];
        for (i=0;i<this.Size;i++){
            for (j=0;j<this.Size;j++){

                this.states[i][j]= AVAILABLE;
            }
        }
        if(this.Size%2==0){
            z= this.Size/2 ;
            w= this.Size/2 ;

            z = z+d.nextInt(2);
            w =w+s.nextInt(2);
            setCurrentDot(z-1,w-1);
        }
        else{
            z= (this.Size-1)/2 ;

            w= (this.Size-1)/2 ;

            z = z+d.nextInt(3);

            w =w+s.nextInt(3);

           setCurrentDot(z-1,w-1);
        }
        for(i=0;i<this.Size;i++){
            for(j =0;j<this.Size;j++){
                int k =h.nextInt(10);
                if (((i!=(z-1))&&(j!=(w-1)))&&(k==0)){

                    select(i,j);
                }

            }

        }

    }

    /**
     * Resets the model to (re)start a game. The previous game (if there is one)
     * is cleared up . The blue dot is positioned as per instructions, and each 
     * dot of the board is either AVAILABLE, or SELECTED (with
     * a probability 1/INITIAL_PROBA). The number of steps is reset.
     */

    public void reset(){

        int j,i,z,w;
        Random d = new Random();
        Random s = new Random();
        Random h = new Random();
        this.NumberOfSteps=0;
        this.states = new int[this.Size][this.Size];
        for (i=0;i<this.Size;i++){
            for (j=0;j<this.Size;j++){
                this.states[i][j]= AVAILABLE;

            }
        }
        if(this.Size%2==0){
            z= this.Size/2 ;
            w= this.Size/2 ;

            z = z+d.nextInt(2);
            w =w+s.nextInt(2);
            setCurrentDot(z-1,w-1);
        }
        else{
            z= (this.Size-1)/2 ;

            w= (this.Size-1)/2 ;

            z = z+d.nextInt(3);

            w =w+s.nextInt(3);

            setCurrentDot(z-1,w-1);
        }
        for(i=0;i<this.Size;i++){
            for(j =0;j<this.Size;j++){
                int k =h.nextInt(10);
                if (((i!=(z-1))&&(j!=(w-1)))&&(k==0)){
                    select(i,j);
                }

            }

        }
    }


    /**
     * Getter <b>class</b> method for the size of the game
     *
     * @return the value of the attribute sizeOfGame
     */

    public int getSize(){
        return this.Size ;

    }

    /**
     * returns the current status (AVAILABLE, SELECTED or DOT) of a given dot in the game
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     * @return the status of the dot at location (i,j)
     */

    public int getCurrentStatus(int i, int j){

        return states[i][j];
    }

    /**
     * Sets the status of the dot at coordinate (i,j) to SELECTED, and 
     * increases the number of steps by one
     *
     * @param i
     *            the x coordinate of the dot
     * @param j
     *            the y coordinate of the dot
     */

    public void select(int i, int j){
       this.states[i][j]=SELECTED;
    }

    /**
     * Puts the blue dot at coordinate (i,j). Clears the previous location 
     * of the blue dot. If the i coordinate is "-1", it means that the blue 
     * dot exits the board (the player lost)
     *
     * @param i
     *            the new x coordinate of the blue dot
     * @param j
     *            the new y coordinate of the blue dot
     */

    public void setCurrentDot(int i, int j) {
        int m, n;
        Point p = getCurrentDot();
        m = p.getX();
        n = p.getY();
        this.states[m][n] = AVAILABLE;
        if (i != -1) {
            this.states[i][j] = DOT;
            NumberOfSteps++;
        }
    }



    /**
     * Getter method for the current blue dot
     *
     * @return the location of the curent blue dot
     */

    public Point getCurrentDot() {
        Point P = new Point(0,0) ;
         for(int i=0;i<this.Size;i++) {
             for (int j = 0; j < this.Size; j++) {

                 if (this.states[i][j] == DOT) {
                     P.reset(i, j);

                 }

             }
         }
    return P;
    }
        /**
     * Getter method for the current number of steps
     *
     * @return the current number of steps
     */

    public int getNumberOfSteps(){

        return this.NumberOfSteps;

    }
}