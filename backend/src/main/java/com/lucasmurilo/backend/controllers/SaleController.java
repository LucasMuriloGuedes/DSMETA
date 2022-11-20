package com.lucasmurilo.backend.controllers;

import com.lucasmurilo.backend.entities.Sale;
import com.lucasmurilo.backend.services.SaleService;
import com.lucasmurilo.backend.services.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SmsService smsService;

    @GetMapping
    public ResponseEntity<Page<Sale>> findAll(@RequestParam(value = "minDate", defaultValue = "") String minDate,
                                              @RequestParam(value = "maxDate", defaultValue = "") String maxDate, Pageable page){
        Page<Sale> sales = saleService.findAll(minDate, maxDate, page);
        return ResponseEntity.ok().body(sales);
    }

    @GetMapping("/{id}/notification")
    public ResponseEntity<Void> notifySms(@PathVariable Long id){
        smsService.sendSms(id);
        return ResponseEntity.noContent().build();
    }

}
