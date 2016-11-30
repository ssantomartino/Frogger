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
* Description: View class of MVC deals with creating and adding actual GUI objects
*
* ****************************************
 */
package FroggerMVC;

import FroggerObjects.Car;
import FroggerObjects.CarPath;
import FroggerObjects.Frog;
import FroggerObjects.LilyPad;
import FroggerObjects.WaterObject;
import FroggerObjects.WaterObjectPath;
import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * View class of MVC deals with creating and adding actual GUI objects
 *
 * @author jeo008, sms063, gmc017
 */
class FroggerView {

    private FroggerModel theModel;
    /*
    Main Pane of the Game Board
     */
    private Pane root;
    /*
    Frog GUI
     */
    private Frog theFrog;
    /*
    Array of CarPaths tracks all the roads of the game
     */
    private CarPath[] theRoads;
    /*
    List of Images of Hearts representing the number of lives left in a play
     */
    private ArrayList<ImageView> theLives;
    /*
    Group housing the cars
     */
    private Group carGroup;
    /*
    constant maximum number of lives in a play
     */
    private static final int NUM_LIVES = 4;
    /*
    Array of WaterObjectPaths tracks all rivers of the game
     */
    private WaterObjectPath[] theRivers;
    /*
    Group housing the water objects
     */
    private Group waterGroup;
    /*
    keeps track of the accumulating score
     */
    private SimpleIntegerProperty score;
    /*
    Array of LilyPads
     */
    private LilyPad[] theLilyPads;
    /*
    Group houses the lily pads
     */
    private Group lilyPadGroup;

    /**
     * Constructor sets the stage for the game board
     *
     * @param theModel
     */
    public FroggerView(FroggerModel theModel) {
        this.theModel = theModel;
        root = new Pane();

        root.setPrefWidth(700);
        root.setPrefHeight(750);

        root.setBackground(new Background(new BackgroundImage(new Image(
                "Frogger_Background.png"),
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundRepeat.NO_REPEAT,
                                                              BackgroundPosition.CENTER,
                                                              BackgroundSize.DEFAULT)));

        this.theFrog = new Frog("basicFrog.png");
        System.out.println(theFrog.getHeight() + "," + theFrog.getWidth());

        addLilyPads();

        this.theLives = new ArrayList<>();
        addLives();

        score = new SimpleIntegerProperty(0);
        addScore();
    }

    /**
     * Adds the Frog object to the Scene
     */
    public void addFrog() {
        root.getChildren().add(theFrog);
    }

    /**
     * Adds the Car and Water Objects along with their transition paths to the
     * Scene
     */
    public void addPaths() {
        this.theRoads = theModel.generateCarPaths();
        this.carGroup = new Group();
        for (CarPath path : this.theRoads) {
            Car[] cars = path.getTheCars();

            for (Car car : cars) {
                carGroup.getChildren().add(car.getThePath());
                carGroup.getChildren().add(car);
            }
        }
        root.getChildren().add(carGroup);

        this.theRivers = theModel.generateWaterObjectPaths();
        this.waterGroup = new Group();
        for (WaterObjectPath path : this.theRivers) {

            WaterObject[] waterObjects = path.getTheObjects();

            for (WaterObject waterObject : waterObjects) {
                waterGroup.getChildren().add(waterObject.getThePath());
                waterGroup.getChildren().add(waterObject);
            }
        }
        root.getChildren().add(waterGroup);
    }

    /**
     * Adds the LilyPads to the Scene
     */
    private void addLilyPads() {
        this.theLilyPads = this.theModel.generateLilyPads();
        this.lilyPadGroup = new Group();
        for (LilyPad lilyPad : theLilyPads) {
            lilyPadGroup.getChildren().add(lilyPad);
        }
        root.getChildren().add(lilyPadGroup);
    }

    /**
     * Adds the images of the lives to the Scene
     */
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

    /**
     * Removes a life from the array of imageviews
     */
    public void removeNextLife() {
        if (this.theLives.size() > 0) {
            root.getChildren().remove(
                    this.theLives.get(this.theLives.size() - 1));
            this.theLives.remove(this.theLives.size() - 1);
        }

    }

    /**
     * Adds the Label that will display the score to the scene and binds it to
     * the score variable that gets updated
     */
    public void addScore() {
        Label scoreLabel = new Label("0");
        scoreLabel.setFont(new Font("Vernanda", 20));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.textProperty().bind(
                score.asString());
        root.getChildren().add(scoreLabel);
    }

    /**
     * updates the score by the designated amount
     *
     * @param score int update amount
     */
    public void updateScore(int score) {
        this.score.set(score);
    }

    /**
     * Returns the root node
     *
     * @return Pane root node
     */
    public Pane getRootNode() {
        return root;
    }

