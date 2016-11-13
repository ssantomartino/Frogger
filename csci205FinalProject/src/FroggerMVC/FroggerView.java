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
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    private Rectangle water;

    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(700);
        root.setPrefHeight(700);

        this.water = new Rectangle(root.getPrefWidth(),
                                   root.getPrefHeight() / 2 - 75, Color.WHITE);
        //this.water.setX(root.getPrefWidth() / 2);
        this.water.setX(0);
        this.water.setY(root.getPrefHeight() - 675);
        root.getChildren().add(this.water);

        Rectangle water1 = new Rectangle(root.getPrefWidth(),
                                         root.getPrefHeight() / 7 - 75,
                                         Color.BLUE);
        water1.setX(0);
        water1.setY(root.getPrefHeight() - 450);
        root.getChildren().add(water1);
        Rectangle water2 = new Rectangle(root.getPrefWidth(),
                                         root.getPrefHeight() / 7 - 75,
                                         Color.BLUE);
        water2.setX(0);
        water2.setY(root.getPrefHeight() - 400);
        root.getChildren().add(water2);

        Rectangle water3 = new Rectangle(root.getPrefWidth(),
                                         root.getPrefHeight() / 7 - 75,
                                         Color.BLUE);
        water3.setX(0);
        water3.setY(root.getPrefHeight() - 500);
        root.getChildren().add(water3);

        Rectangle water4 = new Rectangle(root.getPrefWidth(),
                                         root.getPrefHeight() / 7 - 75,
                                         Color.BLUE);
        water4.setX(0);
        water4.setY(root.getPrefHeight() - 550);
        root.getChildren().add(water4);

        Rectangle water5 = new Rectangle(root.getPrefWidth(),
                                         root.getPrefHeight() / 7 - 75,
                                         Color.BLUE);
        water5.setX(0);
        water5.setY(root.getPrefHeight() - 600);
        root.getChildren().add(water5);

        this.theFrog = new Frog("basicFrog.png", root.getPrefWidth() / 2,
                                root.getPrefHeight() - theFrog.getHeight());
        System.out.println(theFrog.getHeight() + "," + theFrog.getWidth());

        root.getChildren().add(theFrog);
        addPaths();

        this.theLives = new ArrayList<>();
        addLives();
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
        for (WaterObjectPath path : this.theRiver) {
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

    public Rectangle getWater() {
        return water;
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
