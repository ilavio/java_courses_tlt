package potemkin.i.yu;

/**
 * Класс Human имплементирует Comparable
 * 
 * @author Илья Пот
 */
public class Human implements Comparable<Human> {
	private String name;
	private String surname;
	private String patronymic;
	private int age;
	private Address address;

	/**
	 * Конструктор класса Human
	 * 
	 * @param name       - имя
	 * @param surname    - фамилия
	 * @param patronymic - отчество
	 * @param age        - возраст
	 * @param address    - объект класса Address
	 */
	public Human(String name, String surname, String patronymic, int age, Address address) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.age = age;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public int getAge() {
		return age;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Human other = (Human) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patronymic == null) {
			if (other.patronymic != null)
				return false;
		} else if (!patronymic.equals(other.patronymic))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Human: name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", age=" + age
				+ ", \naddress=" + address;
	}

	@Override
	public int compareTo(Human o) {
		return (this.surname.compareTo(o.surname) != 0 ? this.surname.compareTo(o.surname)
				: (this.name.compareTo(o.name) != 0 ? this.name.compareTo(o.name)
						: (this.patronymic.compareTo(o.patronymic))));
	}

}
