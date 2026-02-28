package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "san_pham")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_san_pham", unique = true, nullable = false)
    private String maSanPham;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thuong_hieu_id")
    private ThuongHieu thuongHieu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "danh_muc_id")
    private DanhMuc danhMuc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_lieu_id")
    private ChatLieu chatLieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
    private List<SanPhamChiTiet> chiTietList;
}