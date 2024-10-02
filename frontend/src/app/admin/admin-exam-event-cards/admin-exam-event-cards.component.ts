import {Component} from '@angular/core';
import {ExamEvent} from "../../models/ExamEvent";
import {catchError, of, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-admin-exam-event-cards',
  standalone: true,
  imports: [
    FaIconComponent
  ],
  templateUrl: './admin-exam-event-cards.component.html',
  styleUrl: './admin-exam-event-cards.component.css'
})
export class AdminExamEventCardsComponent {
  examEvents: ExamEvent[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getExamEvents();
  }

  getExamEvents(): void {
    this.http.get<ExamEvent[]>('http://localhost:8080/api/exam-events')
      .pipe(
        tap((data: ExamEvent[]) => {
          this.examEvents = data;
        }),
        catchError((error) => {
          console.error('Error fetching exam events:', error);
          return of([]);
        })
      )
      .subscribe();
  }
}
