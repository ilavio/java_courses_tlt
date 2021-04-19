package potemkin.i.yu;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс Add для записи в файл строки
 * 
 * @author Илья Пот
 */
@Slf4j
public class AddCommandHandler implements Handler {

	/**
	 * Метод выборки текста для добавления в файл из команды
	 * 
	 * @return String - возвращаемый тип
	 */
	private String[] separation(String command, int commandLength, int indexFile) {
		boolean miss = false;
		String[] commandArr = command.split(" ");
		commandLength = commandArr.length;
		String nameFile = commandArr[indexFile];
		StringBuffer stringBuf = new StringBuffer();
		int count = 0;
		String patt = "\"[a-zA-Zа-яА-Я]+";
		for (int i = 0; i < commandArr.length; i++) {
			Pattern patern = Pattern.compile(patt);
			Matcher matcher = patern.matcher(commandArr[i]);
			if (matcher.lookingAt()) {
				miss = true;
				count = i;
				System.out.println(miss + "; " + count);
				break;
			}
		}
		if (miss) {
			for (int i = count; i < commandArr.length; i++) {
				stringBuf.append(commandArr[i]);
				if (i < commandArr.length - 1) {
					stringBuf.append(" ");
				}
			}
			stringBuf.deleteCharAt(0);
			stringBuf.deleteCharAt(stringBuf.length() - 1);
			stringBuf.append("\n");
		}
		log.info(stringBuf.toString());
		String addedText = stringBuf.toString();
		return new String[] { nameFile, addedText };
	}

	/**
	 * Метод записи в файл
	 */
	public void handle(String command) {
		int number = getNumber(command)[1];
		int commandLength = getNumber(command)[2];
		int indexFile = getNumber(command)[0];
		String nameFile = separation(command, commandLength, indexFile)[0];
		String addedText = separation(command, commandLength, indexFile)[1];
		StringBuffer stringBuf = new StringBuffer();
		if (commandLength > 2 & number == 0) {
			File file = new File(nameFile);
			createFile(file);
			writeToFileInEnd(addedText, file);
		} else if (number > 0) {
			File file = new File(nameFile);
			try (BufferedReader bufRead = new BufferedReader(new FileReader(file), 64)) {
				int count = 0;
				while (bufRead.ready()) {
					count += 1;
					if (number == count) {
						stringBuf.append(addedText);
						stringBuf.append("\n");
					}
					stringBuf.append(bufRead.readLine());
					stringBuf.append("\n");
				}
				writingToFile(stringBuf.toString(), file);
			} catch (FileNotFoundException e) {
				log.info("Ошибка записи!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Метод для создания файла
	 * 
	 * @param file - имя файла
	 * @return boolean - в случае создания возвращает true, иначе false
	 */
	public boolean createFile(File file) {
		if (!file.exists()) {
			try {
				return file.createNewFile();
			} catch (IOException e) {
				log.info("Ошибка в создании файла");
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Метод записи в файл
	 * 
	 * @param text - текс записываемый
	 * @param file - имя файла
	 */
	private void writingToFile(String text, File file) {
		try (BufferedOutputStream bufStream = new BufferedOutputStream(new FileOutputStream(file), 64)) {
			bufStream.write(text.getBytes());
			bufStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Метод записи в конец файла
	 * 
	 * @param text - текс записываемый
	 * @param file - имя файла
	 */
	private void writeToFileInEnd(String addedText, File file) {
		try (BufferedOutputStream bufStream = new BufferedOutputStream(new FileOutputStream(file, true), 64)) {
			bufStream.write(addedText.getBytes());
			bufStream.flush();
		} catch (FileNotFoundException e) {
			log.info("Ошибка записи!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int[] getNumber(String command) {
		int indexFile = 2;
		int number = 0;
		String[] commandArr = command.split(" ");
		try {
			number = Integer.parseInt(commandArr[1]);
		} catch (NumberFormatException e) {
			number = 0;
			indexFile = 1;
		}
		return new int[] { indexFile, number, commandArr.length };
	}
}
