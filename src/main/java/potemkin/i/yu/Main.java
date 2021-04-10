package potemkin.i.yu;

import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

/**
 * Основной класс запуска приложжения
 * 
 * @author Илья Пот
 */
@Slf4j
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("Введите команду в формате: add n fileName \"text\"\n"
					+ "где: add - дествие добавить (delete - удаление, print - печать), n - номер строки, fileName - имя файла,\n"
					+ "\"text\" - (только для add) добавляемый текст\n" + "END - завершение работы");
			String commandStr = scan.nextLine();
			if (commandStr.equals("END")) {
				System.out.println("Завершение.");
				break;
			}
			CommandHandler command = new CommandHandler(commandStr);
			command.execute();
		}
	}
}
