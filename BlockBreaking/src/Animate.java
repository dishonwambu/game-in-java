
import java.util.logging.Level;
import java.util.logging.Logger;

public class Animate implements Runnable{
    BlockBreakerPanel bp;
    
    Animate(BlockBreakerPanel b){
        
        bp = b;
    }
    public void run(){
        while(true){
            bp.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
