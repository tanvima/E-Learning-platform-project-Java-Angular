import { Component, OnInit } from '@angular/core';
import { ChartType, ChartDataSets, ChartOptions } from 'chart.js';
import { Color, Label, MultiDataSet } from 'ng2-charts';
import { reduce } from 'rxjs/operators';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-charts',
  templateUrl: './charts.component.html',
  styleUrls: ['./charts.component.scss']
})
export class ChartsComponent implements OnInit {
  no_of_active_students = 0;
  no_of_courses = 0;
  no_of_categories = 0;
  categories: any;
  enrollment: any[] = [];
  avgrating: any[] = [];
  interaction: any[] = [];
  likes: any[] = [];
  courses: any
  coursename: any[] = []
  no_of_complete_course = 0;
  coursePerCategory: any[] = []
  categoryname: any[] = []
  popularCourse!: any
  popularCourseName: any[] = []
  popularCourseRating: any[] = []
  numeric_array: any = [];
  constructor(private as: AdminService) { }

  ngOnInit(): void {


    this.as.getCategoryList().subscribe((data: any) => {
      if (data != undefined && data != null) {
        this.categories = data;
        console.log(data);
        
        this.no_of_categories = this.categories.length;
        this.categories.forEach((element: any) => {
          this.coursePerCategory.push(element.courses.length)
          this.categoryname.push(element.categoryName)
        })
      }
    })

    this.as.getCourseList().subscribe((data: any) => {
      this.courses = data;
      this.no_of_courses = this.courses.length;
      this.courses.forEach((cat: any, index: number) => {
        this.coursename[index] = cat.courseName;
        this.enrollment.push(cat.enrollment.length);
        this.avgrating[index] = cat.rating;
        this.interaction[index] = cat.feedback;
        this.likes[index] = cat.like.length;
      })
      this.coursename.push("")
      this.enrollment.push(0);
      this.likes.push(0)
      this.avgrating.push(0)
    })
  }
  //Course per category
  doughnutChartLabels: Label[] = this.categoryname;
  doughnutChartData: MultiDataSet = [
    this.coursePerCategory
  ];
  doughnutChartType: ChartType = 'doughnut';
  doughnutChartColor: Color[] = [
    {
      hoverBackgroundColor: 'dark grey'

    }
  ]
  //Student enrollment
  enrollBarOptions: ChartOptions = {
    responsive: true
  };
  enrollBarLabels: Label[] = this.coursename;
  enrollBar: ChartType = 'bar';
  enrollBarLegend = true;
  enrollBarChartPlugins = [];
  enrollBarChartData: ChartDataSets[] = [{ data: this.enrollment, label: 'Student enrollment' }];
  enrollBarColor: Color[] = [
    {
      borderColor: 'grey',
      backgroundColor: '#c8a951',
    },
  ];


  // barChartOptions: ChartOptions = {
  //   responsive: true,
  // };
  // barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit', 'Blueberry', 'Orange', 'Grapes'];
  // barChartType: ChartType = 'bar';
  // barChartLegend = true;
  // barChartPlugins = [];

  // barChartData: ChartDataSets[] = [
  //   { data: [45, 37, 60, 70, 46, 33], label: 'Best Fruits' }
  // ];

  //Likes of courses
  lineChartData: ChartDataSets[] = [
    { data: this.likes, label: 'Likes of courses' },
  ];

  lineChartLabels: Label[] = this.coursename

  lineChartOptions = {
    responsive: true,
  };

  lineChartColors: Color[] = [
    {
      borderColor: 'grey',
      backgroundColor: '#f9e076',

    },
  ];
  lineChartLegend = true;
  lineChartPlugins = [];
  lineChartType = 'line';


  //Student enrollment
  ratingBarOptions: ChartOptions = {
    responsive: true
  };
  ratingBarLabels: Label[] = this.coursename;
  ratingBar: ChartType = 'bar';
  ratingLegend = true;
  ratingBarChartPlugins = [];
  ratingBarChartData: ChartDataSets[] = [{ data: this.enrollment, label: 'Average rating of course' }];
  ratingOptions: ChartOptions = {
    responsive: true
  };
  ratingColor: Color[] = [
    {
      borderColor: 'grey',
      backgroundColor: '#fdefb2',
    },
  ];


}
