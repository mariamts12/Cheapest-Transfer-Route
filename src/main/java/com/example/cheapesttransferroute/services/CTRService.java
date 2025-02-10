package com.example.cheapesttransferroute.services;

import com.example.cheapesttransferroute.models.CTRRequest;
import com.example.cheapesttransferroute.models.CTRResponse;
import com.example.cheapesttransferroute.models.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CTRService {

    private List<Transfer> saved = new ArrayList<>();

    public CTRResponse findCheapestTransferRoute(CTRRequest request){
        if (request.getAvailableTransfers() == null){
            return new CTRResponse(new ArrayList<Transfer>(),0, 0);
        }
        int maxWeight = request.getMaxWeight();
        List<Transfer> availableTransfers = request.getAvailableTransfers();

        int [][] dp = knapsack(availableTransfers, maxWeight);

        return getResponse(dp, availableTransfers, maxWeight);
    }

    private int[][] knapsack(List<Transfer> availableTransfers, Integer maxWeight){
        int size = availableTransfers.size();
        int[][] dp = new int[size + 1][maxWeight + 1];

        for(int i = 0; i <= size; i++) {
            for(int w = 0; w <= maxWeight; w++) {
                if(i == 0 || w == 0) {
                    dp[i][w] = 0;
                }else if (availableTransfers.get(i-1).getWeight() <= w){
                    Transfer t = availableTransfers.get(i-1);
                    dp[i][w] = Math.max(t.getCost() + dp[i-1][w-t.getWeight()], dp[i-1][w]);
                }else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        return dp;
    }

    private List<Transfer> getSelectedTransfers(int[][] dp, List<Transfer> availableTransfers, int maxWeight){
        List<Transfer> selected = new ArrayList<>();

        int weight = maxWeight;

        for(int i = availableTransfers.size(); i > 0; i--){
            if(dp[i][weight] <= 0) break;

            Transfer t = availableTransfers.get(i-1);

            if(dp[i][weight] != dp[i-1][weight]){
                weight -= t.getWeight();
                selected.add(t);
            } else {
                saved.add(t);
            }
        }
        return selected;
    }

    private CTRResponse getResponse(int[][] dp, List<Transfer> availableTransfers, int maxWeight){
        List<Transfer> selected = getSelectedTransfers(dp, availableTransfers, maxWeight);
        int totalWeight = 0;
        int totalCost2 = 0;

        for(Transfer t: selected){
            totalWeight += t.getWeight();
        }

        for(Transfer t: availableTransfers){
            if (!selected.contains(t)){
                saved.add(t);
            }
        }

        if (maxWeight > totalWeight){
            int [][] newDP = knapsack(saved, maxWeight - totalWeight);
            List<Transfer> additional = getSelectedTransfers(newDP, saved, maxWeight - totalWeight);
            totalCost2 = newDP[saved.size()][maxWeight - totalWeight];

            for(Transfer t: additional){
                selected.add(t);
                saved.remove(t);
                totalWeight += t.getWeight();
            }
        }

        CTRResponse res = new CTRResponse();
        res.setTotalCost(dp[availableTransfers.size()][maxWeight] + totalCost2);
        res.setTotalWeight(totalWeight);
        res.setSelectedTransfers(selected);
        return res;
    }
}
