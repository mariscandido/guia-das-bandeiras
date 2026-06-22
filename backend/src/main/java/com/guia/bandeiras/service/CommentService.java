package com.guia.bandeiras.service;

import com.guia.bandeiras.dto.CommentRequest;
import com.guia.bandeiras.dto.CommentResponse;
import com.guia.bandeiras.entity.Comment;
import com.guia.bandeiras.entity.User;
import com.guia.bandeiras.repository.CommentRepository;
import com.guia.bandeiras.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    
    public CommentResponse createComment(CommentRequest request) {
        log.info("Creating comment for result: {}", request.getResultId());
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setResultId(request.getResultId());
        comment.setContent(request.getContent());
        comment.setHelpfulCount(0);
        comment.setHelpful(false);
        
        comment = commentRepository.save(comment);
        
        // Award points for contributing
        user.setPoints(user.getPoints() + 5);
        userRepository.save(user);
        
        log.info("Comment created successfully with ID: {}", comment.getId());
        
        return mapToResponse(comment);
    }
    
    public List<CommentResponse> getCommentsByResultId(String resultId) {
        log.info("Fetching comments for result: {}", resultId);
        return commentRepository.findByResultIdOrderByCreatedAtDesc(resultId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public void markAsHelpful(Long commentId) {
        log.info("Marking comment {} as helpful", commentId);
        
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        
        comment.setHelpfulCount(comment.getHelpfulCount() + 1);
        commentRepository.save(comment);
        
        // Award points to comment author
        User author = comment.getUser();
        author.setPoints(author.getPoints() + 2);
        userRepository.save(author);
    }
    
    private CommentResponse mapToResponse(Comment comment) {
        return new CommentResponse(
            comment.getId(),
            comment.getUser().getUsername(),
            comment.getResultId(),
            comment.getContent(),
            comment.getHelpfulCount(),
            comment.getCreatedAt()
        );
    }
}
