import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http'; 
import { HelloWorldBean } from 'src/app/models/hello-world-bean';

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/plain',
        'Content-Type': 'application/plain'
    })
};

  constructor(
    private http : HttpClient
  ) { }

  executeHelloWorldBeanService() {
    return this.http.get<HelloWorldBean>('http://localhost:8080/home');
    //console.log("Execute Hello World Bean Service")
  }

  executeHelloWorldServiceWithPathVariable(name : any) {
    
    // let basic = this.createBasicAuthenticationHeaders();

    // let header = new HttpHeaders({
    //   Authorization : basic
    // });
    
    return this.http.get<HelloWorldBean>(
      `http://localhost:8080/home/${name}`,
      // {headers : header}
      );
  }

  // createBasicAuthenticationHeaders(){
  //   let username = "in28minutes";
  //   let password = 'dummy';
  //   let basicauth = 'Basic ' + window.btoa(username + ':' +  password);
  //   return basicauth;
  // }
  
}
