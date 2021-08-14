package sample;

import java.util.ArrayList;

public class Words {
    private int[] canPlace = new int[8]; //int array of all possible directions a word can be placed
    public char[][] createGrid(ArrayList<String> wArray, char[][] wGrid) {
        for (int i = 0; i < wArray.size(); i ++) {
            int row = (int) (Math.random() * 10);;
            int column = (int) (Math.random() * 10);
            while (wGrid[row][column] != '0') {
                row = (int) (Math.random() * 10);
                column = (int) (Math.random() * 10); //will continue to choose a random spot until it finds one that is valid
            }
            canPlace = checkPlace(row, column, canPlace, wArray.get(i), wGrid);
            int x = (int) (Math.random() * 8);
            while (canPlace[x] != 1) {
                x = (int) (Math.random() * 8);
            }
            if (x == 0) {
                wGrid = placeWord(0, row, column, wArray.get(i), wGrid);
            }
            if (x == 1) {
                wGrid = placeWord(1, row, column, wArray.get(i), wGrid);
            }
            if (x == 2) {
                wGrid = placeWord(2, row, column, wArray.get(i), wGrid);
            }
            if (x == 3) {
                wGrid = placeWord(3, row, column, wArray.get(i), wGrid);
            }
            if (x == 4) {
                wGrid = placeWord(4, row, column, wArray.get(i), wGrid);
            }
            if (x == 5) {
                wGrid = placeWord(5, row, column, wArray.get(i), wGrid);
            }
            if (x == 6) {
                wGrid = placeWord(6, row, column, wArray.get(i), wGrid);
            }
            if (x == 7) {
                wGrid = placeWord(7, row, column, wArray.get(i), wGrid);
            }
        }
        return wGrid;
    }
    //precondition: uses the random direction and spot selected and the word to be placed
    //postcondition: will place the word in a random and valid direction
    public char[][] placeWord(int y, int r, int c, String w1, char[][] grid) {
        grid[r][c] = w1.charAt(0);
        for (int i = 1; i < w1.length(); i ++) {
            if (y == 0) {
                grid[r - i][c] = w1.charAt(i);
            }
            else if (y == 1) {
                grid[r - i][c + i] = w1.charAt(i);
            }
            else if (y == 2) {
                grid[r][c + i] = w1.charAt(i);
            }
            else if (y == 3) {
                grid[r + i][c + i] = w1.charAt(i);
            }
            else if (y == 4) {
                grid[r + i][c] = w1.charAt(i);
            }
            else if (y == 5) {
                grid[r + i][c - i] = w1.charAt(i);
            }
            else if (y == 6) {
                grid[r][c - i] = w1.charAt(i);
            }
            else {
                grid[r - i][c - i] = w1.charAt(i);
            }
        }
        return grid;
    }
    //precondition: get the spot randomly selected and the word to place into the grid
    //postcondition: based on the spot chosen, it will determine all valid directions the word can be placed
    public int[] checkPlace(int a, int b, int[] cPlace, String w, char[][] gridData) {
        for (int i = 1; i < w.length(); i ++) {
            if (a - i > -1 && (gridData[a - i][b] == '0' || gridData[a - i][b] == w.charAt(i - 1))) { //determines if the spot is empty and inside the grid bounds
                cPlace[0] = 1; //if the word can be placed in a spot in this direction, there will be a 1 placed into the int array
            }
            else {
                cPlace[0] = 0; //if one spot in this direction does not work, the direction will not be valid, and there should be a 0 placed in the int array
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (a - i > -1 && b + i < gridData.length && (gridData[a - i][b + i] == '0' || gridData[a - i][b + i] == w.charAt(i))) {
                cPlace[1] = 1;
            }
            else {
                cPlace[1] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (b + i < gridData.length && (gridData[a][b + i] == '0' || gridData[a][b + i] == w.charAt(i))) {
                cPlace[2] = 1;
            }
            else {
                cPlace[2] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (a + i < gridData.length && b + i < gridData.length && (gridData[a + i][b + i] == '0' || gridData[a + i][b + i] == w.charAt(i))) {
                cPlace[3] = 1;
            }
            else {
                cPlace[3] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (a + i < gridData.length && (gridData[a + i][b] == '0' || gridData[a + i][b] == w.charAt(i))) {
                cPlace[4] = 1;
            }
            else {
                cPlace[4] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (a + i < gridData.length && b - i > -1 && (gridData[a + i][b - i] == '0' || gridData[a + i][b - i] == w.charAt(i))) {
                cPlace[5] = 1;
            }
            else {
                cPlace[5] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (b - i > -1 && (gridData[a][b - i] == '0' || gridData[a][b - i] == w.charAt(i))) {
                cPlace[6] = 1;
            }
            else {
                cPlace[6] = 0;
                break;
            }
        }
        for (int i = 1; i < w.length(); i ++) {
            if (a - i > -1 && b - i > -1 && (gridData[a - i][b - i] == '0' || gridData[a - i][b - i] == w.charAt(i))) {
                cPlace[7] = 1;
            }
            else {
                cPlace[7] = 0;
                break;
            }
        }
        return cPlace;
    }
}
