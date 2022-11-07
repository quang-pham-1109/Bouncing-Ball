import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Panel extends JPanel implements Runnable {
    static final int WIDTH = 1000;
    static final int HEIGHT = 650;
    static final int BALL_SIZE = 20;
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);

    static int INITIAL_BALL_NUM = 3;
    static int BALL_NUM = INITIAL_BALL_NUM;
    static int MAXIMUM_BALL_NUM = 1000;

    public Thread gameThread;
    public Graphics graphics;
    public Image image;

    public Ball ball[] = new Ball[MAXIMUM_BALL_NUM];

    Panel() {
        newBall();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall() {
        Random random = new Random();

        //Initialize objects
        for (int i = 0; i < INITIAL_BALL_NUM; i++ ){
            ball[i] = new Ball(random.nextInt(WIDTH - BALL_SIZE), random.nextInt(HEIGHT - BALL_SIZE), BALL_SIZE, BALL_SIZE);
        }
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getWidth());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        //Draw objects
        for (int i = 0; i < BALL_NUM; i++){
            ball[i].draw(g);
        }
    }

    public void move() {
        //Move objects
        for (int i = 0; i < BALL_NUM; i++){
            ball[i].move();
        }
    }
    public void checkCollision() {
        for (int i = 0; i < BALL_NUM; i++) {
            //Check for border collision
            if (ball[i].y <= 0) {
                ball[i].setYDirection(-ball[i].yVelocity);
            }
            if (ball[i].y >= HEIGHT - BALL_SIZE) {
                ball[i].setYDirection(-ball[i].yVelocity);
            }
            if (ball[i].x <= 0) {
                ball[i].setXDirection(-ball[i].xVelocity);
            }
            if (ball[i].x >= WIDTH - BALL_SIZE) {
                ball[i].setXDirection(-ball[i].xVelocity);
            }

            //Check for collision of the balls
            for (int j = 0; j < INITIAL_BALL_NUM; j++){
                if (j != i){
                    if (ball[j].intersects(ball[i])){
                        ball[i].setYDirection(-ball[i].yVelocity);
                        ball[i].setXDirection(-ball[i].xVelocity);

                        ball[j].setYDirection(-ball[j].yVelocity);
                        ball[j].setXDirection(-ball[j].xVelocity);

                        Random random = new Random();
                        ball[BALL_NUM] = new Ball(random.nextInt(WIDTH - BALL_SIZE), random.nextInt(HEIGHT - BALL_SIZE), BALL_SIZE, BALL_SIZE);
                        BALL_NUM++;
                    }
                }
            }
        }
    }


    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        try {
            while (true) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                if (delta >= 1) {
                    move();
                    repaint();
                    checkCollision();
                    delta--;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("THAT'S 1000 BALLS!");
        }
    }
}

