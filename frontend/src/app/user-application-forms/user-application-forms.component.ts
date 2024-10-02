import {Component, input} from '@angular/core';
import {ApplicationForm} from "../models/ApplicationForm";

@Component({
  selector: 'app-user-application-forms',
  standalone: true,
  imports: [],
  templateUrl: './user-application-forms.component.html',
  styleUrl: './user-application-forms.component.css'
})
export class UserApplicationFormsComponent {
  applications = input.required<ApplicationForm[]>();
}
