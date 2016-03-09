import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.*;

/**
 * In the application <b>Circle the dot</b>, a <b>DotButton</b> is a specialized type of
 * <b>JButton</b> that represents a dot in the game. It uses different icons to
 * visually reflect its state: a blue icon if the blue dot is currently on this location
 * an orange icon is the dot has been selected and a grey icon otherwise.
 *
 * The icon images are stored in a subdirectory ``data''. They are:
 * data/ball-0.png =&lt; grey icon
 * data/ball-1.png =&lt; orange icon
 * data/ball-2.png =&lt; blue icon
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class DotButton extends JButton {
     private int row ;
     private int column;
     private int type;


    /**
     * Constructor used for initializing a cell of a specified type.
     *
     * @param row
     *            the row of this Cell
     * @param column
     *            the column of this Cell
     * @param type
     *            specifies the type of this cell
     */

    public DotButton(int row, int column, int type) {
      this.row=row;
      this.column=column;
      this.type=type;
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
      if(this.type==0) {
          ImageIcon warnIcon = new ImageIcon("data/ball-0.png");
      this.setIcon(warnIcon);
      }
        else if(this.type==1){
          ImageIcon warnIcon = new ImageIcon("data/ball-1.png");
          this.setIcon(warnIcon);
      }
        else{
          ImageIcon warnIcon = new ImageIcon("data/ball-2.png");
          this.setIcon(warnIcon);
      }
    }

    /**
     * Changes the cell type of this cell. The image is updated accordingly.
     *
     * @param type
     *            the type to set
     */

    public void setType(int type) {

       this.type=type;
        if(this.type==0) {
            Icon warnIcon = new ImageIcon("data/ball-0.png");
            this.setIcon(warnIcon);
        }
        else if(this.type==1){
            Icon warnIcon = new ImageIcon("data/ball-1.png");
            this.setIcon(warnIcon);
        }


        else{
            Icon warnIcon = new ImageIcon("data/ball-2.png");
            this.setIcon(warnIcon);
        }

    }

    /**
     * Getter method for the attribute row.
     *
     * @return the value of the attribute row
     */

    public int getRow() {

        return this.row;

    }

    /**
     * Getter method for the attribute column.
     *
     * @return the value of the attribute column
     */

    public int getColumn() {

        return  this.column;

    }



}