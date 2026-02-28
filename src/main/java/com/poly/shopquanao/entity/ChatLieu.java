package com.poly.shopquanao.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "chat_lieu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_chat_lieu", unique = true, nullable = false)
    private String tenChatLieu;

    @Column(name = "trang_thai")
    private Boolean trangThai;
}