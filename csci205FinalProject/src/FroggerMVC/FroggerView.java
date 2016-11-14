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
import FroggerObjects.CarPath;
import FroggerObjects.Frog;
import FroggerObjects.WaterObject;
import FroggerObjects.WaterObjectPath;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleIntegerProperty;
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
    private CarPath[] theRoad;
    private ArrayList<ImageView> theLives;
    private Group carGroup;
    private static final int NUM_LIVES = 4;
    private WaterObjectPath[] theRiver;
    private Group waterGroup;
    private SimpleIntegerProperty score;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(700);
        root.setPrefHeight(700);

        this.theFrog = new Frog("basicFrog.png", root.getPrefWidth() / 2,
                                root.getPrefHeight() - theFrog.getHeight());
        System.out.println(theFrog.getHeight() + "," + theFrog.getWidth());

        root.getChildren().add(theFrog);
        addPaths();

        this.theLives = new ArrayList<>();
        addLives();

        score = new SimpleIntegerProperty(0);
        addScore();
    }

    private void addPaths() {
        this.theRoad = theModel.generateCarPaths();
        this.carGroup = new Group();
        for (CarPath path : this.theRoad) {
            Car[] cars = path.getTheCars();
            for (Car car : cars) {
                carGroup.getChildren().add(car.getThePath());
                carGroup.getChildren().add(car);
            }
        }
        root.getChildren().add(carGroup);

        this.theRiver = theModel.generateWaterObjectPaths();
        this.waterGroup = new Group();
        int riverY = 600;
        for (WaterObjectPath path : this.theRiver) {
            path.setRiverY(root.getPrefHeight() - riverY);
            root.getChildren().add(path.getTheRiver());
            riverY = riverY - 50;
            WaterObject[] waterObjects = path.getTheObjects();
            for (WaterObject waterObject : waterObjects) {
                waterGroup.getChildren().add(waterObject.getThePath());
                waterGroup.getChildren().add(waterObject);
            }
        }
        root.getChildren().add(waterGroup);
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

    public void addScore() {
        Label scoreLabel = new Label("0");
        scoreLabel.textProperty().bind(
                score.asString());
        root.getChildren().add(scoreLabel);
    }

    public void updateScore(int score) {
        this.score.set(score);
    }

    private void removeCars() {
        this.root.getChildren().remove(this.carGroup);
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

    public CarPath[] getTheRoad() {
        return theRoad;
    }

    public WaterObjectPath[] getTheRiver() {
        return theRiver;
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
