package com.example.cheapesttransferroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CTRController {
    private final CTRService service;

    @Autowired
    public CTRController(CTRService service) {
        this.service = service;
    }

    @PostMapping("/api/cheapest-transfer-route")
    public ResponseEntity<CTRResponse> findCheapest(@RequestBody CTRRequest request) {
        if (request.getMaxWeight() <= 0 || request.getAvailableTransfers().size() == 0 || request.getAvailableTransfers() == null) {
            return ResponseEntity.badRequest().build();
        }

        CTRResponse response = service.findCheapestTransferRoute(request);

        return ResponseEntity.ok(response);
    }
}
