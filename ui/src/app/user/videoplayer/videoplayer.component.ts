import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { FeedbackformComponent } from '../feedbackform/feedbackform.component';
import { UserService } from '../user.service';
import { MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-videoplayer',
  templateUrl: './videoplayer.component.html',
  styleUrls: ['./videoplayer.component.scss']
})
export class VideoplayerComponent implements OnInit {

  constructor(private us: UserService, private activatedRoute: ActivatedRoute, private router: Router, private _snackBar: MatSnackBar, public dialog: MatDialog, private authservice: AuthenticationService) {
    this.authservice.useridupdate.subscribe((data) => {
      this.userid = data
    })
  }

  userid!: Observable<any>
  courseid!: any
  videoid!: number;
  src = ""
  videolist!: Array<any>;
  status!: string
  nextButton: any = "Next"
  durationInSeconds = 5;
  videotitle!: string
  flagfornext!: boolean
  videodesc=''
  coursename=''
  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe((p) => {
      this.courseid = atob(p['courseId'])
      this.videoid = Number(atob(p['videoId']))
      this.coursename = atob(p['courseName'])
          

    })


    this.us.getVideoList(this.courseid).subscribe(
      (data) => {
        this.videolist = data
        this.videolist.forEach((video, index) => {

          if ((video.videoId) == this.videoid) {
            this.src = video.videoPath
            this.videotitle = video.videoName
            this.videodesc = video.videoDesc

          }

        })

      }
    )



  }

  
  onNext(vid: number) {

    this.us.updateVideoStatus(this.courseid, this.userid, vid).subscribe(
      (data) => {
        this.status = data.msg

        this.videolist.forEach((video, index) => {


         


          if ((video.videoId) == vid) {

            

            if (this.status == "complete") {
              this.nextButton = "Back to Video List";
              this.flagfornext = false

              this.router.navigate(['/videolist'], { queryParams: { courseId: btoa(this.courseid) } });

            }
            else if (this.status == "certificate") {

              this.dialog.open(FeedbackformComponent, {
                width: '650px',
                data: { courseid: this.courseid, userid: this.userid }
              })

             
              this.nextButton = null
             


               this._snackBar.open("Course completed!","Dismiss", {
                duration: 7000,
              });
            }
            else if (this.status == "incomplete") {


              if (index + 1 == this.videolist.length) {
                //LAST VIDEO REDIRECT TO VIDEOLIST
                this.nextButton = "Back to Video List"
                this.flagfornext = false
                this.router.navigate(['/videolist'],{ queryParams: { courseId: btoa(this.courseid)}});

              }

              this.nextButton = "Next Video"
              this.flagfornext = true
             
              this.src = (this.videolist[index + 1].videoPath);
              this.videotitle = video.videoName
              this.videoid = this.videolist[index + 1].videoId

            }
        
          }
        })

      }
    )
  }

  onBack() {
    this.router.navigate(['/videolist'], { queryParams: { courseId: btoa(this.courseid) } });
  }

  gotoCertificate() {

    //certificate pdf generation api
    this.router.navigate(['/certificate'], { queryParams: { courseId: btoa(this.courseid) } });

  }



}
