import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIo{

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

    public void savePlayerData(ArrayList<Team> teams){
        StringBuilder gameData = new StringBuilder("Team name, Player Names");
        for(Team t: teams){
                gameData.append(t.getTeamName() + ", " + t.getTeamPlayerNames()+"\n");
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
            //Name, Number of players, Points, Goal difference
            for(int j = 0; j < data.length; j++) {
                String[] tmpData = data[j].split(",");
                String teamName = tmpData[0];
                int numberOfPlayer = Integer.parseInt(tmpData[1]);
                int points = Integer.parseInt(tmpData[2]);
                int goalDifference = Integer.parseInt(tmpData[3]);
            }
            return data;



    }






}
