import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 4;

    Ball(int x, int y, int width, int height){
        super(x,y,width,height);
        random = new Random();
        int randomXDirectecion = random.nextInt(2);
        if (randomXDirectecion == 0)
            randomXDirectecion--;
        setXDirection(randomXDirectecion*initialSpeed);

        int randomYDirectecion = random.nextInt(2);
        if (randomYDirectecion == 0)
            randomYDirectecion--;
        setYDirection(randomYDirectecion*initialSpeed);

    }
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,width, height);
    }

}
