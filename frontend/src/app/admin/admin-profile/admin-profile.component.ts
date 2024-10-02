import {Component} from '@angular/core';
import {Admin} from '../../models/Admin';

@Component({
  selector: 'app-admin-profile',
  standalone: true,
  imports: [],
  templateUrl: './admin-profile.component.html',
  styleUrl: './admin-profile.component.css'
})
export class AdminProfileComponent {
  admin?: Admin = {
    firstName: 'Admin',
    lastName: 'Admin',
    email: 'admin@email.com',
  };

  getFullName(): string {
    return `${this.admin?.firstName} ${this.admin?.lastName}`;
  }
}
