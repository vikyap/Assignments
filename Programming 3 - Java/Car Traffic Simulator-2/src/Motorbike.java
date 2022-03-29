import java.awt.*;

public class Motorbike extends Car {
    Motorbike(Road road){
        super(road);
        width = 10;
        height = 5;
    }
    public void paintMeHorizontal(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(xPos, yPos, width, height);
    }
    public void paintMeVertical(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(yPos, xPos, height, width);
    }
}

