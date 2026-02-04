package com.poly.shopquanao.config;

import com.poly.shopquanao.entity.NhanVien;
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

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        NhanVien nv = nhanVienRepo
                .findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user"));

        return new User(
                nv.getTenDangNhap(),
                nv.getMatKhau(),
                List.of(new SimpleGrantedAuthority(
                        "ROLE_" + nv.getVaiTro().getTenVaiTro()
                ))
        );
    }
}

