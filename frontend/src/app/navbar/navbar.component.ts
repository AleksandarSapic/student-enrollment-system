import {Component} from '@angular/core';
import {NgOptimizedImage} from "@angular/common";
import {RouterLink, RouterLinkActive} from "@angular/router";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faDashboard, faFile, faHome, faSignOut, faUser} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgOptimizedImage,
    RouterLink,
    FaIconComponent,
    RouterLinkActive
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  protected readonly faHome = faHome;
  protected readonly faFile = faFile;
  protected readonly faSignOut = faSignOut;
  protected readonly faUser = faUser;
  protected readonly faDashboard = faDashboard;
}
