import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-certificate',
  templateUrl: './certificate.component.html',
  styleUrls: ['./certificate.component.scss']
})
export class CertificateComponent implements OnInit {

  pdfSrc = "";

  constructor(private activatedRoute: ActivatedRoute, private authservice: AuthenticationService, private us :UserService,private _snackBar: MatSnackBar) { 

    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
    })
    this.username=this.authservice.username.value
  }

  courseid:any
  userid!:Observable<any>
  username=''
  coursename=''
  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe((p) => {
      this.courseid = Number(atob(p['courseId']))
      })

      this.us.getCourseById(this.courseid).subscribe((data)=>{
        this.coursename=data.courseName
        this.pdfSrc="http://127.0.0.1:8887/"+this.username+data.courseName+".pdf"
      })
  }

  sendMail(){
    this.us.sendCertificateMail(this.userid,this.coursename).subscribe((data)=>{
      
      this._snackBar.open("Mail sent","Dismiss", {
        duration: 7000,
      });
    })
  }
}
