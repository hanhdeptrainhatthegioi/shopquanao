package com.poly.shopquanao.entity;

import com.poly.shopquanao.enums.HinhThucThanhToan;
import com.poly.shopquanao.enums.LoaiDonHang;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "don_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ma_don_hang", unique = true, nullable = false)
    private String maDonHang;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    // ONLINE có thể null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "khuyen_mai_id")
    private KhuyenMai khuyenMai;

    @Column(name = "dia_chi_luu", nullable = false)
    private String diaChiLuu;

    @Column(name = "sdt_luu", nullable = false)
    private String sdtLuu;

    @Column(name = "tong_san_pham", nullable = false)
    private Integer tongSanPham;

    @Column(name = "tong_tien_goc", nullable = false)
    private Double tongTienGoc;

    @Column(name = "giam_gia", nullable = false)
    private Double giamGia;

    @Column(name = "phi_ship", nullable = false)
    private Double phiShip;

    @Column(name = "tong_thanh_toan", nullable = false)
    private Double tongThanhToan;

    @Enumerated(EnumType.STRING)
    @Column(name = "hinh_thuc_thanh_toan", nullable = false)
    private HinhThucThanhToan hinhThucThanhToan;

    @Enumerated(EnumType.STRING)
    @Column(name = "loai_don_hang", nullable = false)
    private LoaiDonHang loaiDonHang;

    @Column(name = "trang_thai", nullable = false)
    private Integer trangThai;

    @Column(name = "ngay_tao", nullable = false)
    private LocalDateTime ngayTao;
}