import {Component} from '@angular/core';

@Component({
  selector: 'app-notifications',
  standalone: true,
  imports: [],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css'
})
export class NotificationsComponent {
  notifications = [
    {
      message: 'Vaš ispit iz Matematike je zakazan za 1. septembar 2024.',
      type: 'is-info'
    },
    {
      message: 'Prijava za prvi termin probnog prijemnog ispita iz Matematike se zatvara 30. avgusta 2024.',
      type: 'is-warning'
    }
  ];
}
