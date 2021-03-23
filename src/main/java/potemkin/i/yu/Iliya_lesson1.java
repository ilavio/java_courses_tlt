package potemkin.i.yu;

import java.util.Arrays;

public class Iliya_lesson1 {
	
	public static void hello() {
		System.out.println("Hello World\n");
	}

	public static void main(String[] args) {
		
		hello();
		// stroki dlya sravneniya
		String str1 = "кто ходит в гости по утрам тот паступает мудро, на то оно и утро";
		String str2 = "Кто ходит в гости по утрам тот паступает мудро, На то оно и Утро";
		
		System.out.println("// stroki dlya sravneniya\r\n"
				+ "		String str1 = \"кто ходит в гости по утрам тот паступает мудро, на то оно и утро\";\r\n"
				+ "		String str2 = \"Кто ходит в гости по утрам тот паступает мудро, На то оно и Утро\";");
		
		compare(str1, str2); 
		
		System.out.println("Dlina 1)"+str1.length()+"\n"+"Dlina 2)"+str2.length()); // Dlina strok
		
		char [] masChar = str1.toCharArray(); // massiv simvolov
		
		System.out.println("Poluchaem massiv simvolov: "+Arrays.toString(masChar));
		
		byte [] masByte = str1.getBytes(); // massiv byte
		
		System.out.println("Poluchaem massiv byte: " + Arrays.toString(masByte));
		
		System.out.println("Preobrozovanie k verhnemu registru: " + str1.toUpperCase()); // Preobrozovanie k verhnemu registru
		
		System.out.println("Pervoye vkhozhdeniye simvola 'a': "+ str1.indexOf('а')); //Pervoye vkhozhdeniye simvola 'a'
		
		System.out.println("Poslednie vkhozhdeniye simvola 'a': "+ str1.lastIndexOf('а')); //Poslednie vkhozhdeniye simvola 'a'
		
		System.out.println("Soderzhit li podstroku \"Sun\": "+ str1.contains("Sun")); // Soderzhit li podstroku "Sun"
		
		// razbivaem stroku na slova
		String [] masString = str1.split("( )|(, )");
		for(int i = 0; i < masString.length; i++) {
			System.out.println(i+")"+masString[i]+";");
		}

	}
	
	// metod sravneniya
	public static void compare(String str1, String str2) {
		
		int i = str1.compareTo(str2);
		
		if(i == 0) {
			System.out.println("cherez compareTo bez ignorCase:\n\t Stroki ravny;");
		}else {
			System.out.println("cherez compareTo bez ignorCase:\n\t Stroki ne ravny;\n");
		}
		
		i = str1.compareToIgnoreCase(str2);
		
		if(i == 0) {
			System.out.println("cherez compareTo s ignorCase:\n\t Stroki ravny;");
		}else {
			System.out.println("cherez compareTo s ignorCase:\n\t Stroki ne ravny;");
		}
		
	}
	
	

}
