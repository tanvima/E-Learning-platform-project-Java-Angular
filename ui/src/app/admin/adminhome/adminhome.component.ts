import { Component, OnInit } from '@angular/core';
import { ChartOptions, ChartType, ChartDataSets, RadialChartOptions } from 'chart.js';
import { Color, Label, MultiDataSet } from 'ng2-charts';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-adminhome',
  templateUrl: './adminhome.component.html',
  styleUrls: ['./adminhome.component.scss']
})
export class AdminhomeComponent implements OnInit {
  no_of_active_students = 0;
  no_of_courses = 0;
  no_of_categories = 0;
  categories: any;
  enrollment:any[]=[];
  avgrating: any[]=[];
  interaction: any[]=[];
  likes: any[]=[];
  courses:any
  coursename:any[]=[]
  no_of_complete_course=0;
  coursePerCategory:any[]=[]
  categoryname:any[]=[]
  popularCourse!:any
  popularCourseName:any[]=[]
  popularCourseRating:any[]=[]

  constructor(private as: AdminService) { }

  ngOnInit(): void {
  

    this.as.getCategoryList().subscribe((data) => {
      if (data != undefined && data != null) {
        this.categories = data;

        this.no_of_categories = this.categories.length;
        this.categories.forEach((element:any) => {
          this.coursePerCategory.push(element.courses.length)
          this.categoryname.push(element.categoryName)
        })
      }
    })

    this.as.getCourseList().subscribe((data)=>{
      this.courses=data;
      this.no_of_courses = this.courses.length;
      this.courses.forEach((cat: any, index: number) => {
        this.coursename[index]=cat.courseName;
        this.enrollment.push(cat.enrollment.length);
        cat.enrollment.forEach((element:any) => {
          if(element.status=="incomplete"){
            this.no_of_active_students++;
          }else if(element.status=="completed"){
            this.no_of_complete_course++;
          }
        });

        this.avgrating[index] = cat.rating;
        this.interaction[index] = cat.feedback;
        this.likes[index] = cat.like.length;
      })

    })

    this.as.getPopularCourse().subscribe((data)=>{
      this.popularCourse=data
      this.popularCourse.forEach((element:any)=> {
        this.popularCourseName.push(element.courseName);
        this.popularCourseRating.push(element.rating)
      });
    })

   

  }

  enrollBarOptions:ChartOptions={
    responsive:true
  };
  enrollBarLabels:Label[]=this.coursename;
  enrollBar:ChartType='bar';
  enrollBarLegend=true;
  enrollBarChartPlugins=[];

  enrollBarChartData:ChartDataSets[]=[{data:this.enrollment,label:'Student enrollment'}];


  coursePerCatOptions:ChartOptions={
    responsive:true
  };

  
  doughnutChartLabels: Label[] =this.coursename;
  doughnutChartData: MultiDataSet = [
    this.enrollment
  ];
  doughnutChartType: ChartType = 'doughnut';

  public radarChartOptions: RadialChartOptions = {
    responsive: true,
  };


//Chart data for Popular courses
lineChartData: ChartDataSets[] = [
  { data: this.popularCourseRating, label: 'Popular courses' },
];

lineChartLabels: Label[] =this.popularCourseName

lineChartOptions = {
  responsive: true,
};

lineChartColors: Color[] = [
  {
    borderColor: 'grey',
    backgroundColor: 'rgba(255,255,0,0.28)',
  },
];

lineChartLegend = true;
lineChartPlugins = [];
lineChartType = 'line';


  //Chart for course per category
  public radarChartLabels: Label[] = this.categoryname;
  public radarChartData: ChartDataSets[] = [
    { data: this.coursePerCategory, label: 'Courses Per Category' }
  ];
  public radarChartType: ChartType = 'radar';

}






