import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface Comment {
  id: number;
  username: string;
  resultId: string;
  content: string;
  helpfulCount: number;
  createdAt: string;
}

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  @Input() resultId: string = '';
  
  comments: Comment[] = [];
  newComment: string = '';
  loading = false;
  showComments = false;

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    if (this.resultId) {
      this.loadComments();
    }
  }

  loadComments(): void {
    this.loading = true;
    this.http.get<Comment[]>(`/api/comments/result/${this.resultId}`).subscribe({
      next: (data) => {
        this.comments = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error loading comments:', err);
        this.loading = false;
      }
    });
  }

  toggleComments(): void {
    this.showComments = !this.showComments;
    if (this.showComments && this.comments.length === 0) {
      this.loadComments();
    }
  }

  submitComment(): void {
    if (!this.newComment.trim()) return;

    this.http.post('/api/comments', {
      resultId: this.resultId,
      content: this.newComment
    }).subscribe({
      next: (comment: any) => {
        this.comments.unshift(comment);
        this.newComment = '';
      },
      error: (err) => {
        console.error('Error submitting comment:', err);
      }
    });
  }

  markAsHelpful(commentId: number): void {
    this.http.post(`/api/comments/${commentId}/helpful`, {}).subscribe({
      next: () => {
        const comment = this.comments.find(c => c.id === commentId);
        if (comment) {
          comment.helpfulCount++;
        }
      },
      error: (err) => {
        console.error('Error marking comment as helpful:', err);
      }
    });
  }
}
