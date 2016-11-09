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

import FroggerObjects.Car;
import FroggerObjects.Frog;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

/**
 *
 * @author jeo008
 */
class FroggerView {

    private FroggerModel theModel;
    private Pane root;
    private Frog theFrog;
    private Car[] theCars;
    private Group carGroup;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(500);
        root.setPrefHeight(500);
        root.setPadding(new Insets(15, 15, 15, 15));

        this.theFrog = new Frog(20, 20, "basicFrog.png", 250, 475);

        root.getChildren().add(theFrog);
        addCars();
    }

    private void addCars() {
        this.theCars = theModel.generateCars();
        this.carGroup = new Group();
        for (Car car : theCars) {
            //root.getChildren().add(car);
            //root.getChildren().add(car.getThePath());
//            car.moveCar();
            carGroup.getChildren().add(car);
            carGroup.getChildren().add(car.getThePath());
        }
        root.getChildren().add(carGroup);
    }

    private void removeCars() {
        this.root.getChildren().remove(this.carGroup);
    }

    public void resetRoad() {
        this.removeCars();
        this.addCars();
    }

    public Pane getRootNode() {
        return root;
    }

    public int getRootXMin() {
        return 0;
    }

    public int getRootXMax() {
        return (int) this.root.getPrefWidth();
    }

    public int getRootYMin() {
        return (int) this.root.getBoundsInLocal().getMinY();
    }

    public int getRootYMax() {
        return (int) this.root.getBoundsInLocal().getMaxY();
    }

    public Frog getTheFrog() {
        return this.theFrog;
    }

    public Car[] getTheCars() {
        return this.theCars;
    }

}
