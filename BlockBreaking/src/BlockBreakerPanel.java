
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<Block> ball = new ArrayList<Block>();
    ArrayList<Block> powerUp = new ArrayList<Block>();
    Block paddle;
    Thread thread;
    Animate animate;
    int size = 25;
    BlockBreakerPanel(){
        paddle = new Block(175, 480, 150, 25,"red.png");
        
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 0, 60, 35,"green.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 25, 60, 35,"green.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2),50, 60, 35,"yellow.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 75, 60, 35,"yellow.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 100, 60, 35,"yellow.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 125, 60, 35,"green.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 150, 60, 35,"green.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 175, 60, 35,"yellow.jpg"));
        }
        for(int i = 0; i<8; i++){
            blocks.add(new Block((i*60+2), 200, 60, 35,"green.jpg"));
        }
        
        Random random = new Random();
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        blocks.get(random.nextInt(32)).powerUp = true; 
        ball.add(new Block(237, 437, 40, 40,"ball.jpg"));
        
        addKeyListener(this);
        setFocusable(true);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        for(Block b: blocks)
            b.draw(g, this);
        for(Block b: ball)
            b.draw(g, this);
        for(Block p: powerUp)
            p.draw(g, this);
            paddle.draw(g, this);
        
    }
    public void update(){
        for(Block p : powerUp){
            p.y+=1;
           if(p.intersects(paddle) && !p.destroyed){
               p.destroyed = true;
               ball.add(new Block(paddle.dx+75, 437, 25, 25, "ball.jpg")); 
           }
        }
        for(Block ba : ball){
            ba.x+=ba.dx;
            if(ba.x>(getWidth()-size) && ba.dx>0 || ba.x<0)
                ba.dx*=-1;
            if(ba.y<0 || ba.intersects(paddle))
                ba.dy*=-1;
            ba.y+=ba.dy;
            for(Block b : blocks){
                if((b.left.intersects(ba)|| b.right.intersects(ba)) && !b.destroyed){
                   ba.dx*=-1;
                   b.destroyed = true;
                    if(b.powerUp)
                        powerUp.add(new Block(b.x, b.y, 25, 19, "extra.jpg"));
                        }
                else if(ba.intersects(b) && !b.destroyed){
                    b.destroyed = true;
                    ba.dy*=-1;
                    if(b.powerUp)
                        powerUp.add(new Block(b.x, b.y, 25, 19, "extra.jpg"));
                        }
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(e.getKeyCode() == KeyEvent.VK_ENTER){
           animate = new Animate(this);
           thread = new Thread(animate);
           thread.start();
       }
       if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x>0){
           paddle.x-=60;
        }
      
       if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth()-paddle.width)){
           paddle.x+=60;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
