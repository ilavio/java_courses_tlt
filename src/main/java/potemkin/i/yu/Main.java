package potemkin.i.yu;

import java.util.Arrays;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[] args) {

		CacheElement<Character> cachE1 = new CacheElement<Character>(1, 'a');
		CacheElement<Character> cachE2 = new CacheElement<Character>(1, 'a');
		log.info(cachE1.equals(cachE2) + ", " + cachE1);

		Cache<String> cashe = new Cache<String>(10);
		Random random = new Random();
		System.out.println(cashe);
		for (int i = 0; i < 10; i++) {
			cashe.add(Character.toString(random.nextInt(25) + 'a'), i);
		}
		log.info(cashe.toString());
		char charTest = (char) (random.nextInt(25) + 'a');
		cashe.add(Character.toString(charTest), 10);
		cashe.add(Character.toString('a'), 21);
		log.info(cashe.get(Character.toString(charTest)));
		log.info(cashe.toString());
		cashe.delete(Character.toString(charTest));
		log.info(charTest + " :" + cashe);
		cashe.clear();
		log.info(cashe + "\n");

		Storage<Integer> stor = new Storage<Integer>();
		log.info(stor.toString());
		for (int x = 0; x < 20; x++) {
			stor.add(random.nextInt(30));
		}
		log.info(stor.toString());
		for (int i = 20; i >= -2; i -= 1) {
			try {
				stor.get(i);
			} catch (MyIndexOutOfBoundException e) {
				log.debug(e.getMessage());
			}
		}
		stor.delete();
		stor.delete();
		log.info(stor.toString());
		stor.delete();
		log.info("stor.getLast(): " + stor.getLast());
		stor.clear();
		log.info(stor.toString());
		CallChekedException cheked = new CallChekedException(cashe);
		try {
			cheked.fieldCache();
		} catch (NoSuchFieldException e) {
			log.debug(new MyNoSuchFieldException(e).getMessage());
		}
	}
}
