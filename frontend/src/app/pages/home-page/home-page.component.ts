import {Component} from '@angular/core';
import {NotificationsComponent} from "../../notifications/notifications.component";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    NotificationsComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

}
