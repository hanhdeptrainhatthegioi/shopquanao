package com.poly.shopquanao.controller.admin;

import com.poly.shopquanao.repository.SanPhamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/san-pham")
@RequiredArgsConstructor
public class ProductController {

    private final SanPhamRepository sanPhamRepo;

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model) {

        model.addAttribute("list", sanPhamRepo.findAll());

        return "admin/product/list";
    }
}