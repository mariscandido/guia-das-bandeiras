package com.guia.bandeiras.controller;

import com.guia.bandeiras.dto.CommentRequest;
import com.guia.bandeiras.dto.CommentResponse;
import com.guia.bandeiras.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
@CrossOrigin(origins = "${cors.allowed-origins}")
public class CommentController {
    
    private final CommentService commentService;
    
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest request) {
        log.info("POST /api/comments");
        CommentResponse response = commentService.createComment(request);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/result/{resultId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByResultId(@PathVariable String resultId) {
        log.info("GET /api/comments/result/{}", resultId);
        return ResponseEntity.ok(commentService.getCommentsByResultId(resultId));
    }
    
    @PostMapping("/{commentId}/helpful")
    public ResponseEntity<Void> markAsHelpful(@PathVariable Long commentId) {
        log.info("POST /api/comments/{}/helpful", commentId);
        commentService.markAsHelpful(commentId);
        return ResponseEntity.ok().build();
    }
}
