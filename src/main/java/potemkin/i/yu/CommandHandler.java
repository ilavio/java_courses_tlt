package potemkin.i.yu;

import java.io.File;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс обработки команд для рработ с файлом
 * @author Илья Пот
 */
@Slf4j
public class CommandHandler {
	private String command;
	private String[] commandArr;
	private AddCommandHandler add;
	private DeletingLines del;
	private PrintsFromFile print;

	public CommandHandler(String command) {
		this.command = command;
		this.commandArr = command.split(" ");
	}

	/**
	 * Метод обработки команды
	 */
	public void execute() {
		if (!command.isEmpty()) {
			switch (commandArr[0]) {
			case "add":
				add = new AddCommandHandler();
				add.writeFile(command);
				log.info(Arrays.toString(commandArr));
				break;
			case "delete":
				del = new DeletingLines();
				del.delete(command);
				log.info(Arrays.toString(commandArr));
				break;
			case "print":
				print = new PrintsFromFile();
				print.printF(command);
				log.info(Arrays.toString(commandArr));
				break;
			case "END":
				break;
			default:
				System.out.println("неверная команда");
			}
		} else {
			System.out.println("пустая команда");
		}
	}
}
