import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-mycourse',
  templateUrl: './mycourse.component.html',
  styleUrls: ['./mycourse.component.scss']
})
export class MycourseComponent implements OnInit {
  panelOpenState = false;
  course:any
  userid!:Observable<any>
  commentFlag=false
  isLiked:boolean=false
  status:any=null
  certificateStatus=''
  form=new FormGroup({
    comment: new FormControl(''),
  });
  constructor(private us : UserService, private router:Router, private authservice: AuthenticationService) { 
    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
    })
  }

  ngOnInit(): void {
    this.us.getEnrollCourse(this.userid).subscribe((data)=>{
      this.course=data
   
       this.us.getLikeStatus(this.userid).subscribe((data1)=>{
         this.status=data1
      
        
      });
     
 
     })

     

    }



  

  gotoVideoList(courseid:any){
    this.router.navigate(['/videolist'],{ queryParams: {courseId:btoa(courseid)}});
  }

  gotoCourse(courseid:any){
    this.router.navigate(['/course'],{ queryParams: { courseId: btoa(courseid)}});
  }

  addComment(courseid:any){
    this.us.addComment(courseid,this.userid,this.form.value).subscribe(
      (data)=>{
        this.commentFlags()
        this.form.setValue({comment:""})
        this.ngOnInit()
      }
    )
  }

 Like(courseid:any){
 this.us.addLike(courseid,this.userid).subscribe((data)=>{
   this.status = data
   this.status=null
   this.ngOnInit()
 })
 
 }

  commentFlags(){
    this.commentFlag=!this.commentFlag
  }

  delComment(id:any){
    this.us.deleteComment(id).subscribe((data)=>{
      
      this.ngOnInit()
    })
  }

  watchCertificate(courseid:any){
    this.router.navigate(['/certificate'],{ queryParams: { courseId: btoa(courseid)}});
  }
}
