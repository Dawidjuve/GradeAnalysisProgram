
public class Entity implements Comparable<Entity>{
	private String name;
	private String surname;
	private int grade;
	
	public Entity(String name, String surname, int grade){
		this.name=name;
		this.surname=surname;
		this.grade=grade;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getGrade() {
		return grade;
	}



	@Override
	public int compareTo(Entity o) {
		return Integer.compare(grade, o.grade);
	}
	
	
}
