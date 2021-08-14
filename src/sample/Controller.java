package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class Controller {
    @FXML
    private GridPane gPane;
    @FXML
    Button[][] wordGrid = new Button[15][15];
    @FXML
    private ListView lst1;
    @FXML
    private ChoiceBox choice1;
    @FXML
    private ChoiceBox choice2;
    @FXML
    private Label lbl1;
    @FXML
    private Label lbl2;
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    private char[][] wordGridData = new char[15][15];
    private int prevRow;
    private int prevCol;
    private int count;
    private boolean noAnswer = true;
    private Timeline timeline;
    private Integer timeSeconds = 0;
    private boolean firstTIme = true;
    ArrayList<String> wordArray = new ArrayList<>();
    Words words = new Words();
    Play play = new Play();
    @FXML
    private void initialize() {
        getMode();
        getCategory();
    }
    @FXML
    private void getMode() {
        ObservableList<String> modeOption = FXCollections.observableArrayList("Easy", "Medium", "Hard");
        choice2.setItems(modeOption);
    }
    @FXML
    private void getCategory() {
        ObservableList<String> categoryOption = FXCollections.observableArrayList("Animals", "Food", "Countries", "Instruments", "Sports");
        choice1.setItems(categoryOption);
    }
    @FXML
    private void handlebtn1(ActionEvent t) {
        if (choice1.getSelectionModel().isEmpty() || choice2.getSelectionModel().isEmpty()) { //ensures the game will not run unless all necessary info has been inputted
            lbl2.setText("Please Choose Difficulty and Category");
        }
        else {
            lbl2.setText("");
            choice1.setDisable(true);
            choice2.setDisable(true);
            btn2.setDisable(false);
            gPane.setDisable(false);
            ArrayList<String> animals = new ArrayList<>(Arrays.asList("tiger", "zebra", "giraffe", "eagle", "horse", "elephant", "frog", "lion", "shark", "donkey", "bear", "dolphin"));
            ArrayList<String> food = new ArrayList<>(Arrays.asList("pizza", "burger", "salad", "sandwich", "cookie", "pasta", "sushi", "steak", "donut", "apple", "taco", "nachos"));
            ArrayList<String> countries = new ArrayList<>(Arrays.asList("america", "china", "france", "egypt", "brazil", "japan", "russia", "korea", "india", "mexico", "canada", "chile"));
            ArrayList<String> instruments = new ArrayList<>(Arrays.asList("flute", "clarinet", "tuba", "violin", "drums", "trumpet", "cello", "piano", "viola", "saxophone", "trombone", "oboe"));
            ArrayList<String> sports = new ArrayList<>(Arrays.asList("soccer", "football", "tennis", "basketball", "hockey", "lacrosse", "baseball", "golf", "track", "rugby", "swimming"));
            int wordSize;
            wordArray.clear();
            if (timeline != null) { //timer code from https://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/
                timeline.stop();
            }
            if (choice2.getSelectionModel().getSelectedItem().equals("Easy")) { //amount of time to solve word search depends on difficulty
                wordSize = 3;
                timeSeconds = 200;
            }
            else if (choice2.getSelectionModel().getSelectedItem().equals("Medium")) {
                wordSize = 4;
                timeSeconds = 120;
            }
            else {
                wordSize = 5;
                timeSeconds = 60;
            }
            lbl1.setText("Time Left: " + timeSeconds.toString());
            timeline = new Timeline(new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    timeSeconds -= 1;
                    lbl1.setText("Time Left: " + timeSeconds.toString());
                    if (timeSeconds <= 0) {
                        timeline.stop();
                        lbl1.setText("Time Left: 0");
                        lbl2.setText("Time Ran Out. Click Play to Play Again");
                        newGame();
                    }
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.setAutoReverse(false);
            timeline.play();
            if (choice1.getSelectionModel().getSelectedItem().equals("Animals")) {
                for (int i = 0; i < wordSize; i ++) { //amount and type of words depend on difficulty and category
                    int randomWord = (int)(Math.random() * animals.size() - 1);
                    wordArray.add(animals.get(randomWord));
                    animals.remove(animals.get(randomWord));
                }
            }
            else if (choice1.getSelectionModel().getSelectedItem().equals("Food")) {
                for (int i = 0; i < wordSize; i ++) {
                    int randomWord = (int)(Math.random() * food.size() - 1);
                    wordArray.add(food.get(randomWord));
                    food.remove(food.get(randomWord));
                }
            }
            else if (choice1.getSelectionModel().getSelectedItem().equals("Countries")) {
                for (int i = 0; i < wordSize; i ++) {
                    int randomWord = (int)(Math.random() * countries.size() - 1);
                    wordArray.add(countries.get(randomWord));
                    countries.remove(countries.get(randomWord));
                }
            }
            else if (choice1.getSelectionModel().getSelectedItem().equals("Instruments")) {
                for (int i = 0; i < wordSize; i ++) {
                    int randomWord = (int)(Math.random() * instruments.size() - 1);
                    wordArray.add(instruments.get(randomWord));
                    instruments.remove(instruments.get(randomWord));
                }
            }
            else {
                for (int i = 0; i < wordSize; i ++) {
                    int randomWord = (int)(Math.random() * sports.size() - 1);
                    wordArray.add(sports.get(randomWord));
                    sports.remove(sports.get(randomWord));
                }
            }
            lst1.getItems().clear();
            noAnswer = true;
            for (int i = 0; i < wordGridData.length; i ++) { //sets all blank spots to be 0
                for (int j = 0; j < wordGridData.length; j ++) {
                    wordGridData[i][j] = '0';
                }
            }
            for (int i = 0; i < wordArray.size(); i ++) {
                lst1.getItems().add(wordArray.get(i));
            }
            wordGridData = words.createGrid(wordArray, wordGridData); //creates the grid with the words
            drawGrid(); //displays the grid
            gPane.setGridLinesVisible(true);
            gPane.setVisible(true);

            EventHandler e = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    count++; //determines whether user is selecting their first letter ot their last letter
                    if (count % 2 != 0) { //if user is selecting their first letter, it will save the spot they selected
                        prevRow = GridPane.getRowIndex(((Button) t.getSource()));
                        prevCol = GridPane.getColumnIndex(((Button) t.getSource()));
                        wordGrid[prevRow][prevCol].setTextFill(Color.RED);
                    }
                    else { //if user is selecting their second letter, it will get the spot they clicked, check if it is a valid spot, and determine whether they found a word
                        int curRow = GridPane.getRowIndex(((Button) t.getSource()));
                        int curCol = GridPane.getColumnIndex(((Button) t.getSource()));
                        wordGrid[curRow][curCol].setTextFill(Color.RED);
                        wordGrid[prevRow][prevCol].setTextFill(Color.BLACK);
                        wordGrid[curRow][curCol].setTextFill(Color.BLACK);
                        checkWord(play.checkUser(prevRow, prevCol, curRow, curCol, wordGridData), prevRow, prevCol, curRow, curCol);
                    }
                }
            };
            for(int i = 0; i < wordGrid.length; i++){
                for(int j = 0; j < wordGrid.length; j++){
                    wordGrid[i][j].setOnAction(e);
                }
            }
            btn1.setDisable(true);
        }
    }
    private void drawGrid() {
        for(int i = 0; i < wordGrid.length; i++){
            for(int j = 0; j < wordGrid.length;j++) {
                if (wordGridData[i][j] == '0' && noAnswer) { //will place random letters in the empty spots if user did not click for answer
                    Random r = new Random();
                    char c = (char) (r.nextInt(26) + 'a');
                    if (wordGrid[i][j] == null) { //will create new button if there are no previous buttons
                        wordGrid[i][j] = new Button(Character.toString(c));
                    } else {
                        wordGrid[i][j].setText(Character.toString(c)); //will not create new button, but only change the text on the buttons
                        wordGrid[i][j].setTextFill(Color.BLACK);
                    }
                }
                else if (wordGridData[i][j] == '0' && !noAnswer) {
                    if (wordGrid[i][j] == null) {
                        wordGrid[i][j] = new Button("");
                    } else {
                        wordGrid[i][j].setText("");
                    }
                    //will put a space in the empty spots so user can see the answers if they ask for it
                }
                else {
                    if (wordGrid[i][j] == null) {
                        wordGrid[i][j] = new Button(Character.toString(wordGridData[i][j]));
                    } else {
                        wordGrid[i][j].setText(Character.toString(wordGridData[i][j]));
                        wordGrid[i][j].setTextFill(Color.BLACK);
                    }
                    //will put the letter of the random words in the correct spots
                }
                wordGrid[i][j].setStyle("-fx-background-color: transparent;");
                wordGrid[i][j].setPrefSize(150, 150);
                if (firstTIme) {
                    gPane.add(wordGrid[i][j], j, i); //will add the created buttons to the gridpane only if there are no previous buttons
                }
            }
        }
        firstTIme = false;
    }
    @FXML
    private void handlebtn2() {
        noAnswer = false;
        drawGrid(); //displays answers
        newGame();
    }
    private void checkWord(String uWord, int rw1, int cl1, int rw2, int cl2) {
        for (int i = 0; i < wordArray.size(); i ++) {
            if (uWord.substring(0, uWord.length() - 1).equals(wordArray.get(i))) { //checks if the user correctly selected a word
                lst1.getItems().remove(wordArray.get(i));
                String compare = uWord.substring(uWord.length() - 1);
                if (compare.equals("0")) { //determines the direction of the word they selected
                    for (int j = rw1; j >= rw2; j --) {
                        wordGrid[j][cl1].setTextFill(Color.RED); //based on the direction, the words the user selected will be highlighted in red
                    }
                }
                else if (compare.equals("1")) {
                    for (int j = 0; j <= rw1 - rw2; j ++) {
                        wordGrid[rw1 - j][cl1 + j].setTextFill(Color.RED);
                    }
                }
                else if (compare.equals("2")) {
                    for (int j = cl1; j <= cl2; j ++) {
                        wordGrid[rw1][j].setTextFill(Color.RED);
                    }
                }
                else if (compare.equals("3")) {
                    for (int j = 0; j <= rw2 - rw1; j ++) {
                        wordGrid[rw1 + j][cl1 + j].setTextFill(Color.RED);
                    }
                }
                else if (compare.equals("4")) {
                    for (int j = rw1; j <= rw2; j ++) {
                        wordGrid[j][cl1].setTextFill(Color.RED);
                    }
                }
                else if (compare.equals("5")) {
                    for (int j = 0; j <= rw2 - rw1; j ++) {
                        wordGrid[rw1 + j][cl1 - j].setTextFill(Color.RED);
                    }
                }
                else if (compare.equals("6")) {
                    for (int j = cl1; j >= cl2; j --) {
                        wordGrid[rw1][j].setTextFill(Color.RED);
                    }
                }
                else {
                    for (int j = 0; j <= rw1 - rw2; j ++) {
                        wordGrid[rw1 - j][cl1 - j].setTextFill(Color.RED);
                    }
                }
            }
        }
        if (lst1.getItems().isEmpty()) {
            lbl2.setText("You Found All The Words. Click Play To Play Again");
            newGame();
        }
    }
    private void newGame() {
        lbl1.setText("");
        gPane.setDisable(true);
        choice1.setDisable(false);
        choice2.setDisable(false);
        btn1.setDisable(false);
        choice1.getSelectionModel().clearSelection();
        choice2.getSelectionModel().clearSelection();
        timeline.stop();
    }
}
