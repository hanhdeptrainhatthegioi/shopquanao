package com.poly.shopquanao.repository.client;

import com.poly.shopquanao.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Integer> {

    // 🔥 Load full sản phẩm + chi tiết + màu + size + kiểu áo
    @Query("""
    select distinct sp from SanPham sp
    left join fetch sp.chiTietList ct
    left join fetch ct.mauSac
    left join fetch ct.kichCo
    left join fetch ct.kieuAo
""")
    List<SanPham> findAllWithChiTiet();

}