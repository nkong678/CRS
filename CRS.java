
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
//please remove header line in csv file
public class CRS {
	public static void main(String[] args) {
		Admin admin = new Admin();
		int firstLaunch;
		int studentIndex = 0;
		boolean menu1 = true;
		boolean login = true;
		boolean isAdmin = false;
		boolean isStudent = false;
		Scanner input = new Scanner(System.in);
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Student> students = new ArrayList<Student>();
		System.out.println("Is this your first time launching the Course Registration System? \n1. Yes\n2. No\n3.Exit");
		while(login) {
		firstLaunch = Integer.parseInt(input.nextLine());
		while(menu1) {
			switch(firstLaunch) {
			case 1:
				//try reading the file
				try{
					FileReader file = new FileReader("MyUniversityCourses.csv");
					BufferedReader bReader = new BufferedReader(file);
					String c;
					while ((c = bReader.readLine()) != null) {
						//split the line into an a list using commas as the delimiter
						String[] lineArray = c.split(",");
						//set the parameters for the course by indexing throught the list
						String name = lineArray[0];
						String id = lineArray[1];
						int max = Integer.parseInt(lineArray[2]);
						int curr = Integer.parseInt(lineArray[3]);
						String instructor = lineArray[5];
						int section = Integer.parseInt(lineArray[6]);
						String location = lineArray[7];
						//create course
						Course d = new Course(name, id, max, curr, instructor, section, location);
						//add the course to the arraylist
						courses.add(d);
						//loop
					}
					bReader.close();
					
				}
				//catch the exceptions
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
				//exit menu
				menu1 =  false;
				break;
			case 2:
				//try deserializing arraylist of courses
				try{
					FileInputStream fis = new FileInputStream("Courses.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					courses = (ArrayList<Course>)ois.readObject();
					fis.close();
					ois.close();
				}
				//catch exceptions
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				//try deserializing arraylist of students
				try {
					FileInputStream fis = new FileInputStream("Students.ser");
					ObjectInputStream ois = new ObjectInputStream(fis);
					students = (ArrayList<Student>)ois.readObject();
					fis.close();
					ois.close();
				}
				//catch exceptions
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
				catch(ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				}
				//exit out of menu
				menu1 = false;
				break;
				//if input isn't 1 or 2 reprompt
			default:
				System.out.println("Please enter 1 or 2.");
				break;
			}
		}
			//prompt for username and password
			System.out.print("Username (Not case sensitive): ");
			String username = input.nextLine();
			System.out.print("Password (Case sensitive): ");
			String password = input.nextLine();
			//admin
			if (username.toUpperCase().equals("ADMIN") && password.equals("Admin001")) {
				isAdmin = true;
				isStudent = false;
				login = false;
			}
			//student
			if (!isAdmin) {
				studentIndex = 0;
				int counter = 0;
				for (int i = 0; i < students.size(); i++) {
					if (username.toUpperCase().equals(students.get(i).getUsername().toUpperCase())) {
						studentIndex = i;
						counter++;
					}
				}
				//if there is no username match
				if (counter == 0) {
					System.out.println("Incorrect Username or Password.");
				}
				//check if passwords of usernames line up
				else if (password.equals(students.get(studentIndex).getPassword())) {
					isAdmin = false;
					isStudent = true;
					login = false;
				}
				//if passwords don't match
				else{
					System.out.println("Incorrect Username or password.");
				}
			}
				//prints admin menu while admin is lgoged in
				while(isAdmin) {
					//calls printmenu method
					admin.printMenu();
					int choice = Integer.parseInt(input.nextLine());
					switch(choice) {
					case 1:
						//creates a course
						admin.createCourse(input,courses);
						break;
					case 2:
						//deletes a chosen course
						admin.deleteCourse(input,courses);
						break;
					case 3:
						//edits the maximum capacity or current number of students
						admin.editCourse(input,courses);
						break;
					case 4:
						//displays a given course
						admin.displayCourse(input,courses);
						break;
					case 5:
						//creates a new student
						admin.registerStudent(input, students);
						break;
					case 6:
						//serializes courses
						try {
							FileOutputStream fos = new FileOutputStream("Courses.ser");
							
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							
							oos.writeObject(courses);
							fos.close();
							oos.close();
						}catch(IOException ioe) {
							ioe.printStackTrace();
						}
						//serializes students
						try {
							FileOutputStream fos1 = new FileOutputStream("Students.ser");
							ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
							oos1.writeObject(students);
							fos1.close();
							oos1.close();
						}catch (IOException ioe) {
							ioe.printStackTrace();
						}
						//logs out
						admin.exit();
						isAdmin = false;
						break;
					case 7:
						//views all courses and their info
						admin.viewAll(courses);
						break;
					case 8:
						//views all the full classes and their info
						admin.viewFull(courses);
						break;
					case 9:
						//writes to a file the name and section of full courses
						admin.writeFull(courses);
						break;
					case 10:
						//views the list of students enrolled in a chosen course
						admin.viewStudentList(input,courses);
						break;
					case 11: 
						//views the list of courses of a student
						admin.viewStudentCourses(input, students);
						break;
					case 12:
						//sourts the course arraylist
						admin.sort(courses);
						break;
					case 13:
						//serializes courses
						try {
						FileOutputStream fos = new FileOutputStream("Courses.ser");
						
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						oos.writeObject(courses);
						fos.close();
						oos.close();
					}catch(IOException ioe) {
						ioe.printStackTrace();
					}
					try {
						//serializes students
						FileOutputStream fos1 = new FileOutputStream("Students.ser");
						ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
						oos1.writeObject(students);
						fos1.close();
						oos1.close();
					}catch (IOException ioe) {
						ioe.printStackTrace();
					}
					//logs out
						admin.exit();
						isAdmin = false;
						break;
					}
				}
				while (isStudent) {
					//prints the menu for students
				students.get(studentIndex).printMenu();
				int choiceStudent = Integer.parseInt(input.nextLine());
				switch(choiceStudent) {
				case 1:
					//views courses and info except for list of names
					students.get(studentIndex).viewAll(courses);
					break;
				case 2:
					//views courses and info except for list of names for courses that aren't full
					students.get(studentIndex).viewNotFull(courses);
					break;
				case 3:
					//registers this student into a chosen course if possible
					students.get(studentIndex).register(input, courses);
					break;
				case 4:
					//withdraws a student from one of their courses and adjusts relevant course information
					students.get(studentIndex).withdraw(input, courses);
					break;
				case 5:
					//views all courses this student is registered in
					students.get(studentIndex).viewStudentCourses();
					break;
				case 6: 
					try {
						//serializes courses
						FileOutputStream fos = new FileOutputStream("Courses.ser");
						
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						
						oos.writeObject(courses);
						fos.close();
						oos.close();
					}catch(IOException ioe) {
						ioe.printStackTrace();
					}
					try {
						//serializes students
						FileOutputStream fos1 = new FileOutputStream("Students.ser");
						ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
						oos1.writeObject(students);
						fos1.close();
						oos1.close();
					}catch (IOException ioe) {
						ioe.printStackTrace();
					}
					//logs out
					students.get(studentIndex).exit();
					isStudent = false;
					break;
				}
			
				}

		}
	}

}
