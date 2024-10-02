import {Component} from '@angular/core';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faPlusCircle} from "@fortawesome/free-solid-svg-icons";
import {CreateEventModalComponent} from "../../admin/create-event-modal/create-event-modal.component";
import {AdminExamEventCardsComponent} from "../../admin/admin-exam-event-cards/admin-exam-event-cards.component";

@Component({
  selector: 'app-admin-dashboard-page',
  standalone: true,
  imports: [
    FaIconComponent,
    CreateEventModalComponent,
    AdminExamEventCardsComponent
  ],
  templateUrl: './admin-dashboard-page.component.html',
  styleUrl: './admin-dashboard-page.component.css'
})
export class AdminDashboardPageComponent {
  isModalOpen = false;

  protected readonly faPlusCircle = faPlusCircle;

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }
}
