package com.cognizant.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.price > :minPrice")
    List<Product> findByCategoryAndMinimumPriceHql(@Param("category") String category, @Param("minPrice") Double minPrice);

    @Query(value = "SELECT * FROM product p WHERE p.name LIKE %:keyword% ORDER BY p.price DESC", nativeQuery = true)
    List<Product> searchByNameNativeQuery(@Param("keyword") String keyword);
    
    @Query("SELECT AVG(p.price) FROM Product p WHERE p.category = ?1")
    Double calculateAveragePriceByCategoryHql(String category);
}
