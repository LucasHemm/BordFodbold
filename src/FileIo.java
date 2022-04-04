import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIo implements IFileIO{


    //Saves the name, number of players, number of point, and goal difference for each team as a line in a .csv file
    public void saveTeamData(ArrayList<Team> data ){
        StringBuilder gameData = new StringBuilder("Name, Number of players, Points, Goal difference\n");
        for(Team t: data) {
            gameData.append(t.getTeamName()+", "+t.getNumberOfPlayers()+
                             ", " + t.getNumberOfPoints()+", " + t.getGoalDifference()
                             +"\n");
        }

        try {
            FileWriter output = new FileWriter("src/teamData.csv");
            output.write(gameData.toString());
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }


    //Saves the teams, the date, the time, and the result for each match as a line in a .csv file
    public void saveGameData(ArrayList<Match> data ) {
        StringBuilder gameData = new StringBuilder("Team-1, versus, Team-2, Date, Time, Result\n");
        for(Match m: data) {
            gameData.append(m+"\n");
        }

        try {
            FileWriter output = new FileWriter("src/gameData.csv");
            output.write(gameData.toString());
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
    }


    //Saves the names of the players for each team as a line in a .csv file
    public void savePlayerData(ArrayList<Team> teams){
        StringBuilder gameData = new StringBuilder("Player Names\n");
        for(Team t: teams){
                gameData.append(t.getTeamPlayerNames()+ "\n");
            }

            try {
                FileWriter output = new FileWriter("src/playerData.csv");
                output.write(gameData.toString());
                output.close();

            }
            catch (IOException e) {
                e.getStackTrace();
            }
    }

    //Loads the data for the matches
    public ArrayList<String> loadGameData(){

        File file = new File("src/gameData.csv");
        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            String header = scan.nextLine();//Ignorer header

            while (scan.hasNextLine()) {
                data.add(scan.nextLine());

            }
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;

    }

    //Loads the data for the teams
    public String[] loadTeamData(){

            File file = new File("src/teamData.csv");
            String[] data = new String[8];

            try {
                Scanner scan = new Scanner(file);
                int i = 0;
                String header = scan.nextLine();//Ignorer header

                while (scan.hasNextLine()) {
                    data[i] = scan.nextLine();
                    i++;
                }
            } catch (FileNotFoundException e) {
                data = null;
            }
            return data;
    }


    //Loads the players names
    public ArrayList<String> loadPlayerData(){

        File file = new File("src/playerData.csv");
        ArrayList<String> data = new ArrayList<>();

        try {
            Scanner scan = new Scanner(file);
            String header = scan.nextLine();//Ignorer header

            while (scan.hasNextLine()) {
                data.add(scan.nextLine());

            }
        } catch (FileNotFoundException e) {
            data = null;
        }
        return data;
    }


    //Clears all data in all the files
    public void clear(){
        String gameData = "";
        try {
            FileWriter output = new FileWriter("src/playerData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
        try {
            FileWriter output = new FileWriter("src/teamData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
        try {
            FileWriter output = new FileWriter("src/gameData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }


    }

    //Adds a filler header in all the files
    public void fill(){

        String gameData = "This is a filler";
        try {
            FileWriter output = new FileWriter("src/playerData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
        try {
            FileWriter output = new FileWriter("src/teamData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }
        try {
            FileWriter output = new FileWriter("src/gameData.csv");
            output.write(gameData);
            output.close();

        }
        catch (IOException e) {
            e.getStackTrace();
        }



    }






}
