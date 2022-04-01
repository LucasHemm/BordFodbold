import textui.SysTextUI;
import textui.TextUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament {

    TextUI textUI = new SysTextUI();
    public ArrayList<Match> matches = new ArrayList();
    IFileIO fileIo = new FileIo();
    ArrayList<Team> teams = new ArrayList<>();
    //ArrayList<String> teamNames = new ArrayList<>();
    String [] teamNames = new String[8];


    public void run() {

        String[] choices = {"New tournament", "Continue tournament", "Delete tournament"};
        int menuChoice = textUI.select("Choose an option to continue", choices, "");
        switch (menuChoice) {
            case 0:
                int counter = 1;
                fileIo.clear();
                for (int i = 0; i < 4; i++) {
                    System.out.println("Enter date, then enter time of match number: " + counter);
                    createMatch(textUI.get(), textUI.get());
                    counter++;
                }
                menu();
                break;
            case 1:

                createTeams(fileIo.loadTeamData(), fileIo.loadPlayerData());
                createMatches(fileIo.loadGameData());
                menu();
                break;
            case 2:
                fileIo.clear();
                System.out.println("Tournament has been deleted");
                break;
        }
    }
    private void menu(){

        createTeamNames();
        boolean check = true;
        while (check) {
            String[] options = {"Register results", "Create semi-finals", "Create final", "View ranking",
                    "View match program", "View teams", "Quit and save"};
            int optionChoice = textUI.select("Choose an option", options, "");

            switch (optionChoice) {
                case 0:
                    showMatchProgram();
                    System.out.println("Enter which match you would like to update");
                    Match match = matches.get(textUI.getInteger() - 1);
                    registerResult(match);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 1:
                    int semiCounter = 1;
                    for (int i = 0; i < 2; i++) {
                        System.out.println("Enter date, then enter time of match number: " + semiCounter);
                        createFinals();
                    }
                    fileIo.saveGameData(matches);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 2:
                    createFinals();
                    fileIo.saveGameData(matches);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 3:
                    Collections.sort(teams, new SortByGoals());
                    Collections.sort(teams, new SortByPoints());
                    System.out.println("\nThis is the team ranking\n");
                    System.out.println("Team name, points, goal difference");
                    for (int i = 7; i >= 0; i--) {
                        System.out.println(teams.get(i));
                    }
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 4:
                    showMatchProgram();

                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 5:
                    System.out.println("List of teams in the tournament");
                    for (Team t : teams) {

                        System.out.println(t.getTeamName() + ", Player names: " + t.getTeamPlayerNames() + "\n");

                    }
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;
                case 6:
                    fileIo.clear();
                    fileIo.saveTeamData(teams);
                    fileIo.saveGameData(matches);
                    fileIo.savePlayerData(teams);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    check = false;
                    System.out.println("Goodbye");
                    break;
            }

        }

    }


    private void createMatch(String date, String time){
        Match match = new Match(date, time);
        teams.add(match.team1);
        teams.add(match.team2);

        matches.add(match);
    }

    private void createTeamNames() {
        for(int i = 0; i < 8;i++){

            teamNames[i] = teams.get(i).getTeamName();
        }
    }

    private void showMatchProgram(){
        System.out.println("Teams, date, time, and results");
        int counter = 1;
        for (Match m : matches) {
            System.out.println("Match " + counter + ", " +  m + "\n");
            counter++;
        }
    }

    private void createFinals() {
        int semi1 = textUI.select("Choose team 1", teamNames, "");

        int semi2 = textUI.select("Choose team 2", teamNames, "");

        System.out.println("Enter date, then enter time");
        Match match = new Match(teams.get(semi1), teams.get(semi2), textUI.get(), textUI.get());
        matches.add(match);
    }

    private void registerResult(Match match) {
        String[] choices = {match.team1.getTeamName(), match.team2.getTeamName()};
        int winner = textUI.select("Enter winning team", choices, "");
        switch (winner) {
            case 0:
                match.team1.updateNumberOfPoints(2);
                match.result = match.team1.getTeamName() + ": Won the match";
                break;
            case 1:
                match.team2.updateNumberOfPoints(2);
                match.result = match.team2.getTeamName() + ": Won the match";
                break;
        }
        System.out.println("Enter number of goals scored by " + match.team1.getTeamName());
        int team1Goal = textUI.getInteger();
        System.out.println("Enter number of goals scored by " + match.team2.getTeamName());
        int team2Goal = textUI.getInteger();
        match.team1.updateGoalDifference(team1Goal);
        match.team1.updateGoalDifference(-team2Goal);

        match.team2.updateGoalDifference(team2Goal);
        match.team2.updateGoalDifference(-team1Goal);
        System.out.println("Result has been registered");
    }

    private void createTeams(String[] teamData, ArrayList<String> playerData) {
        for (int i = 0; i < teamData.length; i++) { // foreach team
            String[] tmpData = teamData[i].split(", ");
            String teamName = tmpData[0];
            int numberOfPlayer = Integer.parseInt(tmpData[1]);
            int points = Integer.parseInt(tmpData[2]);
            int goalDifference = Integer.parseInt(tmpData[3]);
            Team team = new Team(teamName, numberOfPlayer, points, goalDifference);
            teams.add(team);

            String[] tmpPlayerData = null;
           // for (int j = 0; j < 8; j++) { // foreach line in playerData
                tmpPlayerData = playerData.get(i).split(", ");


            for (int k = 0; k < tmpPlayerData.length; k++) {
                String playerName = tmpPlayerData[k];
                team.createPlayer(playerName);
            }

        }
    }

    //Team-1, versus, Team-2, Date, Time, Result
    public void createMatches(ArrayList<String> gameData) {
        for (int i = 0; i < gameData.size(); i++) {
            String[] tmpData = gameData.get(i).split(", ");

            String teamName1 = tmpData[0];
            String teamName2 = tmpData[2];


            String date = tmpData[3];
            String time = tmpData[4];
            String result = tmpData[5];

            Team tmpTeam1 = null;
            Team tmpTeam2 = null;
            for (int j = 0; j < teams.size(); j++) {

                if (teamName1.equals(teams.get(j).getTeamName())) {
                    tmpTeam1 = teams.get(j);
                }
                if (teamName2.equals(teams.get(j).getTeamName())) {
                    tmpTeam2 = teams.get(j);
                }
            }
            Match match = new Match(tmpTeam1, tmpTeam2, date, time, result);
            matches.add(match);

        }


    }
}
