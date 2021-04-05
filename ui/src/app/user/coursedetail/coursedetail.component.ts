import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/shared/login/login.component';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';
@Component({
  selector: 'app-coursedetail',
  templateUrl: './coursedetail.component.html',
  styleUrls: ['./coursedetail.component.scss']
})
export class CoursedetailComponent implements OnInit {
  courseid: any
  course: any
  userid!:Observable<any>
  noOfLike=0
  noOfComment=0
  src="../../../assets/"
  duration: any[] = [];
  constructor(private activatedRoute: ActivatedRoute, private us: UserService,private authservice: AuthenticationService,public dialog: MatDialog,private router:Router) { 
    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
    })

 

  }

  inCart=false
  isEnrolled=false

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe((p) => {
      this.courseid = atob(p['courseId'])
     
      this.us.getCourseById(this.courseid).subscribe((data) => {
        this.course = data
        if(this.authservice.isLoggedIn()){
          if(this.course.cart.find((cart: any) => cart.userId == this.userid)){
            this.inCart=true
          }else if (this.course.enrollment.find((enroll: any) => enroll.user == this.userid)){
            this.isEnrolled=true
          }
        }
        this.course.like.forEach((likes:any) => {
          if(likes.status=='like'){
            this.noOfLike++
          }
        });
        this.noOfComment=this.course.comment.length
      })

    })

   

  }

  addtoCart(){

    if(this.authservice.isLoggedIn()){

      this.us.addToCart(this.courseid, this.userid).subscribe((data)=>{
        this.authservice.updateCartSizeData()
        this.ngOnInit()
      })
    }else{
      const dialogRef = this.dialog.open(LoginComponent, {
      })
    }

   
    }
  
    gotoMyCourse(){
      this.router.navigate(['/mycourse'])
    }

    onMetadata(e:any, video:any) {
      this.duration[this.duration.length]=video.duration
    
     // this.duration = video.duration
    }

 


  
  DummyCommentList = [
    {
      id: 1,
      nameCredentials: 'NV',
    },
    {
      id: 1,
      nameCredentials: 'DA',
    },
    {
      id: 1,
      nameCredentials: 'UG',
     },
    {
      id: 1,
      nameCredentials: 'VS',
      },
      {
        id: 1,
        nameCredentials: 'NV',
      },
      {
        id: 1,
        nameCredentials: 'DA',
      },
      {
        id: 1,
        nameCredentials: 'UG',
       },
      {
        id: 1,
        nameCredentials: 'VS',
        },
  ];

 


}
