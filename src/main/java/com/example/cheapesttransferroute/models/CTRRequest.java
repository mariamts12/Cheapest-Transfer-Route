package com.example.cheapesttransferroute.models;

import java.util.List;

public class CTRRequest {
    private int maxWeight;
    private List<Transfer> availableTransfers;

    public CTRRequest() {}

    public CTRRequest(int maxWeight, List<Transfer> availableTransfers) {
        this.maxWeight = maxWeight;
        this.availableTransfers = availableTransfers;
    }
    public int getMaxWeight() {
        return maxWeight;
    }

    public List<Transfer> getAvailableTransfers() {
        return availableTransfers;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setAvailableTransfers(List<Transfer> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }
}
