package com.poly.shopquanao.controller.client;


import com.poly.shopquanao.repository.KhachHangRepository;
import com.poly.shopquanao.repository.client.SanPhamChiTietRepository;
import com.poly.shopquanao.repository.client.SanPhamRepository;
import com.poly.shopquanao.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop")
@RequiredArgsConstructor
public class ShopController {

    private final SanPhamRepository sanPhamRepo;
    private final CartService cartService; // đổi sang CartService
    private final KhachHangRepository khachHangRepo;

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("list", sanPhamRepo.findAllWithChiTiet());
        return "client/product";
    }
}