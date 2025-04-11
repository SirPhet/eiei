import java.awt.*;

public class Player_info {
    public int coin ;
    public int tower_healt ;
    public Font font = new Font("Minecraft",Font.PLAIN,60) ;

    public Player_info(int coin ,int tower_healt) {
        this.coin = coin ;
        this.tower_healt = tower_healt ;
    }

    public void buy(int price) {
        coin = coin - price ;
    }
    public int getCoin() {
        return coin ;
    }

    public void addMoney(int money_added) {
        coin += money_added ;
    }
    public void atkTower(int damange_form_enemy){
        tower_healt -= damange_form_enemy ;
    }


    public void draw(Graphics g) {

        //Money
        g.setFont(font);
        String coint_string = String.valueOf(getCoin()) ;
        g.drawString(coint_string,90,60);
        //Tower Healt
        String tower_healt_string = String.valueOf(tower_healt) ;
        g.drawString(tower_healt_string,1200,60);



    }

    public Font getFont() {
        return font;
    }
}
