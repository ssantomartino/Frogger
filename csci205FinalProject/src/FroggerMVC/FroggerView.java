/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 2, 2016
* Time: 5:19:31 PM
*
* Project: csci205FinalProject
* Package:
* File: FroggerView
* Description:
*
* ****************************************
 */
package FroggerMVC;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 *
 * @author jeo008
 */
class FroggerView {
    private Pane root;
    private ArrayList<Circle> lights;

    public FroggerView() {
        root = new Pane();
    }

    public Pane getRootNode() {
        return root;
    }
}
