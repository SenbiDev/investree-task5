package com.investree.demo.model;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "payment_history")
public class PaymentHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_transaksi")
    private Transaksi idTransaksi;

    @Column(name = "pembayaran_ke")
    private Integer pembayaranKe;

    @Column(name = "jumlah", columnDefinition = "DOUBLE PRECISION")
    private Double jumlah;

    @Column(name = "bukti_pembayaran")
    private String buktiPembayaran;
}
