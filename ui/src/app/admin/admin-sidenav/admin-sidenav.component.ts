import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { AuthenticationService } from 'src/app/utilities/authentication.service';

@Component({
  selector: 'app-admin-sidenav',
  templateUrl: './admin-sidenav.component.html',
  styleUrls: ['./admin-sidenav.component.scss']
})
export class AdminSidenavComponent implements OnInit {
  isVisible: boolean=false;
  
  constructor(private authservice:AuthenticationService, private router: Router) { }




  ngOnInit(): void {
  }
   showHide(){
     this.isVisible=!this.isVisible
   }

   logout(){
     this.authservice.logout()

    


   }
}
