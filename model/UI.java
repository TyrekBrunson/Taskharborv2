import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UI {
    private static final String WELCOME_MESSAGE = "Welcome to our Project Management System";
    private String[] mainMenuOptions = {"Create Account", "Login", "Projects", "Logout"};
    private String[] projectsMenuOptions = {"View Projects", "Add Column", "Remove Column", "Add Task", "Remove Task", "Edit Column", "Edit Task", "Back to Main Menu", "Logout"};
    private Scanner scanner;
    private UiFacade uiFacade;

    public UI() {
        scanner = new Scanner(System.in);
        uiFacade = new UiFacade();
    }

    public void run() {
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            displayMainMenu();

            int userCommand = getUserCommand(mainMenuOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            if (userCommand == mainMenuOptions.length - 1) {
                uiFacade.logout();
                System.out.println("You have successfully logged out");
                break;
            }

            switch (userCommand) {
                case 0:
                    createAccount();
                    break;
                case 1:
                    login();
                    break;
                case 2:
                    if (uiFacade.getCurrentUser() == null) {
                        System.out.println("You must be logged in to view projects");
                    } else {
                        projectsMenu();
                    }
                    break;
            }
        }

        System.out.println("Goodbye, and have a nice day");
    }

    private void displayMainMenu() {
        System.out.println("\n************ Main Menu *************");
        for (int i = 0; i < mainMenuOptions.length; i++) {
            System.out.println((i + 1) + ". " + mainMenuOptions[i]);
        }
        System.out.println("\n");
    }

    private void displayProjectsMenu() {
        System.out.println("\n************ Projects Menu *************");
        for (int i = 0; i < projectsMenuOptions.length; i++) {
            System.out.println((i + 1) + ". " + projectsMenuOptions[i]);
        }
        System.out.println("\n");
    }

    private int getUserCommand(int numCommands) {
        System.out.print("What would you like to do?: ");
        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if (command >= 0 && command <= numCommands - 1) return command;
        return -1;
    }

    private void createAccount() {
        String userName = getField("Username");
        String firstName = getField("First Name");
        String lastName = getField("Last Name");
        String password = getField("Password");

        if (uiFacade.createAccount(firstName, lastName, userName, password)) {
            System.out.println("You have successfully created an account");
        } else {
            System.out.println("Sorry, an account with that username already exists");
        }
    }

    private void login() {
        String userName = getField("Username");
        String password = getField("Password");

        if (uiFacade.login(userName, password)) {
            User currentUser = uiFacade.getCurrentUser();
            System.out.println("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName() + "!");
        } else {
            System.out.println("Sorry, invalid username or password");
        }
    }

    private String getField(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private void projectsMenu() {
        while (true) {
            displayProjectsMenu();
            int userCommand = getUserCommand(projectsMenuOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case 0:
                    viewProjects();
                    break;
                case 1:
                    addColumn();
                    break;
                case 2:
                    removeColumn();
                    break;
                case 3:
                    addTask();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    editColumn();
                    break;
                case 6:
                    editTask();
                    break;
                case 7:
                    return;
                case 8:
                    uiFacade.logout();
                    System.out.println("You have successfully logged out");
                    return;
            }
        }
    }

	private void viewProjects() {
		System.out.println("-----Projects-----");
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to view projects");
			return;
		}
	
		ProjectManager projectManager = currentUser.getProjectManager();
		if (projectManager == null) {
			System.out.println("Project Manager is not initialized for the current user.");
			return;
		}
	
		ArrayList<Project> projects = projectManager.getAllProjects();
	
		if (projects.isEmpty()) {
			System.out.println("No projects found");
			return;
		}
	
		for (int i = 0; i < projects.size(); i++) {
			Project project = projects.get(i);
			System.out.println((i + 1) + ". " + project.getProjectName());
	
			ArrayList<Column> columns = project.getColumns();
			if (columns.isEmpty()) {
				System.out.println("   No columns in this project.");
			} else {
				System.out.println("   Columns:");
	
				// Set the fixed width for columns
				int columnWidth = 30;
	
				// Print column names
				for (Column column : columns) {
					System.out.printf("\t%-" + columnWidth + "s", "- " + column.getColumnName());
				}
				System.out.println();
	
				// Print tasks under each column
				int maxTasks = 0;
				for (Column column : columns) {
					int numTasks = column.getColumnTaskList().size();
					maxTasks = Math.max(maxTasks, numTasks);
				}
									System.out.print("   Tasks:\n");
				for (int j = 0; j < maxTasks; j++) {
					for (Column column : columns) {
						ArrayList<Task> tasks = column.getColumnTaskList();
						if (j < tasks.size()) {
							Task task = tasks.get(j);
							String taskString = "- " + task.getTaskName() + ": " + task.getTaskNotes();
							System.out.printf("\t%-" + columnWidth + "s", taskString);
						} else {
							System.out.printf("\t%-" + columnWidth + "s", "");
						}
					}
					System.out.println();
				}
			}
		}
	}


	private void addColumn() {
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to add a column");
			return;
		}
	
		ProjectManager projectManager = currentUser.getProjectManager();
		int projectIndex = selectProject(projectManager);
	
		if (projectIndex == -1) {
			return;
		}
	
		if (projectIndex >= 0) {
			String columnName = getField("Column Name");
			Column column = new Column();  // Create a new Column instance
			column.setColumnName(columnName);
			Project project = projectManager.getAllProjects().get(projectIndex);
			project.addColumn(column);
			System.out.println("Column added to the project.");
		}
	}
	
	

    private void removeColumn() {
        User currentUser = uiFacade.getCurrentUser();
        if (currentUser == null) {
            System.out.println("You must be logged in to remove a column");
            return;
        }
    
        ProjectManager projectManager = currentUser.getProjectManager();
        int projectIndex = selectProject(projectManager);
    
        if (projectIndex != -1) {
            Project project = projectManager.getAllProjects().get(projectIndex);
            ArrayList<Column> columns = project.getColumns();
    
            if (columns.isEmpty()) {
                System.out.println("No columns found in the selected project.");
                return;
            }
    
            System.out.println("Select a column to remove:");
            for (int i = 0; i < columns.size(); i++) {
                System.out.println((i + 1) + ". " + columns.get(i).getColumnName());
            }
    
            int columnIndex = getUserCommand(columns.size());
            if (columnIndex != -1) {
                columns.remove(columnIndex);
                System.out.println("Column removed successfully.");
            }
        }
    }

	private void editColumn() {
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to edit a column");
			return;
		}
	
		ProjectManager projectManager = currentUser.getProjectManager();
		int projectIndex = selectProject(projectManager);
	
		if (projectIndex == -1) {
			return;
		}
	
		ArrayList<Column> columns = projectManager.getProject(projectIndex).getColumns();
	
		if (columns.isEmpty()) {
			System.out.println("No columns available to edit.");
			return;
		}
	
		int columnIndex = selectColumn(columns);
	
		if (columnIndex == -1) {
			return;
		}
	
		String newColumnName = getField("New Column Name");
		columns.get(columnIndex).setColumnName(newColumnName);
		System.out.println("Column edited successfully.");
	}
	
	private int selectProject(ProjectManager projectManager) {
		ArrayList<Project> projects = projectManager.getAllProjects();
		System.out.println("Select a project:");
	
		for (int i = 0; i < projects.size(); i++) {
			System.out.println((i + 1) + ". " + projects.get(i).getProjectName());
		}
	
		int userChoice = getUserCommand(projects.size());
	
		if (userChoice == -1) {
			System.out.println("Invalid selection");
			return -1;
		}
		
	
		return userChoice;
	}

	private void addTask() {
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to add a task");
			return;
		}
	
		ProjectManager projectManager = currentUser.getProjectManager();
		int projectIndex = selectProject(projectManager);
		if (projectIndex == -1) {
			return;
		}
	
		ArrayList<Column> columns = projectManager.getAllProjects().get(projectIndex).getColumns();
		if (columns.isEmpty()) {
			System.out.println("No columns available to add a task.");
			return;
		}
	
		int columnIndex = selectColumn(columns);
		if (columnIndex == -1) {
			return;
		}
	
		String taskName = getField("Task Name");
		String taskDescription = getField("Task Description");
		
		if (projectManager.addTask(projectIndex, columnIndex, taskName, taskDescription)) {
			System.out.println("Task added successfully.");
		} else {
			System.out.println("Failed to add the task.");
		}
	}
	
	private int selectColumn(ArrayList<Column> columns) {
		System.out.println("Select a column:");
		for (int i = 0; i < columns.size(); i++) {
			System.out.println((i + 1) + ". " + columns.get(i).getColumnName());
		}
	
		int userChoice = getUserCommand(columns.size());
	
		if (userChoice == -1) {
			System.out.println("Invalid selection");
			return -1;
		}
	
		return userChoice;
	}

	private void removeTask() {
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to remove a task");
			return;
		}
	
		int projectIndex = selectProject(currentUser.getProjectManager());
		if (projectIndex == -1) {
			return;
		}
	
		ArrayList<Column> columns = currentUser.getProjectManager().getAllProjects().get(projectIndex).getColumns();
		if (columns.isEmpty()) {
			System.out.println("No columns available to remove a task from.");
			return;
		}
	
		int columnIndex = selectColumn(columns);
		if (columnIndex == -1) {
			return;
		}
	
		ArrayList<Task> tasks = columns.get(columnIndex).getColumnTaskList();
		if (tasks.isEmpty()) {
			System.out.println("No tasks to remove.");
			return;
		}
	
		System.out.println("Select a task to remove:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ". " + tasks.get(i).getTaskName());
		}
	
		int taskIndex = getUserCommand(tasks.size());
		if (taskIndex == -1) {
			System.out.println("Invalid selection.");
			return;
		}
	
		if (uiFacade.removeTask(projectIndex, columnIndex, taskIndex)) {
			System.out.println("Task removed successfully.");
		} else {
			System.out.println("Failed to remove the task.");
		}
	}

	private void editTask() {
		User currentUser = uiFacade.getCurrentUser();
		if (currentUser == null) {
			System.out.println("You must be logged in to edit a task");
			return;
		}
	
		ProjectManager projectManager = currentUser.getProjectManager();
		int projectIndex = selectProject(projectManager);
		if (projectIndex == -1) {
			return;
		}
	
		ArrayList<Column> columns = projectManager.getProject(projectIndex).getColumns();
		if (columns.isEmpty()) {
			System.out.println("No columns available to edit a task.");
			return;
		}
	
		int columnIndex = selectColumn(columns);
		if (columnIndex == -1) {
			return;
		}
	
		ArrayList<Task> tasks = columns.get(columnIndex).getColumnTaskList();
		if (tasks.isEmpty()) {
			System.out.println("No tasks available to edit.");
			return;
		}
	
		System.out.println("Select a task to edit:");
		for (int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ". " + tasks.get(i).getTaskName());
		}
	
		int taskIndex = getUserCommand(tasks.size());
		if (taskIndex == -1) {
			System.out.println("Invalid selection.");
			return;
		}
	
		String newTaskName = getField("New Task Name");
		String newTaskDescription = getField("New Task Description");
		Task task = tasks.get(taskIndex);
		task.setTaskName(newTaskName);
		task.setTaskNotes(newTaskDescription);
		System.out.println("Task edited successfully.");
	}
	
	
	
	
	

    public static void main(String[] args) {
        UI projectUI = new UI();
        projectUI.run();
    }
}
