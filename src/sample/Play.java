package sample;

public class Play {
    //precondition: get the two spots the user chose
    //postcondition: determine the direction of the word and therefore the letters they have chosen
    public String checkUser(int r1, int c1, int r2, int c2, char[][] grid) {
        if (c1 == c2 && r2 < r1) {
            return matchWord(0, r1, c1, r2, c2, grid);
        }
        else if (r1 > r2 && c1 < c2 && ((r1 - r2)/(c1 - c2) == -1)) {
            return matchWord(1, r1, c1, r2, c2, grid);
        }
        else if (r1 == r2 && c1 < c2) {
            return matchWord(2, r1, c1, r2, c2, grid);
        }
        else if (r1 < r2 && c1 < c2 && ((r1 - r2)/(c1 - c2) == 1)) {
            return matchWord(3, r1, c1, r2, c2, grid);
        }
        else if (c1 == c2 && r1 < r2) {
            return matchWord(4, r1, c1, r2, c2, grid);
        }
        else if (r1 < r2 && c1 > c2 && ((r1 - r2)/(c1 - c2) == -1)) {
            return matchWord(5, r1, c1, r2, c2, grid);
        }
        else if (r1 == r2 && c1 > c2) {
            return matchWord(6, r1, c1, r2, c2, grid);
        }
        else if (r1 > r2 && c1 > c2 && ((r1 - r2)/(c1 - c2) == 1)) {
            return matchWord(7, r1, c1, r2, c2, grid);
        }
        else {
            return "Try Again";
        }
    }
    //precondition: get the direction the user selected and the two spots they have clicked
    //postcondition: determine the letters the user has chosen
    public String matchWord(int check, int rw1, int cl1, int rw2, int cl2, char[][] wordGrid) {
        StringBuilder userWord = new StringBuilder();
        if (check == 0) {
            for (int i = rw1; i >= rw2; i --) {
                userWord.append(wordGrid[i][cl1]); //based on the direction, the letters chosen will be added to a string along with the direction (specified by integers from 0 to 7)
            }
            return userWord.append(0).toString();
        }
        else if (check == 1) {
            for (int i = 0; i <= rw1 - rw2; i ++) {
                userWord.append(wordGrid[rw1 - i][cl1 + i]);
            }
            return userWord.append(1).toString();
        }
        else if (check == 2) {
            for (int i = cl1; i <= cl2; i ++) {
                userWord.append(wordGrid[rw1][i]);
            }
            return userWord.append(2).toString();
        }
        else if (check == 3) {
            for (int i = 0; i <= rw2 - rw1; i ++) {
                userWord.append(wordGrid[rw1 + i][cl1 + i]);
            }
            return userWord.append(3).toString();
        }
        else if (check == 4) {
            for (int i = rw1; i <= rw2; i ++) {
                userWord.append(wordGrid[i][cl1]);
            }
            return userWord.append(4).toString();
        }
        else if (check == 5) {
            for (int i = 0; i <= rw2 - rw1; i ++) {
                userWord.append(wordGrid[rw1 + i][cl1 - i]);
            }
            return userWord.append(5).toString();
        }
        else if (check == 6) {
            for (int i = cl1; i >= cl2; i --) {
                userWord.append(wordGrid[rw1][i]);
            }
            return userWord.append(6).toString();
        }
        else {
            for (int i = 0; i <= rw1 - rw2; i ++) {
                userWord.append(wordGrid[rw1 - i][cl1 - i]);
            }
            return userWord.append(7).toString();
        }
    }
}
