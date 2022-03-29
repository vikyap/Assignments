import java.awt.*;

public class Bus extends Car {
    Bus(Road road){
        super(road);
        width = 45;
        height = 15;
    }
    public void paintMeHorizontal(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(xPos, yPos, width, height);
    }
    public void paintMeVertical(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(yPos, xPos, height, width);
    }}
