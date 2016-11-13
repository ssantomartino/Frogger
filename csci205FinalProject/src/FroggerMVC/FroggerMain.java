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
* Description:
*
* ****************************************
 */
package FroggerMVC;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author sms063
 */
public class FroggerMain extends Application implements EventHandler<KeyEvent> {

    private FroggerView theView;
    private FroggerModel theModel;
    private FroggerController theController;

    //private AnimationTimer timer;
    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new FroggerModel();
        this.theView = new FroggerView(this.theModel);

    }

    @Override
    public void start(Stage primaryStage) {

        this.theController = new FroggerController(this.theView, this.theModel);

        Scene scene = new Scene(theView.getRootNode());
        scene.setOnKeyPressed(this);
        System.out.println("Started");
        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();

        //this.theController.moveCars();
        this.theController.startTheCars();
        this.theController.startTheWaterObjects();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }

        }.start();

    }

    private void update() {
        this.theController.checkCarCollisions();
        this.theController.checkFellInWater();
    }

    @Override
    /**
     * Handles the Key Pressed Event
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
