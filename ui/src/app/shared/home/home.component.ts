import { Component, HostListener, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { LoginComponent } from 'src/app/shared/login/login.component';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../../user/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


  categories:any
  popularcourse:any
  slides: any;
  userid!:Observable<any>
  columnlength: string = 'col-3';

  constructor(private us: UserService,public dialog: MatDialog, private authservice:AuthenticationService) {
    // this.courses=this.us.getAllCourse();
    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
    })

    this.us.getAllCategory().subscribe((data)=>{
      this.categories=data;
    },(err)=>{
      throw Error("Cannot fetch Category")
      
    });

    // this.us.getAllCategory().toPromise().then((data)=>{
    //   this.categories=data;
    //   console.log(data);
    // })

    this.us.getPopularCourse().subscribe((data)=>{
      this.popularcourse=data;
    this.setSlide(window.innerWidth);
    })
  }
   

  ngOnInit(): void {
  }
  

  chunk(arr: any, chunkSize: number) {
    let R = [];
    for (let i = 0, len = arr.length; i < len; i += chunkSize) {
      R.push(arr.slice(i, i + chunkSize));
    }
    return R;
  }


  @HostListener('window:resize', ['$event'])
  onResize(event:any) {
    this.setSlide( event.target.innerWidth);
  }



  setSlide(myWidth: number) {
    if (myWidth <= 768) {
      this.columnlength = 'col-12';
      this.slides = this.chunk(this.popularcourse, 1);
    } else if (myWidth <= 996) {
      this.columnlength = 'col-6';
      this.slides = this.chunk(this.popularcourse, 2);
    } else {
      this.columnlength = 'col-4';
      this.slides = this.chunk(this.popularcourse, 3);
    }
  }



  openLoginDialog(){
    const dialogRef = this.dialog.open(LoginComponent, {
      // width: '650px',
    })
  }


  

}
