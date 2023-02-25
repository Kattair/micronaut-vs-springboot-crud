package com.aardwark.coc.four.spring_vs_micronaut.domain;

import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String isbn;
    @NotBlank
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @DateCreated
    private Instant createdAt;
    @DateUpdated
    private Instant updatedAt;
}
