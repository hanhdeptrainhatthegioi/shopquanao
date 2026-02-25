package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "gio_hang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    private KhachHang khachHang;

    @Column(name = "ngay_tao")
    private java.time.LocalDateTime ngayTao;
}
