import { HttpRequest, HttpHandler } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService {

  constructor() { }

  intercept(req: HttpRequest<any>, next: HttpHandler){
    if(sessionStorage.getItem('username')&& sessionStorage.getItem('token')){
      let userToken=sessionStorage.getItem("token")
      req = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${userToken}`)
     
      })
    }
    return next.handle(req)
  }
}

