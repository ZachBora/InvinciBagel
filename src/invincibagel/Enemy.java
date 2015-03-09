package invincibagel;

import javafx.scene.image.Image;

public class Enemy extends Actor{

    public Enemy(String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        spriteFrame.setTranslateX(xLocation);
        spriteFrame.setTranslateY(yLocation);
        isAlive = true;
        isBonus = true;
        hasValu = true;
    }

    @Override
    public void update() {
        
    }
    
}
