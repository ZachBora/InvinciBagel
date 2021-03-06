package invincibagel;

import static invincibagel.InvinciBagel.HEIGHT;
import static invincibagel.InvinciBagel.WIDTH;
import javafx.scene.image.Image;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class Bagel extends Hero {

    protected static final double SPRITE_PIXELS_X = 81;
    protected static final double SPRITE_PIXELS_Y = 81;
    protected static final double rightBoundary = WIDTH - SPRITE_PIXELS_X;
    protected static final double leftBoundary = 0;
    protected static final double bottomBoundary = HEIGHT - SPRITE_PIXELS_Y;
    protected static final double topBoundary = 0;
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
        //playAudioClip();
        checkCollision();
    }
    
    @Override
    public boolean collide(Actor object) {
        boolean collisionDetect = false;
        
        if(invinciBagel.iBagel.spriteFrame.getBoundsInParent().intersects(object.getSpriteFrame().getBoundsInParent())) {
            Shape intersection = SVGPath.intersect(invinciBagel.iBagel.getSpriteBound(), object.getSpriteBound());
            if(intersection.getBoundsInLocal().getWidth() != -1) {
                collisionDetect = true;
            }
        }
        
        return collisionDetect;
    }
    
    private void setXYLocation() {
        if(invinciBagel.isRight()) { iX += vX * 2; }
        if(invinciBagel.isLeft()) { iX -= vX * 2; }
        if(invinciBagel.isDown()) { iY += vY * 2; }
        if(invinciBagel.isUp()) { iY -= vY * 2; }
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
    
    private void playAudioClip() {
        if(invinciBagel.isLeft()) { invinciBagel.playiSound0(); }
        if(invinciBagel.isRight()) { invinciBagel.playiSound1(); }
        if(invinciBagel.isUp()) { invinciBagel.playiSound2(); }
        if(invinciBagel.isDown()) { invinciBagel.playiSound3(); }
        if(invinciBagel.iswKey()) { invinciBagel.playiSound4(); }
        if(invinciBagel.issKey()) { invinciBagel.playiSound5(); }
    }

    private void setBoundaries() {
        if (iX > rightBoundary) { iX = rightBoundary; }
        if (iX < leftBoundary) { iX = leftBoundary; }
        if (iY > bottomBoundary) { iY = bottomBoundary; }
        if (iY < topBoundary) { iY = topBoundary; }
    }
    
    public void checkCollision() {
        for(int i=0; i<invinciBagel.castDirector.getCurrentCast().size(); i++) {
            Actor object = invinciBagel.castDirector.getCurrentCast().get(i);
            
            if(collide(object)) {
                invinciBagel.castDirector.addToRemovedActors(object);
                invinciBagel.root.getChildren().remove(object.getSpriteFrame());
                invinciBagel.castDirector.resetRemovedActors();
                scoringEngine(object);
            }
        }
    }
    
    private void scoringEngine(Actor object) {
        if(object instanceof Prop) {
            invinciBagel.gameScore -= 1;
            invinciBagel.playiSound0();
        } else if(object instanceof PropV) {
            invinciBagel.gameScore -= 2;
            invinciBagel.playiSound1();
        } else if(object instanceof PropH) {
            invinciBagel.gameScore -= 1;
            invinciBagel.playiSound2();
        } else if(object instanceof PropB) {
            invinciBagel.gameScore -= 2;
            invinciBagel.playiSound3();
        } else if(object instanceof Treasure) {
            invinciBagel.gameScore += 5;
            invinciBagel.playiSound4();
        } else if(object.equals(invinciBagel.iBullet)) {
            invinciBagel.gameScore -= 5;
            invinciBagel.playiSound5();
        } else if(object.equals(invinciBagel.iCheese)) {
            invinciBagel.gameScore += 5;
            invinciBagel.playiSound0();
        } else if(object.equals(invinciBagel.iBeagle)) {
            invinciBagel.gameScore += 10;
            invinciBagel.playiSound0();
        }
        invinciBagel.scoreText.setText(String.valueOf(invinciBagel.gameScore));
    }
}
