/*
 * StringMethods
 * Илья Пот
 */

package potemkin.i.yu;

import java.util.Arrays;

/**
 * Класс для отработки работ методов
 * типа String 
 * 
 * @author Илья Пот
 */
public class StringMethods {

	/**
	 * метод вывода "Hello World"
	 */
	public void hello() {
		System.out.println("Hello World\n");
	}

	/**
	 * Метод сравнения двух строк с учетом и без учета регистра
	 * @param poemStr1 - первоя строка
	 * @param poemStr2 - вторая строка
	 */
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

	/**
	 * Метод вывода длины 2-х сравниваемых строк
	 * @param poemStr1 - первоя строка
	 * @param poemStr2 - вторая строка
	 */
	public void stringLength(String poemStr1, String poemStr2) {
		System.out.println("Длина строки 1)" + poemStr1.length() + "\n" + "Длина строки 2)" + poemStr2.length());
	}

	/**
	 * Метод получения массива символов из строки
	 * @param poemStr1 - строка из которой будем получать массив символов
	 */
	public void gettingSymbols(String poemStr1) {
		char[] masChar = poemStr1.toCharArray();
		System.out.println("Получаем массив символов: " + Arrays.toString(masChar));
	}

	/**
	 * Метод получения массива байтов из строки
	 * @param poemStr1 - строка из которой будем получать массив байтов
	 */
	public void gettingByte(String poemStr1) {
		byte[] masByte = poemStr1.getBytes();
		System.out.println("Получаем массив байтов: " + Arrays.toString(masByte));
	}

	/**
	 * метод преоброзования строки к верхнему регистру
	 * @param poemStr1 - строка преоброзования к верхнему регистру
	 */
	public void convertingToUppercase(String poemStr1) {
		System.out.println("Преоброзования строки к верхнему регистру: " + poemStr1.toUpperCase());
	}

	/**
	 * Метод поиска первого вхождения символа 'а' в строке
	 * @param poemStr1 - строка где будем искать символ 'а'
	 */
	public void firstOccurrence(String poemStr1) {
		System.out.println("Первое вхождение символа 'a': " + poemStr1.indexOf('а'));
	}

	/**
	 * Метод поиска последнего вхождения символа 'а' в строке
	 * @param poemStr1 - строка
	 */
	public void lastOccurrence(String poemStr1) {
		System.out.println("Последнее вхождение символа 'a': " + poemStr1.lastIndexOf('а'));
	}

	/**
	 * Метод поиска подстроки в строке
	 * @param poemStr1 - строка где будем искать построку
	 * @param strSearch - строку которую будем искать
	 */
	public void substringSearch(String poemStr1, String strSearch) {
		System.out.println("Содержит ли подстроку \"" + strSearch + "\": " + poemStr1.contains(strSearch));
	}

	/**
	 * Метод разбития строки на слова
	 * @param poemStr1 - строка для разбора
	 */
	public void breakString(String poemStr1) {
		String[] masString = poemStr1.split("( )|(, )");
		for (int i = 0; i < masString.length; i++) {
			System.out.println(i + ")" + masString[i] + ";");
		}
	}

}
