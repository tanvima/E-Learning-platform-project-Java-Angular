import { ErrorHandler, NgZone } from '@angular/core';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ErrorMessageComponent } from '../shared/error-message/error-message.component';

@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandlerService implements ErrorHandler {

  constructor(public dialog: MatDialog, private ngZone: NgZone) { }
  handleError(error: Error): void {
    this.ngZone.run(() => {
      if(error!=null && error.name!="TypeError" && error.name!='InvalidStateError' &&(error.message!=''||error.message!=null)){
        this.dialog.open(ErrorMessageComponent, {
          width: '550px',
          data: { errorMessage: error }
        })
      }
    })
  }
}
