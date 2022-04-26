import textui.SysTextUI;
import textui.TextUI;

public class Match {

    //Fields
    private String date;
    private  String time;
    TextUI textUI = new SysTextUI();
    private String result = "TBC";
    private Team team1;
    private Team team2;


    //Constructor used when creating a new tournament
    public Match(String date, String time) {
        this.date = date;
        this.time = time;
        createTeams();
    }

    //Constructor used when creating the semis and the final
    public Match(Team team1, Team team2,String date, String time){
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
    }

    //Constructor used when loading a tournament
    public Match(Team team1, Team team2,String date, String time, String result){
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
        this.result = result;
    }

    //Creates two instances of the class Team
    public void createTeams() {
        System.out.println("Enter team 1 name then enter number of players: ");
        team1 = new Team(textUI.get(), textUI.getInteger(2, 5));
        System.out.println("Enter team 2 name then enter number of players: ");
        team2 = new Team(textUI.get(), textUI.getInteger(2, 5));

    }

    //Prints out the teams, the date, the time, and the result
    @Override
    public String toString(){
        return "" +  team1.getTeamName() + ", versus, " +  team2.getTeamName() + ", " + date + ", " + time + ", " + result;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public String getResult() {
        return result;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
