import java.util.ArrayList;


public class changeHistory {
    private ArrayList<String> taskHistory;

    // Constructor to initialize the taskHistory
    public changeHistory() {
        this.taskHistory = new ArrayList<>();
    }

    // Get an instance of ChangeHistory
    public static changeHistory getInstance() {
        return new changeHistory();
    }

    // View the task history
    public ArrayList<String> viewTaskHistory() {
        return taskHistory;
    }

    // Add a history entry to the task history
    public void addTaskHistory(String historyEntry) {
        taskHistory.add(historyEntry);
    }
}
