package potemkin.i.yu;

public class Main {
	public static void main(String[] args) {
		StartHuman startHuman = new StartHuman();
		System.out.println(startHuman.randomFilling());
		System.out.println(startHuman.removeDuplicate());
		System.out.println(startHuman.sortFIO());
		System.out.println(startHuman.sortAge());
		System.out.println(startHuman.sortAddress());
		System.out.println(startHuman.fillingHashMap());
		System.out.println(startHuman.sortHashMapByKey());
		System.out.println(startHuman.sortHashMapByValue());
	}
}
