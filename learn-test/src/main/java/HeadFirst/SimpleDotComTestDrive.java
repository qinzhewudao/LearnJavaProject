package HeadFirst;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * author sheyang
 * created at 2018/10/24
 */
class SimpleDotCom {

    private ArrayList<String> locationCells;

    private int numOfHits;

    public ArrayList<String> getLocationCells() {
        return locationCells;
    }

    public void setLocationCells(ArrayList<String> locationCells) {
        this.locationCells = locationCells;
    }

    public int getNumOfHits() {
        return numOfHits;
    }

    public void setNumOfHits(int numOfHits) {
        this.numOfHits = numOfHits;
    }

    String checkYourself(String userGuess) {

        String result = "miss";

        int index = locationCells.indexOf(userGuess);

        if (0 < index) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
            } else {
                result = "hit";
            }
        }
        System.out.println(result);
        return result;
    }
}

class GameHelper {
    String getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}

public class SimpleDotComTestDrive {
    public static void main(String[] args) {

        GameHelper helper = new GameHelper();
        SimpleDotCom dot = new SimpleDotCom();
        Integer randomNumber = (int) (Math.random() * 5);
        ArrayList<String> list = new ArrayList<>();
        list.add(randomNumber.toString());
        list.add(Integer.valueOf(randomNumber + 1).toString());
        list.add(Integer.valueOf(randomNumber + 2).toString());
        dot.setLocationCells(list);
        int numOfGuess = 0;
        Boolean isAlive = true;
        while (isAlive) {
            String guess = helper.getUserInput("enter a number:");
            String result = dot.checkYourself(guess);
            numOfGuess++;
            if (result.equals("kill")) {
                isAlive = false;
                System.out.println("Congratulations! You kill the dot com!The number of Guess is " + numOfGuess);
            }
        }
    }
}
