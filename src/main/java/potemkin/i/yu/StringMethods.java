package potemkin.i.yu;

import java.util.Arrays;


/*
 * Класс для отработки работ методов
 * типа String
 * */

public class StringMethods {

	String str1;
	String str2;
	
	public StringMethods(String str1, String str2) {
		
		this.str1 = str1;
		this.str2 = str2;
		
	}
	
	//public StringMethods() {}
	
	// метод вывода "Hello World"
	public void hello() {
		System.out.println("Hello World\n");
	}
	
	// Метод сравнения двух строк
	
		public void comparison() {
			
			int i = str1.compareTo(str2);
			
			if(i == 0) {
				System.out.println("Через метод compareTo без ignorCase:\n\t Строки равны;");
			}else {
				System.out.println("Через метод compareTo без ignorCase:\n\t Строки не равны;\n");
			}
			
			i = str1.compareToIgnoreCase(str2);
			
			if(i == 0) {
				System.out.println("Через compareTo с ignorCase:\n\t Строки равны;");
			}else {
				System.out.println("Через compareTo с ignorCase:\n\t Строки не равны;");
			}
			
		}
		
		// Метод вывода длины строки
		
		public void stringLength() {
			System.out.println("Длина строки 1)"+str1.length()+"\n"+"Длина строки 2)"+str2.length()); // длины 2-х строк
		}
		
		// Метод получения массива символов из строки
		
		public void gettingSymbols() {
			char [] masChar = str1.toCharArray(); // Массив символов из строки
			
			System.out.println("Получаем массив символов: "+Arrays.toString(masChar));
		}
		
		// Метод получения массива байтов из строки
		
		public void gettingByte() {
			byte [] masByte = str1.getBytes(); // массив байтов из строки
			
			System.out.println("Получаем массив байтов: " + Arrays.toString(masByte));
		}
		
		// метод преоброзования строки к верхнему регистру
		
		public void convertingToUppercase() {
			
			System.out.println("Преоброзования строки к верхнему регистру: " + str1.toUpperCase()); // преоброзования строки к верхнему регистру
		
		}
		
		// Метод поиска первого вхождения символа 'а' в строке
		
		public void firstOccurrence() {
		
			System.out.println("Первое вхождение символа 'a': "+ str1.indexOf('а')); //
		
		}
		
		// Метод поиска последнего вхождения символа 'а' в строке
		
		public void lastOccurrence() {
			
			System.out.println("Последнее вхождение символа 'a': "+ str1.lastIndexOf('а')); //
			
		}
		
		// Метод поиска подстроки "Sun" в строке
		
		public void substringSearch () {
			
			System.out.println("Содержит ли подстроку \"Sun\": "+ str1.contains("Sun")); // Содержит ли подстроку "Sun" 	
			
		}
		
		// Метод разбития строки на слова
		
		public void breakString() {
			
			String [] masString = str1.split("( )|(, )"); // указываем символы для делителя
			
			for(int i = 0; i < masString.length; i++) {      // запускаем цикл для печати массива
				System.out.println(i+")"+masString[i]+";");
			}
			
		}
		
		
		
		
}
