import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Course } from 'src/app/interface/course';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-videolist',
  templateUrl: './videolist.component.html',
  styleUrls: ['./videolist.component.scss']
})
export class VideolistComponent implements OnInit {

  courseid!:number
  course!: Course; 
  videostatus:any
  userid!:Observable<any>
  duration: any[] = [];
  constructor(private us: UserService, private router: Router, private activatedRoute : ActivatedRoute, private authservice:AuthenticationService) {  
    this.authservice.useridupdate.subscribe((data)=>{
    this.userid=data
  })}

  ngOnInit(): void {


    this.activatedRoute.queryParams.subscribe((p) => {
      this.courseid =Number( atob(p['courseId']))
    }) 

    this.us.getCourseById(this.courseid).subscribe(
      (data)=>{
            this.course=data
      }
    )

      this.us.getVideoStatus(this.courseid,this.userid).subscribe(
        (data)=>{
          this.videostatus=data
        }
      )

  }

  gotoVideoPlayer(videoId:any,courseId:any){
    this.router.navigate(['/videoplayer'],{ queryParams: { courseId: btoa(courseId),videoId:btoa(videoId),courseName:btoa(this.course.courseName)}});
  }

  onMetadata(e:any, video:any) {
    this.duration[this.duration.length]=video.duration
    console.log(this.duration)
   // this.duration = video.duration
  }
}
