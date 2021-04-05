import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  constructor(private us: UserService, private authservice: AuthenticationService,private route:Router) { 
    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
    })
  }

  userid!:Observable<any>
  course:any
  ngOnInit(): void {
    this.us.getCartCourses(this.userid).subscribe(
      (data)=>{
        this.course = data
      },
      (err)=>{
      }
    )
  }

  removeFromCart(courseid:any){
    this.us.removeFromCart(courseid,this.userid).subscribe((data)=>{
      this.authservice.updateCartSizeData()
      this.ngOnInit()
      
    })
  }

  enrollAll(){
    this.us.enrollCourse(this.userid).subscribe((data)=>{
      this.authservice.updateCartSizeData()
      this.route.navigate(['/mycourse'])
    })
  }

 
}
