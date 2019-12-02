import java.io.*;
import java.util.*;
public interface AdminInterface {
	public void createCourse(Scanner scanner,ArrayList<Course> arraylist);
	public void deleteCourse(Scanner scanner,ArrayList<Course> arrayList);
	public void editCourse(Scanner scanner, ArrayList<Course> arraylist);
	public void registerStudent(Scanner scanner, ArrayList<Student> arraylist);
	public void viewFull(ArrayList<Course> arraylist);
	public void writeFull(ArrayList<Course> arraylist);
	public void viewStudentList(Scanner scanner, ArrayList<Course> arraylist);
	public void sort(ArrayList<Course> arraylist);
	public void printMenu();
}

	
	
