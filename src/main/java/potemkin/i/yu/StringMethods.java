/*
 * StringMethods
 *
 * Илья Пот
 */

package potemkin.i.yu;

import java.util.Arrays;

/*
 * Класс для отработки работ методов
 * типа String 
 * 
 * Илья Пот
 */
public class StringMethods {

	/* метод вывода "Hello World" */
	public void hello() {
		System.out.println("Hello World\n");
	}

	/* Метод сравнения двух строк */
	public void comparison(String poemStr1, String poemStr2) {
		if ((poemStr1.compareTo(poemStr2)) == 0) {
			System.out.println("Через метод compareTo без ignorCase:\n\t Строки равны;");
		} else {
			System.out.println("Через метод compareTo без ignorCase:\n\t Строки не равны;\n");
		}

		if ((poemStr1.compareToIgnoreCase(poemStr2)) == 0) {
			System.out.println("Через compareTo с ignorCase:\n\t Строки равны;");
		} else {
			System.out.println("Через compareTo с ignorCase:\n\t Строки не равны;");
		}
	}

	/* Метод вывода длины строки */
	public void stringLength(String poemStr1, String poemStr2) {
		System.out.println("Длина строки 1)" + poemStr1.length() + "\n" + "Длина строки 2)" + poemStr2.length());
	}

	/* Метод получения массива символов из строки */
	public void gettingSymbols(String poemStr1) {
		char[] masChar = poemStr1.toCharArray();
		System.out.println("Получаем массив символов: " + Arrays.toString(masChar));
	}

	/* Метод получения массива байтов из строки */
	public void gettingByte(String poemStr1) {
		byte[] masByte = poemStr1.getBytes();
		System.out.println("Получаем массив байтов: " + Arrays.toString(masByte));
	}

	/* метод преоброзования строки к верхнему регистру */
	public void convertingToUppercase(String poemStr1) {
		System.out.println("Преоброзования строки к верхнему регистру: " + poemStr1.toUpperCase());
	}

	/* Метод поиска первого вхождения символа 'а' в строке */
	public void firstOccurrence(String poemStr1) {
		System.out.println("Первое вхождение символа 'a': " + poemStr1.indexOf('а'));
	}

	/* Метод поиска последнего вхождения символа 'а' в строке */
	public void lastOccurrence(String poemStr1) {
		System.out.println("Последнее вхождение символа 'a': " + poemStr1.lastIndexOf('а'));
	}

	/* Метод поиска подстроки "Sun" в строке */
	public void substringSearch(String poemStr1) {
		System.out.println("Содержит ли подстроку \"Sun\": " + poemStr1.contains("Sun"));
	}

	/* Метод разбития строки на слова */
	public void breakString(String poemStr1) {
		String[] masString = poemStr1.split("( )|(, )");
		for (int i = 0; i < masString.length; i++) {
			System.out.println(i + ")" + masString[i] + ";");
		}
	}

}
