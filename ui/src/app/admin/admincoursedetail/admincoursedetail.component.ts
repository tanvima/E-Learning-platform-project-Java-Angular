import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/user/user.service';

@Component({
  selector: 'app-admincoursedetail',
  templateUrl: './admincoursedetail.component.html',
  styleUrls: ['./admincoursedetail.component.scss']
})
export class AdmincoursedetailComponent implements OnInit {

  courseid:any
  course:any
  noOfLike=0
  noOfComment=0
  duration: any[] = [];
  constructor(private activatedRoute: ActivatedRoute,  private us: UserService) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe((p) => {
      this.courseid = atob(p['courseId'])

      this.us.getCourseById(this.courseid).subscribe((data) => {
        this.course = data
        this.noOfComment=this.course.comment.length
        this.course.like.forEach((likes:any) => {
          if(likes.status=='like'){
            this.noOfLike++
          }
        });
       

      })
    })

       

  }
  onMetadata(e:any, video:any) {
    this.duration[this.duration.length]=video.duration
    console.log(this.duration)
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
