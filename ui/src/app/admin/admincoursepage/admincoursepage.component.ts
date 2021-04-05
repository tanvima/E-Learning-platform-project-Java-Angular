import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Observable } from 'rxjs';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';
@Component({
  selector: 'app-admincoursepage',
  templateUrl: './admincoursepage.component.html',
  styleUrls: ['./admincoursepage.component.scss']
})
export class AdmincoursepageComponent implements OnInit {
  categoryById: any;
  cId: any;
  updateId: any;
  category: any;
  path!: string;
  course: any;
  selectedCategory:any
  @ViewChild('categorynotnull')
  categorynotnull!: ElementRef;

  @ViewChild('confirmation')
  confirmation!: ElementRef;

  @ViewChild('notification')
  notification!:ElementRef;
  storingImageUrl: any;

  constructor(private as: AdminService,
    private router: Router,private _snackBar: MatSnackBar) {
    this.as.getCategoryList().subscribe(res => {
      this.category = res;
    })
  }

  categoryUpdateForm = new FormGroup({
    categoryName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    categoryLogo: new FormControl('', [Validators.required]),
    categoryDesc: new FormControl('', [
      Validators.required,
      Validators.minLength(25),
      Validators.maxLength(3000)
    ])
  })

  ngOnInit() {
  
    this.as.getCategoryList().subscribe(res => {
      this.category = res;
    })
  }
  updateCategoryId(upId: any) {
    this.updateId = upId;
    this.categoryById = this.as.getCategoryById(this.updateId).subscribe(res => {
      this.categoryById = res;
      this.categoryUpdateForm = new FormGroup({
        categoryName: new FormControl(res.categoryName, [Validators.required, Validators.maxLength(50)]),
        categoryLogo: new FormControl('', [Validators.required]),
        categoryDesc: new FormControl(res.categoryDesc, [
          Validators.required,
          Validators.minLength(25),
          Validators.maxLength(3000)
        ])
      })
    })
  }
  deleteCategory(id: number) {
    this.cId=id
    this.selectedCategory=this.category.find((c: any) => c.categoryId == id)
    if(this.selectedCategory.courses.length== 0){
      //confirmation model
      this.confirmation.nativeElement.click()
      //code for delete category
  
  }else{
    this.categorynotnull.nativeElement.click()
  }
  }
  updateCategory(id: number) {
 
    
    this.path = this.categoryUpdateForm.value.categoryLogo
    if(this.path==''){
     
     this.categoryUpdateForm.value.categoryLogo=this.categoryById.categoryLogo;
   
      
    
    }
   else{
    this.categoryUpdateForm.value.categoryLogo = this.path.replace(/^.*\\/, "../../../assets/")
    
   }
    this.as.updateCategory(this.updateId, this.categoryUpdateForm.value)
      .subscribe({
        next: () => {
          this._snackBar.open("Category updated","Dismiss", {
            duration: 7000,
            verticalPosition: 'top'
          });
          this.as.getCategoryList().subscribe(res => {
            this.category = res;
          })
        }
      })
    
  }

  confirmdel(){
    let ob = this.as.deleteCategory(this.cId);
    ob.subscribe({
      next: () => {
        this._snackBar.open("Category deleted","Dismiss", {
          duration: 7000,
          verticalPosition: 'top'
        });
         this.ngOnInit()
      }
    })
  }


}