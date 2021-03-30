package potemkin.i.yu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

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
				System.out.print(arrayList.get(i).equals(arrayList.get(e)) ? ++count + ") " + arrayList.remove(e) + "\n" : "");
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
		
		User user = new User("Саня", "ПУф", "ГУф", Role.USER);
		System.out.println(user.talk());
	}
}
