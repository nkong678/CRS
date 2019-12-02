import java.util.*;

public interface StudentInterface {
	public void viewNotFull(ArrayList<Course> arraylist);
	public void register(Scanner scanner, ArrayList<Course> arraylist);
	public void printMenu();
	public void withdraw(Scanner scanner,ArrayList<Course> arraylist);
	public void viewStudentCourses();
	public void viewAll(ArrayList<Course> arraylist);
	
}
