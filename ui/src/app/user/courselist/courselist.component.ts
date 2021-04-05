import { AfterContentInit, Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from 'src/app/interface/course';
import { UserService } from '../user.service';

@Component({
  selector: 'app-courselist',
  templateUrl: './courselist.component.html',
  styleUrls: ['./courselist.component.scss']
})
export class CourselistComponent implements OnInit {

  course!: Course[];
  categoryid:any
  categoryname:any
  constructor(private activatedRoute : ActivatedRoute, private us:UserService) {}

  ngOnInit(): void {
      this.activatedRoute.queryParams.subscribe((p)=>{
      this.categoryid=atob(p['categoryId'])
      this.categoryname=atob(p['categoryName'])
      this.us.getCourseByCat(this.categoryid).subscribe((data)=>{
        this.course=data
        
      },
      (err)=>{
        this.course=[]
      })  
    })
    
  }



 

}

