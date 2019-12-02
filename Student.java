import java.io.*;
import java.util.*;
public class Student extends User implements Serializable{
	static final long serialVersionUID = 2;
	private ArrayList<Course> isRegistered;

	Student(String user, String pass, String first, String last){
		setUsername(user);
		setPassword(pass);
		setFirstName(first.toUpperCase());
		setLastName(last.toUpperCase());
		this.isRegistered = new ArrayList<Course>();

	}
	public void viewAll(ArrayList<Course> arraylist) {
		for (int i = 0; i < arraylist.size(); i++) {
			System.out.println(arraylist.get(i).getName());
			System.out.println(arraylist.get(i).getId());
			System.out.println(arraylist.get(i).getMax());
			System.out.println(arraylist.get(i).getCurr());
			System.out.println(arraylist.get(i).getInstructor());
			System.out.println(arraylist.get(i).getSectionNumber());
			System.out.println(arraylist.get(i).getLocation() + "\n");
		}
	}
	public void viewNotFull(ArrayList<Course> arraylist){
		for (int i = 0; i < arraylist.size(); i++) {
			if (arraylist.get(i).getCurr() != arraylist.get(i).getMax()) {
				System.out.println(arraylist.get(i).getName());
				System.out.println(arraylist.get(i).getId());
				System.out.println(arraylist.get(i).getMax());
				System.out.println(arraylist.get(i).getCurr());
				System.out.println(arraylist.get(i).getInstructor());
				System.out.println(arraylist.get(i).getSectionNumber());
				System.out.println(arraylist.get(i).getLocation() + "\n");

			}
		}
	}
	public void register(Scanner scanner, ArrayList<Course> arraylist) {
		System.out.println("Enter the course id of the course you would like to register in:");
		String id = scanner.nextLine();
		System.out.println("Enter the section number of the course you would like to register in:");
		int section = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < arraylist.size(); i++) {
			if (arraylist.get(i).getId().toUpperCase().equals(id.toUpperCase()) && arraylist.get(i).getSectionNumber() == section) {
				if(arraylist.get(i).getNames().contains(this.getFirstName() + " " + this.getLastName())) {
					System.out.println("You're already in this class!");
				}
				else if (arraylist.get(i).getCurr() == arraylist.get(i).getMax()) {
					System.out.println("This Course is full.");
					return;
				}
				else {
					System.out.println("Registration Sucessful! You just enrolled in " + arraylist.get(i).getName() + " Section " + arraylist.get(i).getSectionNumber());
					arraylist.get(i).getNames().add(this.getFirstName() + " " + this.getLastName());
					arraylist.get(i).setCurr(arraylist.get(i).getCurr() + 1);
					this.isRegistered.add(arraylist.get(i));
					return;
				}
			}
		}
		System.out.println("This course doesn't exist.");

	}
	public void printMenu() {
		System.out.println("Course Management");
		System.out.println("1. View all Courses");
		System.out.println("2. View all Courses that are not full");
		System.out.println("3. Register in a Course");
		System.out.println("4. Withdraw from a Course");
		System.out.println("5. View all registered Courses");
		System.out.println("6. Exit");
	}
	public void withdraw(Scanner scanner,ArrayList<Course> arraylist) {
		String id = "";
		int section = 0;
		System.out.println("What course would you like to withdraw from?");
		String withdrawname = scanner.nextLine();
		for (int i = 0; i < this.isRegistered.size(); i++) {
			if (withdrawname.toUpperCase().equals(this.isRegistered.get(i).getName().toUpperCase())) {
				id = this.isRegistered.get(i).getId();
				section = this.isRegistered.get(i).getSectionNumber();
				this.isRegistered.remove(i);
				System.out.println("removal successful!");
				for (Course course : arraylist) {
					if (id.equals(course.getId()) && section == course.getSectionNumber()) {
						course.getNames().remove((this.getFirstName() + " " + this.getLastName()));
						course.setCurr(course.getCurr() - 1);
					}
				}
			}
		}
	}
	public void viewStudentCourses() {
		for (int i = 0; i < this.isRegistered.size(); i++) {
			System.out.println(this.isRegistered.get(i).getName() + " Section " + this.isRegistered.get(i).getSectionNumber());
		}
	}
	public ArrayList<Course> getIsRegistered() {
		return isRegistered;
	}
	public void setIsRegistered(ArrayList<Course> isRegistered) {
		this.isRegistered = isRegistered;
	}
}

