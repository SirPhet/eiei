import javax.swing.*;
import java.awt.*;

public class Char {
    public int damage ;
    public int hitbox ;
    public int range ;
    public int cost ;
    public int char_x ;
    public int char_y ;
    public map form_map ;
    public int number_img ;
    public int x_after_spawm ;
    public int y_after_spawm ;
    public Enemy enemy ;
    public boolean target = false;

    //immage char
    public Image Char_number_1  ;
    public Image Char_number_2  ;
    public Image Char_number_3  ;
    public Image Char_number_4  ;
    public Image Char_number_5  ;
    public Image Char_number_6  ;

    //for hitbox
    public int hit_area_left ;
    public int hit_area_right ;
    public int hit_area_top ;
    public int hit_area_bottoms ;
    public boolean check_can_hit ;


   public Char() {

   }


    public Char(int number_img, int damage , int range ,int cost,int char_x,int char_y){
        enemy =new Enemy() ;
        this.damage = damage ;
        this.range = range ;
        this.cost = cost ;
        this.char_x = char_x ;
        this.char_y = char_y ;
        this.number_img = number_img ;

        this.hit_area_left = (char_x) - range/2 ;
        this.hit_area_right = (char_x) + range/2 ;
        this.hit_area_top = (char_y) - range /2 ;
        this.hit_area_bottoms = (char_y) + range/2 ;



        ImageIcon char_1 = new ImageIcon("char1.png") ;
        Char_number_1 = char_1.getImage() ;
        ImageIcon char_2 =new ImageIcon("char2.png") ;
        Char_number_2 = char_2.getImage() ;



    }

    public void draw(Graphics g) {
            if(number_img == 1) {
                g.drawImage(Char_number_1,char_x-30,char_y-30,50,50,form_map) ;
                g.setColor(Color.red);
                g.drawOval(char_x-105,char_y-105,range,range);


            }
        if(number_img == 2) {
            g.drawImage(Char_number_2,char_x-30,char_y-30,50,50,form_map) ;
            g.drawOval(char_x-105,char_y-105,range,range);
        }

    }

    public void hit(Enemy e) {
       if(e.enemy_y+50 >= hit_area_top && e.enemy_y <= hit_area_bottoms && e.enemy_x+50 >= hit_area_left && e.enemy_x <= hit_area_right) {
           check_can_hit = true ;
           target = true ;
       }
       else {
           check_can_hit = false ;
           target = false ;
       }
       if(check_can_hit&& target) {
           e.damage_take(this);
//           System.out.println("in area");
       }

   }
   public boolean select(Enemy e) {
       if(e.enemy_y+50 >= hit_area_top && e.enemy_y <= hit_area_bottoms && e.enemy_x+50 >= hit_area_left && e.enemy_x <= hit_area_right) {
           return true ;
       }
       else {
           return false ;
       }
   }

    public int getDamage() {
        return damage ;

    }

}
