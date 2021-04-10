package potemkin.i.yu;

import java.io.File;
import java.util.Arrays;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс обработки команд для рработ с файлом
 * 
 * @author Илья Пот
 */
@Slf4j
public class CommandHandler {
	private AddCommandHandler add = new AddCommandHandler();
	private DeletingLines del = new DeletingLines();
	private PrintsFromFile print = new PrintsFromFile();
	private TreeMap<String, Handler> treeProcessing;

	public CommandHandler() {
		this.treeProcessing = new TreeMap<String, Handler>();
		treeProcessing.put("add", add);
		treeProcessing.put("delete", del);
		treeProcessing.put("print", print);
	}

	/**
	 * Метод обработки команды
	 */
	public void execute(String command) {
		String[] commandArr = command.split(" ");
		if (!command.isEmpty()) {
			if (commandArr[0] != "END") {
				treeProcessing.get(commandArr[0]).handle(command);
			}
		}
	}
}
