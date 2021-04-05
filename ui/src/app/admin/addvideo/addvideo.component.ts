import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-addvideo',
  templateUrl: './addvideo.component.html',
  styleUrls: ['./addvideo.component.scss']
})
export class AddvideoComponent implements OnInit {
  cId:any;
  catId:any;
  data:any ;
  category:any;
  course:any;
 videoForm!: FormGroup
  path!: string;
  categorycourseForm!: FormGroup;
  constructor(private as:AdminService,private route:ActivatedRoute,
    private router:Router,private _snackBar: MatSnackBar) {
    }
ngOnInit(){
  this.as.getCategoryList().subscribe(res=>{
    this.category=res;
  }) 

  this.videoForm = new FormGroup({
  videoName: new FormControl('', [Validators.required,Validators.minLength(3),Validators.maxLength(50)]),
 
   videoDesc: new FormControl('', [
       Validators.required, 
       Validators.minLength(10), 
       Validators.maxLength(3000),
       
     ]),
     videoPath: new FormControl('',[Validators.required])
   })

   this.categorycourseForm=new FormGroup({
    categoryName : new FormControl('null',[Validators.required]),
    courseName : new FormControl('null',[Validators.required])
   })
 }
 getCategoryId(catId:any){
  let c= this.category.find((cat:any)=>(cat.categoryId==catId))
  this.course = c.courses
  this.catId=catId;
}
getCourseId(cId:any){
  this.cId=cId;
}
  onSubmit()
  { 
    this.path = this. videoForm.value.videoPath
    this. videoForm.value.videoPath = this.path.replace(/^.*\\/, "../../../assets/")
    this.data=this.videoForm.value;
    this.as.addVideo(this.cId,this.data)
    .subscribe({next:() => 
    { this._snackBar.open("Video added","Dismiss", {
      duration: 7000,
      verticalPosition: 'top'
    });
      this.router.navigate([{outlets: {admin: 'video-list'}}])
    }})
  }
}

