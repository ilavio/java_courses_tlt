package potemkin.i.yu;

import java.util.Arrays;

public class Iliya_lesson1 {
	
	
	public static void main(String[] args) {
		
		// строки для вывода
		String str1 = "кто ходит в гости по утрам тот паступает мудро, на то оно и утро";
		String str2 = "Кто ходит в гости по утрам тот паступает мудро, На то оно и Утро";
		
		// выводим строки какие будем использовать
		System.out.println("// stroki dlya sravneniya\r\n"
				+ "		String str1 = \"кто ходит в гости по утрам тот паступает мудро, на то оно и утро\";\r\n"
				+ "		String str2 = \"Кто ходит в гости по утрам тот паступает мудро, На то оно и Утро\";");
		
		StringMethods stringMethods = new StringMethods(str1, str2);
		
		
		// метод вывода "Hello World"
		stringMethods.hello();
		
		// Метод сравнения двух строк
		stringMethods.comparison();
		
		// Метод получения массива символов из строки
		stringMethods.gettingSymbols();
		
		// Метод получения массива байтов из строки
		stringMethods.gettingByte();
		
		// метод преоброзования строки к верхнему регистру
		stringMethods.convertingToUppercase();

		// Метод поиска первого вхождения символа 'а' в строке
		stringMethods.firstOccurrence();
		
		// Метод поиска последнего вхождения символа 'а' в строке
		stringMethods.lastOccurrence();
		
		// Метод вывода длины строки
		stringMethods.stringLength();
		
		// Метод поиска подстроки "Sun" в строке
		stringMethods.substringSearch();
		
		// Метод разбития строки на слова
		stringMethods.breakString();
		

	}
	
	
}
