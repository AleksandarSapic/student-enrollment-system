import {Routes} from '@angular/router';
import {HomePageComponent} from "./pages/home-page/home-page.component";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";
import {ExamsPageComponent} from "./pages/exams-page/exams-page.component";
import {PageNotFoundComponent} from "./pages/page-not-found/page-not-found.component";
import {AdminProfilePageComponent} from "./pages/admin-profile-page/admin-profile-page.component";
import {AdminDashboardPageComponent} from "./pages/admin-dashboard-page/admin-dashboard-page.component";
import {ExamEventDetailPageComponent} from "./pages/exam-event-detail-page/exam-event-detail-page.component";
import {UserDetailPageComponent} from "./pages/user-detail-page/user-detail-page.component";

export const routes: Routes = [
  {path: '', component: HomePageComponent},
  {path: 'events', component: ExamsPageComponent},
  {path: 'exam-events/:eventId', component: ExamEventDetailPageComponent},
  {path: 'users/:userId', component: UserDetailPageComponent},
  {path: 'profile', component: ProfilePageComponent},
  {path: 'admin/profile', component: AdminProfilePageComponent},
  {path: 'admin/dashboard', component: AdminDashboardPageComponent},
  {path: '**', component: PageNotFoundComponent}
];
