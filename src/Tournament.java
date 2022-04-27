import textui.SysTextUI;
import textui.TextUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class Tournament {

    //Fields
    TextUI textUI = new SysTextUI();
    public ArrayList<Match> matches = new ArrayList();
    IFileIO fileIo;
    ArrayList<Team> teams = new ArrayList<>();
    String[] teamNames = new String[8];

    //Method that runs the whole tournament
    public void run() {

        //We get user input to check if we want to create a tournament, load one, or delete it
        String[] datastoragechoice = {"Load and save from file", "Load and save from database"};
        int datachoice = textUI.select("Please select a way to load and save your tournament", datastoragechoice, "");
        String[] choices = {"New tournament", "Continue tournament", "Delete tournament"};
        int menuChoice = textUI.select("Choose an option to continue", choices, "");


        switch (datachoice) {

            case 0:
                fileIo = new FileIo();
                break;

            case 1:
                fileIo = new DatabaseIO();
                break;
        }

        switch (menuChoice) {

            //This case creates a tournament by creating the first 4 matches and the 8 teams - then it calls the menu
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
                //This case loads the data for the team, matches and players - then calls the menu
                createTeams(fileIo.loadTeamData(), fileIo.loadPlayerData());
                createMatches(fileIo.loadGameData());
                menu();
                break;

            case 2:
                //This case clears the files and ends the program
                fileIo.clear();
                System.out.println("Tournament has been deleted");
                break;
        }
    }


    //Helper method that shows a menu and calls for user input
    private void menu() {

        createTeamNames();
        //Decides whether the program keeps showing the menu or ends the program
        boolean check = true;

        //Loop in which we call for a user input to do the actions: register result, view ranking, etc.
        while (check) {
            String[] options = {"Register results", "Create semi-finals", "Create final", "View ranking",
                    "View match program", "View teams","Search team","Search player","Add player","Remove player", "Quit and save"};
            int optionChoice = textUI.select("Choose an option", options, "");

            switch (optionChoice) {

                //This case can register a result for an existing match
                case 0:
                    showMatchProgram();
                    System.out.println("Enter which match you would like to update");
                    Match match = matches.get(textUI.getInteger() - 1);
                    registerResult(match);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;

                //This case will create two matches and saves them
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

                //This case will create a match and save them
                case 2:
                    createFinals();
                    fileIo.saveGameData(matches);
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;

                //This case sorts the teams first by number of goals then number of points.
                //This means team with the most points is ranked the highest.
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

                //This case shows the planned matches
                case 4:
                    showMatchProgram();

                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;

                //This case prints out the team names and the names for the players in each team
                case 5:
                    System.out.println("List of teams in the tournament");
                    for (Team t : teams) {

                        System.out.println(t.getTeamName() + ", Player names: " + t.getTeamPlayerNames() + "\n");

                    }
                    System.out.println("Press enter to continue");
                    textUI.get();
                    textUI.clear();
                    break;

                //Allows the user to search for a team
                case 6:
                    searchTeam();
                    break;

                //Allows the user to search for an existing player
                case 7:
                    searchPlayer();
                    break;

                //Allows the user to add a player to a team
                case 8:
                    addPlayer();
                    break;

                //Allows the user to remove an existing player
                case 9:
                    removePlayer();
                    break;

                //This case clear the exiting files, as to not save on top of old data
                //Then it saves all the data in the files and ends the program
                case 10:
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

    //Helper method that creates a match and adds it in the ArrayList, matches
    //It adds the two teams in the ArrayList, teams
    private void createMatch(String date, String time) {
        Match match = new Match(date, time);
        teams.add(match.getTeam1());
        teams.add(match.getTeam2());

        matches.add(match);
    }

    //Adds the team names in the ArrayList, teams, to the String Array, teamNames
    private void createTeamNames() {
        for (int i = 0; i < 8; i++) {

            teamNames[i] = teams.get(i).getTeamName();
        }
    }


    //Shows all planned matches, with team names, date, time, and result
    private void showMatchProgram() {
        System.out.println("Teams, date, time, and results");
        int counter = 1;
        for (Match m : matches) {
            System.out.println("Match " + counter + ", " + m + "\n");
            counter++;
        }
    }

    //Creates a new match from existing teams after user input
    private void createFinals() {
        int semi1 = textUI.select("Choose team 1", teamNames, "");

        int semi2 = textUI.select("Choose team 2", teamNames, "");

        System.out.println("Enter date, then enter time");
        Match match = new Match(teams.get(semi1), teams.get(semi2), textUI.get(), textUI.get());
        matches.add(match);
    }

    //Gets user input to choose a match and then the admin can register the result for said match
    private void registerResult(Match match) {
        String[] choices = {match.getTeam1().getTeamName(), match.getTeam2().getTeamName()};
        int winner = textUI.select("Enter winning team", choices, "");
        switch (winner) {
            case 0:
                match.getTeam1().updateNumberOfPoints(2);
                match.setResult(match.getTeam1().getTeamName() + ": Won the match");
                break;
            case 1:
                match.getTeam2().updateNumberOfPoints(2);
                match.setResult(match.getTeam2().getTeamName() + ": Won the match");
                break;
        }
        System.out.println("Enter number of goals scored by " + match.getTeam1().getTeamName());
        int team1Goal = textUI.getInteger();
        System.out.println("Enter number of goals scored by " + match.getTeam2().getTeamName());
        int team2Goal = textUI.getInteger();
        match.getTeam1().updateGoalDifference(team1Goal);
        match.getTeam1().updateGoalDifference(-team2Goal);

        match.getTeam2().updateGoalDifference(team2Goal);
        match.getTeam2().updateGoalDifference(-team1Goal);
        System.out.println("Result has been registered");
    }

    //Used when reloading data to re-create the Team objects
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
            for (int j = 0; j < 8; j++) { // foreach line in playerData
                tmpPlayerData = playerData.get(i).split(", ");

            }

            for (int k = 0; k < tmpPlayerData.length; k++) {
                String playerName = tmpPlayerData[k];
                team.createPlayer(playerName);
            }

        }
    }

    //Used when continuing a tournament to recreate the matches already planned (and played)
    private void createMatches (ArrayList < String > gameData) {

        for (String s : gameData) {
            String[] tmpData = s.split(", ");
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

        //Helper method that allows to find teams from parts of their name
        private void searchTeam(){
        System.out.println("Please enter search for team");
        String s = textUI.get();
            textUI.clear();
        for(String n: teamNames){
            if(n.toLowerCase().contains(s.toLowerCase())){
                System.out.println("You searched for: " + n);
            }
        }
        System.out.println("Press Enter to continue");
        textUI.get();
        }

        //Helper method that allows to find players, and what team they play fo,r from parts of their name
        private void searchPlayer(){
        ArrayList<String> allPlayerNames = new ArrayList<>();

        for(Team  t : teams){
            String[] arrayOfNames = t.getTeamPlayerNames().split(", ");
            for(int i = 0; i < t.getNumberOfPlayers();i++) {
                String name = arrayOfNames[i];
                name += ", who plays for " + t.getTeamName();

                allPlayerNames.add(name);
            }
        }
            System.out.println("Please enter search for player");
            String s = textUI.get();
            textUI.clear();
            for(String n: allPlayerNames){
                if(n.toLowerCase().contains(s.toLowerCase())){
                    System.out.println("You searched for: " + n);
                }
            }
            System.out.println("Press Enter to continue");
            textUI.get();

        }

        //Creates a new player and adds him to an existing team
        private void addPlayer(){
            int teamChoice = textUI.select("Enter team you wish to add a player to",teamNames,"");
            int numOfPlayers = teams.get(teamChoice).getNumberOfPlayers();
            if(numOfPlayers >= 5){
                textUI.print("The team is full!");
                textUI.print("Press enter to continue");
                textUI.get();
             } else {
                textUI.print("Please Enter name of player you wish to add");
                String nameOfPlayer = textUI.get();
                Player player = new Player(nameOfPlayer);
                teams.get(teamChoice).teamPlayers.add(player);
                teams.get(teamChoice).setNumberOfPlayers(numOfPlayers+=1);
            }

        }

        //Removes a player from a team
        private void removePlayer(){
            int teamChoice = textUI.select("Enter team you wish to remove a player from",teamNames,"");
            int numOfPlayers = teams.get(teamChoice).getNumberOfPlayers();
            if(numOfPlayers <= 2){
                textUI.print("The teams needs at least 2 players");
                textUI.print("Press enter to continue");
                textUI.get();
            } else {
                ArrayList<String> playerNamesFromTeam = new ArrayList<>();

                for(Player p: teams.get(teamChoice).teamPlayers) {

                    playerNamesFromTeam.add(p.getName());
                }

                int playerToRemove = textUI.select("Choose player to remove from team",playerNamesFromTeam,"");


                teams.get(teamChoice).teamPlayers.remove(playerToRemove);
                teams.get(teamChoice).setNumberOfPlayers(numOfPlayers-=1);
            }
        }
}
