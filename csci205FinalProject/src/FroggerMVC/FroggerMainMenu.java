/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 20, 2016
* Time: 12:46:53 PM
*
* Project: csci205FinalProject
* Package: FroggerMVC
* File: FroggerMainMenu
* Description: Main Menu class displays start screen to gather game parameters
*
* ****************************************
 */
package FroggerMVC;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * Main Menu class displays start screen to gather game parameters
 *
 * @author jeo008, sms063, gmc017
 */
public class FroggerMainMenu {
    /*
    Main Pane of Main Menu
     */
    private StackPane root;

    /*
    Start Button
     */
    private Button start;
    /*
    Exit Button
     */
    private Button exit;
    /*
    Title Label
     */
    private Label title;
    /*
    VBox houses items on Stack Pane
     */
    private VBox theVBox;
    /*
    Toggle Group contains beginner and expert options
     */
    private ToggleGroup levelGroup;
    /*
    radio option for beginner level
     */
    private RadioButton beginner;
    /*
    radio option for expert level
     */
    private RadioButton expert;

    /*
    the amount of time required for game to load after start is selected
     */
    private static final Integer LOADTIME = 7;
    /*
    the timeline that counts down the time left in progress bar
     */
    private Timeline timeline;
    /*
    the seconds countdown of progress bar
     */
    private IntegerProperty timeSeconds = new SimpleIntegerProperty(
            LOADTIME * 100);

    /*
    SubStage of game to display before main game
     */
    private Stage subStage;

    private FroggerModel theModel;

    /*
    Flag returns true if the start button has been clicked, false otherwise
     */
    private boolean startGame;

    /**
     * Constructor initializes instance variables and adds features to substage
     *
     * @param theModel
     */
    public FroggerMainMenu(FroggerModel theModel) {
        this.theModel = theModel;

        this.subStage = new Stage();
        subStage.setTitle("Main Menu");

        this.root = new StackPane();
        root.setPrefWidth(700);
        root.setPrefHeight(750);
        Scene scene = new Scene(root);

        this.theVBox = new VBox();
        this.theVBox.setPadding(new Insets(10, 50, 10, 50));
        this.theVBox.setSpacing(50);
        this.theVBox.setAlignment(Pos.CENTER);

        this.startGame = false;

        addTitle();
        addLevelOptions();
        addButtons();
        addProgressBar();
        this.root.getChildren().add(this.theVBox);

        StackPane.setAlignment(this.theVBox, Pos.CENTER);

        subStage.setScene(scene);
        subStage.setResizable(false);
        subStage.show();

        // if main menu screen is closed, the whole program closes
        subStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent ev) {
                System.exit(0);
            }
        });
    }

    /**
     * Adds the Progress Bar to the SubStage that will fill up over the
     * designated time the start button is clicked
     */
    public void addProgressBar() {

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(
                timeSeconds.divide(LOADTIME * 100.0).subtract(1).multiply(-1));

        timeSeconds.set((LOADTIME + 1) * 100);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(LOADTIME + 1),
                                                 new KeyValue(timeSeconds, 0)));

        HBox timeContainer = new HBox(20);             // gap between components is 20
        timeContainer.setAlignment(Pos.CENTER);
        timeContainer.getChildren().addAll(progressBar);
        this.theVBox.getChildren().add(timeContainer);

        /*
        Action listener closes the substage when the progress bar is finished filling up,
        thus indicating the game is ready to begin
         */
        this.timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FroggerMainMenu.this.subStage.close();
            }
        });

    }

    /**
     * Adds the radio group options to choose Beginner or Expert level of game
     * play
     */
    public void addLevelOptions() {
        HBox levelContainer = new HBox();
        levelGroup = new ToggleGroup();
        levelContainer.setAlignment(Pos.CENTER);
        levelContainer.setSpacing(10);
        this.beginner = new RadioButton("Beginner");
        this.beginner.setFont(new Font("Arial", 30));
        this.beginner.setToggleGroup(levelGroup);
        this.beginner.setSelected(true);

        this.expert = new RadioButton("Expert");
        this.expert.setFont(new Font("Arial", 30));
        this.expert.setToggleGroup(levelGroup);
        //this.expert.setSelected(true);

        levelContainer.getChildren().addAll(this.beginner, this.expert);
        this.theVBox.getChildren().add(levelContainer);

    }

    /**
     * Adds the title Label to the top of the screen
     */
    public void addTitle() {
        this.title = new Label("Welcome to FROGGER!");
        title.setFont(new Font("Arial", 50));
        title.setTextFill(Color.BLUEVIOLET);

        this.theVBox.getChildren().add(this.title);
    }

    /**
     * Adds the start and exit buttons to the screen
     */
    public void addButtons() {

        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER);
        buttonContainer.setSpacing(10);

        this.start = new Button("Start");
        this.start.setFont(new Font("Arial", 20));
        this.start.setTextFill(Color.BLUEVIOLET);
        this.start.setPrefSize(100, 50);
        //this.start.setDisable(true);

        /*
        Action listener - handles the event when the start button is clicked,
        the desired game level is sent into the controller and
        the progress bar begins to load
         */
        this.start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FroggerMainMenu.this.start.setDisable(true);
                timeline.playFromStart();
                FroggerMainMenu.this.startGame = true;
                if (FroggerMainMenu.this.levelGroup.getSelectedToggle() == FroggerMainMenu.this.beginner) {
                    FroggerMainMenu.this.theModel.setGameMode(50);
                } else {
                    FroggerMainMenu.this.theModel.setGameMode(100);
                }
            }
        });

        this.exit = new Button("Exit");
        this.exit.setFont(new Font("Arial", 20));
        this.exit.setTextFill(Color.GREEN);
        this.exit.setPrefSize(100, 50);

        /*
        Action Listener - handles the event when the exit button is clicked
         */
        this.exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonContainer.getChildren().addAll(this.start, this.exit);
        this.theVBox.getChildren().add(buttonContainer);
    }

    /**
     * Returns the status of the start button flag returns true if the start
     * button has been clicked, false otherwise
     *
     * @return boolean
     */
    public boolean getStartGame() {
        return this.startGame;
    }

    /**
     * Returns the appropriate amount of time delay between objects depending on
     * the selected game level
     *
     * @return int representing a time delay between cars or water objects
     */
    public int getGameDelay() {
        if (this.levelGroup.getSelectedToggle() == this.beginner) {
            return 3000;
        } else {
            return 1000;
        }
    }

}
