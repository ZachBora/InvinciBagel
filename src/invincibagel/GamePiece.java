/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

/**
 *
 * @author ZachBora
 */
public class GamePiece {
    
    private static final String FLAVOR_OF_BAGEL = "Pumpernickel";
    private static final String SIZE_OF_BAGEL = "Mini Bagel";
    
    public int invinciBagelX = 0;
    public int invinciBagelY = 0;
    public String bagelOrientation = "side";
    public int lifeIndex = 1000;
    public int hitsIndex = 0;
    public String directionFacing = "E";
    public String movementType = "idle";
    public boolean currentlyMoving = false;
    
    GamePiece() {
        invinciBagelX = 0;
        invinciBagelY = 0;
        bagelOrientation = "side";
        lifeIndex = 1000;
        hitsIndex = 0;
        directionFacing = "E";
        movementType = "idle";
        currentlyMoving = false;
    }
    
    GamePiece(int x, int y, String orientation, int lifespan, String direction, 
            String movement) {
        invinciBagelX = x;
        invinciBagelY = y;
        bagelOrientation = orientation;
        lifeIndex = lifespan;
        hitsIndex = 0;
        directionFacing = direction;
        movementType = movement;
        currentlyMoving = false;
    }
    
    public void moveInvinciBagel(int x, int y) {
        currentlyMoving = true;
        invinciBagelX = x;
        invinciBagelY = y;
    }
    
    public String getInvinciBagelOrientation() {
        return bagelOrientation;
    }
    
    public void setInvinciBagelOrientation(String orientation) {
        bagelOrientation = orientation;
    }
    
    public int getInvinciBagelLifeIndex() {
        return lifeIndex;
    }
    
    public void setInvinciBagelLifeIndex(int lifespan) {
        lifeIndex = lifespan;
    }
    
    public String getInvinciBagelDirection() {
        return directionFacing;
    }
    
    public void setInvinciBagelDirection(String direction) {
        directionFacing = direction;
    }
    
    public int getInvinciBagelHitsIndex() {
        return hitsIndex;
    }
    
    public void setInvinciBagelHitsIndex(int damage) {
        hitsIndex = damage;
    }
    
    public String getInvinciBagelMovementType() {
        return movementType;
    }
    
    public void setInvinciBagelMovementType(String movement) {
        movementType = movement;
    }
}
