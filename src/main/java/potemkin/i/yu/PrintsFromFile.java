package potemkin.i.yu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс печати файла в консоль
 * 
 * @author Илья Пот
 */
@Slf4j
public class PrintsFromFile {
	private StringBuffer stringBuf = new StringBuffer();
	public int number = 0;
	private File file;

	/**
	 * Метод разбора команды на составные переменный
	 * 
	 * @param command
	 */
	public void handle(String command) {
		String[] commandArr = command.split(" ");
		int printFile = 2;
		try {
			number = Integer.parseInt(commandArr[1]);
		} catch (NumberFormatException e) {
			number = 0;
			printFile = 1;
		}
		file = new File(commandArr[printFile]);
	}

	/**
	 * Метод для вывода всего файла в консоль
	 * 
	 * @param file - имя файла
	 * @return String - возвращаемый тип
	 */
	public String printAll(File file) {
		try (BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file), 64)) {
			byte[] bytemas = new byte[64];

			while (buf2.available() != 0) {
				bytemas = buf2.readAllBytes();

				stringBuf.append(new String(bytemas));
			}
		} catch (FileNotFoundException e) {
			log.info("Ошибка чтения файла");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringBuf.toString();
	}

	/**
	 * Метод печати файла
	 */
	public void printF(String command) {
		handle(command);
		if (!file.exists()) {
			log.info("Файла нет!");
		}
		if (number == 0) {
			System.out.println(printAll(file));
		}
		if (number > 0) {
			System.out.println(printNum(file));
		}
	}

	/**
	 * Метод печати строки под номером
	 * 
	 * @param file - имя файла
	 * @return String - возвращаемый тип
	 */
	public String printNum(File file) {
		try (BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file), 64)) {
			byte[] bytemas = new byte[64];
			while (buf2.available() != 0) {
				bytemas = buf2.readAllBytes();
				stringBuf.append(new String(bytemas));
			}
		} catch (FileNotFoundException e) {
			log.info("Ошибка чтения файла");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] printMass = stringBuf.toString().split("\n");
		log.info(Arrays.toString(printMass));
		stringBuf.setLength(0);
		if (printMass.length - 1 < number) {
			return "Не найдено";
		}
		return printMass[number - 1];
	}
}
