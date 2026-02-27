package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;
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

    @Column(name = "ma_don_hang", unique = true)
    private String maDonHang;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "khuyen_mai_id")
    private KhuyenMai khuyenMai;

    @Column(name = "dia_chi_luu")
    private String diaChiLuu;

    @Column(name = "sdt_luu")
    private String sdtLuu;

    @Column(name = "tong_san_pham")
    private Integer tongSanPham;

    @Column(name = "tong_tien_goc")
    private Double tongTienGoc;

    @Column(name = "giam_gia")
    private Double giamGia;

    @Column(name = "phi_ship")
    private Double phiShip;

    @Column(name = "tong_thanh_toan")
    private Double tongThanhToan;

    @Column(name = "hinh_thuc_thanh_toan")
    private Integer hinhThucThanhToan;

    @Column(name = "loai_don_hang")
    private Boolean loaiDonHang;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "ngay_tao")
    private java.time.LocalDateTime ngayTao;
}