package com.example.cheapesttransferroute.models;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()){
            return false;
        } else {
            return ((Transfer) obj).getCost() == this.cost && ((Transfer) obj).getWeight() == this.weight;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, cost);
    }
}

