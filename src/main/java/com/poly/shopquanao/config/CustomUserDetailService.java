package com.poly.shopquanao.config;

import com.poly.shopquanao.entity.NhanVien;
import com.poly.shopquanao.repository.KhachHangRepository;
import com.poly.shopquanao.repository.NhanVienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final NhanVienRepository nhanVienRepo;
    private final KhachHangRepository khachHangRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // 1️⃣ Kiểm tra nhân viên
        var nvOpt = nhanVienRepo.findByTenDangNhap(username);
        if (nvOpt.isPresent()) {
            var nv = nvOpt.get();
            return new User(
                    nv.getTenDangNhap(),
                    nv.getMatKhau(),
                    List.of(new SimpleGrantedAuthority(
                            "ROLE_" + nv.getVaiTro().getTenVaiTro()
                    ))
            );
        }

        // 2️⃣ Kiểm tra khách hàng (KHÔNG CÓ ROLE)
        var khOpt = khachHangRepo.findByTenDangNhap(username);
        if (khOpt.isPresent()) {
            var kh = khOpt.get();
            return new User(
                    kh.getTenDangNhap(),
                    kh.getMatKhau(),
                    List.of()   // 👈 KHÔNG GÁN ROLE NỮA
            );
        }

        throw new UsernameNotFoundException("Không tìm thấy user");
    }
}