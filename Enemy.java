import java.awt.*;
import java.util.Map;

public class Enemy {
    public int healt ;
    public int speed ;
    public int enemy_x ;
    public int enemy_y ;
    public Map form_map ;


    public Enemy(int healt , int speed,int enemy_x , int enemy_y) {
        this.healt = healt ;
        this.speed = speed ;
        this.enemy_x = enemy_x ;
        this.enemy_y = enemy_y ;
    }

    public void move() {

        if(enemy_y > 65 ) {
            enemy_y -= speed ;
        }
        else {
            enemy_x += speed ;
        }

    }

    public void draw(Graphics g) {
        g.fillRect(enemy_x,enemy_y,50,50);
    }


}
