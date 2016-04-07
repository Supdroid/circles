package circles.haouech.com.circles;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * Created by Haouech on 14/03/2016.
 */
public class Circle {

    protected int x,y, size;
    protected Color color;
    protected int world_x, world_y;

    public Circle()
    {
        x=0;
        y=0;
        size=0;
        color=null;
    }
    public Circle(int x, int y, int size,Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }


    public int getX() {return x;}
    public int getY() {return y;}
    public int getSize() {return size;}
    public Color getColor(){return color;}

    public void setX(int x) {this.x=x;}
    public void setY(int y) {this.y=y;}
    public void setSize(int size) {this.size= size;}
    public void setColor(Color c){this.color=c;}

    public void addX(int a){x+=a;}
    public void addY(int a){y+=a;}
    public void addSize(int a){size+=a;}

    public void animate()
    {

    }

}
