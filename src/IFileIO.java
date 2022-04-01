import java.util.ArrayList;

public interface IFileIO {

    public void saveGameData(ArrayList<Match> data);
    public String[] loadTeamData();
    public void saveTeamData(ArrayList<Team> data );
    public void savePlayerData(ArrayList<Team> teams);
    ArrayList<String> loadPlayerData();
    ArrayList<String> loadGameData();
    void clear();
}
