/* *****************************************
* CSCI205 - Software Engineering and Design
* Fall 2016
*
* Name: Sam Santomartino, Grace Cook, and John O'Brien
* Date: Nov 14, 2016
* Time: 6:30:02 PM
*
* Project: csci205FinalProject
* Package: FroggerObjects
* File: HighScores
* Description:
*
* ****************************************
 */
package FroggerObjects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gmc017
 */
public class HighScores {

    private ArrayList<Integer> scores;

    public HighScores() {
        this.scores = getScores();
        printScores();

    }

    public ArrayList<Integer> insertScore(int score) {
        for (int i = 0; i < this.scores.size(); i++) {
            if (score > this.scores.get(i)) {
                this.scores.add(i, score);
                this.scores.remove(this.scores.size() - 1);
                return this.scores;
            }
        }
        return this.scores;
    }

    public void saveScores() {

        BufferedWriter bufOutput = null;
        try {
            bufOutput = new BufferedWriter(new FileWriter("scores.txt"));
            for (int i = 0; i < this.scores.size(); i++) {
                if (i == this.scores.size() - 1) {
                    bufOutput.write(this.scores.get(i).toString());
                } else {
                    bufOutput.write(this.scores.get(i) + "\n");
                }

            }
            bufOutput.close();
        } catch (IOException ex) {
            System.out.println("IOException when writing to file.");
        }

    }

    public ArrayList<Integer> getScores() {
        BufferedReader bufInput;
        ArrayList<Integer> myScores = new ArrayList<>();
        String line;
        try {
            bufInput = new BufferedReader(new FileReader(
                    "scores.txt"));
            while ((line = bufInput.readLine()) != null) {
                myScores.add(Integer.parseInt(line));
            }
            bufInput.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException when reading from file");
            for (int x = 0; x < 10; x++) {
                myScores.add(0);
            }

        } catch (IOException e) {
            System.out.println("IOExeption when reading from file");
            for (int x = 0; x < 10; x++) {
                myScores.add(0);
            }
        }
        return myScores;
    }

    public void printScores() {
        for (int i = 0; i < this.scores.size(); i++) {
            System.out.println(this.scores.get(i));
        }
    }

    public static void main(String[] args) {
        HighScores myH = new HighScores();
        ArrayList<Integer> testing = myH.insertScore(137);
        myH.printScores();
        myH.saveScores();
    }

}
