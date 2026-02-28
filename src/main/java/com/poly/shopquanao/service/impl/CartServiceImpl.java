package com.poly.shopquanao.service.impl;

import com.poly.shopquanao.entity.GioHang;
import com.poly.shopquanao.entity.GioHangChiTiet;
import com.poly.shopquanao.entity.KhachHang;
import com.poly.shopquanao.repository.client.GioHangChiTietRepository;
import com.poly.shopquanao.repository.client.GioHangRepository;
import com.poly.shopquanao.repository.client.SanPhamChiTietRepository;
import com.poly.shopquanao.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final GioHangRepository gioHangRepo;
    private final GioHangChiTietRepository chiTietRepo;
    private final SanPhamChiTietRepository spRepo;

    @Override
    public void addToCart(Integer khachHangId,
                          Integer sanPhamChiTietId,
                          Integer soLuong) {

        if (soLuong <= 0) {
            throw new RuntimeException("Số lượng không hợp lệ");
        }

        GioHang gioHang =
                gioHangRepo.findByKhachHang_Id(khachHangId);

        if (gioHang == null) {
            gioHang = GioHang.builder()
                    .khachHang(KhachHang.builder()
                            .id(khachHangId)
                            .build())
                    .ngayTao(LocalDateTime.now())
                    .build();
            gioHangRepo.save(gioHang);
        }

        var sanPham = spRepo.findById(sanPhamChiTietId)
                .orElseThrow(() ->
                        new RuntimeException("Sản phẩm không tồn tại"));

        GioHangChiTiet chiTiet =
                chiTietRepo.findByGioHang_IdAndSanPhamChiTiet_Id(
                        gioHang.getId(), sanPhamChiTietId);

        int tongSoLuong =
                (chiTiet != null ? chiTiet.getSoLuong() : 0) + soLuong;

        if (sanPham.getSoLuong() < tongSoLuong) {
            throw new RuntimeException("Không đủ số lượng trong kho");
        }

        if (chiTiet != null) {
            chiTiet.setSoLuong(tongSoLuong);
        } else {
            chiTiet = GioHangChiTiet.builder()
                    .gioHang(gioHang)
                    .sanPhamChiTiet(sanPham)
                    .soLuong(soLuong)
                    .build();
        }

        chiTietRepo.save(chiTiet);
    }
    @Override
    public List<GioHangChiTiet> getCartByKhachHang(Integer khId) {

        GioHang gioHang = gioHangRepo.findByKhachHang_Id(khId);

        if (gioHang == null) {
            return List.of(); // trả về danh sách rỗng
        }

        return chiTietRepo.findByGioHang_Id(gioHang.getId());
    }
    @Override
    public void removeItem(Integer cartItemId) {
        chiTietRepo.deleteById(cartItemId);
    }

    @Override
    public void updateQuantity(Integer cartItemId, Integer quantity) {

        GioHangChiTiet item = chiTietRepo.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        if (quantity <= 0) {
            chiTietRepo.delete(item);
            return;
        }

        if (quantity > item.getSanPhamChiTiet().getSoLuong()) {
            throw new RuntimeException("Vượt quá số lượng tồn kho");
        }

        item.setSoLuong(quantity);
        chiTietRepo.save(item);
    }
}