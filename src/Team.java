import textui.SysTextUI;
import textui.TextUI;

import java.util.ArrayList;

public class Team {
    //Fields
    private String teamName;
    private int numberOfPlayers;
    private int numberOfPoints = 0;
    private int goalDifference = 0;
    ArrayList<Player> teamPlayers = new ArrayList<>();
    TextUI textUI = new SysTextUI();

    //Constructor used when creating a new tournament
    public Team(String teamName, int numberOfPlayers){
        this.teamName = teamName;
        this.numberOfPlayers = numberOfPlayers;

        for(int i = 0; i < numberOfPlayers;i++){
            System.out.println("Write player name: ");
            Player p = new Player(textUI.get());
            teamPlayers.add(p);
        }
    }


    //Constructor used when loading a tournament
    public Team(String teamName, int numberOfPlayers, int numberOfPoints, int goalDifference){
        this.teamName = teamName;
        this.numberOfPlayers = numberOfPlayers;
        this.numberOfPoints = numberOfPoints;
        this.goalDifference = goalDifference;



    }
    //Creates an instance of the pPlayer class and adds it to the ArrayList teamPlayers
    public void createPlayer(String name){
        Player player = new Player(name);
        this.teamPlayers.add(player);

    }

    //Getter for number of players in a team
    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }

    //Gets the team name
    public String getTeamName(){
        return this.teamName = teamName;
    }

    //Allows us to add points to teams
    public void updateNumberOfPoints(int pointsToAdd){
        this.numberOfPoints += pointsToAdd;
    }

    //Gets the number of points a team has
    public int getNumberOfPoints(){
        return this.numberOfPoints;
    }

    //Can update the goal difference after a match
    public void updateGoalDifference(int goalDiffToAdd){
        this.goalDifference += goalDiffToAdd;
    }

    //Gets the goal difference for the instance of team
    public int getGoalDifference(){
        return this.goalDifference;
    }

    //Runs through the ArrayList teamPlayers and calls getName method for each instance of Player
    public String getTeamPlayerNames(){
        String names = "";
        for(Player c : teamPlayers){
            names += c.getName() + ", ";
        }
        return names;
    }

    public Player getTeamPlayers(int i){

        return teamPlayers.get(i);

    }


    //Prints out the team's name, points, and goal difference
    @Override
    public String toString(){
        return "" + this.teamName + ", " + this.getNumberOfPoints()+ ", " + this.getGoalDifference();
    }

}
