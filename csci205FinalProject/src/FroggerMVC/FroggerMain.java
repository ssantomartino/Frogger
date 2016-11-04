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

    @Override
    public void init() throws Exception {
        super.init();
        this.theModel = new FroggerModel();
        this.theView = new FroggerView(this.theModel);

    }

    @Override
    public void start(Stage primaryStage) {

        theController = new FroggerController(this.theView, this.theModel);

        Scene scene = new Scene(theView.getRootNode());
        scene.setOnKeyPressed(this);
        System.out.println("Started");
        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    @Override
    /**
     * Handles the Key Pressed Event
     */
    public void handle(KeyEvent event) {
        // TO DO for Grace -- in here call theController.updatefrogPosition or something like that
        if (event.getCode() == KeyCode.UP) {
            System.out.println("up arrow pressed");
            this.theView.getTheFrog().setTranslateY(
                    this.theView.getTheFrog().getTranslateY() - 13);
        } else if (event.getCode() == KeyCode.RIGHT) {
            System.out.println("right arrow pressed");
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() + 13);
        } else if (event.getCode() == KeyCode.LEFT) {
            this.theView.getTheFrog().setTranslateX(
                    this.theView.getTheFrog().getTranslateX() - 13);
            System.out.println("left arrow pressed");
        } else if (event.getCode() == KeyCode.DOWN) {
            System.out.println("down arrow pressed");
            this.theView.getTheFrog().setTranslateY(
                    this.theView.getTheFrog().getTranslateY() + 13);
        } else {
            System.out.println("not a valid key pressed");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
