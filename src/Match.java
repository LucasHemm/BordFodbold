import textui.SysTextUI;
import textui.TextUI;

public class Match {
    String date;
    String time;
    TextUI textUI = new SysTextUI();
    String result = "TBC";
    Team team1;
    Team team2;



    public Match(String date, String time) {
        this.date = date;
        this.time = time;
        createTeams();
    }
    public Match(Team team1, Team team2,String date, String time){
        this.date = date;
        this.time = time;
        this.team1 = team1;
        this.team2 = team2;
    }

    public void createTeams() {
        System.out.println("Enter team 1 name then enter number of players: ");
        team1 = new Team(textUI.get(), textUI.getInteger(2, 5));
        System.out.println("Enter team 2 name then enter number of players: ");
        team2 = new Team(textUI.get(), textUI.getInteger(2, 5));

    }

    public String getDateAndTime() {
        return date + time;
    }


    @Override
    public String toString(){

        return "" +  team1.getTeamName() + ", versus, " +  team2.getTeamName() + ", " + date + ", " + time + ", " + result;
    }

}
