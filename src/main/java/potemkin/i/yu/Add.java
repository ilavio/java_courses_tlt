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
public class Add {
	private String[] commandArr;
	private int number = 0;
	private boolean miss = false;

	public Add(String[] commandArr) {
		this.commandArr = commandArr;
	}

	/**
	 * Метод выборки текста для добавления в файл из команды
	 * 
	 * @return String - возвращаемый тип
	 */
	public String addString() {
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
		return stringBuf.toString();
	}

	/**
	 * Метод записи в файл
	 */
	public void writeFile() {
		StringBuffer stringBuf = new StringBuffer();
		int indexFile = 2;
		try {
			number = Integer.parseInt(commandArr[1]);
		} catch (NumberFormatException e) {
			number = 0;
			indexFile = 1;
		}
		if (commandArr.length > 2 & number == 0) {
			File file = new File(commandArr[indexFile]);
			try (BufferedOutputStream bufStream = new BufferedOutputStream(new FileOutputStream(file, true), 64)) {
				bufStream.write(addString().getBytes());
				bufStream.flush();
			} catch (FileNotFoundException e) {
				log.info("Ошибка записи!");
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (number > 0) {
			File file = new File(commandArr[indexFile]);
			try (BufferedReader bufRead = new BufferedReader(new FileReader(file), 64)) {
				int count = 0;
				while (bufRead.ready()) {
					count += 1;
					if (number == count) {
						stringBuf.append(addString());
						stringBuf.append("\n");
					}
					stringBuf.append(bufRead.readLine());
					stringBuf.append("\n");
				}
				try (BufferedOutputStream bufStream = new BufferedOutputStream(new FileOutputStream(file), 64)) {
					bufStream.write(stringBuf.toString().getBytes());
					bufStream.flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
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
}
