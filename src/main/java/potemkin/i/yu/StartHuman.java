package potemkin.i.yu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

/**
 * Класс наполнения arrayList, hashUser и осуществления действий над коллекциями
 * StartHuman
 * 
 * @author Илья Пот
 */
public class StartHuman {
	private ArrayList<Human> arrayList = new ArrayList<Human>();
	private Random random = new Random();
	private String[] city;
	private HashMap<String, TreeSet<User>> hashUser;
	private int count = 0;

	/**
	 * Метод рандомной вставки в arrayList объектов класса Human и Address
	 * 
	 * @return String - строка итогового представления arrayList
	 */
	public String randomFilling() {
		StringBuffer stringBuffer = new StringBuffer();
		String[] street = new String[] { "Октября", "Победа", "40 Лет", "Новозоводская", "Ленина", "Мичурина",
				"Молодежная" };
		city = new String[] { "Тольятти", "Самара", "Ульяновск", "Сызрань" };
		String[] name = new String[] { "Максим", "Александр", "Дмитрий", "Павел" };
		String[] surname = new String[] { "Лидовск", "Муладыка", "Спирин", "Курчагин", "Жилин", "Курченко", "Сидоров" };
		String[] patronymic = new String[] { "Васильевич", "Ильич", "Максимович", "Павлович" };
		for (int i = 0; i < 7; i++) {
			int adr = random.nextInt(4);
			arrayList.add(new Human(name[adr], surname[i], patronymic[adr], random.nextInt(90),
					new Address(street[i], city[adr], random.nextInt(100), random.nextInt(100))));
		}
		for (int i = 0; i < 3; i++) {
			Human human = arrayList.get(i);
			arrayList.add(human);
		}
		for (Human human : arrayList) {
			count++;
			stringBuffer.append(count + ") " + human + "\n");
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод удаления дубликатов в arrayList
	 * 
	 * @return String - строка удаленных дублирующих объектов из arrayList
	 */
	public String removeDuplicate() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Удаленные дубликаты:\n");
		for (int i = 0; i < arrayList.size(); i++) {
			for (int e = i + 1; e < arrayList.size(); e++) {
				stringBuffer.append(
						arrayList.get(i).equals(arrayList.get(e)) ? ++count + ") " + arrayList.remove(e) + "\n" : "");
			}
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод сортировки arrayList по ФИО
	 * 
	 * @return String - итог сортировки arrayList
	 */
	public String sortFIO() {
		StringBuffer stringBuffer = new StringBuffer();
		System.out.println("\nСортировка по ФИО :\n");
		Collections.sort(arrayList);
		for (Human human : arrayList) {
			count++;
			stringBuffer.append(count + ") " + human + "\n");
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод сортировки arrayList по возрасту
	 * 
	 * @return String - итог сортировки arrayList
	 */
	public String sortAge() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Сортировка по возрасту:\n");
		Collections.sort(arrayList, Comparator.comparingInt(o -> o.getAge()));
		for (Human human : arrayList) {
			count++;
			stringBuffer.append(count + ") " + human + "\n");
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод сортировки arrayList по адресу
	 * 
	 * @return String - итог сортировки arrayList
	 */
	public String sortAddress() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Сортировка по адресу:\n");
		Collections.sort(arrayList, Comparator.comparing(o -> o.getAddress()));
		for (Human human : arrayList) {
			count++;
			stringBuffer.append(count + ") " + human + "\n");
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод наполнения hashUser и вывода итога наполнения
	 * 
	 * @return String - итог наполнения hashUser User'ами
	 */
	public String fillingHashMap() {
		StringBuffer stringBuffer = new StringBuffer();
		hashUser = new HashMap<String, TreeSet<User>>();
		for (int a = 0; a < city.length; a++) {
			TreeSet<User> userSet = new TreeSet<User>();
			hashUser.put(city[a], userSet);
		}
		Role[] role = Role.values();
		for (int i = 0; i < arrayList.size(); i++) {
			User user = new User(arrayList.get(i), role[random.nextInt(3)]);
			hashUser.get(user.getCity()).add(user);
		}
		for (Map.Entry<String, TreeSet<User>> entry : hashUser.entrySet()) {
			stringBuffer.append(entry.getKey() + ": ");

			for (User user : entry.getValue()) {
				++count;
				stringBuffer.append(count + ")" + user.talk() + ";\n");
			}
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод сортировки hashUser по ключу
	 * 
	 * @return String - итог сортировки
	 */
	public String sortHashMapByKey() {
		StringBuffer stringBuffer = new StringBuffer();
		TreeMap<String, TreeSet<User>> treeUser = new TreeMap<String, TreeSet<User>>(hashUser);
		stringBuffer.append("Сортировка по ключу:\n");
		for (Map.Entry<String, TreeSet<User>> entry : treeUser.entrySet()) {
			stringBuffer.append(entry.getKey() + ":\n");

			for (User user : entry.getValue()) {
				++count;
				stringBuffer.append(count + ")" + user.talk() + ";\n");
			}
		}
		count = 0;
		return stringBuffer.toString();
	}

	/**
	 * Метод сортировки hashUser по значению
	 * 
	 * @return String - итог сортировки
	 */
	public String sortHashMapByValue() {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Сортировка по значению:\n");
		HashMap<String, String> hashMap = new HashMap<String, String>();
		for (int i = 0; i < arrayList.size(); i++) {
			hashMap.put(arrayList.get(i).getName(), arrayList.get(i).getSurname());
		}
		Set<Entry<String, String>> entrySet = hashMap.entrySet();
		ArrayList<Entry<String, String>> arraySet = new ArrayList<>(entrySet);
		Collections.sort(arraySet, new Comparator<Entry<String, String>>() {

			@Override
			public int compare(Entry<String, String> o1, Entry<String, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		for (Map.Entry<String, String> entry : arraySet) {
			stringBuffer.append(entry.getKey() + " : " + entry.getValue() + "\n");
		}
		count = 0;
		return stringBuffer.toString();
	}
}
