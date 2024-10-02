import {Component} from '@angular/core';
import {ApplyExamFormComponent} from "../apply-exam-form/apply-exam-form.component";
import {HttpClient} from "@angular/common/http";
import {catchError, of, tap} from "rxjs";
import {ExamEvent} from "../models/ExamEvent";

@Component({
  selector: 'app-exam-events',
  standalone: true,
  imports: [
    ApplyExamFormComponent
  ],
  templateUrl: './exam-events.component.html',
  styleUrl: './exam-events.component.css'
})
export class ExamEventsComponent {
  selectedEvent?: ExamEvent;
  examEvents?: ExamEvent[];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getExamEvents();
  }

  getExamEvents(): void {
    const apiUrl = 'http://localhost:8080/api/exam-events';

    this.http.get<ExamEvent[]>(apiUrl)
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

  selectEvent(event: any): void {
    this.selectedEvent = event;
  }

  resetSelectedEvent(): void {
    this.selectedEvent = undefined;
  }
}
