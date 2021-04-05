import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header/header.component';
import { FormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { NgxInputStarRatingModule } from 'ngx-input-star-rating';
import { ReactiveFormsModule } from '@angular/forms';
import { FilterPipe } from './filter.pipe';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatBadgeModule } from '@angular/material/badge';
import { MatTabsModule } from '@angular/material/tabs';
import { MatRadioModule } from '@angular/material/radio'
import { MatDialogModule } from '@angular/material/dialog';
import { LoginComponent } from './login/login.component';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { LandingComponent } from './landing/landing.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { CoursecardComponent } from './coursecard/coursecard.component';
import { PrimepricePipe } from './primeprice.pipe';
import { AppRoutingModule } from '../app-routing.module';
import {MatTooltipModule} from '@angular/material/tooltip';
import { ErrorpageComponent } from './errorpage/errorpage.component';
import { ErrorMessageComponent } from './error-message/error-message.component';

@NgModule({
  declarations: [HeaderComponent, FilterPipe, LoginComponent, LandingComponent, FooterComponent,HomeComponent,CoursecardComponent,PrimepricePipe, ErrorpageComponent, ErrorMessageComponent],
  imports: [
    AppRoutingModule,
    CommonModule,
  FormsModule,
  MatSelectModule,
  MatFormFieldModule,
  MatIconModule,
  NgxInputStarRatingModule,
  ReactiveFormsModule,
  MatAutocompleteModule,
  MatInputModule,
  MatBadgeModule,
  MatTabsModule,
  MatRadioModule,
  MatDialogModule,
  MatCheckboxModule,
  MatIconModule,
  MatTooltipModule,
  ],
  exports: [HeaderComponent, FilterPipe,LoginComponent,LandingComponent,FooterComponent,HomeComponent,CoursecardComponent]
})
export class SharedModule { }
