package com.investree.demo.view;

import com.investree.demo.model.Transaksi;
import java.util.Map;

public interface TransaksiService {
    Map save(Long idPeminjam, Long idMeminjam, Transaksi transaksi);

    Map updateStatus(Transaksi transaksi);
}
