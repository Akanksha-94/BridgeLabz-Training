import java.util.Random;
public class EmployeeWageComputationProblem {




    static int WAGE_PER_HOUR = 20;
    static int FULL_DAY_HOURS = 8;
    static int PART_TIME_HOURS = 8; 

    public static void main(String[] args) {

        displayWelcomeMessage();

        int employeeType = getEmployeeType();

        int dailyWage = calculateDailyWage(employeeType);

        displayWage(employeeType, dailyWage);
    }

 
    static void displayWelcomeMessage() {
        System.out.println("Welcome to Employee Wage Computation Program");
    }


    static int getEmployeeType() {
        Random random = new Random();
        return random.nextInt(3);
    }


    static int calculateDailyWage(int employeeType) {

        int workHours = 0;

        switch (employeeType) {
            case 1: 
                workHours = FULL_DAY_HOURS;
                break;

            case 2: 
                workHours = PART_TIME_HOURS;
                break;

            default: 
                workHours = 0;
        }

        return workHours * WAGE_PER_HOUR;
    }


    static void displayWage(int employeeType, int wage) {

        switch (employeeType) {
            case 1:
                System.out.println("Employee is Full Time");
                break;

            case 2:
                System.out.println("Employee is Part Time");
                break;

            default:
                System.out.println("Employee is Absent");
        }

        System.out.println("Daily Wage = " + wage);
    }
}
  
  

