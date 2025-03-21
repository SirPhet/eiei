import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("TowerDefense");
        window.setSize(800,600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false) ; //กันทะลึ่งบ้องมาลองบัคเกมกู
        window.add(new map()) ;

        window.setVisible(true);

    }
}
