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
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 *
 * @author jeo008
 */
class FroggerView {

    private FroggerModel theModel;
    private Pane root;
    private Frog theFrog;
    private Car[][] theCars;
    private ArrayList<ImageView> theLives;
    private Group carGroup;
    private static final int NUM_LIVES = 4;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(700);
        root.setPrefHeight(700);

        this.theFrog = new Frog("basicFrog.png", root.getPrefWidth() / 2,
                                root.getPrefHeight() - theFrog.getHeight());

        root.getChildren().add(theFrog);
        this.theLives = new ArrayList<>();
        addCars();
        addLives();
    }

    private void addCars() {
        this.theCars = theModel.generateCars();
        this.carGroup = new Group();
        for (Car[] carList : theCars) {
            for (Car car : carList) {
                carGroup.getChildren().add(car.getThePath());
                carGroup.getChildren().add(car);
            }
        }
        root.getChildren().add(carGroup);
    }

    private void addLives() {
        for (int x = 0; x < NUM_LIVES; x++) {
            ImageView life = new ImageView("heart.png");
            life.setFitHeight(25);
            life.setFitWidth(25);
            life.setTranslateX(x * life.getFitWidth());
            life.setTranslateY(root.getPrefHeight() - life.getFitHeight());
            this.theLives.add(life);
            root.getChildren().add(life);

        }
    }

    public void removeNextLife() {
        if (this.theLives.size() > 0) {
            root.getChildren().remove(
                    this.theLives.get(this.theLives.size() - 1));
            this.theLives.remove(this.theLives.size() - 1);
        }

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

    public Car[][] getTheCars() {
        return this.theCars;
    }

    public static int getNUM_LIVES() {
        return NUM_LIVES;
    }

    public void endGame() {
        root.getChildren().clear();
        Label endLabel = new Label("Game Over!!!!!!");
        endLabel.setTranslateX((root.getPrefWidth() / 2) - 100);
        endLabel.setTranslateY(root.getPrefHeight() / 2);
        endLabel.setTextFill(Color.AQUA);
        endLabel.setTextAlignment(TextAlignment.CENTER);
        endLabel.setFont(Font.font(30));
        root.getChildren().add(endLabel);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), endLabel);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.setCycleCount(1000);
        ft.setAutoReverse(true);
        ft.play();

    }

}
