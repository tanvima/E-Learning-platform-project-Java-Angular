import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from 'src/app/user/user.service';
import { map, startWith } from 'rxjs/operators';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { EditprofileComponent } from 'src/app/user/editprofile/editprofile.component';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
userid!: Observable<any>;
  constructor(private us: UserService, private router: Router, public dialog: MatDialog, private authservice: AuthenticationService) {
   
    this.authservice.useridupdate.subscribe((data)=>{
      this.userid=data
      this.authservice.cartsizeupdate.subscribe((data)=>{
        this.cartsize=data
        this.cartsize1=data
      })
    
    })
  
   }
  categories: any
  cartsize!: Observable<any>
  myControl = new FormControl();
  options: string[] = [];
  filteredOptions?: Observable<string[]>;
  cartsize1!:any
  
  
  ngOnInit(): void {
  


   
    this.us.getAllCategory().subscribe((data) => {
      if (data != undefined && data != null) {
        this.categories = data;
        
        for (let category of this.categories) {
          
          for (let course of category.courses) {
            this.options.push(course.courseName)

          }
        }
       
      }
    });
    this.filteredOptions = this.myControl.valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
   
  }
  private _filter(value: string): string[] {
   
    if (value == '') {
      return [];
    }
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }




  AutocompleteArray = [];




  getCategory(categoryId: any, categoryName: any) {
    this.router.navigate(['/courselist'], { queryParams: {categoryId: btoa(categoryId), categoryName: btoa(categoryName) } });
  }
  gotoHome() {
    this.router.navigate(['/home']);
  }

  gotoCart() {
    this.router.navigate(['/cart']);
  }
  gotoMycourse() {
    this.router.navigate(['/mycourse']);
  }

  gotoCourse(coursename: string) {

    for (let category of this.categories) {
      for (let course of category.courses) {
        if (course.courseName === coursename) {
          this.router.navigate(['/course'], { queryParams: { courseId: btoa(course.courseId) } });
        }
      }
    }



  }

  openLoginDialog(){
    const dialogRef = this.dialog.open(LoginComponent, {
    })
  }

  openEditProfileDialog(){
    const dialogRef = this.dialog.open(EditprofileComponent, {
      width: '650px',
      data: {userid: this.userid}
    })
  }

  logout(){
    this.authservice.logout();
  }







}
