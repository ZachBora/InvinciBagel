package invincibagel;

import javafx.scene.image.Image;

public class PropB extends Actor {

    public PropB(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        this.setIsFlipH(true);
        this.setIsFlipV(true);
        spriteFrame.setScaleX(-1);
        spriteFrame.setScaleY(-1);
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
    }

    @Override
    public void update() {
        
    }
    
}
