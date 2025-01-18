package com.example.cheapesttransferroute.services;

import com.example.cheapesttransferroute.models.CTRRequest;
import com.example.cheapesttransferroute.models.CTRResponse;
import com.example.cheapesttransferroute.models.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CTRService {
    public CTRResponse findCheapestTransferRoute(CTRRequest request){
        if (request.getAvailableTransfers() == null){
            return new CTRResponse(new ArrayList<Transfer>(),0, 0);
        }
        int maxWeight = request.getMaxWeight();
        List<Transfer> availableTransfers = request.getAvailableTransfers();
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

        return getResponse(dp, availableTransfers, maxWeight);
    }

    private CTRResponse getResponse(int[][] dp, List<Transfer> availableTransfers, int maxWeight){
        int totalWeight = 0;
        List<Transfer> selected = new ArrayList<>();

        int weight = maxWeight;

        for(int i = availableTransfers.size(); i > 0; i--){
            if(dp[i][weight] <= 0) break;

            if(dp[i][weight] != dp[i-1][weight]){
                Transfer t = availableTransfers.get(i-1);
                totalWeight += t.getWeight();
                weight -= t.getWeight();
                selected.add(t);
            }
        }

        CTRResponse res = new CTRResponse();
        res.setTotalCost(dp[availableTransfers.size()][maxWeight]);
        res.setTotalWeight(totalWeight);
        res.setSelectedTransfers(selected);
        return res;
    }
}
