//import javax.swing.*;
//
//public class Main {
//    public static void main(String[] args) {
//
//        JFrame window = new JFrame("TowerDefense");
//        window.setSize(1440,810);
//        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
////        window.setLocationRelativeTo(null);
//
//
//        window.setResizable(false) ; //กันทะลึ่งบ้องมาลองบัคเกมกู
//        window.add(new map()) ;
//
//        window.setVisible(true);
//
//    }
//}
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame("TowerDefense");
        window.setSize(1440, 810);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ดึงข้อมูลจอทั้งหมด
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        // ถ้ามีจอมากกว่า 1 (คือมีจอเสริม)
        if (screens.length > 1) {
            Rectangle bounds = screens[1].getDefaultConfiguration().getBounds();
            window.setLocation(bounds.x + 1100, bounds.y + 500); // ไปขึ้นที่จอเสริม
        } else {
            window.setLocationRelativeTo(null); // ถ้ามีจอเดียวก็เปิดกลางจอ
        }

        window.setResizable(false); // กันทะลึ่งบ้องมาลองบัคเกมกู
        window.add(new map());
        window.setVisible(true); 
    }
}
