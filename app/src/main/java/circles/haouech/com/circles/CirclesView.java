package circles.haouech.com.circles;

/**
 * Created by Haouech on 14/03/2016.
 */
import android.content.Context;
import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.os.Handler;
        import android.os.Message;
        import android.util.AttributeSet;
        import android.view.MotionEvent;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;
public class CirclesView extends SurfaceView implements
        SurfaceHolder.Callback {

    private Context myContext;
    private SurfaceHolder mySurfaceHolder;
    private Bitmap backgroundImg;
    private int screenW = 1;
    private int screenH = 1;
    private boolean running = false;
    private boolean onTitle = true;
    private CircleP CPlayer;
    private Circle[] c = new Circle[400];
    private int circle_number=400;

    private WhackAMoleThread thread;
    public CirclesView(Context context,
                          AttributeSet attrs) {
        super(context, attrs);
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        thread = new WhackAMoleThread(holder, context,
                new Handler() {
                    @Override
                    public void handleMessage(Message m) {
                    }
                });
        setFocusable(true);
    }
    public WhackAMoleThread getThread() {
        return thread;
    }
    class WhackAMoleThread extends Thread {
        public WhackAMoleThread(SurfaceHolder
                                        surfaceHolder, Context context,
                                Handler handler) {
            mySurfaceHolder = surfaceHolder;
            myContext = context;

        }
        @Override
        public void run() {
            while (running) {
                Canvas c = null;
                try {
                    c = mySurfaceHolder.lockCanvas(null);
                    synchronized (mySurfaceHolder) {
                        animate();
                        draw(c);
                    }
                } finally {
                    if (c != null) {
                        mySurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
        private void animate()
        {
            for(int i=0;i<circle_number; i++)
            {
                c[i].animate();
            }
        }

        private void draw(Canvas canvas) {
            try {
                canvas.drawBitmap(backgroundImg, 0, 0,
                        null);
            } catch (Exception e) {
            }
        }
        boolean doTouchEvent(MotionEvent event) {
            synchronized (mySurfaceHolder) {
                int eventaction = event.getAction();
                int X = (int)event.getX();
                int Y = (int)event.getY();
                switch (eventaction ) {
                    case MotionEvent.ACTION_DOWN:
                        CPlayer.animate(X,Y, 0);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        CPlayer.animate(X,Y, 0);
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
            }
            return true;
        }
        public void setSurfaceSize(int width,
                                   int height) {
            synchronized (mySurfaceHolder) {
                screenW = width;
                screenH = height;
                backgroundImg = Bitmap.createScaledBitmap(
                        backgroundImg, width, height,
                        true);
            }
        }
        public void setRunning(boolean b) {
            running = b;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {return thread.doTouchEvent(event);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int
            format, int width, int height) {
        thread.setSurfaceSize(width, height);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        if (thread.getState() == Thread.State.NEW) {
            thread.start();
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread.setRunning(false);
    }
}



