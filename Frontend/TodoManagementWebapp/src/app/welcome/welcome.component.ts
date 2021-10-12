import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  message = 'Some Welcome Message'
  welcomeMessageFromService: string = ""
  isWelcomeString : boolean = false
  name = ''

  constructor(private route : ActivatedRoute,
    private service : WelcomeDataService
    ) { }

  ngOnInit(): void {
    this.isWelcomeString = false;
    this.name = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    //console.log(this.service.executeHelloWorldBeanService());
    
    this.service.executeHelloWorldBeanService().subscribe(
      response => {
        console.log(response);
        this.handleSuccessfulResponse(response)
      },
      error => this.handleErrorResponse(error)
    );
  }

  getWelcomeMessageWithParameter() {    
    // console.log("Message logged");
    this.isWelcomeString = true;
    this.service.executeHelloWorldServiceWithPathVariable(this.name).subscribe(
      response => {
        // console.log("res is " + response);
        this.handleSuccessfulResponse(response);
      },
      error => {
        // console.log("error is " + error);
        this.handleErrorResponse(error);
      }
    );
  }

  handleSuccessfulResponse(response : any){
    this.welcomeMessageFromService = response.message
  }

  handleErrorResponse(error : any) {
    this.welcomeMessageFromService = error.error.message
  }

}

export class Class1 {

}

export class Class2 {
  
}
