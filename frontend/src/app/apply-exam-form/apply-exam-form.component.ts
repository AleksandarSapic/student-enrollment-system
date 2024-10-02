import {Component, EventEmitter, input, Output} from '@angular/core';
import {ExamEvent} from "../models/ExamEvent";
import {User} from "../models/User";
import {HttpClient} from "@angular/common/http";
import {catchError, of, tap} from "rxjs";

@Component({
  selector: 'app-apply-exam-form',
  standalone: true,
  imports: [],
  templateUrl: './apply-exam-form.component.html',
  styleUrl: './apply-exam-form.component.css'
})
export class ApplyExamFormComponent {
  event = input.required<ExamEvent>();
  @Output() close = new EventEmitter<void>();

  userId = 1;
  user?: User;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.fetchUserInfo();
  }

  fetchUserInfo(): void {
    const apiUrl = `http://localhost:8080/api/users/${this.userId}`;
    this.http.get<any>(apiUrl)
      .pipe(
        tap((data) => {
          this.user = data;
        }),
        catchError((error) => {
          console.error('Error fetching user info:', error.error);
          return of(null);
        })
      )
      .subscribe();
  }

  applyForExam(): void {
    if (!this.event || !this.user) {
      console.error('Event or user information is missing');
      return;
    }

    const apiUrl = 'http://localhost:8080/api/application-forms';
    const payload = {
      examEventId: this.event().id,
      userId: this.user.id
    };

    this.http.post(apiUrl, payload)
      .pipe(
        tap((response) => {
          console.log('Application submitted successfully:', response);
          this.closeDialog();
        }),
        catchError((error) => {
          console.error('Error submitting application:', error);
          return of(null);
        })
      )
      .subscribe();
  }

  getFullName(): string {
    return `${this.user?.firstName} (${this.user?.middleName}) ${this.user?.lastName}`;
  }

  closeDialog(): void {
    this.close.emit();
  }
}
