import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BasicAuthenticationService } from '../basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpinterceptorbasicService implements HttpInterceptor {

  constructor(
    private basicAuthenticationService : BasicAuthenticationService
  ) { }
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // let username = "in28minutes";
    // let password = 'dummy';
    // let basicauth = 'Basic ' + window.btoa(username + ':' +  password);

    let basicauth = this.basicAuthenticationService.getAuthenticatedToken();
    let username = this.basicAuthenticationService.getAuthenticatedUser();
    
    if(basicauth && username)
    {
      req = req.clone({
        setHeaders:{
          authorization : basicauth
        }
      })
    }
    return next.handle(req);
  }

}
