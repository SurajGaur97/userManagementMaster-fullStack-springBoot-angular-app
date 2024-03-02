import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';

declare const require: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: String;
  pass: String;

  constructor(private router: Router, private http: HttpClient, private cookieService: CookieService) {
    this.email = '';
    this.pass = '';
  }

  ngAfterViewInit(): void {
    const countDownTime = require('./vendor/countdowntime/countdowntime.js');
    const main = require('./js/main.js');
  }

  ngOnInit(): void {
    this.checkIfSessionIsActive();
  }

  checkIfSessionIsActive() {
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/token/isSessionActive";
    this.http.get(url,  { headers: headers, withCredentials: true }).toPromise().then((isActive: any) => {
      if (isActive) {
        this.router.navigate(['dashboard']);
      }
    }).catch(e => {
      console.log(e);
    });
  }

  goToPage(): void {
    if (this.email != '' && this.pass != '' && this.email != null && this.pass != null) {
      this.login();
      this.router.navigate(['dashboard']);
    }
  }

  login() {
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/token/generate";

    this.http.post(url, {
      emailAddress: this.email,
      pass: this.pass

    }, { headers: headers, withCredentials: true }).toPromise().then((accessLevel: any) => {
      this.cookieService.set('access-level', accessLevel);
      this.router.navigate(['dashboard']);
    }).catch(e => {
      console.log(e);
    });
  }

}
