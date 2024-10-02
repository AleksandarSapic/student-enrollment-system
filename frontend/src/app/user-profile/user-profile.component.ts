import {Component, input} from '@angular/core';
import {User} from "../models/User";

@Component({
  selector: 'app-user-profile',
  standalone: true,
  imports: [],
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  user = input.required<User>();

  getFullName(): string {
    return `${this.user().firstName} (${this.user().middleName}) ${this.user().lastName}`;
  }
}
