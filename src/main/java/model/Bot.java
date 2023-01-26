package model;

import model.order.Symbol;

public class Bot {
    private static int countID =1;
    private int id;
    private String apiKey;
    private String apiSec;
    private float step;
    private float coefficient;
    private int lvl;
    private Symbol symbol;

    public Bot(String apiKey, String apiSec, float step, float coefficient, int lvl, Symbol symbol) {
        id = countID++;
        this.apiKey = apiKey;
        this.apiSec = apiSec;
        this.step = step;
        this.coefficient = coefficient;
        this.lvl = lvl;
        this.symbol = symbol;
    }

    public float getStep() {
        return step;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public int getLvl() {
        return lvl;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSec() {
        return apiSec;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bot bot = (Bot) o;

        return id == bot.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
