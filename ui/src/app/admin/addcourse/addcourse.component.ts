import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from '../admin.service';
@Component({
  selector: 'app-addcourse',
  templateUrl: './addcourse.component.html',
  styleUrls: ['./addcourse.component.scss']
})
export class AddcourseComponent  {
cId:any;
data:any=null;
category:any;
courseForm!: FormGroup
  path!: string;
  categoryForm!: FormGroup;
   constructor(private as:AdminService,private route:ActivatedRoute,
     private router:Router,private _snackBar: MatSnackBar) {
   }
   

 ngOnInit(){
   
  this.as.getCategoryList().subscribe(res=>{
          this.category=res;
        })
        //render through html 
   this.courseForm = new FormGroup({
    courseName: new FormControl('', [Validators.required,Validators.minLength(3),Validators.maxLength(50)]),
    courseLogo: new FormControl('',[Validators.required]),
    courseDesc: new FormControl('', [
        Validators.required, 
        Validators.minLength(10), 
        Validators.maxLength(3000)]),
        coursePrice: new FormControl('', [Validators.required,Validators.max(100000),Validators.maxLength(5)] 
        ),
        authorName: new FormControl('',[Validators.required])
     
    })
   
    this.categoryForm = new FormGroup({
      categoryName : new FormControl('null',[Validators.required])
    })

  }
  getCategoryId(cId:any){
    this.cId=cId;
  }
  addCourse()
  {  
    this.path = this.courseForm.value.courseLogo
    this.courseForm.value.courseLogo = this.path.replace(/^.*\\/, "../../../assets/")
   this.data=this.courseForm.value;
    this.as.addCourse(this.cId,this.data)
    .subscribe({next:() => 
    {  this._snackBar.open("Course added","Dismiss", {
      duration: 7000,
      verticalPosition: 'top'
    });
      this.router.navigate([{outlets: {admin: 'course-list'}}])
    }})
  }
}
