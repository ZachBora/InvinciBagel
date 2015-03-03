package invincibagel;

import javafx.animation.AnimationTimer;

public class GamePlayLoop extends AnimationTimer {
    
    protected InvinciBagel invinciBagel;
    
    public GamePlayLoop(InvinciBagel iBagel) {
        this.invinciBagel = iBagel;
    }
    
    @Override
    public void handle(long now) {
        invinciBagel.iBagel.update();
    }
    
    @Override
    public void start() {
        super.start();
    }
    
    @Override
    public void stop() {
        super.stop();
    }

}
