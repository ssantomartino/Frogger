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

import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author jeo008
 */
class FroggerView {

    private FroggerModel theModel;
    private Pane root;
    private Rectangle theFrog;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(500);
        root.setPrefHeight(500);
        root.setPadding(new Insets(15, 15, 15, 15));

        this.theFrog = new Rectangle(20, 20, Color.GREEN);
        this.theFrog.setTranslateX(250);
        this.theFrog.setTranslateY(480);

        root.getChildren().add(theFrog);

    }

    public Pane getRootNode() {
        return root;
    }

    public int getRootXMin() {
        return (int) this.root.getBoundsInLocal().getMinX();
    }

    public int getRootXMax() {
        return (int) this.root.getBoundsInLocal().getMaxX();
    }

    public int getRootYMin() {
        return (int) this.root.getBoundsInLocal().getMinY();
    }

    public int getRootYMax() {
        return (int) this.root.getBoundsInLocal().getMaxY();
    }

    public Rectangle getTheFrog() {
        return theFrog;
    }

}
