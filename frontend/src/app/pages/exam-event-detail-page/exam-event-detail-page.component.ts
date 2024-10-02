import {Component} from '@angular/core';
import {ExamEvent} from "../../models/ExamEvent";
import {HttpClient} from "@angular/common/http";
import {catchError, of, tap} from "rxjs";
import {ActivatedRoute, RouterLink} from "@angular/router";

@Component({
  selector: 'app-exam-event-detail-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './exam-event-detail-page.component.html',
  styleUrl: './exam-event-detail-page.component.css'
})
export class ExamEventDetailPageComponent {
  eventId?: string;
  examEvent?: ExamEvent;
  eventNotFound: boolean = false;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.eventId = this.activatedRoute.snapshot.params['eventId'];
    this.getExamEventDetails();
  }

  getExamEventDetails(): void {
    this.http.get<ExamEvent>(`http://localhost:8080/api/exam-events/${this.eventId}/application-forms`)
      .pipe(
        tap((data: ExamEvent) => {
          this.examEvent = data;
        }),
        catchError((error) => {
          if (error.status === 400) {
            this.eventNotFound = true;
          }
          console.error('Error fetching exam event details:', error.error);
          return of(null);
        })
      )
      .subscribe();
  }
}
