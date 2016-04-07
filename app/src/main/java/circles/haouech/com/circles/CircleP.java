package circles.haouech.com.circles;

import android.graphics.Color;

/**
 * Created by Haouech on 14/03/2016.
 */
public class CircleP extends Circle {

    int score;
    public CircleP(int x, int y, int size, Color c)
    {
        this.x=x;
        this.y=y;
        this.size=size;
        this.color=c;
    }

    public void animate(int a, int b, int s)
    {
        x+=a;
        y+=b;
        size+=s;
    }

}
