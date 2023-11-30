import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TaskManagement {
    private static TaskManagement taskManager;
    private static HashMap<Boolean, ArrayList<Task>> taskList;

    private TaskManagement() {
        taskList = new HashMap<>();
        taskList.put(false, new ArrayList<>()); // Initialize an empty list for tasks not completed
        taskList.put(true, new ArrayList<>());  // Initialize an empty list for completed tasks
    }

    public static TaskManagement getInstance() {
        if(taskManager == null){
            taskManager = new TaskManagement();
        }
        return taskManager;
    }
   
    public static boolean addTask(Task task) {
        ArrayList<Task> tasks = taskList.get(false);
        tasks.add(task);
        return true;
    }

    public boolean removeTask(String taskName) {
    Task task = getTask(taskName);
    if (task != null) {
        ArrayList<Task> tasks = taskList.get(false);
        boolean removed = tasks.remove(task);
        if (removed) {
            taskList.put(false, tasks);
        }
        return removed;
    }
    return false;
}

    public static Task getTask(String taskName) {
        ArrayList<Task> tasks = taskList.get(false);
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    

    public boolean hasTask(String taskName) {
        return getTask(taskName) != null;
    }

    public boolean editTaskPriority(String taskName, int taskPriority) {
        Task task = getTask(taskName);
        if (task != null) {
            task.setTaskPriority(taskPriority);
            return true;
        }
        return false;
    }

    public boolean editTask(String taskName, String newTaskName, int newTaskPriority, ArrayList<String> newTaskTags, Date newTaskDueDate, String newTaskNotes) {
        Task task = getTask(taskName);
        if (task != null) {
            task.setTaskName(newTaskName);
            task.setTaskPriority(newTaskPriority);
            task.setTaskTags(newTaskTags);
            task.setTaskDueDate(newTaskDueDate);
            task.setTaskNotes(newTaskNotes);
            return true;
        }
        return false;
    }

    public boolean saveTasks() {
        // Implement code to save the task data to a data source (e.g., a file or database)
         DataWriter.saveTasks();
         return true;
    }

    public boolean markTaskComplete(String taskName) {
        Task task = getTask(taskName);
        if (task != null) {
            taskList.get(false).remove(task);
            taskList.get(true).add(task);
            return true;
        }
        return false;
    }

    public boolean markTaskWorkingOn(String taskName) {
        Task task = getTask(taskName);
        if (task != null) {
            taskList.get(true).remove(task);
            taskList.get(false).add(task);
            return true;
        }
        return false;
    }

    public ArrayList<Task> getTaskList() {
        return null;
    }

}