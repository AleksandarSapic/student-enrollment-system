import {Component, EventEmitter, Output} from '@angular/core';
import {ExamEventType} from "../../models/ExamEventType";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, of, tap} from "rxjs";
import {FormsModule, NgForm} from "@angular/forms";
import {NgClass} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faCheck, faExclamationTriangle} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-create-event-modal',
  standalone: true,
  imports: [
    FormsModule,
    NgClass,
    FaIconComponent
  ],
  templateUrl: './create-event-modal.component.html',
  styleUrl: './create-event-modal.component.css'
})
export class CreateEventModalComponent {
  @Output() close = new EventEmitter<void>();
  examEventTypes?: ExamEventType[];
  currentDate!: string;
  minPocetakPrijave!: string;
  minKrajPrijave!: string;

  createdById: number = 1;  // Assuming the creator ID is known

  formData: any = {
    datumOdrzavanja: null,
    pocetakPrijave: null,
    krajPrijave: null,
  };

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getExamEventTypes();
    this.currentDate = this.getCurrentDate();

    this.minPocetakPrijave = this.getCurrentDate();
    this.minKrajPrijave = this.getCurrentDate();
  }

  // Retrieving exam event types for form
  getExamEventTypes(): void {
    const apiUrl = `http://localhost:8080/api/exam-event-types`;

    this.http.get<ExamEventType[]>(apiUrl)
      .pipe(
        tap((data) => {
          this.examEventTypes = data;
        }),
        catchError((error) => {
          console.error('Error fetching application forms:', error);
          return of([]);
        })
      )
      .subscribe();
  }

  onSubmit(form: NgForm): void {
    const {naziv, oblast, datumOdrzavanja, pocetakPrijave, krajPrijave, cenaPrijave} = form.value;

    const examEventData = {
      examEventName: naziv,
      maintenanceDate: this.formatDateForApi(datumOdrzavanja),
      applicationDateRange: {
        startDate: this.formatDateForApi(pocetakPrijave),
        endDate: this.formatDateForApi(krajPrijave),
      },
      price: cenaPrijave,
      typeId: oblast,
      createdById: this.createdById,
    };

    // Sending POST request
    const apiUrl = 'http://localhost:8080/api/exam-events';
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    this.http.post<any>(apiUrl, examEventData, {headers})
      .pipe(
        tap((response) => {
          console.log('Success:', response);
          this.closeDialog();
        }),
        catchError((error) => {
          console.error('Error while creating new exam event:', error.error);
          return of([]);
        })
      )
      .subscribe();
  }

  closeDialog(): void {
    this.close.emit();
  }

  getCurrentDate(): string {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Add leading zero if needed
    const day = String(now.getDate()).padStart(2, '0'); // Add leading zero if needed
    return `${year}-${month}-${day}`;
  }

  // Method to format date to 'dd-MM-yyyy'
  formatDateForApi(date: string): string {
    const [year, month, day] = date.split('-');
    return `${day}-${month}-${year}`;
  }

  // Function to format date as yyyy-MM-dd
  formatDateForForm(date: Date): string {
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    return `${year}-${month}-${day}`;
  }

  addOneDay(date: string): string {
    const newDate = new Date(date);
    newDate.setDate(newDate.getDate() + 1);
    return this.formatDateForForm(newDate);
  }

  updateMinDates() {
    if (this.formData.datumOdrzavanja) {
      this.minPocetakPrijave = this.addOneDay(this.formData.datumOdrzavanja);
      this.minKrajPrijave = this.formData.pocetakPrijave
        ? this.addOneDay(this.formData.pocetakPrijave)
        : this.minPocetakPrijave;
    }
  }

  updateMinKrajPrijave() {
    if (this.formData.pocetakPrijave) {
      this.minKrajPrijave = this.addOneDay(this.formData.pocetakPrijave);
    }
  }

  protected readonly faExclamationTriangle = faExclamationTriangle;
  protected readonly faCheck = faCheck;
}
