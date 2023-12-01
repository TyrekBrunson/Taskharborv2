
import java.util.ArrayList;
import java.util.Date;

public class Task {
    // Attributes
    private String taskName;
    private int taskPriority;
    private Date taskDueDate;
    private String taskNotes;
    private ArrayList<comments> taskComments;

    // Constructor
    public Task(String taskName, int taskPriority, Date taskDueDate, String taskNotes , ArrayList<comments> taskComments) {
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskDueDate = taskDueDate;
        this.taskNotes = taskNotes;
        this.taskComments = taskComments; 
    }

    // Getters and Setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(int taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskNotes() {
        return taskNotes;
    }

    public void setTaskNotes(String taskNotes) {
        this.taskNotes = taskNotes;
    }

     public ArrayList<comments> getComments(){
        return taskComments;
    }

    public void addComment(comments comment) {
        if (comment != null) {
            taskComments.add(comment);
        }
    }

    public boolean removeComment(comments comment) {
        return taskComments.remove(comment);
    }
    
 

    // Additional Methods
   

    @Override
    public String toString() {
        StringBuilder commentsString = new StringBuilder();
        for (comments comment : taskComments) {
            commentsString.append(comment.toString()).append("\n");
        }

        return "Task Name: " + taskName +
               "\nPriority: " + taskPriority +
               "\nDue Date: " + taskDueDate +
               "\nNotes: " + taskNotes +
               "\nComments:\n" + commentsString;
    }

    public Object getTaskCompletion() {
        return null;
    }

    public Object getTaskTags() {
        return null;
    }

    public void setTaskTags(ArrayList<String> newTaskTags) {
    }
}


