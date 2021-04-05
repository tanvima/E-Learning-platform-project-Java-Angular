import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPopularCourse(){
   return  this.http.get(environment.baseUserUrl+'/popularCourse')
  }

  getAllCourse(): Observable<any>{
    return this.http.get(environment.baseUserUrl+'/course')
  }

  getAllCategory(): Observable<any>{
    return this.http.get(environment.baseUserUrl+'/category')
  }

  getCourseByCat(id:number):Observable<any>{
    return this.http.get(environment.baseUserUrl+'/course/'+id)
  }

  getCourseById(id:number):Observable<any>{
    return this.http.get(environment.baseUserUrl+'/courseid/'+id)
  }
  getVideoList(courseid:number): Observable<any>{
    return this.http.get(environment.baseUserUrl+'/user/videolist/'+courseid)
  }

  updateVideoStatus(courseid:any,userid:any,videoid:any): Observable<any>{
    return this.http.post(environment.baseUserUrl+'/user/nextvideo',null,{params:{courseid:courseid,userid:userid,videoid:videoid}})
  }

  getVideoStatus(courseid:any,userid:any):Observable<any>{
    return this.http.get(environment.baseUserUrl+"/user/getVideoStatus/"+userid+"/"+courseid)
  }
  addFeedback(userid:any,courseid:any,feedbackdata:any):Observable<any>{
    return this.http.post<any>(environment.baseUserUrl+"/user/feedback/"+userid+"/"+courseid,feedbackdata)
  }
  addToCart(courseid:any,userid:any): Observable<any>{
    return this.http.post(environment.baseUserUrl+'/user/addtocart',null,{params:{courseid:courseid,userid:userid}})
  }
  getCartCourses(userid:any): Observable<any>{
    return this.http.get(environment.baseUserUrl+"/user/getCartCourses/"+userid)
  }
  removeFromCart(courseid:any,userid:any): Observable<any>{
    return this.http.delete(environment.baseUserUrl+'/user/removecart',{params:{courseid:courseid,userid:userid}})
  }

  enrollCourse(userid:any):Observable<any>{
    return this.http.post(environment.baseUserUrl+'/user/enroll',null,{params:{userid:userid}})
  }

  getEnrollCourse(userid:any):Observable<any>{
     return this.http.get(environment.baseUserUrl+"/user/mycourses/"+userid)
  }
  addComment(courseid:any, userid:any,comments:any): Observable<any>{
   
    return this.http.post<any>(environment.baseUserUrl+'/user/addcomment/'+courseid+'/'+userid,comments)
  }

  addLike(courseid:any,userid:any):Observable<any>{
    return this.http.get(environment.baseUserUrl+'/user/addlike/'+userid+'/'+courseid)
  }

  getLikeStatus(userid:any):Observable<any>{
    return this.http.get(environment.baseUserUrl+'/user/getlike/'+userid)
}
deleteComment(commentid:any):Observable<any>{
  return this.http.delete(environment.baseUserUrl+'/user/deleteComment/'+commentid)
}

addUser(user:any,type:String):Observable<any>{
return this.http.post<any>(environment.baseUserUrl+"/user/"+type,user)
}

sendOTP(email:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+"/user/sendOTP/"+email)
}
activateAccount(username:any):Observable<any>{
 
  return this.http.get(environment.baseUserUrl+'/user/activateaccount/'+username)
}

resetPassword(email:any,password:any):Observable<any>{
  return this.http.put(environment.baseUserUrl+'/user/resetPassword/'+email,password)
}

getAllUser():Observable<any>{
  return this.http.get(environment.baseUserUrl+"/user")
}

getUserById(userid:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/'+userid)
}

updateNoOfAttempts(username:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/updateNoOfAttempts/'+username)
}

clearNoOfAttempts(username:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/clearNoOfAttempts/'+username)
}

blockUser(username:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/blockUser/'+username)
}
requestAdmin(username:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/requestadmin/'+username)
}
updateProfile(user:any):Observable<any>{
  return this.http.put(environment.baseUserUrl+'/user/updateUser',user)
}



sendCertificateMail(userid:any,courseid:any):Observable<any>{
  return this.http.get(environment.baseUserUrl+'/user/mailcerti/'+userid+'/'+courseid)
}

}

