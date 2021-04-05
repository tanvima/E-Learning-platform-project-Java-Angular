import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-addcategory',
  templateUrl: './addcategory.component.html',
  styleUrls: ['./addcategory.component.scss']
})
export class AddcategoryComponent implements OnInit {
categoryForm!: FormGroup
  path!: string;
  category: any;


   constructor(private as:AdminService,private router:Router,private _snackBar: MatSnackBar) {}
 ngOnInit(){
  this.as.getCategoryList().subscribe(res=>{
    this.category=res;
  })
   this.categoryForm = new FormGroup({
    categoryName: new FormControl('', [Validators.required,Validators.minLength(3),Validators.maxLength(50)]),
    categoryLogo: new FormControl('',[Validators.required]),
     categoryDesc: new FormControl('', [
        Validators.required, 
        Validators.minLength(10), 
        Validators.maxLength(3000)
      ])
    })
  }
  addCategory()
  {  
    this.path = this.categoryForm.value.categoryLogo
    this.categoryForm.value.categoryLogo = this.path.replace(/^.*\\/, "../../../assets/")
    this.as.addCategory(this.categoryForm.value)
    .subscribe((data)=>{
      this._snackBar.open("Category added","Dismiss", {
        duration: 7000,
        verticalPosition: 'top'
      });
      this.router.navigate([{outlets: {admin: 'category-list'}}])
    },
    (err)=>{
    },
    )
  
  }
  
  
  }  