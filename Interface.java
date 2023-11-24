import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> tasks;
    private boolean[] completionStatus;
    

    public Interface() {

        completionStatus = new boolean[0];
        tasks = new ArrayList<>();
    }

    public void menu (){

        System.out.println("To-Do-List: ");
        boolean bool = true;
        
        while (bool) {

            System.out.println("\n1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Complete");
            System.out.println("5. Exit");
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    viewTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    markComplete();
                    break;
                case 5:
                    System.err.println("Bye :)");
                    System.exit(0);
                    break;
            
                default:
                System.out.println("Invalid Response");
                    break;
            }
        }
       
    }

    public void addTask (){
        while (true) {
            System.out.print("\nEnter a task to add or 'exit' to finish: ");
            String task = scan.nextLine();
    
            if (task.equalsIgnoreCase("exit")) {
                break;
            } else if (tasks.contains(task)) {
                System.out.println("That task is already on your To-Do-List.");
            } else {
                tasks.add(task);
                completionStatus = new boolean[tasks.size()];
                
                System.out.println("The task has been added: " + task);
            }
        }
        tasks.remove(0); // removes the extra task item added at the beginning
    }
    
    public void removeTask (){
        viewTasks();
        while (!tasks.isEmpty()) {
            System.out.print("\nEnter the task number to delete or select 0 to finish: ");
            String input = scan.nextLine();
    
            if (input.equals("0")) {
                break;
            }
    
            try {
                int remove = Integer.parseInt(input);
                remove--;
    
                if (remove >= 0 && remove < tasks.size()) {
                    tasks.remove(remove);
                    System.out.println("Task " + (remove + 1) + " has been deleted.");
                } else {
                    System.out.println("Invalid task number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid task number or '0' to finish.");
            }
    
            viewTasks(); // Updates the view after removing a task
        }
        
    }

    public void viewTasks(){
        String notCompleteBox = "☐ ";
        String completeSymbol = "☑ ";
        if (tasks.isEmpty()) {
            System.out.println("\nThere are no tasks in your To-Do-List!");
        }else{
            System.out.println("---------------------------");
            System.out.println("\nYour Current To-Do-List: \n");
            int i = 1;
            
            for (int j = 0; j < tasks.size(); j++) {
                String task = tasks.get(j);
                String status = completionStatus[j] ? completeSymbol : notCompleteBox;
                System.out.println(i + ": " + status + task);
                i++;
            }

            System.out.println("---------------------------");
        }
        
    }

    public void markComplete(){
        viewTasks();
        while (true) {
            System.out.print("Enter the task number to mark complete or select 0 to finish: \n");
        
            int taskNo = scan.nextInt();
            scan.nextLine();
            System.out.println("");
            taskNo = taskNo - 1;

           
            if (tasks.isEmpty()) {
                System.out.println("There are no items in yout To-Do-List to mark complete.");
                break;
            }

            if (taskNo == -1) {
                break;
            }
    
            if (taskNo >= 0 && taskNo < tasks.size() && !completionStatus[taskNo]) {
                completionStatus[taskNo] = true;
                System.out.println("Task " + (taskNo + 1) + " has been marked as complete.");
                viewTasks();
            } else if (taskNo >= 0 && taskNo < tasks.size() && completionStatus[taskNo]) {
                System.out.println("Task " + (taskNo + 1) + " is already marked as complete.");
            } else {
                System.out.println("Invalid task number.");
            }
    
        }
    }
       
}