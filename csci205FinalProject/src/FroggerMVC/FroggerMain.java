/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 2, 2016
* Time: 4:41:44 PM
*
* Project: csci205FinalProject
* Package:
* File: FroggerMain
* Description: Main Class controls and runs the MVC classes
*
* ****************************************
 */
package FroggerMVC;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Main Class controls and runs the MVC classes
 *
 * @author jeo008, sms063, gmc017
 */
public class FroggerMain extends Application implements EventHandler<KeyEvent> {

    private FroggerView theView;
    private FroggerMainMenu theMainMenu;
    private FroggerModel theModel;
    private FroggerController theController;

    /*
    MediaPLayer Allows for sound capabilities
     */
    MediaPlayer mediaPlayer;

    /**
     * initializes the model and view for the application
     *
     * @throws Exception
     */
    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new FroggerModel();
        this.theView = new FroggerView(this.theModel);
    }

    /**
     * Source: Using an Animation Timer
     * http://blog.netopyr.com/2012/06/14/using-the-javafx-animationtimer/
     *
     * @param primaryStage the primary stage associated with the application
     * @throws InterruptedException
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        this.theController = new FroggerController(this.theView, this.theModel,
                                                   this.theMainMenu);

        Scene scene = new Scene(theView.getRootNode());
        scene.setOnKeyPressed(this);
        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        File file = new File("CrazyFrog.m4a");
        Media musicFile = new Media(file.toURI().toString());
        this.mediaPlayer = new MediaPlayer(musicFile);
        this.mediaPlayer.setAutoPlay(true);
        this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        this.theMainMenu = new FroggerMainMenu(this.theModel);

        /*
        continually checks the main menu screen to see if the start button has been clicked, once clicked,
        the cars and water objects are started according to the designated gammer level (beginner vs expert)
         */
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (FroggerMain.this.theMainMenu.getStartGame()) {
                    FroggerMain.this.theView.addPaths();
                    FroggerMain.this.theView.addFrog();
                    FroggerMain.this.theController.startTheCars(
                            FroggerMain.this.theMainMenu.getGameDelay());
                    FroggerMain.this.theController.startTheWaterObjects(
                            FroggerMain.this.theMainMenu.getGameDelay());
                    this.stop();
                }
            }

        }.start();

    }

    @Override
    /**
     * Handles the Key Pressed Event for arrow keys and moves the frog
     * accordingly - note: this event handler is in main because the action
     * listener is on the scene of the game
     */

    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.UP && theController.checkTopBound()) {
            this.theController.updateFrogUpPosition();
        } else if (event.getCode() == KeyCode.RIGHT && theController.checkRightBound()) {
            this.theController.updateFrogRightPosition();
        } else if (event.getCode() == KeyCode.LEFT && theController.checkLeftBound()) {
            this.theController.updateFrogLeftPosition();
        } else if (event.getCode() == KeyCode.DOWN && theController.checkBottomBound()) {
            this.theController.updateFrogDownPosition();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
