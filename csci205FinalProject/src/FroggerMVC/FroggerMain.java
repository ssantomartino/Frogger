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
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sms063
 */
public class FroggerMain extends Application {

    private FroggerView theView;

    private FroggerController theController;

    @Override
    public void init() throws Exception {
        super.init();
        theView = new FroggerView();
    }

    @Override
    public void start(Stage primaryStage) {

        theController = new FroggerController(theView);

        Scene scene = new Scene(theView.getRootNode());

        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
