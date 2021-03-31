package potemkin.i.yu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) {
		int count = 0;
		ArrayList<Human> arrayList = new ArrayList<Human>();
		Random random = new Random();
		String[] street = new String[] { "Октября", "Победа", "40 Лет", "Новозоводская", "Ленина", "Мичурина",
				"Молодежная" };
		String[] city = new String[] { "Тольятти", "Самара", "Ульяновск", "Сызрань" };
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
			System.out.println(count + ") " + human + "\n");
		}
		count = 0;
		System.out.println("Удаленные дубликаты:\n");
		for (int i = 0; i < arrayList.size(); i++) {
			for (int e = i + 1; e < arrayList.size(); e++) {
				System.out.print(
						arrayList.get(i).equals(arrayList.get(e)) ? ++count + ") " + arrayList.remove(e) + "\n" : "");
			}
		}

		count = 0;
		System.out.println("\nСортировка по ФИО :\n");
		Collections.sort(arrayList);
		for (Human human : arrayList) {
			count++;
			System.out.println(count + ") " + human + "\n");
		}
		count = 0;
		System.out.println("Сортировка по возрасту:\n");
		Collections.sort(arrayList, Comparator.comparingInt(o -> o.getAge()));
		for (Human human : arrayList) {
			count++;
			System.out.println(count + ") " + human + "\n");
		}
		count = 0;
		System.out.println("Сортировка по адресу:\n");
		Collections.sort(arrayList, Comparator.comparing(o -> o.getAddress()));
		for (Human human : arrayList) {
			count++;
			System.out.println(count + ") " + human + "\n");
		}

		HashMap<String, TreeSet<User>> hashUser = new HashMap<String, TreeSet<User>>();
		for (int a = 0; a < city.length; a++) {
			TreeSet<User> userSet = new TreeSet<User>();
			hashUser.put(city[a], userSet);
		}
		Role[] role = Role.values();
		for (int i = 0; i < arrayList.size(); i++) {
			User user = new User(arrayList.get(i), role[random.nextInt(3)]);
			hashUser.get(user.getCity()).add(user);
		}
		count = 0;
		for (Map.Entry<String, TreeSet<User>> entry : hashUser.entrySet()) {
			System.out.println(entry.getKey() + ": ");

			for (User user : entry.getValue()) {
				++count;
				System.out.println(count + ")" + user.talk() + ";");
			}
		}
		count = 0;
		TreeMap<String, TreeSet<User>> treeUser = new TreeMap<String, TreeSet<User>>(hashUser);
		System.out.println("Сортировка по ключу:");
		for (Map.Entry<String, TreeSet<User>> entry : treeUser.entrySet()) {
			System.out.println(entry.getKey() + ": ");

			for (User user : entry.getValue()) {
				++count;
				System.out.println(count + ")" + user.talk() + ";");
			}
		}
		count = 0;
		System.out.println("Сортировка по значению:");
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
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}
