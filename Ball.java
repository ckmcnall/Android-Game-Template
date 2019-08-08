//Name: Connor McNally
//Description: Ball class. Ball objects are runnable; each ball will run in a seperate thread.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

//Ball class
public class Ball extends JComponent implements Runnable {

    private int x, y;
    private final int radius = 50;
    private final Color color;
    private int vx, vy;
    private final int delay;
    int numTimesHitFloor = 0;

    //Used for passing a reference to frame to get window size if it changes
    private final BouncingBallFrame b;

    private int shadowWidth, shadowHeight;

    //Constructor. Frame rate can be adjusted by changing Delay arguement
    public Ball(int X, int Y, int DX, int DY, Color C, int Delay, BouncingBallFrame B) {

        x = X;
        y = Y;
        vx = DX;
        vy = DY;
        color = C;
        delay = Delay;
        b = B;

    }

    //Run function, you specified that only a call to move should be made here
    //so I added the repaint and sleep functions and the loop to move()
    @Override
    public void run() {

        move();

    }

    //move function: move ball, repaint, wait, repeat
    public void move() {

        while (true) {
            //normal movement
            x = x + (int)vx;
            y = y + (int)vy;
            

                vy += 1;


            //hit left edge
            if (x - radius < 0) {

                vx = -vx - 1;
                x = radius;

                //hit right edge
            } else if (x + radius > b.getWidth() - 15) {

                vx = -vx  + 1;
                x = b.getWidth() - 15 - radius;
            }

            //hit top edge
            if (y - radius < 0) {

                vy = -vy;
                y = radius;

                //hit floor (leaves space for shadows)
            } else if (y + radius > b.getHeight() - 90) {

                vy =  -vy + 5;
                y = b.getHeight() - 90 - radius;
                
   

            }

            repaint();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {

            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent((Graphics2D) g);

        //This makes sure the balls will contiune to get drawn even when the window resizes
        g.setClip(0, 0, b.getWidth(), b.getHeight());

        //shadow size changes depending on how high the ball is
        shadowWidth = (int) (radius * 2 - (0 - y / (15.0) + (radius)));
        shadowHeight = (int) (radius / 4 - ((0 - y / (50.0))));

        //Draw shadow first (so ball will overlap it)
        g.setColor(Color.BLACK);
        g.fillOval(x - (shadowWidth / 2), b.getHeight() - 100, shadowWidth, shadowHeight);

        //Draw Ball
        g.setColor(color);
        g.fillOval((x - radius), (y - radius), radius * 2, radius * 2);

        //The following code will draw line betwen the ball center and shadow center
        //to ensure they are aligned, commented out as I only used it to test
        g.setColor(color.black);
        g.drawLine(x, y, x, y + (int)vy * 10);
        g.setColor(color.red);
        g.drawLine(x, y, x + vx *10, y + (int)vy * 10);
        g.setColor(color.black);
        g.drawLine(x, y, x + vx *10,y);
    }

}







