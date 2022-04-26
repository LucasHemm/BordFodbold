import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DatabaseIO implements IFileIO {

    private String JdbcUrl = "jdbc:mysql://localhost/world?" + "autoReconnect=true&useSSL=false";
    private String username = "root";
    private String password = "*****";
    private Connection connection = null;

    @Override
    public String[] loadTeamData() {
        String[] teamData = new String[8];
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM sp3.teams ORDER BY id");

            ResultSet result = statement.executeQuery();

            int counter = 0;
            while (result.next()) {

                String teamName = result.getString("name");
                String numOfPlayers = result.getString("numOfPlayers");
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
        ArrayList<String> playerData = new ArrayList<>();
        try {
                connection = DriverManager.getConnection(JdbcUrl, username, password);
                PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM sp3.players ORDER BY teamid");

                ResultSet result1 = statement1.executeQuery();
                String p1 = "";
                String p2 = "";
                String p3 = "";
                String p4 = "";
                String p5 = "";
                String p6 = "";
                String p7 = "";
                String p8 = "";
                while (result1.next()) {


                    int teamid = result1.getInt("teamid");
                     String playerName = "";
                   switch(teamid){
                       case 1:
                            playerName = result1.getString("name");
                           p1 += playerName + ", ";
                           break;
                       case 2:
                           playerName = result1.getString("name");
                           p2 += playerName + ", ";

                           break;
                       case 3:
                           playerName = result1.getString("name");

                           p3 += playerName + ", ";

                           break;

                       case 4:
                           playerName = result1.getString("name");

                           p4 += playerName + ", ";

                           break;
                       case 5:
                           playerName = result1.getString("name");

                           p5 += playerName + ", ";

                           break;
                       case 6:
                           playerName = result1.getString("name");

                           p6 += playerName + ", ";

                           break;
                       case 7:
                           playerName = result1.getString("name");

                           p7 += playerName + ", ";

                           break;
                       case 8:
                           playerName = result1.getString("name");

                           p8 += playerName + ", ";
                           break;
                   }

                }
            playerData.add(p1);
            playerData.add(p2);
            playerData.add(p3);
            playerData.add(p4);
            playerData.add(p5);
            playerData.add(p6);
            playerData.add(p7);
            playerData.add(p8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerData;
    }

    @Override
    public ArrayList<String> loadGameData(){
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
                        s1 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                        ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 2:
                        s2 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 3:
                        s3 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;

                    case 4:
                        s4 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 5:

                            s5 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                    ", " + result1.getString("time") + ", " + result1.getString("result");


                        break;
                    case 6:
                        s6 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;
                    case 7:
                        s7 += getNameFromID(result1.getInt("team1")) + ", " + "versus" + ", " + getNameFromID(result1.getInt("team2")) + ", " + result1.getString("date") +
                                ", " + result1.getString("time") + ", " + result1.getString("result");
                        break;

                }

            }
            gameData.add(s1);
            gameData.add(s2);
            gameData.add(s3);
            gameData.add(s4);

            if(s5.equals("") == false){
                System.out.println("DO NOT DO THIS");
                gameData.add(s5);

            }
            if(s6.equals("") == false){
                gameData.add(s6);

            }
            if(s7.equals("") == false){
                gameData.add(s7);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameData;

    }

    private String getNameFromID(int index){
        String s = "";
        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT name FROM sp3.teams where id = ? ORDER BY id");

            statement1.setInt(1,index);
            ResultSet result1 = statement1.executeQuery();

            while(result1.next()){


                s = result1.getString("name");
                if(s == null){
                    s= " ";
                }
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

        return s;
    }
    private int getIDFromName(String name){
        int ID = 0;

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("SELECT id FROM sp3.teams where name = ? ORDER BY id");

            statement1.setString(1,name);
            ResultSet result1 = statement1.executeQuery();

            while(result1.next()){
                ID = result1.getInt("id");
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

        return ID;
    }

    @Override
    public void saveTeamData(ArrayList<Team> data) {

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = null;

            for(Team t : data) {
                statement1 = connection.prepareStatement("INSERT INTO sp3.teams (name,numOfPlayers, points, goalDiff) VALUES(?,?,?,?)");


                statement1.setString(1,t.getTeamName());
                statement1.setInt(2,t.getNumberOfPlayers());
                statement1.setInt(3, t.getNumberOfPoints());
                statement1.setInt(4, t.getGoalDifference());

                int result1 = statement1.executeUpdate();
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

    }
    @Override
    public void saveGameData(ArrayList<Match> data) {

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = null;

            for(Match m : data) {
                statement1 = connection.prepareStatement("INSERT INTO sp3.matches (team1,team2, date, time, result) VALUES(?,?,?,?,?)");


                statement1.setInt(1, getIDFromName(m.getTeam1().getTeamName()));
                statement1.setInt(2, getIDFromName(m.getTeam2().getTeamName()));
                statement1.setString(3, m.getDate());
                statement1.setString(4, m.getTime());
                statement1.setString(5, m.getResult());


                int result1 = statement1.executeUpdate();
            }



        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void savePlayerData(ArrayList<Team> teams) {
        int ID = 0;

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("INSERT INTO sp3.players (name, teamid) VALUES(?,?)");
            int counter = 1;
            for(Team t : teams){

                for(int i=0; i<t.getNumberOfPlayers();i++)
                {
                    statement1.setString(1,t.getTeamPlayers(i).getName());
                    statement1.setInt(2,counter);
                    int result1 = statement1.executeUpdate();
                }
                counter++;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {

        try {
            connection = DriverManager.getConnection(JdbcUrl, username, password);
            PreparedStatement statement1 = connection.prepareStatement("set foreign_key_checks = 0;");
            PreparedStatement statement2 = connection.prepareStatement("TRUNCATE sp3.teams;");
            PreparedStatement statement3 = connection.prepareStatement("TRUNCATE sp3.players;");
            PreparedStatement statement4 = connection.prepareStatement("TRUNCATE sp3.matches;");
            PreparedStatement statement5 = connection.prepareStatement("set foreign_key_checks = 1;");

            int result1 = statement1.executeUpdate();
            int result2 = statement2.executeUpdate();
            int result3 = statement3.executeUpdate();
            int result4 = statement4.executeUpdate();
            int result5 = statement5.executeUpdate();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void fill() {

    }
}
