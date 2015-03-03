/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package invincibagel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ZachBora
 */
public class InvinciBagel extends Application {
    static final double WIDTH = 640, HEIGHT = 400;
    private boolean up, down, left, right, aKey, dKey, sKey, wKey;
    private StackPane root;
    private HBox buttonContainer;
    Bagel iBagel;
    Prop iPR0, iPR1;
    PropH iPH0;
    PropV iPV0, iPV1;
    PropB iPB0;
    private Scene scene;
    private Image splashScreen, instructionLayer, legalLayer, scoresLayer;
    private Image iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8, iP0, iP1;
    private ImageView splashScreenBackplate, splashScreenTextArea;
    private Button gameButton, helpButton, scoreButton, legalButton;
    private Insets buttonContainerPadding;
    private GamePlayLoop gamePlayLoop;
    private CastingDirector castDirector;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("InvinciBagel");
        root = new StackPane();
        scene = new Scene(root, WIDTH, HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
        createSceneEventHandling();
        loadImageAssets();
        createGameActors();
        addGameActorNodes();
        createCastingDirection();
        createSplashScreenNodes();
        addNodesToStackPane();
        createStartGameLoop();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
    
    public boolean isaKey() {
        return aKey;
    }

    public void setaKey(boolean aKey) {
        this.aKey = aKey;
    }

    public boolean isdKey() {
        return dKey;
    }

    public void setdKey(boolean dKey) {
        this.dKey = dKey;
    }

    public boolean issKey() {
        return sKey;
    }

    public void setsKey(boolean sKey) {
        this.sKey = sKey;
    }

    public boolean iswKey() {
        return wKey;
    }

    public void setwKey(boolean wKey) {
        this.wKey = wKey;
    }
    
    private void createSplashScreenNodes() {
        buttonContainer = new HBox(12);
        buttonContainer.setAlignment(Pos.BOTTOM_LEFT);
        buttonContainerPadding = new Insets(0, 0, 10, 16);
        buttonContainer.setPadding(buttonContainerPadding);
        gameButton = new Button();
        gameButton.setLayoutX(0);
        gameButton.setText("PLAY GAME");
        gameButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(false);
            splashScreenTextArea.setVisible(false);
        });
        helpButton = new Button();
        helpButton.setText("INSTRUCTIONS");
        helpButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(instructionLayer);
        });
        scoreButton = new Button();
        scoreButton.setText("HIGH SCORES");
        scoreButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(scoresLayer);
        });
        legalButton = new Button();
        legalButton.setText("LEGAL & CREDITS");
        legalButton.setOnAction((ActionEvent) -> {
            splashScreenBackplate.setVisible(true);
            splashScreenTextArea.setVisible(true);
            splashScreenTextArea.setImage(legalLayer);
        });
        buttonContainer.getChildren().addAll(gameButton, helpButton, scoreButton, legalButton);
        splashScreenBackplate = new ImageView();
        splashScreenBackplate.setImage(splashScreen);
        splashScreenTextArea = new ImageView();
        splashScreenTextArea.setImage(instructionLayer);
    }
    
    private void addNodesToStackPane() {
        root.getChildren().add(splashScreenBackplate);
        root.getChildren().add(splashScreenTextArea);
        root.getChildren().add(buttonContainer);
    }

    private void createSceneEventHandling() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = true; break;
                case UP: up = true; break;
                case S:  sKey = true; break;
                case DOWN: down = true; break;
                case A:  aKey = true; break;
                case LEFT: left = true; break;
                case D: dKey = true; break;
                case RIGHT: right = true; break;
            }
        });
        scene.setOnKeyReleased((KeyEvent event) -> {
            switch (event.getCode()) {
                case W:  wKey = false; break;
                case UP: up = false; break;
                case S: sKey = false; break;
                case DOWN: down = false; break;
                case A: aKey = false; break;
                case LEFT: left = false; break;
                case D: dKey = false; break;
                case RIGHT: right = false; break;
            }
        });
    }

    private void createStartGameLoop() {
        gamePlayLoop = new GamePlayLoop(this);
        gamePlayLoop.start();
    }

    private void loadImageAssets() {
        splashScreen = new Image("/invincibagelsplash.png", 640, 400, true, false, true);
        instructionLayer = new Image("/invincibagelinstruct.png", 640, 400, true, false, true);
        legalLayer = new Image("/invincibagelcreds.png", 640, 400, true, false, true);
        scoresLayer = new Image("/invincibagelscores.png", 640, 400, true, false, true);
        iB0 = new Image("/sprite0.png", 81, 81, true, false, true);
        iB1 = new Image("/sprite1.png", 81, 81, true, false, true);
        iB2 = new Image("/sprite2.png", 81, 81, true, false, true);
        iB3 = new Image("/sprite3.png", 81, 81, true, false, true);
        iB4 = new Image("/sprite4.png", 81, 81, true, false, true);
        iB5 = new Image("/sprite5.png", 81, 81, true, false, true);
        iB6 = new Image("/sprite6.png", 81, 81, true, false, true);
        iB7 = new Image("/sprite7.png", 81, 81, true, false, true);
        iB8 = new Image("/sprite8.png", 81, 81, true, false, true);
        iP0 = new Image("/prop0.png", 72, 32, true, false, true);
        iP1 = new Image("/prop1.png", 496, 92, true, false, true);
    }

    private void createGameActors() {
        iBagel = new Bagel(this, "M150 0 L75 500 L225 200 Z", 0, 0, iB0, iB1, iB2, iB3, iB4, iB5, iB6, iB7, iB8);
        iPR0 = new Prop("M150 0 L75 200 L225 200 Z", 0, 148, iP0);
        iPR1 = new Prop("M150 0 L75 200 L225 200 Z", 0, -150, iP1);
        iPH0 = new PropH("M150 0 L75 200 L225 200 Z", 72, 148, iP0);
        iPV0 = new PropV("M150 0 L75 200 L225 200 Z", 0, 116, iP0);
        iPV1 = new PropV("M150 0 L75 200 L225 200 Z", 0, -58, iP1);
        iPB0 = new PropB("M150 0 L75 200 L225 200 Z", 72, 116, iP0);
    }

    private void addGameActorNodes() {
        root.getChildren().add(iPR0.spriteFrame);
        root.getChildren().add(iPR1.spriteFrame);
        root.getChildren().add(iPH0.spriteFrame);
        root.getChildren().add(iPV0.spriteFrame);
        root.getChildren().add(iPV1.spriteFrame);
        root.getChildren().add(iPB0.spriteFrame);
        root.getChildren().add(iBagel.spriteFrame);
    }

    private void createCastingDirection() {
        castDirector = new CastingDirector();
        castDirector.addCurrentCast(iBagel);
        castDirector.addCurrentCast(iPR0);
        castDirector.addCurrentCast(iPR1);
        castDirector.addCurrentCast(iPH0);
        castDirector.addCurrentCast(iPV0);
        castDirector.addCurrentCast(iPV1);
        castDirector.addCurrentCast(iPB0);
    }
}
