package com.guia.bandeiras.repository;

import com.guia.bandeiras.entity.SearchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {
    
    List<SearchHistory> findByUser_UsernameOrderBySearchedAtDesc(String username);
    
    @Query("SELECT sh FROM SearchHistory sh WHERE sh.searchedAt >= :startDate ORDER BY sh.searchedAt DESC")
    List<SearchHistory> findRecentSearches(@Param("startDate") LocalDateTime startDate);
    
    @Query("SELECT COUNT(sh) FROM SearchHistory sh WHERE sh.query = :query")
    Long countByQuery(@Param("query") String query);
    
    @Query("SELECT sh.cardBrand, COUNT(sh) FROM SearchHistory sh GROUP BY sh.cardBrand")
    List<Object[]> countByCardBrand();
}
