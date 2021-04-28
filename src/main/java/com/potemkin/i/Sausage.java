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

    public Sausage(String type, int weight, long cost) {
        this.type = type;
        this.weight = weight;
        this.cost = cost;
    }

    public Sausage() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Sausage [type=" + type + ", weight=" + weight + ", cost=" + cost + "]";
    }
}
