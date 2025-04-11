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
    public int high = 810;
    public int width = 1440;
    public Player_info player ;
    private int spawn_x;
    private int spawn_y;


    private int speed = 1;

    //Immage
    private final Image map_bg;
    private final Image char_bar;
    private final Image effect_bar;

    //Char price
    private final int char_1_pric = 50 ;
    private final int char_2_pric = 100 ;
    private final int char_3_pric = 150 ;
    private final int char_4_pric = 200 ;
    private final int char_5_pric = 250 ;
    private final int char_6_pric = 1000 ;

    private ArrayList<Char> unit = new ArrayList<>();
    private ArrayList<Enemy> enemy = new ArrayList<>() ;


    //eney wave spawn delay
    private int spawnDelay = 2000  ;
    private int enemy_count = 0  ;
    private int enemy_deat = 0 ;

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
    private int currentWave = 1;

    //target setting
    private Enemy target = null ;

    public map() {
        setFocusable(true);
        addMouseListener(this);
        addMouseListener(mouse);

        //player info
        player = new Player_info(100,200) ;


        ImageIcon bg_map = new ImageIcon("bg_map.png");
        map_bg = bg_map.getImage();
        ImageIcon bar_char = new ImageIcon("char_bar.png");
        char_bar = bar_char.getImage();
        ImageIcon bar_effect = new ImageIcon("Selected_effect.png");
        effect_bar = bar_effect.getImage();

        //enemy spawm part //ตอนเทสปรับเลือดตรงนี้
        //start form x45 y 100


        timer_for_enemy = new Timer(spawnDelay, e -> {
            if (currentWave == 1 && enemy_count < 7) {
                enemy.add(new Enemy(200, 1, 45, 100));
                enemy_count += 1;
                if (enemy_count == 7) {
                    currentWave = 2;
                }
            } else if (currentWave == 2 && enemy_deat >= 7 && enemy_count < 14) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 13) {
                    currentWave = 3 ;
                }
            } else if (currentWave == 3 && enemy_deat >= 11 && enemy_count < 21) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 20) {
                    currentWave = 4 ;
                }
            } else if (currentWave == 4 && enemy_deat >= 17 && enemy_count < 28 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 28) {
                    currentWave = 5 ;
                }
            }
            else if (currentWave == 5 && enemy_deat >= 23 && enemy_count < 35 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 35) {
                    currentWave = 6 ;
                }
            }
            else if (currentWave == 6 && enemy_deat >= 31 && enemy_count < 42 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 42) {
                    currentWave = 7 ;
                }
            }
            else if (currentWave == 7 && enemy_deat >= 38 && enemy_count < 49 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 49) {
                    currentWave = 8 ;
                }
            }
            else if (currentWave == 8 && enemy_deat >= 45 && enemy_count < 56 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 56) {
                    currentWave = 9 ;
                }
            }
            else if (currentWave == 9 && enemy_deat >= 51 && enemy_count < 63 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 63) {
                    currentWave = 10 ;
                }
            }
            else if (currentWave == 10 && enemy_deat >= 57 && enemy_count < 70 ) {
                enemy.add(new Enemy(200, 2, 45, 100));
                enemy_count += 1;
                if (enemy_count == 70) {
                    currentWave = 10 ;
                }
            }
        });
        timer_for_enemy.start();










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

            //for check enemy alive and add

            try {
                if(enemy.size() != 0) {
                    for (int i =  0 ; i < enemy.size() ; i++) {
                        if (!enemy.get(i).isAlive) {
                            player.addMoney(100);
                            enemy.remove(i) ;
                            enemy_deat += 1 ;
                            System.out.println("enemy_deat"+enemy_deat);

                        }
                        if(enemy.get(i).enemy_x>1300 && enemy.get(i).enemy_y > 600) {
                            player.atkTower(50);
                            enemy.remove(i) ;

                        }


                    }
                }

            }
            catch (Exception exception) {
                System.out.println("out of enemy");
            }






            repaint();

        });
        timer.start();

        //area_of_bar





    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(map_bg, 0, 0, width, high, this);

        g2d.setFont(player.getFont());
        String wave_string = String.valueOf(currentWave) ;
        g2d.drawString("wave "+wave_string,600,70);


        //player info update
        player.draw(g2d);

        //Chartactr and enemy paint
        for (Char units : unit) {
            units.draw(g2d);
        }
        for (Enemy enemys : enemy) {
            enemys.draw(g2d);
        }


        //Charracter bar
        g2d.drawImage(char_bar, 420, 680, 600, 100, this);


        //effect selected
        if(isEffect_select_1  ) {
            g2d.drawImage(effect_bar, 420, 680, 100, 100, this);

        }
        if(isEffect_select_2) {
            g2d.drawImage(effect_bar, 520, 680, 100, 100, this);

        }
        if(isEffect_select_3) {
            g2d.drawImage(effect_bar, 620, 680, 100, 100, this);
        }
        if(isEffect_select_4) {
            g2d.drawImage(effect_bar, 720, 680, 100, 100, this);
        }
        if(isEffect_select_5) {
            g2d.drawImage(effect_bar, 820, 680, 100, 100, this);
        }
        if(isEffect_select_6) {
            g2d.drawImage(effect_bar, 920, 680, 100, 100, this);
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

        if (mouse.getMouse_x() >= 420 && mouse.getMouse_x() <= 520 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_1 = true;
        }
        if (mouse.getMouse_x() >= 520 && mouse.getMouse_x() <= 620 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_2 = true;
        }
        if (mouse.getMouse_x() >= 620 && mouse.getMouse_x() <= 720 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_3 = true;
        }
        if (mouse.getMouse_x() >= 720 && mouse.getMouse_x() <= 820 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_4 = true;
        }
        if (mouse.getMouse_x() >= 820 && mouse.getMouse_x() <= 920 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_5 = true;
        }
        if (mouse.getMouse_x() >= 920 && mouse.getMouse_x() <= 1020 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
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
        if(!(spawn_x >= 420 && spawn_x <= 1020 && spawn_y >= 680 && spawn_y <= 780) &&
                !(spawn_x >= 3 && spawn_x <=1335 &&spawn_y>= 90 &&spawn_y<=154) &&
                !(spawn_x>=1253 && spawn_x <= 1336 && spawn_y>=163 && spawn_y <= 500) &&
                !(spawn_x>=128 && spawn_x <= 1250 && spawn_y>=414 && spawn_y <= 503) &&
                !(spawn_x >= 128 && spawn_x <= 228 && spawn_y>= 500 && spawn_y <= 600)&&
                !(spawn_x >= 130 && spawn_x<= 1439 && spawn_y>= 600 && spawn_y <= 685))  {
            if(isEffect_select_1 && player.coin >= 100) {
                //ปรับดาเมจตัวแรกตรงนี้
                unit.add(new Char(1,150,200,20,spawn_x,spawn_y)) ;
                player.addMoney(-100);
            }
            if(isEffect_select_2) {
                unit.add(new Char(2,20000,200,20,spawn_x,spawn_y)) ;
            }
//        if(isEffect_select_3) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }
             if(isEffect_select_4) {
               unit.add(new Char(4,300,200,20,spawn_x,spawn_y)) ;
             }
        if(isEffect_select_5) {
            unit.add(new Char(5,300,200,20,spawn_x,spawn_y)) ;
        }
//        if(isEffect_select_6) {
//            unit.add(new Char(300,200,20,spawn_x,spawn_y)) ;
//        }
        }






        if (mouse.getMouse_x() >= 420 && mouse.getMouse_x() <= 520 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_1 = false;
        }
        if (mouse.getMouse_x() >= 520 && mouse.getMouse_x() <= 620 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_2 = false;
        }
        if (mouse.getMouse_x() >= 620 && mouse.getMouse_x() <= 720 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_3 = false;
        }
        if (mouse.getMouse_x() >= 720 && mouse.getMouse_x() <= 820 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_4 = false;
        }
        if (mouse.getMouse_x() >= 820 && mouse.getMouse_x() <= 920 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
            isEffect_select_5 = false;
        }
        if (mouse.getMouse_x() >= 920 && mouse.getMouse_x() <= 1020 && mouse.getMouse_y() >= 680 && mouse.getMouse_y() <= 780) {
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
