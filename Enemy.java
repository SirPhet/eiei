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
    public int slow = 2 ;
    public boolean line_1 = true ;
    public boolean line_2 = true ;
    public boolean line_3 = true ;
    public boolean line_4 = true ;
    public boolean line_5 = true ;

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

        if(slow <= 0) {
            if(enemy_x <= 1270 && enemy_y == 100 &&line_1) {
                enemy_x += speed ;
            }
            else if (enemy_x >= 1270 && enemy_y <= 440&&line_2) {
                line_1 = false;
                enemy_y += speed ;
            }
            else if (enemy_x >= 155 && enemy_y >=440 && line_3) {
                line_2 = false ;
                enemy_x -= speed;
            }
            else if (enemy_x<= 155 && enemy_y <= 620 && line_4){
                line_3 =false;
                enemy_y += speed ;
            }
            else if (enemy_x <= 1500 && enemy_y >= 620&& line_5) {
                line_4 = false ;
                enemy_x += speed ;
            }
            slow = 0 ;
        }
        else {
            slow-- ;
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
