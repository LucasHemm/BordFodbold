import java.util.Comparator;

public class SortByGoals implements Comparator<Team> {



    public int compare(Team a, Team b)
    {
        return a.getGoalDifference() - b.getGoalDifference();
    }
}
