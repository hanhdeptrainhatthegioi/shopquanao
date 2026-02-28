package com.poly.shopquanao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(
        name = "san_pham_chi_tiet",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"san_pham_id", "kich_co_id", "mau_sac_id", "kieu_ao_id"}
        )
)
@JsonIgnoreProperties({"sanPham"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamChiTiet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_san_pham_chi_tiet", unique = true, nullable = false)
    private String maSanPhamChiTiet;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_id", nullable = false)
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kich_co_id")
    private KichCo kichCo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "mau_sac_id", nullable = false)
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kieu_ao_id")
    private KieuAo kieuAo;

    @Column(name = "gia_nhap", nullable = false)
    private Double giaNhap;

    @Column(name = "gia_ban", nullable = false)
    private Double giaBan;

    @Column(name = "so_luong", nullable = false)
    private Integer soLuong;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai;

    // 🔥 Mỗi màu có nhiều ảnh
    @OneToMany(mappedBy = "sanPhamChiTiet", fetch = FetchType.LAZY)
    private List<SanPhamHinhAnh> hinhAnhList;
}