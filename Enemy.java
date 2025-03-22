import java.awt.*;
import java.util.Map;

public class Enemy {
    public int healt ;
    public int speed ;
    public int enemy_x ;
    public int enemy_y ;
    public Map form_map ;
    public Char unit ;
    public int damage_count = 0 ;
    public boolean isAlive = true ;

    public Enemy() {

    }

    public Enemy(int healt , int speed,int enemy_x , int enemy_y) {
        unit = new Char() ;
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

    public void damage_take(Char unit) {

        if(healt > 0 && unit.check_can_hit ) {
            healt = healt - unit.getDamage() ;
            damage_count = damage_count + unit.getDamage() ;
            System.out.println("damage taked");
            System.out.printf("damage count : %d",damage_count);

        }
        else {
            isAlive =false ;
        }
    }

    public void draw(Graphics g) {
        if(healt > 0) {
            g.setColor(Color.BLACK);
            g.fillRect(enemy_x,enemy_y,50,50);
        }


    }




}
