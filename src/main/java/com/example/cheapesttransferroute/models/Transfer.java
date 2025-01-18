package com.example.cheapesttransferroute;

public class Transfer {
    private int weight;
    private int cost;

    public Transfer() {}

    public Transfer(int weight, int cost){
        this.weight = weight;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getWeight() {
        return weight;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

