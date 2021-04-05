import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  loginStatus = new BehaviorSubject<boolean>(false)
  username = new BehaviorSubject<any>(sessionStorage.getItem('username'))
  userrole= new BehaviorSubject<any>(sessionStorage.getItem('userrole'))
  userroleupdate= this.userrole.asObservable()
  userid= new BehaviorSubject<any>(sessionStorage.getItem('userid'))
  usertype= new BehaviorSubject<any>(sessionStorage.getItem('usertype'))
intialCart!:Observable<any>
  cartsize= new BehaviorSubject<any>(0)
  cartsizeupdate=this.cartsize.asObservable()
  useridupdate = this.userid.asObservable()
  usertypeupdate=this.usertype.asObservable()

  constructor(private http: HttpClient, private router: Router) { 
  this.cartsizeupdate.subscribe((data)=>{
    this.intialCart=data
  })
  }

  updateCartSizeData(){
    this.useridupdate.subscribe((data)=>{
      if(data== null||data=='undefined'){
        
      }
      else{
      
        this.getCartCourses(data).subscribe((data1) => {
          this.cartsize.next(data1.length)
        },
        (err)=>{
          throw Error("cannot fetch cart")
        })
      }
      
    })
  }

  updateUserRole(data:any){
    
    this.userrole.next(data)
  }

  authneticate(username:string,password:string){
    return this.http.post("http://localhost:8080/auth/authenticate",{username,password})
    .pipe(
      map((userdata:any)=>{
        sessionStorage.setItem('username',userdata.username)
        sessionStorage.setItem('userrole',userdata.roles[0])
        sessionStorage.setItem('userid',userdata.id)
        sessionStorage.setItem('usertype',userdata.type)
        sessionStorage.setItem('token',userdata.token)


        this.loginStatus.next(true)
        this.username.next(sessionStorage.getItem('username'))
        this.userrole.next(sessionStorage.getItem('userrole'))
        this.userid.next(sessionStorage.getItem('userid'))
        this.usertype.next(sessionStorage.getItem('usertype'))

        return userdata
      }))
    
      }

      getCartCourses(userid:any): Observable<any>{
        return this.http.get(environment.baseUserUrl+"/user/getCartCourses/"+userid)
      }




      isLoggedIn(){
        let user = sessionStorage.getItem('username')
        return !(user===null)
      }
    
      logout(){
        this.loginStatus.next(false)
        sessionStorage.removeItem("username")
        sessionStorage.removeItem("userrole")
        sessionStorage.removeItem("token")
        sessionStorage.removeItem("userid")
        sessionStorage.removeItem("usertype")  
        this.username.next(null)
        this.userrole.next(null)
        this.loginStatus.next(false)
        this.userid.next(null)
        this.usertype.next(null)
        this.router.navigate(['/home'])
      }
}
