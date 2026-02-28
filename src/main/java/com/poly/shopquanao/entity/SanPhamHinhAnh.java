package com.poly.shopquanao.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "san_pham_hinh_anh")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SanPhamHinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "san_pham_chi_tiet_id", nullable = false)
    private SanPhamChiTiet sanPhamChiTiet;

    @Column(name = "duong_dan", nullable = false)
    private String duongDan;

    @Column(name = "anh_chinh", nullable = false)
    private Boolean anhChinh;
}
