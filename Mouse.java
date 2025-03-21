import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

public class Mouse  implements MouseListener {
    public boolean mouse_clicked ;
    public int mouse_x ;
    public int mouse_y ;
    public Timer timer ;
    public int cick_count = 0 ;

    public Mouse() {

    }

    //mouse setting
    @Override
    public void mouseClicked(MouseEvent e) {
        mouse_x = e.getX() ;
        mouse_y = e.getY() ;
        mouse_clicked = true ;
        cick_count = cick_count + 1;

    }

    public int getMouse_y() {
        return mouse_y;
    }

    public int getMouse_x() {
        return mouse_x;
    }

    public boolean isMouse_clicked() {
        return mouse_clicked;
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouse_clicked = false ;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
