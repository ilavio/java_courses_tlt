package potemkin.i.yu;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс удаления строк из файла
 * 
 * @author Илья Пот
 */
@Slf4j
public class Delete {
	private String[] commandArr;
	private int number;
	private StringBuffer stringBuf = new StringBuffer();
	int deleteFile = 2;

	public Delete(String[] commandArr) {
		this.commandArr = commandArr;
	}

	/**
	 * Метод удаления строки из файла
	 */
	public void delete() {
		int deleteFile = 2;
		try {
			number = Integer.parseInt(commandArr[1]);
		} catch (NumberFormatException e) {
			number = 0;
			deleteFile = 1;
		}
		File file = new File(commandArr[deleteFile]);
		log.info(read(file));
		String str = editString(number, stringBuf);
		try (BufferedOutputStream bufStream = new BufferedOutputStream(new FileOutputStream(file), 64)) {
			bufStream.write(str.getBytes());
			bufStream.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Метод чтения файла с которым собираемся производить действие
	 * 
	 * @param file - имя файла
	 * @return String - возвращаемый тип
	 */
	public String read(File file) {
		try (BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file), 64)) {
			byte[] bytemas = new byte[64];
			while (buf2.available() != 0) {
				bytemas = buf2.readAllBytes();
				stringBuf.append(new String(bytemas));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuf.toString();
	}

	/**
	 * Метод непосредственного редактирования данных
	 * 
	 * @param number    - номер строки удаляемой
	 * @param stringBuf - данные из файла
	 * @return String - возвращаемый тип
	 */
	public String editString(int number, StringBuffer stringBuf) {
		StringBuffer editBuf = new StringBuffer();
		String stringEdit = stringBuf.toString();
		String[] printMass = stringEdit.split("\n");
		System.out.println(printMass.length + "number:" + number);
		System.out.println(Arrays.toString(printMass));
		if (number > printMass.length) {
			System.out.println("Не найдено");
		}
		if (number == 0) {
			for (int i = 0; i < printMass.length - 1; i++) {
				if (i == printMass.length - 1) {
					editBuf.append(printMass[i]);
					break;
				}
				editBuf.append(printMass[i]);
				editBuf.append("\n");
			}
			return editBuf.toString();
		}
		for (int i = 0; i < printMass.length; i++) {
			if (i == printMass.length - 1) {
				editBuf.append(printMass[i]);
				break;
			}
			if (i < number - 1 || number - 1 < i) {
				editBuf.append(printMass[i]);
				editBuf.append("\n");
			}
		}
		return editBuf.toString();
	}
}
