package com.poly.shopquanao.service;

import com.poly.shopquanao.entity.GioHangChiTiet;

import java.util.List;

public interface CartService {

    void addToCart(Integer khachHangId,
                   Integer sanPhamId,
                   Integer soLuong);
    List<GioHangChiTiet> getCartByKhachHang(Integer khId);
    void removeItem(Integer cartItemId);

    void updateQuantity(Integer cartItemId, Integer quantity);
}