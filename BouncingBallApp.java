//Name: Connor McNally
//Description: Creates a window in which bouncing balls can be created. Up to 20 balls will
//be created at location of a click inside window. Balls have random color, speed, and direction.
//Each ball has a shadow that will dynamically resize based on how high up the ball is. The balls will
//bounce off the edges of the frame and window resizeing is allowed, balls will adjust accordingly.
//Each ball will run in a sperate thread.

import javax.swing.JFrame;

public class BouncingBallApp{
    
    public static void main(String[] args){
        
        BouncingBallFrame frame = new BouncingBallFrame();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 1000);
        frame.setVisible(true);

             
    }
    
}
