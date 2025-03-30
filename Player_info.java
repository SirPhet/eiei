import java.awt.*;

public class Player_info {
    public int coin ;
    public int tower_healt ;

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


    public void draw(Graphics g) {
        String coint_string = String.valueOf(getCoin()) ;
        g.drawString(coint_string,20,40);


    }


}
