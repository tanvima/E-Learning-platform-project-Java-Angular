import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Course } from 'src/app/interface/course';
import { AuthenticationService } from 'src/app/utilities/authentication.service';

@Component({
  selector: 'app-coursecard',
  templateUrl: './coursecard.component.html',
  styleUrls: ['./coursecard.component.scss']
})
export class CoursecardComponent implements OnInit {

  type!:Observable<any>
  isPrime=false
  constructor(private router:Router, private authservice: AuthenticationService) { 
    this.authservice.usertypeupdate.subscribe((data)=>{
      this.type=data
      if(data =='prime'){
        this.isPrime=true
      }
      else{
        this.isPrime=false
      }
     
    })
  }

@Input() mycourse:Course | undefined;

  ngOnInit(): void {
    
  }

  gotoCourse(courseId:any){
    this.router.navigate(['/course'],{ queryParams: { courseId: btoa(courseId)}});
  }

}
