package com.lucasmurilo.backend.services;

import com.lucasmurilo.backend.entities.Sale;
import com.lucasmurilo.backend.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> findAll(String minDate, String maxDate, Pageable page){

        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        LocalDate min = minDate.equals("")? today.minusDays(365):  LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        return saleRepository.findAll(min, max, page);
    }


}
