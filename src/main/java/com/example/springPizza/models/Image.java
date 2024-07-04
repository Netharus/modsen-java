package com.example.springPizza.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", length = 100, nullable = false)
    private String url;

    @Column(name = "delete_hash", length = 40, nullable = false)
    private String deleteHash;
}
