package invincibagel;

import static invincibagel.InvinciBagel.HEIGHT;
import static invincibagel.InvinciBagel.WIDTH;
import javafx.scene.image.Image;

public class Bagel extends Hero {

    protected static final double SPRITE_PIXELS_X = 81;
    protected static final double SPRITE_PIXELS_Y = 81;
    protected static final double rightBoundary = WIDTH / 2 - SPRITE_PIXELS_X / 2;
    protected static final double leftBoundary = -(WIDTH / 2 - SPRITE_PIXELS_X / 2);
    protected static final double bottomBoundary = HEIGHT / 2 - SPRITE_PIXELS_Y / 2;
    protected static final double topBoundary = -(HEIGHT / 2 - SPRITE_PIXELS_Y / 2);
    protected InvinciBagel invinciBagel;
    private boolean animator = false;
    private byte framecounter = 0;
    private byte runningspeed = 6;
    
    public Bagel(InvinciBagel iBagel, String SVGdata, double xLocation, double yLocation, Image... spriteCels) {
        super(SVGdata, xLocation, yLocation, spriteCels);
        this.invinciBagel = iBagel;
    }

    @Override
    public void update() {
        setXYLocation();
        setBoundaries();
        setImageState();
        moveInvinciBagel(iX, iY);
    }
    
    @Override
    public boolean collide(Actor object) {
        return false;
    }
    
    private void setXYLocation() {
        if(invinciBagel.isRight()) { iX += vX; }
        if(invinciBagel.isLeft()) { iX -= vX; }
        if(invinciBagel.isDown()) { iY += vY; }
        if(invinciBagel.isUp()) { iY -= vY; }
    }
    
    private void setImageState() {
        if (!invinciBagel.isDown() && 
            !invinciBagel.isUp() &&
            !invinciBagel.isLeft() &&
            !invinciBagel.isRight()) {
            spriteFrame.setImage(imageStates.get(0));
            animator = false;
            framecounter = 0;
        }
        if (invinciBagel.isRight()) { 
            spriteFrame.setScaleX(1);
            this.setIsFlipH(false);
            if (!invinciBagel.isUp() && !invinciBagel.isDown()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(2));
                } else {
                    spriteFrame.setImage(imageStates.get(1));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (invinciBagel.isLeft()) { 
            spriteFrame.setScaleX(-1);
            this.setIsFlipH(true);
            if (!invinciBagel.isUp() && !invinciBagel.isDown()) {
                if (animator) {
                    spriteFrame.setImage(imageStates.get(2));
                } else {
                    spriteFrame.setImage(imageStates.get(1));
                }
                if (framecounter >= runningspeed) {
                    animator = !animator;
                    framecounter = 0;
                } else {
                    framecounter += 1;
                }
            }
        }
        if (invinciBagel.isDown()) { spriteFrame.setImage(imageStates.get(6)); }
        if (invinciBagel.isUp()) { spriteFrame.setImage(imageStates.get(4)); }
        if (invinciBagel.iswKey()) { spriteFrame.setImage(imageStates.get(5)); }
        if (invinciBagel.issKey()) { spriteFrame.setImage(imageStates.get(8)); }
    }
    
    private void moveInvinciBagel(double x, double y) {
        spriteFrame.setTranslateX(x);
        spriteFrame.setTranslateY(y);
    }

    private void setBoundaries() {
        if (iX > rightBoundary) { iX = rightBoundary; }
        if (iX < leftBoundary) { iX = leftBoundary; }
        if (iY > bottomBoundary) { iY = bottomBoundary; }
        if (iY < topBoundary) { iY = topBoundary; }
    }
    
}
