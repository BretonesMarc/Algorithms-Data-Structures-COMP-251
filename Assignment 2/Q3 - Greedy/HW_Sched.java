import java.util.*;

public class HW_Sched {
    ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
    int m;
    int lastDeadline = 0;

    protected HW_Sched(int[] weights, int[] deadlines, int size) {
        for (int i=0; i<size; i++) {
            Assignment homework = new Assignment(i, weights[i], deadlines[i]);
            this.Assignments.add(homework);
            if (homework.deadline > lastDeadline) {
                lastDeadline = homework.deadline;
            }
        }
        m = size;
    }

    /**
     * @return Array where output[i] corresponds to the assignment
     * that will be done at time i.
     */
    public int[] SelectAssignments() {
        // create a copy of list to be able to modify it without overriding
        ArrayList<Assignment> sortedAssignments = new ArrayList<Assignment>(Assignments);

        int[] homeworkPlan = new int[lastDeadline];
        Arrays.fill(homeworkPlan, -1);

        // Sort the assignments by weight (decreasing order) -- override compare method in collections.sort
        Collections.sort(sortedAssignments , new Comparator<Assignment>() {
            @Override
            public int compare(Assignment a1, Assignment a2) {
                return Integer.compare(a2.weight, a1.weight);
            }
        });

        // Loop through sorted assignments and add them to homework plan
        for (Assignment assignment : sortedAssignments) {
            // Add corresponding homework to earliest available slot before its due date
            int slot = assignment.deadline - 1;
            while (slot >= 0 && homeworkPlan[slot] != -1) {
                slot--;
            }
            // if timeslot is not already taken, plan the current assignment at that slot
            if (slot >= 0) {
                homeworkPlan[slot] = assignment.number;
            }
        }
        return homeworkPlan;
    }
}
