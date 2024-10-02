import {Component} from '@angular/core';
import {User} from "../../models/User";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {catchError, of, tap} from "rxjs";

@Component({
  selector: 'app-user-detail-page',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './user-detail-page.component.html',
  styleUrl: './user-detail-page.component.css'
})
export class UserDetailPageComponent {
  userId?: string;
  userDetail?: User;
  userNotFound: boolean = false;

  constructor(private http: HttpClient, private activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.params['userId'];
    this.getUserDetails();
  }

  getUserDetails(): void {
    this.http.get<User>(`http://localhost:8080/api/users/${this.userId}/application-forms`)
      .pipe(
        tap((data: User) => {
          this.userDetail = data;
        }),
        catchError((error) => {
          if (error.status === 400) {
            this.userNotFound = true;
          }
          console.error('Error fetching user details:', error.error);
          return of(null);
        })
      )
      .subscribe();
  }
}
