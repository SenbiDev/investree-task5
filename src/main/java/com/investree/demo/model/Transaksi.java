package com.investree.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_peminjam")
    private Users idPeminjam;

    @ManyToOne
    @JoinColumn(name = "id_meminjam")
    private Users idMeminjam;

    @Column(name = "tenor")
    private Integer tenor;

    @Column(name = "total_pinjaman")
    private Double totalPinjaman;

    @Column(name = "bunga_persen")
    private Double bungaPersen;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "idTransaksi")
    @JsonIgnore
    private List<PaymentHistory> paymentHistory;
}
