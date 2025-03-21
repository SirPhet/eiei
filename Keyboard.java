import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {
    public boolean k ;
    public boolean u ;
    public boolean y ;

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_K) {
            k = true ;
        }
        if(e.getKeyCode() == KeyEvent.VK_U) {
            u = true ;
        }
        if(e.getKeyCode() == KeyEvent.VK_Y) {
            y = true ;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
