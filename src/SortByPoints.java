import java.util.Comparator;

public class SortByPoints implements Comparator<Team> {



        public int compare(Team a, Team b)
        {
            return a.getNumberOfPoints() - b.getNumberOfPoints();
    }
}
