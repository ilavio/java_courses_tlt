package potemkin.i.yu;

import java.util.Arrays;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {

		CacheElement<Character> cachE1 = new CacheElement<Character>(1, 'a');
		CacheElement<Character> cachE2 = new CacheElement<Character>(1, 'a');
		System.out.println(cachE1.equals(cachE2) + ", " + cachE1);

		Cache<String> cashe = new Cache<String>(10);
		Random random = new Random();
		System.out.println(cashe);
		for (int i = 0; i < 10; i++) {
			cashe.add(Character.toString(random.nextInt(25) + 'a'), i);
		}
		System.out.println(cashe);
		char charTest = (char) (random.nextInt(25) + 'a');
		cashe.add(Character.toString(charTest), 10);
		cashe.add(Character.toString('a'), 21);
		System.out.println(cashe.get(Character.toString(charTest)));
		System.out.println(cashe);
		cashe.delete(Character.toString(charTest));
		System.out.println(charTest + " :" + cashe);
		cashe.clear();
		System.out.println(cashe + "\n");

		Storage<Integer> stor = new Storage<Integer>();
		System.out.println(stor);
		for (int x = 0; x < 20; x++) {
			stor.add(random.nextInt(30));
		}
		System.out.println(stor);
		for (int i = 20; i >= -2; i -= 1) {
			try {
				System.out.println(i + ") " + stor.get(i));
			} catch (MyIndexOutOfBoundException e) {
				System.err.println(e.getMessage());
				log.debug(e.getMessage());
			}
		}
		stor.delete();
		stor.delete();
		System.out.println(stor);
		stor.delete();
		System.out.println("stor.getLast(): " + stor.getLast());
		stor.clear();
		System.out.println(stor);
		ChekedException cheked = new ChekedException(cashe);
		try {
			cheked.fieldCache();
		} catch (NoSuchFieldException e) {
			new MyNoSuchFieldException(e).printStackTrace();
		}
	}
}
