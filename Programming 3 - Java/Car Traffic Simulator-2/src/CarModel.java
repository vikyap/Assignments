import java.awt.*;

public class CarModel extends Car{
    CarModel(Road road){
        super(road);
        width = 30;
        height = 10;
    }
    public void paintMeHorizontal(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(xPos, yPos, width, height);
    }
    public void paintMeVertical(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect(yPos, xPos, height, width);
    }
}