    /**
     * Returns the X Minimum bound of the Scene
     *
     * @return int 0
     */
    public int getRootXMin() {
        return 0;
    }

    /**
     * Returns the X Maximum bound of the Scene
     *
     * @return int scene width
     */
    public int getRootXMax() {
        return (int) this.root.getPrefWidth();
    }

    /**
     * Returns the Y Minimum bound of the Scene
     *
     * @return int
     */
    public int getRootYMin() {
        return (int) this.root.getBoundsInLocal().getMinY();
    }

    /**
     * Returns the Y Maximum bound of the Scene
     *
     * @return int
     */
    public int getRootYMax() {
        return (int) this.root.getBoundsInLocal().getMaxY();
    }

    /**
     * Returns the Frog Object
     *
     * @return Frog
     */
    public Frog getTheFrog() {
        return this.theFrog;
    }

    /**
     * Launches a new Frog Object into play
     */
    public void launchNewFrog() {
        this.theFrog = new Frog("basicFrog.png");
    }

    /**
     * Returns the CarPaths array
     *
     * @return CarPath[]
     */
    public CarPath[] getTheRoads() {
        return theRoads;
    }

    /**
     * Returns the LilyPads array
     *
     * @return LilyPad[]
     */
    public LilyPad[] getTheLilyPads() {
        return theLilyPads;
    }

    /**
     * Returns the WaterObjectPaths array
     *
     * @return WaterObjectPath[]
     */
    public WaterObjectPath[] getTheRivers() {
        return theRivers;
    }

    /**
     * Returns the number of lives allowed in a play
     *
     * @return int num lives
     */
    public static int getNUM_LIVES() {
        return NUM_LIVES;
    }

    /**
     * Launches the end game screen after the game has been won or all lives
     * have been lost
     *
     * @param finalScore the final score the player has ended with
     * @param scores the high scores array
     * @param winGame boolean true if the game has been won, false if the game
     * has been lost from losing all lives
     */
    public void endGame(int finalScore, ArrayList<Integer> scores,
                        boolean winGame) {
        root.getChildren().clear();
        // determines which label to display
        if (winGame) {
            Label winLbl = new Label("YOU WON!!!!!!");
            winLbl.setFont(new Font("Arial", 60));
            winLbl.setTranslateX((root.getPrefWidth() / 2) - 100);
            winLbl.setTranslateY(root.getPrefHeight() / 2 - 100);
            winLbl.setTextFill(Color.BLUEVIOLET);
            winLbl.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(winLbl);
            FadeTransition ft_Win = new FadeTransition(Duration.millis(1000),
                                                       winLbl);
            ft_Win.setFromValue(1.0);
            ft_Win.setToValue(0.0);
            ft_Win.setCycleCount(1000);
            ft_Win.setAutoReverse(true);
            ft_Win.play();
        } else {
            Label endLabel = new Label("Game Over!!!!!!");
            endLabel.setFont(new Font("Arial", 50));
            endLabel.setTranslateX((root.getPrefWidth() / 2) - 100);
            endLabel.setTranslateY(root.getPrefHeight() / 2 - 100);
            endLabel.setTextFill(Color.AQUA);
            endLabel.setTextAlignment(TextAlignment.CENTER);
            root.getChildren().add(endLabel);
            FadeTransition ft = new FadeTransition(Duration.millis(1000),
                                                   endLabel);
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
            ft.setCycleCount(1000);
            ft.setAutoReverse(true);
            ft.play();
        }

        // displays your score form the game
        Label yourScore = new Label("Your Score: " + finalScore);
        yourScore.setFont(new Font("Arial", 20));
        yourScore.setTextFill(Color.WHITE);
        yourScore.setTranslateX((root.getPrefWidth() / 2) - 50);
        yourScore.setTranslateY(root.getPrefHeight() / 2 - 26);
        root.getChildren().add(yourScore);

        // displays the high scores
        Label titleLabel = new Label("High Scores: ");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setTranslateX((root.getPrefWidth() / 2) - 40);
        titleLabel.setTranslateY(root.getPrefHeight() / 2);
        root.getChildren().add(titleLabel);
        boolean alreadyOnList = false;
        for (int i = 0; i < scores.size(); i++) {
            Label temp = new Label(
                    String.format("%2d: %d", i + 1, scores.get(i)));
            temp.setTextFill(Color.WHITE);
            temp.setTranslateX((root.getPrefWidth() / 2) - 30);
            temp.setTranslateY(root.getPrefHeight() / 2 + (35 + (15 * i)));
            if ((scores.get(i) == finalScore) && (!alreadyOnList)) {
                temp.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
                alreadyOnList = true;
            }
            root.getChildren().add(temp);
        }

    }

}
