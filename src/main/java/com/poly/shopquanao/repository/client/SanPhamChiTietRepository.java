package com.poly.shopquanao.repository.client;

import com.poly.shopquanao.entity.SanPhamChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository
        extends JpaRepository<SanPhamChiTiet, Integer> {
}
