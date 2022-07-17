package com.shablinskaya.testproject.repository;

import com.shablinskaya.testproject.repository.model.CryptoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CryptoRepository extends JpaRepository<CryptoEntity, Long> {
    Optional<CryptoEntity> findBySymbol(String symbol);

    @Modifying
    @Query("update CryptoEntity c set c.price = :price where c.id = :id")
    void update(@Param("id") Long id,
                @Param("price") Float price);

}
