import {Component} from '@angular/core';
import {ExamEventsComponent} from "../../exam-events/exam-events.component";

@Component({
  selector: 'app-exams-page',
  standalone: true,
  imports: [
    ExamEventsComponent
  ],
  templateUrl: './exams-page.component.html',
  styleUrl: './exams-page.component.css'
})
export class ExamsPageComponent {

}
