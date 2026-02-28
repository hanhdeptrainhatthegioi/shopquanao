package com.poly.shopquanao.repository.client;

import com.poly.shopquanao.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangRepository
        extends JpaRepository<GioHang, Integer> {

    GioHang findByKhachHang_Id(Integer khachHangId);
}