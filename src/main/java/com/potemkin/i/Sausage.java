package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс POJO Sausage для обработки и внедрения значений из file.txt
 * 
 * @author Илья Пот
 */
@Slf4j
public class Sausage {
    private String type;

    private int weight;

    private long cost;

    /**
     * Конструктор класса Sausage
     * 
     * @param type   - тип колбасы
     * @param weight - вес
     * @param cost   - стоимость
     */
    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    /**
     * Конструктор дефолтный класса Sausage
     */
    public Sausage() {
    }

    public String getType() {
        log.info("getType() Тип колбасы: {}", type);
        return type;
    }

    public void setType(String type) {
        log.info("setType() Тип колбасы установлено: {}", type);
        this.type = type;
    }

    public int getWeight() {
        log.info("getWeight() Вес колбасы: {}", weight);
        return weight;
    }

    public void setWeight(int weight) {
        log.info("getWeight() Вес колбасы установлено: {}", weight);
        this.weight = weight;
    }

    public long getCost() {
        log.info("getCost() Стоимость колбасы: {}", cost);
        return cost;
    }

    public void setCost(long cost) {
        log.info("getCost() Стоимость колбасы установлено: {}", cost);
        this.cost = cost;
    }

    @Override
    public String toString() {
        log.info("toString() {}{}{}{}{}{}{}", "Sausage [type=", type, ", weight=", weight, ", cost=", cost, "]");
        return "Sausage [type=" + type + ", weight=" + weight + ", cost=" + cost + "]";
    }
}
