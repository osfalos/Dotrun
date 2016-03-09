import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** The class <b>GameView</b> provides the current view of the entire Game. It extends
        * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and
        * two instances of JButton. The action listener for the buttons is the controller.
        *
        * @author Guy-Vincent Jourdan, University of Ottawa
        */

public class GameView extends JFrame  {

    private GameController gameController;
    private GameModel model ;
    private BoardView Board ;

    /**
     * Constructor used for initializing the Frame
     *
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModel model, GameController gameController) {
        super("DotsGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.white);

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        this.model=model ;
        this.gameController=gameController;
        this.Board=new BoardView(this.model,this.gameController);
        add(this.Board,BorderLayout.CENTER);
        JButton Reset = new JButton("Reset");
        Reset.addActionListener(this.gameController);
        Reset.setBackground(Color.white);
        JButton Exit = new JButton("Exit");
        Exit.addActionListener(this.gameController);
        Exit.setBackground(Color.white);
        panel.add(Reset);
        panel.add(Exit);
        add(panel, BorderLayout.SOUTH);

        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Getter method for the attribute board.
     *
     * @return a reference to the BoardView instance
     */

    public BoardView getBoardView(){
       return this.Board;
    }


}