package potemkin.i.yu;

public class IliyaLesson1 {

	public static void main(String[] args) {

		StringMethods stringMethods = new StringMethods();

		// метод вывода "Hello World"
		stringMethods.hello();

		// строки для вывода
		String poemStr1 = "кто ходит в гости по утрам тот паступает мудро, на то оно и утро";
		String poemStr2 = "Кто ходит в гости по утрам тот паступает мудро, На то оно и Утро";

		// выводим строки какие будем использовать
		System.out.println("// Строки для сравнения\r\n" + "String poemStr1 = \"" + poemStr1 + "\";\r\n"
				+ "String poemStr2 = \"" + poemStr2 + "\";");

		// 1) Метод вывода длины строки
		stringMethods.stringLength(poemStr1, poemStr2);

		// 2) Метод сравнения двух строк
		stringMethods.comparison(poemStr1, poemStr2);

		// 4) Метод получения массива символов из строки
		stringMethods.gettingSymbols(poemStr1);

		// 5) Метод получения массива байтов из строки
		stringMethods.gettingByte(poemStr1);

		// 6) Метод преоброзования строки к верхнему регистру
		stringMethods.convertingToUppercase(poemStr1);

		// 7) Метод поиска первого вхождения символа 'а' в строке
		stringMethods.firstOccurrence(poemStr1);

		// 8) Метод поиска последнего вхождения символа 'а' в строке
		stringMethods.lastOccurrence(poemStr1);

		// 9) Метод поиска подстроки "Sun" в строке
		stringMethods.substringSearch(poemStr1, "Sun");

		// 14) Метод разбития строки на слова
		stringMethods.breakString(poemStr1);

	}

}
