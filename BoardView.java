
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * The class <b>BoardView</b> provides the current view of the board. It extends
 * <b>JPanel</b> and lays out a two dimensional array of <b>DotButton</b> instances.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class BoardView extends JPanel  {
    private JPanel[] row ;
    private DotButton[][] Dots ;
    private GameModel gameModel;
    private GameController gameController;

    /**
     * Constructor used for initializing the board. The action listener for
     * the board's DotButton is the game controller
     *
     * @param gameModel
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public BoardView(GameModel gameModel, GameController gameController) {
        setBackground(Color.WHITE);
        this.gameController=gameController;
        this.gameModel=gameModel;
         this.row = new JPanel[gameModel.getSize()];
       this.Dots = new DotButton[gameModel.getSize()][gameModel.getSize()];
      int i=0;
           // setLayout(new GridLayout(gameModel.getSize(),0));

        for ( i=0;i<gameModel.getSize();i++){
            for (int j=0;j<gameModel.getSize();j++){
                this.Dots[i][j]=new DotButton(i,j,gameModel.getCurrentStatus(i,j));
                this.Dots[i][j].setPreferredSize(new Dimension(40,40));
                this.Dots[i][j].addActionListener(this.gameController);
            }
        }



        for ( i=0;i<this.gameModel.getSize();i++){
            this.row[i]= new JPanel();
        }
        for ( i=0;i<this.gameModel.getSize();i++){

            if(i%2==0) {
                this.row[i].setBackground(Color.WHITE);
                this.row[i].setBorder(new EmptyBorder(0,0,0,20));

            }

            else{
                this.row[i].setBackground(Color.WHITE);
               this.row[i].setBorder(new EmptyBorder(0,20,0,0));
            }
            for (int j=0;j<this.gameModel.getSize();j++){
                this.row[i].add(this.Dots[i][j]);
            }
        }
            JPanel BoardP = new JPanel();
        BoardP.setLayout(new GridLayout(gameModel.getSize(),0));
        for ( i=0;i<this.gameModel.getSize();i++){
            BoardP.add(this.row[i]);
        }
        add(BoardP);
    }



    /**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update() {

        for (int i = 0; i < gameModel.getSize(); i++) {
            for (int j = 0; j < gameModel.getSize(); j++) {
                this.Dots[i][j].setType(gameModel.getCurrentStatus(i, j));
            }
        }
    }






}