/**
 * The class <b>Point</b> is a simple helper class that stares a 2 dimentional element on a grid
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class Point {
 private int X ;
 private int Y ;

    /**
     * Constructor
     *
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */

    public Point(int x, int y){
        this.X=x ;
        this.Y=y ;


    }

    /**
     * Getter method for the attribute x.
     *
     * @return the value of the attribute x
     */

    public int getX(){

    return  this.X ;
    }

    /**
     * Getter method for the attribute y.
     *
     * @return the value of the attribute y
     */

    public int getY(){

        // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION
      return  this.Y ;
    }

    /**
     * Setter for x and y.
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */

    public void reset(int x, int y){

        this.X=x;
        this.Y=y;

    }

}