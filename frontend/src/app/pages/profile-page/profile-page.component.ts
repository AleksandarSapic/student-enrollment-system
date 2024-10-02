import {Component} from '@angular/core';
import {UserProfileComponent} from "../../user-profile/user-profile.component";
import {UserApplicationFormsComponent} from "../../user-application-forms/user-application-forms.component";
import {User} from "../../models/User";
import {catchError, of, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-profile-page',
  standalone: true,
  imports: [
    UserProfileComponent,
    UserApplicationFormsComponent
  ],
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.css'
})
export class ProfilePageComponent {
  userId = 1;
  user?: User;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.fetchUserDetails();
  }

  fetchUserDetails(): void {
    const apiUrl = `http://localhost:8080/api/users/${this.userId}/application-forms`;

    this.http.get<User>(apiUrl)
      .pipe(
        tap((data: User) => {
          this.user = data;
        }),
        catchError((error) => {
          console.error('Error fetching application forms:', error.error);
          return of([]);
        })
      )
      .subscribe();
  }
}
