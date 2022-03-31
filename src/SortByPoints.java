import java.util.Comparator;

public class SortByPoints implements Comparator<Team> {



        // Method
        // Sorting in ascending order of roll number
        public int compare(Team a, Team b)
        {
            return a.getNumberOfPoints() - b.getNumberOfPoints();
    }
}
