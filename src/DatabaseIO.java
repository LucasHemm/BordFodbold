import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseIO implements IFileIO {


    @Override
    public void saveGameData(ArrayList<Match> data) {

    }

    @Override
    public String[] loadTeamData() {
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "*****";
        String[] teamData = new String[8];
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sp3.teams ORDER BY id");

            ResultSet result = statement.executeQuery();

            int counter = 0;
            while (result.next()) {

                String teamName = result.getString("name");
                String numOfPlayers = result.getString("numberOfPlayers");
                String points = result.getString("points");
                String goalDiff = result.getString("goalDiff");

                String teamBuild = teamName + ", " + numOfPlayers + ", " + points + ", " + goalDiff;

                teamData[counter] = teamBuild;

                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamData;
    }

    @Override
    public ArrayList<String> loadPlayerData() {
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "*****";
        ArrayList<String> playerData = new ArrayList<>();
        try {
                connection = DriverManager.getConnection(JdbcUrl, username, password);
                PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM sp3.players ORDER BY teamid");

                ResultSet result1 = statement1.executeQuery();
                String s1 = "";
                String s2 = "";
                String s3 = "";
                String s4 = "";
                String s5 = "";
                String s6 = "";
                String s7 = "";
                String s8 = "";
                while (result1.next()) {

                    String playerName = result1.getString("name");
                    int teamid = result1.getInt("teamid");

                   switch(teamid){

                       case 1:

                           s1 += playerName + ", ";
                           break;
                       case 2:
                           s2 += playerName + ", ";

                           break;
                       case 3:
                           s3 += playerName + ", ";

                           break;

                       case 4:
                           s4 += playerName + ", ";

                           break;
                       case 5:
                           s5 += playerName + ", ";

                           break;
                       case 6:
                           s6 += playerName + ", ";

                           break;
                       case 7:
                           s7 += playerName + ", ";

                           break;
                       case 8:
                           s8 += playerName + ", ";
                           break;
                   }
                    playerData.add(s1);
                    playerData.add(s2);
                    playerData.add(s3);
                    playerData.add(s4);
                    playerData.add(s5);
                    playerData.add(s6);
                    playerData.add(s7);
                    playerData.add(s8);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerData;
    }

    @Override
    public ArrayList<String> loadGameData(){
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "*****";
        ArrayList<String> gameData = new ArrayList<>();



        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM sp3.matches ORDER BY id");
            PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM sp3.teams ORDER BY id");

            ResultSet result1 = statement1.executeQuery();
            ResultSet result2 = statement2.executeQuery();


            String s1 = "";
            String s2 = "";
            String s3 = "";
            String s4 = "";
            String s5 = "";
            String s6 = "";
            String s7 = "";
            while (result1.next()) {
                int id = result1.getInt("id");


                switch(id){

                    case 1:
                        s1 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2"));
                        break;
                    case 2:
                       // s2 +=  + ", ";

                        break;
                    case 3:
                        s3 +=   ", ";

                        break;

                    case 4:
                        s4 +=   ", ";

                        break;
                    case 5:
                        s5 +=   ", ";

                        break;
                    case 6:
                        s6 +=   ", ";

                        break;
                    case 7:
                        s7 +=   ", ";

                        break;

                }
                gameData.add(s1);
                gameData.add(s2);
                gameData.add(s3);
                gameData.add(s4);
                gameData.add(s5);
                gameData.add(s6);
                gameData.add(s7);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameData;

    }

    private String getNameFromID(int index){
        Connection connection = null;
        String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "*****";
        String s = "";

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT name FROM sp3.teams where id = ? ORDER BY id");


            statement1.setInt(1,index);
            ResultSet result1 = statement1.executeQuery();

            while(result1.next()){
                s = result1.getString("name");
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

        return s;
    }



    @Override
    public void saveTeamData(ArrayList<Team> data) {

    }

    @Override
    public void savePlayerData(ArrayList<Team> teams) {

    }



    @Override
    public void clear() {

    }

    @Override
    public void fill() {

    }
}
