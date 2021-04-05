import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from './utilities/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'ui';
  userrole!:Observable<any>
  userroleString:any
constructor(private authservice: AuthenticationService){
 
}
ngOnInit(){
this.authservice.userroleupdate.subscribe((data)=>{
   

    this.userroleString=data
   
  })
}

}
