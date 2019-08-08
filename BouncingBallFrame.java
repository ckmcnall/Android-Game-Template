//Name: Connor McNally
//Description: Bouncing ball frame class

import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

//JFrame that will hold balls
public class  BouncingBallFrame extends JFrame implements MouseListener {
    
    private final Ball[] balls;
    private final int MAX_NUM_BALLS = 20;
    private int currentBall;
    protected SecureRandom rand;
    protected ExecutorService executorService;
    protected int width = getWidth();
    protected int height = getHeight();
    
    
    public BouncingBallFrame(){
        
        super("Bouncing Balls");
        currentBall = 0;
        balls = new Ball[MAX_NUM_BALLS];
        rand = new SecureRandom();
        addMouseListener(this);
        
        
        executorService = Executors.newCachedThreadPool();
        
    }
    
    //When mouse is clicked
    @Override public void mouseClicked(MouseEvent event){
        
        //make sure max num balls is not excceded
        if(currentBall < MAX_NUM_BALLS){
            
            //create ball object at location mouse is clicked with random speed, direction and color
            balls[currentBall] = new Ball(event.getX(), event.getY(), rand.nextInt(10) * (rand.nextBoolean() ? -1 : 1),
                    rand.nextInt(10) * (rand.nextBoolean() ? -1 : 1), 
                    new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)), 20, this);
            
            //execute ball object and add it to frame.
            executorService.execute(balls[currentBall]);
            add(balls[currentBall]);
            
            ++currentBall;
            this.validate();
            
        }
        
    }
    
    //unimplemeted mouse listener functions (they aren't needed but compiler complains if they're not here)
    @Override public void mouseExited(MouseEvent event){

    }
    
    @Override public void mouseEntered(MouseEvent event){

    }
    
    @Override public void mouseReleased(MouseEvent event){

    }
    
    @Override public void mousePressed(MouseEvent event){

    }
    
}
