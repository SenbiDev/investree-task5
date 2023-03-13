package com.investree.demo.controller;

import com.investree.demo.model.Transaksi;
import com.investree.demo.repository.TransaksiRepository;
import com.investree.demo.view.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/transaksi")
public class TransaksiController {

    @Autowired
    TransaksiService transaksiService;

    @Autowired
    TransaksiRepository transaksiRepository;

    @PostMapping("/{idPeminjam}/with/{idMeminjam}")
    ResponseEntity<Map> save(
            @PathVariable("idPeminjam") Long idPeminjam,
            @PathVariable("idMeminjam") Long idMeminjam,
            @RequestBody Transaksi objModel
    ) {
        Map save = transaksiService.save(idPeminjam, idMeminjam, objModel);
        return new ResponseEntity<Map>(save, HttpStatus.OK);
    }

    @PutMapping("")
    ResponseEntity<Map> update(@RequestBody Transaksi objModel) {
        Map update = transaksiService.updateStatus(objModel);
        return new ResponseEntity<Map>(update, HttpStatus.OK);
    }

    @GetMapping("/list")
    ResponseEntity<Page<Transaksi>> list(
            @RequestParam() Integer page,
            @RequestParam() Integer size,
            @RequestParam(required = false) String status
    ) {
        Pageable show_data = PageRequest.of(page, size);
        Page<Transaksi> list;

        if (status != null) {
            list = transaksiRepository.getByStatus(status, show_data);
        } else {
            list = transaksiRepository.getAllData(show_data);
        }

        return  new ResponseEntity(list, new HttpHeaders(), HttpStatus.OK);
    }
}
