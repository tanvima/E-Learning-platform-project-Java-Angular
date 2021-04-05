import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddcategoryComponent } from './admin/addcategory/addcategory.component';
import { AddcourseComponent } from './admin/addcourse/addcourse.component';
import { AddvideoComponent } from './admin/addvideo/addvideo.component';
import { AdmincoursepageComponent } from './admin/admincoursepage/admincoursepage.component';
import { UnblockuserComponent } from './admin/unblockuser/unblockuser.component';
import { VideopageComponent } from './admin/videopage/videopage.component';
import { LoginComponent } from './shared/login/login.component';
import { CartComponent } from './user/cart/cart.component';
import { CertificateComponent } from './user/certificate/certificate.component';
import { CoursedetailComponent } from './user/coursedetail/coursedetail.component';
import { CourselistComponent } from './user/courselist/courselist.component';
import { HomeComponent } from './shared/home/home.component';
import { MycourseComponent } from './user/mycourse/mycourse.component';
import { VideolistComponent } from './user/videolist/videolist.component';
import { VideoplayerComponent } from './user/videoplayer/videoplayer.component';
import { AuthGuard } from './utilities/auth.guard';
import { AdminhomeComponent } from './admin/adminhome/adminhome.component';
import { CoursepageComponent } from './admin/coursepage/coursepage.component';
import { ErrorpageComponent } from './shared/errorpage/errorpage.component';
import { ChartsComponent } from './admin/charts/charts.component';
import { AdmincoursedetailComponent } from './admin/admincoursedetail/admincoursedetail.component';
import { UserlogComponent } from './admin/userlog/userlog.component';


const routes: Routes = [
  {path:"home",component:HomeComponent},
  {path:"",redirectTo: '/home', pathMatch: 'full' },

  {path:"courselist",component:CourselistComponent},

  {path:"course",component:CoursedetailComponent},


  {path:"videoplayer",component:VideoplayerComponent,
  data:{role:'ROLE_user'},
  canActivate:[AuthGuard]    
},
  {path:"videolist",component:VideolistComponent,
  data:{role:'ROLE_user'},
  canActivate:[AuthGuard]  
},
  {path:"certificate",component:CertificateComponent,
  data:{role:'ROLE_user'},
  canActivate:[AuthGuard]
},
  {path:"cart",component:CartComponent,
  data:{role:'ROLE_user'},
  canActivate:[AuthGuard]
},
  {path:"mycourse",component:MycourseComponent,
  data:{role:'ROLE_user'},
  canActivate:[AuthGuard]
},
{path:"login",component:LoginComponent},

{path:'category-list',component:AdmincoursepageComponent,
data:{role:'ROLE_admin'},
outlet:'admin',
canActivate:[AuthGuard]  
},
 {path:"add-category",component:AddcategoryComponent,
 data:{role:'ROLE_admin'},
 outlet:'admin',
 canActivate:[AuthGuard]
},  

 {path:"add-course",component:AddcourseComponent,
 data:{role:'ROLE_admin'},
 outlet:'admin',
 canActivate:[AuthGuard]
},

{path:"course-list",component:CoursepageComponent,
data:{role:'ROLE_admin'},
outlet:'admin',
canActivate:[AuthGuard],

},

{path:"add-video",component:AddvideoComponent,
data:{role:'ROLE_admin'},
outlet:'admin',
canActivate:[AuthGuard],

},

 {path:"video-list",component:VideopageComponent,
 data:{role:'ROLE_admin'},
 outlet:'admin',
 canActivate:[AuthGuard],

},

 {path:"unblock-user",component:UnblockuserComponent,
 data:{role:'ROLE_admin'},
 outlet:'admin',
 canActivate:[AuthGuard],

},
   {path:"adminhome",component:AdminhomeComponent,
   data:{role:'ROLE_admin'},
   outlet:'admin',
   canActivate:[AuthGuard],
  
  },
  {path:"charts",component:ChartsComponent,
  data:{role:'ROLE_admin'},
  outlet:'admin',
  canActivate:[AuthGuard],
 
 },
 {path:"userlog",component:UserlogComponent,
 data:{role:'ROLE_admin'},
 outlet:'admin',
 canActivate:[AuthGuard]
},
 {path:"admincoursedetail",component:AdmincoursedetailComponent,
   data:{role:'ROLE_admin'},
   outlet:'admin',
   canActivate:[AuthGuard],
  
  },

  
  {path:"**",component:ErrorpageComponent}
                      
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
