import java.util.*;
public class Course implements java.io.Serializable{
	private String name;
	private String id;
	private int max;
	private int curr;
	private String instructor;
	private ArrayList<String> names;
	private int sectionNumber;
	private String location;
	static final long serialVersionUID = 1;
	Course(String n, String i, int m, int c, String in, int sN, String l){
		this.name = n;
		this.id = i;
		this.max = m;
		this.curr = c;
		this.instructor = in;
		this.sectionNumber = sN;
		this.location = l;
		this.names = new ArrayList<String>();
	}
	public void printCourse() {
		System.out.println(this.name);
		System.out.println(this.id);
		System.out.println(this.max);
		System.out.println(this.curr);
		System.out.println(this.instructor);
		System.out.println(this.names);
		System.out.println(this.sectionNumber);
		System.out.println(this.location + "\n" );
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCurr() {
		return curr;
	}
	public void setCurr(int curr) {
		this.curr = curr;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public ArrayList<String> getNames() {
		return names;
	}
	public void setNames(ArrayList<String> names) {
		this.names = names;
	}
	public int getSectionNumber() {
		return sectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
