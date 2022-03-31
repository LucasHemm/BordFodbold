import textui.SysTextUI;
import textui.TextUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tournament {

    TextUI textUI = new SysTextUI();
    public ArrayList<Match> matches = new ArrayList();
    FileIo fileIo = new FileIo();
    ArrayList<Team> teams = new ArrayList<>();
    ArrayList<String> teamNames = new ArrayList<>();



    public void run() {

        String[] choices = {"New tournament", "Continue tournament", "Delete tournament"};
        int menuChoice = textUI.select("Choose an option to continue", choices, "FOOTER");
        switch (menuChoice) {
            case 0:
                int counter = 1;
               // fileIo.clear();**********************
                for(int i = 0; i < 4; i++){
                    System.out.println("Enter date, then enter time of match number: " + counter);
                    createMatch(textUI.get(), textUI.get());
                    counter++;
                }
                fileIo.saveTeamData(teams);
                fileIo.saveGameData(matches);
                //evt menuchoice++ ***************************
            case 1:
               // fileIo.loadTeams & fileIo.loadMatches *************
                boolean check = true;
                while(check){
                    String[] options = {"Register results", "Create semi-finals", "Create final", "View ranking",
                                        "View match program", "View teams", "Quit and save"};
                    int optionChoice = textUI.select("Choose an option", options, "OPTION FOOTER");

                    switch (optionChoice) {
                        case 0:
                            // evt smid view match program ind
                            System.out.println("Enter which match you would like to update");
                            Match match = matches.get(textUI.getInteger()-1);
                            registerResult(match);
                            System.out.println("Press enter to continue");
                            textUI.get();
                            textUI.clear();
                            break;
                        case 1:
                            int semiCounter = 1;
                            for(int i = 0; i < 2; i++){
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
                            for (int i = 7; i >= 0; i--) {
                                System.out.println(teams.get(i));
                            }
                            System.out.println("Press enter to continue");
                            textUI.get();
                            textUI.clear();
                            break;
                        case 4:
                            System.out.println("Teams, date, time, and results");
                            for(Match m: matches){
                                System.out.println(m + "\n");
                            }

                            System.out.println("Press enter to continue");
                            textUI.get();
                            textUI.clear();
                            break;
                        case 5:
                            System.out.println("List of teams in the tournament");
                            for(Team t: teams){
                                System.out.println(t.getTeamName()+"\n");
                            }
                            System.out.println("Press enter to continue");
                            textUI.get();
                            textUI.clear();
                            break;
                        case 6:
                            fileIo.saveTeamData(teams);
                            fileIo.saveGameData(matches);
                            System.out.println("Press enter to continue");
                            textUI.get();
                            textUI.clear();
                            check = false;
                            System.out.println("Goodbye");
                            break;
                    }
                    break;
                }

            case 2:
                // fileIO.clear();**********************
                System.out.println("Tournament has been deleted");
                break;
        }
    }

    private void createMatch(String date, String time){
        Match match = new Match(date,time);
        teams.add(match.team1);
        teams.add(match.team2);

        matches.add(match);
    }

    private void createFinals(){
        if(teamNames == null) {
            for (Team t : teams) {
                teamNames.add(t.getTeamName());
            }
        }
        int semi1 = textUI.select("Choose team 1", teamNames, "");

        int semi2 = textUI.select("Choose team 2", teamNames, "");

        System.out.println("Enter date, then enter time");
        Match match = new Match(teams.get(semi1), teams.get(semi2), textUI.get(), textUI.get());
        matches.add(match);
    }

    private void registerResult(Match match){
       String[] choices = {match.team1.getTeamName(), match.team2.getTeamName()};
       int winner = textUI.select("Enter winning team", choices, "");
        switch(winner) {
            case 0:
                match.team1.updateNumberOfPoints(2);
                match.result = match.team1.getTeamName() + ": Won the match";
                break;
            case 1:
                match.team2.updateNumberOfPoints(2);
                match.result = match.team2.getTeamName() + ": Won the match";
                break;
        }
        System.out.println("Enter number of goals scored by "+ match.team1.getTeamName());
        int team1Goal = textUI.getInteger();
        System.out.println("Enter number of goals scored by "+ match.team2.getTeamName());
        int team2Goal = textUI.getInteger();
        match.team1.updateGoalDifference(team1Goal);
        match.team1.updateGoalDifference(-team2Goal);

        match.team2.updateGoalDifference(team2Goal);
        match.team2.updateGoalDifference(-team1Goal);
        System.out.println("Result has been registered");
    }

}
