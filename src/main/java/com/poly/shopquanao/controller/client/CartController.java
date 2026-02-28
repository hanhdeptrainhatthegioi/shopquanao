package com.poly.shopquanao.controller.client;

import com.poly.shopquanao.service.CartService;
import com.poly.shopquanao.repository.KhachHangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final KhachHangRepository khachHangRepo;

    @PostMapping("/add")
    public String addToCart(@RequestParam("spId") Integer spId,
                            @RequestParam("quantity") Integer quantity,
                            Authentication authentication) {

        String username = authentication.getName();

        var kh = khachHangRepo.findByTenDangNhap(username)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy khách hàng"));

        cartService.addToCart(kh.getId(), spId, quantity);

        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model, Authentication authentication) {

        String username = authentication.getName();

        var kh = khachHangRepo.findByTenDangNhap(username)
                .orElseThrow(() ->
                        new RuntimeException("Không tìm thấy khách hàng"));

        var cartItems = cartService.getCartByKhachHang(kh.getId());

        double total = cartItems.stream()
                .mapToDouble(i -> i.getSoLuong() *
                        i.getSanPhamChiTiet().getGiaBan())
                .sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", total);

        return "client/cart";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam("id") Integer id) {
        cartService.removeItem(id);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") Integer id,
                         @RequestParam("quantity") Integer quantity) {

        cartService.updateQuantity(id, quantity);
        return "redirect:/cart";
    }
}