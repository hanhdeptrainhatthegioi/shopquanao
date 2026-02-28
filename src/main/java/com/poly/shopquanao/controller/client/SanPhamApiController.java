package com.poly.shopquanao.controller.client;

import com.poly.shopquanao.entity.SanPhamChiTiet;
import com.poly.shopquanao.repository.client.SanPhamChiTietRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SanPhamApiController {

    private final SanPhamChiTietRepository repo;

    @GetMapping("/chitiet/{id}")
    public SanPhamChiTiet getChiTiet(@PathVariable Integer id) {
        return repo.findById(id).orElseThrow();
    }
}