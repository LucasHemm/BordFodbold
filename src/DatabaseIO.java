import java.util.ArrayList;

public class DatabaseIO implements IFileIO {


    @Override
    public void saveGameData(ArrayList<Match> data) {

    }

    @Override
    public String[] loadTeamData() {
        return new String[0];
    }

    @Override
    public void saveTeamData(ArrayList<Team> data) {

    }

    @Override
    public void savePlayerData(ArrayList<Team> teams) {

    }

    @Override
    public ArrayList<String> loadPlayerData() {
        return null;
    }

    @Override
    public ArrayList<String> loadGameData() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public void fill() {

    }
}
