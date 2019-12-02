import java.util.*;
import java.io.*;
public class User implements Serializable{
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	User(){
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public void viewStudentCourses(Scanner scanner, ArrayList<Student> arraylist) {
		System.out.println("What is the student's first name?");
		String firstname = scanner.nextLine().toUpperCase();
		System.out.println("What is the student's last name?");
		String lastname = scanner.nextLine().toUpperCase();
		for (int i = 0; i < arraylist.size(); i++)
			if (firstname.equals(arraylist.get(i).getFirstName()) && lastname.equals(arraylist.get(i).getLastName())) {
				for (int j = 0; j < arraylist.get(i).getIsRegistered().size(); j++) {
					System.out.println(arraylist.get(i).getIsRegistered().get(j).getName() + " Section " + arraylist.get(i).getIsRegistered().get(j).getSectionNumber());
				}
			}
			else {
				System.out.println(firstname + " " + lastname + " doesn't exist in the system.");
			}
		
	}
	public void viewAll(ArrayList<Course> arraylist) {
		for(int i = 0; i < arraylist.size(); i++) {
			arraylist.get(i).printCourse();
		}
	}
	public void exit() {
		System.out.println("Goodbye.");
	}
}
