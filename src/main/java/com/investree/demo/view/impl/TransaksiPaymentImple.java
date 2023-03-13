package com.investree.demo.view.impl;

import com.investree.demo.model.Transaksi;
import com.investree.demo.model.Users;
import com.investree.demo.repository.TransaksiRepository;
import com.investree.demo.repository.UsersRepository;
import com.investree.demo.view.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class TransaksiPaymentImple implements TransaksiService {

    @Autowired
    TransaksiRepository transaksiRepository;

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Map<String, Object> save(Long idPeminjam, Long idMeminjam, Transaksi transaksi) {
        Map<String, Object> map = new HashMap<>();
        Optional<Users> userPeminjam = usersRepository.findById(idPeminjam);
        Optional<Users> userMeminjam = usersRepository.findById(idMeminjam);

        if (idPeminjam.equals(idMeminjam)) {
            map.put("code", 401);
            map.put("status", String.format("id peminjam %s tidak boleh sama dengan id meminjam %s", idPeminjam, idMeminjam));
            return map;
        }

        if (!userPeminjam.isPresent()) {
            map.put("code", 404);
            map.put("status", String.format("id peminjam %s tidak ditemukan", idPeminjam));
            return map;
        }

        if (!userMeminjam.isPresent()) {
            map.put("code", 404);
            map.put("status", String.format("id meminjam %s tidak ditemukan", idMeminjam));
            return map;
        }

        try {
            transaksi.setIdPeminjam(userPeminjam.get());
            transaksi.setIdMeminjam(userMeminjam.get());
            Transaksi save = transaksiRepository.save(transaksi);
            map.put("data", save);
            map.put("code", 200);
            map.put("status", "sukses");
            return map;
        } catch(Exception e) {
            map.put("code", 500);
            map.put("status", "gagal");
            return map;
        }
    }

    @Override
    public Map<String, Object> updateStatus(Transaksi transaksi) {
        Map<String, Object> map = new HashMap<>();

        try {
            Transaksi update = transaksiRepository.getByID(transaksi.getId());

            if(update == null ){
                map.put("code", "404");
                map.put("status", "Data id tidak ditemukan");
                return map;
            }

            update.setStatus(transaksi.getStatus());
            Transaksi doSave = transaksiRepository.save(update);

            map.put("data", doSave);
            map.put("code", 200);
            map.put("status", "sukses");
            return map;
        } catch(Exception e) {
            map.put("code", 500);
            map.put("status", "gagal");
            return map;
        }
    }
}