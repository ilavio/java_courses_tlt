package potemkin.i.yu;

import java.util.HashMap;

public class User {
	private String name;
	private String surname;
	private String patronymic;
	private Role role;
	private HashMap<Role, String> hashMap;

	public User(String name, String surname, String patronymic, Role role) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
		this.role = role;
		hashMap = new HashMap<Role, String>();
		hashMap.put(role.ADMIN, "Имеет высший доступ безопасности, добавлять - удалять - блокировать пользователей");
		hashMap.put(role.MODERATOR, "Доступ безопасноти умереный , добавлять - удалять - редактировать пользователей");
		hashMap.put(role.USER, "Доступ безопасности - наблюдатель , редактирование своего профиля");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
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
		if (role != other.role)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	public String talk() {
		return "Приветствуем " + name + " " + surname + " " + patronymic + " с ролью " + hashMap.get(role);
	}

}
