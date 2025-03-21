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
    //immage char
    public Image Char_number_1  ;
    public Image Char_number_2  ;
    public Image Char_number_3  ;
    public Image Char_number_4  ;
    public Image Char_number_5  ;
    public Image Char_number_6  ;

    public Char(int number_img, int damage , int range ,int cost,int char_x,int char_y){
        this.damage = damage ;
        this.range = range ;
        this.cost = cost ;
        this.char_x = char_x ;
        this.char_y = char_y ;
        this.number_img = number_img ;

        ImageIcon char_1 = new ImageIcon("char1.png") ;
        Char_number_1 = char_1.getImage() ;
        ImageIcon char_2 =new ImageIcon("char2.png") ;
        Char_number_2 = char_2.getImage() ;



    }

    public void draw(Graphics g) {
            if(number_img == 1) {
                g.drawImage(Char_number_1,char_x-30,char_y-30,50,50,form_map) ;
                g.setColor(Color.red);
                g.drawOval(char_x,char_y,range,range+60);
                g.drawRect(char_x,char_y,range,range+60);

            }
        if(number_img == 2) {
            g.drawImage(Char_number_2,char_x-30,char_y-30,50,50,form_map) ;
            g.setColor(Color.red);
            g.drawOval(char_x-105,char_y-105,range,range);
        }

    }

    public void hit() {

    }


}
