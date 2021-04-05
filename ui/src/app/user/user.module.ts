import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from '../shared/home/home.component';
import { CoursecardComponent } from '../shared/coursecard/coursecard.component';
import { FormsModule } from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CourselistComponent } from './courselist/courselist.component';
import { NgxInputStarRatingModule } from 'ngx-input-star-rating';
import { ReactiveFormsModule } from '@angular/forms';
import { CoursedetailComponent } from './coursedetail/coursedetail.component';
import { VideolistComponent } from './videolist/videolist.component';
import { VideoplayerComponent } from './videoplayer/videoplayer.component';
import { FeedbackformComponent } from './feedbackform/feedbackform.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatRadioModule} from '@angular/material/radio';
import { CertificateComponent } from './certificate/certificate.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { CartComponent } from './cart/cart.component';
import { MycourseComponent } from './mycourse/mycourse.component';
import { EditprofileComponent } from './editprofile/editprofile.component';
import { PrimepricePipe } from '../shared/primeprice.pipe';
import { SnackbarComponent } from './snackbar/snackbar.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
 import {MatExpansionModule} from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { SharedModule } from '../shared/shared.module';
import { AppRoutingModule } from '../app-routing.module';
import {MatTooltipModule} from '@angular/material/tooltip';
import { MatTabsModule } from '@angular/material/tabs';

@NgModule({
  declarations: [CourselistComponent, CoursedetailComponent, VideolistComponent, VideoplayerComponent, FeedbackformComponent, CertificateComponent, CartComponent, MycourseComponent, EditprofileComponent, SnackbarComponent],
  imports: [
    AppRoutingModule,
    CommonModule,
    FormsModule,
    MatSelectModule,
    MatFormFieldModule,
    NgxInputStarRatingModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatRadioModule,
    PdfViewerModule,
    MatSnackBarModule,
    MatExpansionModule,
    MatIconModule,
    SharedModule,
    MatTooltipModule,
    MatTabsModule,
  ]
})
export class UserModule { }
