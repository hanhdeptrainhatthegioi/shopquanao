package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mau_sac")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MauSac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_mau", unique = true)
    private String tenMau;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}