import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class map extends JPanel implements MouseListener {
    public int high = 600;
    public int width = 800;

    private int spawn_x;
    private int spawn_y;


    private int speed = 1;

    //Immage
    private Image map_bg;
    private Image char_bar;
    private Image effect_bar;


    private ArrayList<Char> unit = new ArrayList<>();
    private ArrayList<Enemy> enemy = new ArrayList<>() ;
    //enemy in hitbox
    public Timer timer_spawn ;


    private Mouse mouse = new Mouse();
    boolean isClicked = mouse.isMouse_clicked();

    public boolean isEffect_select_1 = false;
    private boolean isEffect_select_2 = false;
    private boolean isEffect_select_3 = false;
    private boolean isEffect_select_4 = false;
    private boolean isEffect_select_5 = false;
    private boolean isEffect_select_6 = false;

    // Timer for fps
    private Timer timer;
    private Timer timer_for_enemy ;

    //target setting
    private Enemy target = null ;

    public map() {
        setFocusable(true);
        addMouseListener(this);
        addMouseListener(mouse);

        ImageIcon bg_map = new ImageIcon("bg_map.png");
        map_bg = bg_map.getImage();
        ImageIcon bar_char = new ImageIcon("char_bar.png");
        char_bar = bar_char.getImage();
        ImageIcon bar_effect = new ImageIcon("Selected_effect.png");
        effect_bar = bar_effect.getImage();

        //enemy spawm part //ตอนเทสปรับเลือดตรงนี้
        enemy.add(new Enemy(20000,1,80,550))  ;

        timer_spawn = new Timer(2000,e -> {
            enemy.add(new Enemy(20000,1,80,550))  ;
        });
        timer_spawn.start();



        //game loop
        timer = new Timer(1000 / 60, e -> {


            isClicked = mouse.isMouse_clicked();
            select_effect();

            for(Enemy enemys : enemy) {
                enemys.move();
            }
            //just for check x y
            if (isClicked) {
                chenck_x_y();
            }
            //default hit (aoe)
//            for (Char units : unit) {
//                for (Enemy enemys : enemy) {
//                    units.hit(enemys);
//
//                }
//            }
            //hit char(single)
            for (Char units : unit) {
                if (target == null || !target.isAlive || !units.select(target)) {
                    target = null;
                    for (Enemy enemys : enemy) {
                        if (units.select(enemys) && enemys.isAlive) {
                            target = enemys;
                            break;
                        }
                    }
                }
                if (target != null && target.isAlive) {
                    units.hit(target);
                }
            }





            repaint();

        });
        timer.start();





    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(map_bg, 0, 0, width, high, this);




        //Chartactr and enemy paint
        for (Char units : unit) {
            units.draw(g2d);
        }
        for (Enemy enemys : enemy) {
            enemys.draw(g2d);
        }


        //Charracter bar
        g2d.drawImage(char_bar, 120, 450, 600, 100, this);


        //effect selected
        if(isEffect_select_1) {
            g2d.drawImage(effect_bar, 120, 450, 100, 100, this);

        }
        if(isEffect_select_2) {
            g2d.drawImage(effect_bar, 220, 450, 100, 100, this);

        }
        if(isEffect_select_3) {
            g2d.drawImage(effect_bar, 320, 450, 100, 100, this);
        }
        if(isEffect_select_4) {
            g2d.drawImage(effect_bar, 420, 450, 100, 100, this);
        }
        if(isEffect_select_5) {
            g2d.drawImage(effect_bar, 520, 450, 100, 100, this);
        }
        if(isEffect_select_6) {
            g2d.drawImage(effect_bar, 620, 450, 100, 100, this);
        }




    }


    public void chenck_x_y() {
        if (isClicked) {
            System.out.print("X: ");
            System.out.println(mouse.getMouse_x());
            System.out.printf("Y: ");
            System.out.println(mouse.getMouse_y());
            mouse.mouse_clicked = false;
            System.out.println(mouse.cick_count);
        }
    }

    public void select_effect() {

        if (mouse.getMouse_x() >= 121 && mouse.getMouse_x() <= 218 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_1 = true;
        }
        if (mouse.getMouse_x() >= 220 && mouse.getMouse_x() <= 320 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_2 = true;
        }
        if (mouse.getMouse_x() >= 320 && mouse.getMouse_x() <= 420 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_3 = true;
        }
        if (mouse.getMouse_x() >= 420 && mouse.getMouse_x() <= 520 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_4 = true;
        }
        if (mouse.getMouse_x() >= 520 && mouse.getMouse_x() <= 620 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_5 = true;
        }
        if (mouse.getMouse_x() >= 620 && mouse.getMouse_x() <= 720 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_6 = true;
        }


    }

    public void selected() {

    }


    //mosue setting
    @Override
    public void mouseClicked(MouseEvent e) {
        spawn_x = e.getX();
        spawn_y = e.getY();
        if(isEffect_select_1) {
            //ปรับดาเมจตัวแรกตรงนี้
            unit.add(new Char(1,50,200,20,spawn_x,spawn_y)) ;
        }
        if(isEffect_select_2) {
            unit.add(new Char(2,300,200,20,spawn_x,spawn_y)) ;
        }
//        if(isEffect_select_3) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }
//        if(isEffect_select_4) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }
//        if(isEffect_select_5) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }
//        if(isEffect_select_6) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }



        if (mouse.getMouse_x() >= 121 && mouse.getMouse_x() <= 218 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_1 = false;
        }
        if (mouse.getMouse_x() >= 220 && mouse.getMouse_x() <= 320 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_2 = false;
        }
        if (mouse.getMouse_x() >= 320 && mouse.getMouse_x() <= 420 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_3 = false;
        }
        if (mouse.getMouse_x() >= 420 && mouse.getMouse_x() <= 520 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_4 = false;
        }
        if (mouse.getMouse_x() >= 520 && mouse.getMouse_x() <= 620 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_5 = false;
        }
        if (mouse.getMouse_x() >= 620 && mouse.getMouse_x() <= 720 && mouse.getMouse_y() >= 460 && mouse.getMouse_y() <= 549) {
            isEffect_select_6 = false;
        }




    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
