package com.shablinskaya.testproject.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "crypto_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CryptoEntity implements Serializable {
    @Id
    @Column
    private Long id;

    @Column
    private String symbol;

    @Column
    private Float price;
}
