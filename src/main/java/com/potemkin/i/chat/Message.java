package com.potemkin.i.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Message сообщение
 * 
 * @author Илья Пот
 */
public class Message {
    private volatile String massege = "SSS";
    private volatile List<String> listMassage = new ArrayList<String>();

    public synchronized String getMassege() {
        return massege;
    }

    public synchronized void setMassege(String massege) {
        this.massege = massege;
    }

    public List<String> getListMassage() {
        return listMassage;
    }

    /**
     * Метод добавления сообщения в список
     * 
     * @param sms - добавляемое сообщение
     */
    public void addListMassage(String sms) {
        this.listMassage.add(sms);
    }
}
