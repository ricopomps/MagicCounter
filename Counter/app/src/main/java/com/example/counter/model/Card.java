package com.example.counter.model;

public class Card {
    private String cardName;
    private int counters;
    private int initialAttack;
    private int initialDefense;

    public Card() {
    }

    public Card(String cardName, int counters, int initialAttack, int initialDefense) {
        this.cardName = cardName;
        this.counters = counters;
        this.initialAttack = initialAttack;
        this.initialDefense = initialDefense;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCounters() {
        return counters;
    }

    public void setCounters(int counters) {
        this.counters = counters;
    }

    public int getInitialAttack() {
        return initialAttack;
    }

    public void setInitialAttack(int initialAttack) {
        this.initialAttack = initialAttack;
    }

    public int getInitialDefense() {
        return initialDefense;
    }

    public void setInitialDefense(int initialDefense) {
        this.initialDefense = initialDefense;
    }
}
