import  java.util.Random;
import  java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

/**
 * The class <b>GameController</b> is the controller of the game. It implements
 * the interface ActionListener to be called back when the player makes a move. It computes
 * the next step of the game, and then updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameController implements  ActionListener {

   private GameView GameV ;
   private GameModel GameM;
   private int size ;
    /**
     * Constructor used for initializing the controller. It creates the game's view
     * and the game's model instances
     *
     * @param size
     *            the size of the board on which the game will be played
     */

    public GameController(int size) {
         this.size=size;
        start();
        // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

    }

    /**
     * Starts the game
     */

    public void start(){
        this.GameM = new GameModel(this.size);
        this.GameV = new GameView(this.GameM,this);
        // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

    }

    /**
     * resets the game
     */

    public void reset(){

        GameM.reset();
        BoardView brd = this.GameV.getBoardView();
        brd.update();


    }

    /**
     * Callback used when the user clicks a button or one of the dots.
     * Implements the logic of the game
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String cmd = e.getActionCommand();
            if (cmd.equals("Reset")) {
             reset();
            }
            if (cmd.equals("Exit")) {
                System.exit(0);
            }
        }
        if (e.getSource() instanceof DotButton) {
             DotButton dot = (DotButton) e.getSource();

            int i=dot.getRow();
            int j=dot.getColumn();
            if(GameM.getCurrentStatus(i,j)==0) {
                this.GameM.select(i, j);
                BoardView brd = this.GameV.getBoardView();

                Point p =this.GameM.getCurrentDot();
                int m= p.getX();
                int n= p.getY();
                Point[] tabp = dotrun1(m,n);
               if((tabp.length==1)&&(tabp[0].getX()==-1)&&(tabp[0].getY()==-1)){
                   brd.update();
                   String[] buttons = {"Play Again","Exit" };

                   int reponse = JOptionPane.showOptionDialog(null, "Congratulations,you won in "+this.GameM.getNumberOfSteps()+" steps! whould you like to play again", "Win",JOptionPane.INFORMATION_MESSAGE,0, null, buttons, buttons[1]);
                   if(reponse==0){
                       reset();
                   }
                   else{
                       System.exit(0);
                   }

                }
                else if ((tabp.length!=1)&&(verif(tabp[1].getX(),tabp[1].getY())))
                {

                    GameM.setCurrentDot(tabp[1].getX(),tabp[1].getY());
                    brd.update();

                    String[] buttons = {"Play Again","Exit" };

                    int reponse = JOptionPane.showOptionDialog(null, "Sorry!!,You Lose ,Would you play again", "Lose",JOptionPane.INFORMATION_MESSAGE,0, null, buttons, buttons[1]);
                    if(reponse==0){
                        reset();
                    }
                    else{
                        System.exit(0);
                    }

                }
                else if((tabp.length==1)&&(tabp[0].getX()==-1)&&(tabp[0].getY()==0)){
                    GameM.setCurrentDot(tabp[1].getX(),tabp[1].getY());
                   brd.update();
                   String[] buttons = {"Play Again","Exit" };

                   int reponse = JOptionPane.showOptionDialog(null, "Sorry!!,You Lose ,Want you play again", "Lose",JOptionPane.INFORMATION_MESSAGE,0, null, buttons, buttons[1]);
                   if(reponse==0){
                       reset();
                   }
                   else{
                       System.exit(0);
                   }
                 }
                else {
                   GameM.setCurrentDot(tabp[1].getX(), tabp[1].getY());
                   brd.update();
               }

            }
            // REPLACE THE BODY OF THIS METHOD WITH YOUR OWN IMPLEMENTATION

        }
    }

         private Point[] dotrun1(int i, int j) {
             boolean[][] blocket= new boolean[GameM.getSize()][GameM.getSize()];
             for (int k = 0; k< GameM.getSize(); k++) {
                 for (int l = 0; l < GameM.getSize(); l++) {
                     if ((GameM.getCurrentStatus(k,l) == 1)||(GameM.getCurrentStatus(k,l)==2)) {
                         blocket[k][l] = false;
                     }
                     else{
                         blocket[k][l] = true;
                     }
                 }
             }
             LinkedList<Point[]>q ;
             q = new LinkedList<Point[]>() ;
             LinkedList<Point[]>p ;
             p = new LinkedList<Point[]>() ;
             q.addLast(new Point[]{new Point(i,j)});

             if(!verif(i,j)) {
                 while (!q.isEmpty()) {
                     Point[] tab = q.removeFirst();
                     Point r = tab[tab.length - 1];

                     i = r.getX();
                     j = r.getY();
                     blocket[i][j] = false;
                    int x ,y;
                     //right
                     x = i;
                     y = j + 1;
                     if (blocket[x][y]) {
                         if (verif(x, y)) {

                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             p.addLast(tab1);
                             //return tab1;
                         } else {

                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             q.addLast(tab1);
                         }
                         blocket[x][y]= false;
                     }
                    //left
                     x = i;
                     y = j - 1;
                     if (blocket[x][y]) {
                         if (verif(x, y)) {

                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             p.addLast(tab1);
                           //return tab1;
                         } else {
                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             q.addLast(tab1);
                         }
                         blocket[x][y]= false;
                     }
                        //up
                     x=i-1;
                     y = j ;
                     if (blocket[x][y]) {
                         if (verif(x, y)) {

                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             p.addLast(tab1);
                           //return tab1;
                         } else {
                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             q.addLast(tab1);
                         }
                         blocket[x][y]= false;
                     }
                      //down
                     x = i + 1;
                     y = j;
                     if (blocket[x][y]) {
                         if (verif(x, y)) {

                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             p.addLast(tab1);
                            //return tab1;
                         } else {
                             Point[] tab1 = new Point[tab.length + 1];
                             System.arraycopy(tab, 0, tab1, 0, tab.length);
                             tab1[tab1.length - 1] = new Point(x, y);
                             q.addLast(tab1);
                         }
                         blocket[x][y]= false;
                     }

                     //test position of i
                     if (i % 2 == 1) {
                         //up1
                          x = i - 1;
                          y = j+1;
                         if (blocket[x][y]) {
                             if (verif(x, y)) {

                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 p.addLast(tab1);
                               //return tab1;
                             } else {

                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 q.addLast(tab1);
                             }
                             blocket[x][y]= false;
                         }
                         //down2
                         x = i + 1;
                         y = j + 1;
                         if (blocket[x][y]) {
                             if (verif(x, y)) {

                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 p.addLast(tab1);
                                 //return tab1;
                             } else {
                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 q.addLast(tab1);
                                 }
                            }
                         blocket[x][y]= false;
                        }
                     else {
                         //up1
                           x = i - 1;
                           y = j - 1;
                         if (blocket[x][y]) {
                             if (verif(x, y)) {

                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 p.addLast(tab1);
                                // return tab1;
                             } else {
                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 q.addLast(tab1);
                             }
                             blocket[x][y]= false;
                         }
                         //down2
                         x = i + 1;
                         y = j - 1;
                         if (blocket[x][y]) {
                             if (verif(x, y)) {

                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 p.addLast(tab1);
                               // return tab1;
                             } else {
                                 Point[] tab1 = new Point[tab.length + 1];
                                 System.arraycopy(tab, 0, tab1, 0, tab.length);
                                 tab1[tab1.length - 1] = new Point(x, y);
                                 q.addLast(tab1);
                             }
                             blocket[x][y]= false;
                         }
                     }

                    if (!p.isEmpty()){
                        Point[] path = p.removeFirst();
                        p.addLast(path);
                            Random ran= new Random();
                        if(p.size()==1){

                            return path = p.removeFirst();

                        }
                        int  s=ran.nextInt(p.size()-1);
                            s=s+1;
                            for(int count=0;count<s;count++){
                                path=p.removeFirst();
                            }

                           return path;
                    }
                 }
                 }


             else{

                 Point[] tab1 = new Point[]{new Point(-1,0)};
                 return tab1 ;
             }
                Point[] tab1 = new Point[]{new Point(-1,-1)};
                return tab1 ;

    }

    private boolean verif(int i, int j) {
        boolean verif = false;
        if(i==0){
            verif =true;
        }
        else if(i==this.GameM.getSize()-1){
            verif =true;
        }
        else if (j==this.GameM.getSize()-1){
            verif =true;
        }
        else if (j==0){
            verif =true;
        }
        return verif;
    }


}