package com.example.cheapesttransferroute;

import com.example.cheapesttransferroute.models.CTRRequest;
import com.example.cheapesttransferroute.models.CTRResponse;
import com.example.cheapesttransferroute.models.Transfer;
import com.example.cheapesttransferroute.services.CTRService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CTRServiceTest {
    @Test
    void testFindCheapestTransferRouteValidInput() {
        CTRService service = new CTRService();
        List<Transfer> transfers = Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        );
        CTRRequest request = new CTRRequest(15, transfers);

        CTRResponse response = service.findCheapestTransferRoute(request);

        assertNotNull(response);
        assertEquals(30, response.getTotalCost());
        assertEquals(15, response.getTotalWeight());
        assertEquals(2, response.getSelectedTransfers().size());

        Set<Transfer> res = new HashSet<>();
        res.add(new Transfer(5, 10));
        res.add(new Transfer(10, 20));

        for(int i = 0; i < res.size(); i++){
            assertTrue(res.contains(response.getSelectedTransfers().get(i)));
        }
    }

    @Test
    void testFindCheapestTransferRouteEmptyList() {
        CTRService service = new CTRService();

        CTRRequest request = new CTRRequest(15, new ArrayList<>());

        CTRResponse response = service.findCheapestTransferRoute(request);

        assertNotNull(response);
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getSelectedTransfers().isEmpty());
    }

    @Test
    void testFindCheapestTransferRouteInvalidInput() {
        CTRService service = new CTRService();

        CTRRequest request = new CTRRequest(-1, null);

        CTRResponse response = service.findCheapestTransferRoute(request);

        assertNotNull(response);
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getSelectedTransfers().isEmpty());
    }


    @Test
    void testFindCheapestTransferRouteNoValidAnswer() {
        CTRService service = new CTRService();

        List<Transfer> transfers = Arrays.asList(
                new Transfer(20, 10),
                new Transfer(16, 20)
        );
        CTRRequest request = new CTRRequest(15, transfers);

        CTRResponse response = service.findCheapestTransferRoute(request);

        assertNotNull(response);
        assertEquals(0, response.getTotalCost());
        assertEquals(0, response.getTotalWeight());
        assertTrue(response.getSelectedTransfers().isEmpty());
    }
}