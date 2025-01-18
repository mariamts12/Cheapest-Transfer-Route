package com.example.cheapesttransferroute.models;

import java.util.List;

public class CTRResponse {
    private List<Transfer> selectedTransfers;
    private int totalCost;
    private int totalWeight;

    public CTRResponse() {}
    public CTRResponse(List<Transfer> selectedTransfers, int totalCost, int totalWeight){
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }
    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setSelectedTransfers(List<Transfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
    }
}
