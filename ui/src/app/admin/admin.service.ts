import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

 constructor(private http:HttpClient) { }
  addCategory(category:any):Observable<any>{
    return  this.http.post('http://localhost:8080/elearning/category',category,{responseType:"text"})
  }
  getCategoryList():Observable<any>{
     return  this.http.get('http://localhost:8080/elearning/category')
  }
  deleteCategory(id:number):Observable<any>{
    return  this.http.delete('http://localhost:8080/elearning/category/'+id,{responseType:"text"})
  }
  getCategory(id: number): Observable<Object> {  
        return this.http.get('http://localhost:8080/elearning/category/'+id);  
  }  
  updateCategory(id: number, category:any): Observable<Object> {  
      return this.http.put('http://localhost:8080/elearning/category/'+ id, category,{responseType:"text"})
  }   
  getCategoryById(id:number):Observable<any>{
    return  this.http.get('http://localhost:8080/elearning/category/'+id);
  }


  getCourseBYId(id:number):Observable<any>{
    return  this.http.get('http://localhost:8080/elearning/courseid/'+id);
  }

  getCourseList():Observable<any>{
     return  this.http.get('http://localhost:8080/elearning/course')
  }
  addCourse(id:any,course:any):Observable<any>{
    return  this.http.post('http://localhost:8080/elearning/course/'+id,course,{responseType:"text"})
  }
  deleteCourse(id:number):Observable<any>{
    return  this.http.delete('http://localhost:8080/elearning/course/'+id,{responseType:"text"})
  }
  updateCourse(id: number, course:any): Observable<Object> {  
    return this.http.put('http://localhost:8080/elearning/course/'+ id, course,{responseType:"text"})
  }
  
  

  getVideoList():Observable<any>{
    return  this.http.get('http://localhost:8080/elearning/video')
 }
  addVideo(id:any,video:any):Observable<any>{
   return  this.http.post('http://localhost:8080/elearning/video/'+id,video,{responseType:"text"})
 }
 deleteVideo(id:number):Observable<any>{
   return  this.http.delete('http://localhost:8080/elearning/video/'+id,{responseType:"text"})
 }
 updateVideo(id: number, video:any): Observable<Object> {  
   return this.http.put('http://localhost:8080/elearning/video/'+ id, video,{responseType:"text"})
 } 
 getVideoById(id:number):Observable<any>{
  return  this.http.get('http://localhost:8080/elearning/video/'+id);
} 

getCourseByCatId(cId:any):Observable<any>{
  return  this.http.get('http://localhost:8080/elearning/display/'+cId);
}

getVideoByCourseId(cId:any):Observable<any>{
  return  this.http.get('http://localhost:8080/elearning/videolist/'+cId);
}

getPopularCourse(){
  return  this.http.get(environment.baseUserUrl+'/popularCourse')
 }
 getAllBlockUser():Observable<any>{
  return this.http.get('http://localhost:8080/elearning/blockuser');
}
unblockUser(id:any):Observable<any>{
  return this.http.get('http://localhost:8080/elearning/blockuser/'+id);
}

}