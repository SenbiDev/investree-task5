package com.investree.demo.repository;

import com.investree.demo.model.Transaksi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {

    @Query("select t from Transaksi t WHERE t.id = :id")
    Transaksi getByID(@Param("id") Long id);

    @Query("select t from Transaksi t WHERE LOWER(t.status) LIKE LOWER(CONCAT(:status))")
    Page<Transaksi> getByStatus(String status, Pageable pageable);

    @Query("select t from Transaksi t")
    Page<Transaksi> getAllData(Pageable pageable);
}
