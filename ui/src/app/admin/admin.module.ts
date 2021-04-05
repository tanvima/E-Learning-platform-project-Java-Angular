import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AddcategoryComponent } from './addcategory/addcategory.component';
import { AddcourseComponent } from './addcourse/addcourse.component';
import { AddvideoComponent } from './addvideo/addvideo.component';
import { AdminSidenavComponent } from './admin-sidenav/admin-sidenav.component';
import { AdmincoursepageComponent } from './admincoursepage/admincoursepage.component';
import { CoursepageComponent } from './coursepage/coursepage.component';
import { UnblockuserComponent } from './unblockuser/unblockuser.component';
import { VideopageComponent } from './videopage/videopage.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { MatExpansionModule } from '@angular/material/expansion';
import { RouterModule } from '@angular/router';
import { AdminhomeComponent } from './adminhome/adminhome.component';
import { AppRoutingModule } from '../app-routing.module';
import { ChartsModule } from 'ng2-charts';
import { ChartsComponent } from './charts/charts.component';
import { AdmincoursedetailComponent } from './admincoursedetail/admincoursedetail.component';
import { NgxInputStarRatingModule } from 'ngx-input-star-rating';
import {MatCardModule} from '@angular/material/card';
import { UserlogComponent } from './userlog/userlog.component';

@NgModule({
  declarations: [AddcategoryComponent, AddcourseComponent, AddvideoComponent, AdminSidenavComponent, AdmincoursepageComponent, CoursepageComponent, UnblockuserComponent, VideopageComponent, AdminhomeComponent, ChartsComponent, AdmincoursedetailComponent, UserlogComponent],
  imports: [
    AppRoutingModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatIconModule,
    MatExpansionModule,
    RouterModule,
    ChartsModule,
    NgxInputStarRatingModule,
    MatCardModule
  ],
  exports:[AdminSidenavComponent]
})
export class AdminModule { }
