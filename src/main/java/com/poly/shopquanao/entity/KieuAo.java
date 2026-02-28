package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kieu_ao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KieuAo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_kieu_ao", unique = true, nullable = false)
    private String tenKieuAo;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}