import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';

export const TOKEN = "token";
export const AUTHENTICATED_USER = "authenticaterUser";

@Injectable({
  providedIn: 'root'
})

export class BasicAuthenticationService {

  constructor(

    private http : HttpClient
  ) { }

  // authenticate(username : any, password : any){
  //   if(username==="in28minutes" && password === 'dummy') {
  //     sessionStorage.setItem('authenticaterUser', username);
  //     //console.log('after ' + this.isUserLoggedIn());
  //     return true;
  //   }
  //   return false;
  // }

  isUserLoggedIn() {
    let user = sessionStorage.getItem(AUTHENTICATED_USER)
    return !(user === null)
  }

  logout(){
    sessionStorage.removeItem(AUTHENTICATED_USER);
    sessionStorage.removeItem(TOKEN);
    
  }

  executeAuthenticationService(username : any, password : any) {
    let basicauth = 'Basic ' + window.btoa(username + ':' +  password);
    let header = new HttpHeaders({
      Authorization : basicauth
    });
    
    return this.http.get<AuthenticationBean>(
      `http://localhost:8080/user/basicauth`,
      {headers : header}
      ).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN,'Basic ' + window.btoa(username + ':' +  password));
            return data;
          }
        )
      )
  }

  executeJWTAuthenticationService(username : any, password : any) {
    
    
    return this.http.post<any>(
      `http://localhost:8080/authenticate`,
      {username, password}
      ).pipe(
        map(
          data => {
            sessionStorage.setItem(AUTHENTICATED_USER, username);
            sessionStorage.setItem(TOKEN, `Bearer ${data.token}` );
            return data;
          }
        )
      )
  }

  getAuthenticatedUser(){
    return sessionStorage.getItem(AUTHENTICATED_USER);
  }

  getAuthenticatedToken(){
    if(this.getAuthenticatedUser()){
      return sessionStorage.getItem(TOKEN);
    }
    return null;
  }

}

export class AuthenticationBean{
  constructor(public message : String){}
}