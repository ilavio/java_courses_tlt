package potemkin.i.yu;

import java.util.Arrays;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Cache<CacheElement<Character>> cashe = new Cache<CacheElement<Character>>(10);
		Random random = new Random();
		System.out.println(cashe);
		for (int i = 0; i < 10; i++) {
			cashe.add(new CacheElement(i, (char) (random.nextInt(25) + 'a')));
		}
		System.out.println(cashe);
		char charTest = (char) (random.nextInt(25) + 'a');
		cashe.add(new CacheElement(25, charTest));
		cashe.add('a', 21);
		System.out.println(cashe);
		cashe.delete(new CacheElement<Character>(25, charTest));
		System.out.println(cashe);
		cashe.clear();
		System.out.println(cashe + "\n");

		Storage<Integer> stor = new Storage<Integer>();
		System.out.println(stor);
		for (int x = 0; x < 20; x++) {
			stor.add(random.nextInt(30));
		}
		System.out.println(stor);
		for (int i = 20; i > 0; i -= 1) {
			System.out.println(i + ") " + stor.get(i));
		}
		stor.delete();
		stor.delete();
		System.out.println(stor);
		stor.delete();
		System.out.println("stor.getLast(): " + stor.getLast());
		stor.clear();
		System.out.println(stor);
	}
}
