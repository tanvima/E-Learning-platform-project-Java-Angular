import { ErrorHandler, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { UserModule } from './user/user.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {MatSelectModule} from '@angular/material/select';
import { FilterPipe } from './shared/filter.pipe';
import { AuthInterceptorService } from './utilities/auth-interceptor.service';
import { AdminModule } from './admin/admin.module';
import { GlobalErrorHandlerService } from './utilities/global-error-handler.service';
import { HttpErrorInterceptorService } from './utilities/http-error-interceptor.service';



@NgModule({
  declarations: [
    AppComponent,
    
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    UserModule,
    SharedModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSelectModule,
    AdminModule,
  ],
  providers: [
    // {
    //   provide:ErrorHandler,
    //   useClass:GlobalErroHandlerService
    // }

    {
      provide:HTTP_INTERCEPTORS,
      useClass: AuthInterceptorService,
      multi:true
    }, {
      provide:ErrorHandler,
    useClass:GlobalErrorHandlerService
  },
  {
    provide:HTTP_INTERCEPTORS,
    useClass:HttpErrorInterceptorService,
    multi:true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
