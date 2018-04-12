import java.util.Arrays;

public class ClassTest {

	private Entity[] arrayOfEntities;
	private int enteredStudents = 0;
	private int numberOfStudents;

	public ClassTest(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
		this.arrayOfEntities = new Entity[numberOfStudents];
	}

	public void addEntity(Entity entity) {
		arrayOfEntities[enteredStudents] = entity;
		enteredStudents++;
	}

	public int getNumberOfEntered() {
		return enteredStudents;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public double getAverage() {
		double sum = 0;
		for (Entity entity : arrayOfEntities) {
			sum += entity.getGrade();
		}
		return sum / numberOfStudents;
	}

	public int getMin() {
		int min = 100;
		for (Entity entity : arrayOfEntities) {
			if (entity.getGrade() < min)
				min = entity.getGrade();
		}
		return min;
	}

	public int getMax() {
		int max = 1;
		for (Entity entity : arrayOfEntities) {
			if (entity.getGrade() > max)
				max = entity.getGrade();
		}
		return max;
	}

	public Entity[] getSortedList() {
		Entity[] sortedList = arrayOfEntities.clone();
		Arrays.sort(sortedList);
		return sortedList;
	}

	public Entity[] getStudentBySurname(String surname) {
		Entity[] list;
		switch (getNumberOfStudentsWithSurname(surname)) {
		case 0: {
			list = new Entity[0];
			break;
		}
		default: {
			list = new Entity[getNumberOfStudentsWithSurname(surname)];
			int i = 0;
			for (Entity entity : arrayOfEntities) {
				if (entity.getSurname().toLowerCase().equals(surname.toLowerCase())) {
					list[i] = entity;
					i++;
				}
			}
			break;
		}
		}
		return list;

	}

	private int getNumberOfStudentsWithSurname(String surname) {
		int count = 0;
		for (Entity entity : arrayOfEntities) {
			if (entity.getSurname().toLowerCase().equals(surname.toLowerCase()))
				count++;
		}
		return count;
	}

}
