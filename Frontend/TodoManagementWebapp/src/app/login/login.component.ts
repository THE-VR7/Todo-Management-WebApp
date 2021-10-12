import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BasicAuthenticationService } from '../service/basic-authentication.service';
import { HardcodedAuthenticationService } from '../service/hardcoded-authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'in28minutes'
  password = ''
  errorMessage = 'Invalid Credentials'
  invalidLogin = false

  constructor(private router : Router, private basicAuthenticationService : BasicAuthenticationService,
    private hardcodedAuthenticationService : HardcodedAuthenticationService
    ) { }

  ngOnInit(): void {
  }

  // handleLogin() {
  //   console.log(this.username);
  //   if(this.hardcodedAuthenticationService.authenticate(this.username, this.password)) {
  //     //Redirect to Welcome Page
  //     this.router.navigate(['welcome', this.username])
  //     this.invalidLogin = false
  //   } else {
  //     this.invalidLogin = true
  //   }
  // }

  handleBasicLogin() {
    console.log(this.username);
    this.basicAuthenticationService.executeAuthenticationService(this.username, this.password).subscribe(
      res => {
        console.log(res);
        this.router.navigate(['welcome', this.username])
        this.invalidLogin = false
      },
      err => {
        console.log(err);
        this.invalidLogin = true
      }
    )
  }

  handleJWTLogin() {
    console.log(this.username);
    this.basicAuthenticationService.executeJWTAuthenticationService(this.username, this.password).subscribe(
      res => {
        console.log(res);
        this.router.navigate(['welcome', this.username])
        this.invalidLogin = false
      },
      err => {
        console.log(err);
        this.invalidLogin = true
      }
    )
  }

}
