import java.util.*;
import java.io.*;
@SuppressWarnings("serial")
public class Admin extends User implements AdminInterface, Serializable{

	public  Admin(){
		setUsername("Admin");
		setPassword("Admin01");
	}
	public void createCourse(Scanner scanner,ArrayList<Course> arraylist){
		System.out.println("Please enter a name");
		String n = scanner.nextLine();
		System.out.println("Please enter an id");
		String i = scanner.nextLine();
		System.out.println("Please enter a maximum capacity");
		int m = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter the instructor name");
		String in = scanner.nextLine();
		System.out.println("Please enter the section number");
		int sN = Integer.parseInt(scanner.nextLine());
		System.out.println("Please enter the location");
		String l = scanner.nextLine();
		Course a = new Course(n,i,m, 0, in, sN, l);
		arraylist.add(a);
	} 
	//delete
	public void deleteCourse(Scanner scanner, ArrayList<Course> arraylist) {
		System.out.print("Please enter the course id: ");
		String inputiddelete = scanner.nextLine();
		System.out.print("Please enter the course section: ");
		int inputsectiondelete = Integer.parseInt(scanner.nextLine());
		for(int i = 0; i < arraylist.size(); i++) {
			if (inputiddelete.toUpperCase().equals(arraylist.get(i).getId().toUpperCase()) && inputsectiondelete == arraylist.get(i).getSectionNumber()) {
				arraylist.remove(i);
				System.out.println("Course deleted.");
				return;
			}
		}
		System.out.println("No such course exists.");
		
	}
	//edit
	public void editCourse(Scanner scanner,ArrayList<Course> arraylist) {
		System.out.print("Please enter the course id: ");
		String inputid = scanner.nextLine();
		System.out.print("Please enter the section of the course: ");
		int index = 0;
		int inputsection = Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < arraylist.size(); i++) {
			if (inputid.toUpperCase().equals(arraylist.get(i).getId()) && inputsection == arraylist.get(i).getSectionNumber()) {
				index = i;
		}
		if (inputid.toUpperCase().equals(arraylist.get(index).getId()) && inputsection == arraylist.get(index).getSectionNumber()) {
			System.out.print("What would you like to edit? \n1. Maxmimum capacity \n2. Current Number of Students");
			int choice = Integer.parseInt(scanner.nextLine());
			switch(choice) {
			case 1:
				boolean negative = true;
				while (negative) {
					System.out.print("Current max capacity is " + arraylist.get(i).getMax() + " Please enter the new maximum capacity: ");
					int newMax = Integer.parseInt(scanner.nextLine());
					if (newMax < 0) {
						System.out.println("This is negative.Try again. Current max capacity is " +  arraylist.get(i).getMax());
						break;
					}
					else if (newMax == arraylist.get(i).getMax()) {
						System.out.println("This is the same as the current capacity. Try again. Current max capacity is " + arraylist.get(i).getMax());
						break;
					}
					else {
						arraylist.get(i).setMax(newMax);
						System.out.println("Success! the new max capacity is " + newMax);
						negative = false;
						break;
				
					}
				}
				break;
			case 2:
				System.out.println("Current number of students is " + arraylist.get(i).getCurr() + ". Please enter the new current number of students.");
				int newCurr = Integer.parseInt(scanner.nextLine());
				boolean negative1 = true;
				boolean overMax = true;
				while (negative1 || overMax) {
					if (newCurr < 0) {
						System.out.println("This is a negative number. Please try again. ");
						negative = true;
						overMax = false;
						break;
					}
					else if (newCurr > arraylist.get(i).getMax()) {
						System.out.println("This exceeds the maximum capacity. Please try again.");
						negative = false;
						overMax = true;
						break;
					}
					else {
						arraylist.get(i).setCurr(newCurr);
						System.out.println("Success! The current number of students is now " + arraylist.get(i).getCurr());
						negative = false;
						overMax = false;
						break;
					}
				}
				break;
			default:
				System.out.println("That's not valid.");
				break;
			}
		}
	}
	}
	
	public void displayCourse(Scanner scanner, ArrayList<Course> arraylist) {	
		System.out.print("Please enter the course id: ");
		String inputid2 = scanner.nextLine();
		for (int i = 0; i < arraylist.size(); i++) {
			if(inputid2.toUpperCase().equals(arraylist.get(i).getId())) {
				arraylist.get(i).printCourse();
			}
		}	
	}
	
	public void registerStudent(Scanner scanner, ArrayList<Student> arraylist) {
		System.out.println("What is the student's first name?");
		String first = scanner.nextLine();
		System.out.println("What is the student's last name?");
		String last = scanner.nextLine();
		System.out.println("Enter a username");
		String user = scanner.nextLine();
		System.out.println("Enter a password");
		String pass = scanner.nextLine();
		for (int i = 0; i < arraylist.size(); i++) {
			if (user.toUpperCase().equals(arraylist.get(i).getUsername().toUpperCase())) {
				System.out.println("This username is taken.");
				return;
			}
		}
		Student a = new Student(user,pass,first,last);
		arraylist.add(a);
		System.out.println("Student added!");
		
		
	}
	public void viewFull(ArrayList<Course> arraylist) {
		for (int i = 0; i < arraylist.size(); i++) {
			if (arraylist.get(i).getCurr() == arraylist.get(i).getMax()) {
				arraylist.get(i).printCourse();
			}
		}
	}
	public void writeFull(ArrayList<Course> arraylist) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("full_courses.txt"));
			writer.write("List of Full Courses: \n");
			for (int i = 0; i < arraylist.size(); i++) {
				if (arraylist.get(i).getCurr() == arraylist.get(i).getMax()) {
					writer.append(arraylist.get(i).getName() + " Section " + arraylist.get(i).getSectionNumber() + "\n");
				}
			}
		writer.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	public void viewStudentList(Scanner scanner, ArrayList<Course> arraylist) {
		System.out.println("Please enter the id of the course:");
		String inputidStudent = scanner.nextLine();
		System.out.println("Please enter the course section:");
		int inputsectionStudent = Integer.parseInt(scanner.nextLine());
		for(int i = 0; i < arraylist.size(); i++) {
			if (arraylist.get(i).getId().toUpperCase().equals(inputidStudent.toUpperCase()) && arraylist.get(i).getSectionNumber() == inputsectionStudent) {
				System.out.print("\nThe students in this class are: \n");
				for (int j = 0; j < arraylist.get(i).getNames().size(); j++) {
					System.out.println(arraylist.get(i).getNames());
				}
			}
		}
	}
	public void sort(ArrayList<Course> arraylist) {
		for (int i = 0; i < arraylist.size() - 1; i++) {
			if (arraylist.get(i).getCurr() > arraylist.get(i+1).getCurr()) {
				Course temp1 = arraylist.get(i);
				Course temp2 = arraylist.get(i+1);
				arraylist.set(i, temp2);
				arraylist.set(i+1, temp1);
			}
		}
		System.out.println("Sorted!");
	}
	public void printMenu() {
		System.out.println("Course Management");
		System.out.println("1. Create a Course");
		System.out.println("2. Delete a Course");
		System.out.println("3. Edit a Course");
		System.out.println("4. Display a Course");
		System.out.println("5. Register a Student");
		System.out.println("6. Exit");
		System.out.println("\nReports");
		System.out.println("7. View all Courses");
		System.out.println("8. View full Courses");
		System.out.println("9. Write full Courses to a file");
		System.out.println("10. View Students in a Course");
		System.out.println("11. View a Student's Courses");
		System.out.println("12. Sort Courses");
		System.out.println("13. Exit");
	}
}
