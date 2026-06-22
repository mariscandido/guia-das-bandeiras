package com.guia.bandeiras.repository;

import com.guia.bandeiras.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByResultIdOrderByCreatedAtDesc(String resultId);
    
    List<Comment> findByUser_UsernameOrderByCreatedAtDesc(String username);
}
