import {Component} from '@angular/core';
import {AdminProfileComponent} from "../../admin/admin-profile/admin-profile.component";

@Component({
  selector: 'app-admin-profile-page',
  standalone: true,
  imports: [
    AdminProfileComponent
  ],
  templateUrl: './admin-profile-page.component.html',
  styleUrl: './admin-profile-page.component.css'
})
export class AdminProfilePageComponent {

}
