import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

declare const require: any;

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  fullName: String;
  email: String;
  userName: String;
  userRoll: String;

  constructor(private http: HttpClient, private cookieService: CookieService, private router: Router) {
    this.fullName = '';
    this.email = '';
    this.userName = '';
    this.userRoll = '';
  }

  ngOnInit(): void {
    this.validateUser();
  }

  ngAfterViewInit(): void {
    const main = require('./js/global.js');
  }

  items: any = [
    { id: 1, name: "LEVEL1" },
    { id: 2, name: "LEVEL2" },
    { id: 3, name: "LEVEL3" }
  ];
  selectedLevel = this.items[0];

  items1: any[] = [ "LEVEL1",
  "LEVEL2",
  "LEVEL3"
  ];

  name: any[] = ["Choose option", "LEVEL1"];

  selected() {
    console.log(this.selectedLevel.name);
  }

  validateUser() {
    let accessLevel = this.cookieService.get('access-level');
    if (accessLevel == 'LEVEL3' || accessLevel == 'LEVEL2') {
      return;
    }
    this.router.navigate(['dashboard']);
  }

  createData() {
    let accessLevel = this.cookieService.get('access-level');
    if (accessLevel == 'LEVEL3' || accessLevel == 'LEVEL2') {
      const headers = new HttpHeaders()
        .set('content-type', 'application/json')
        .set('Accept', '*/*');
      let url = "http://localhost:8080/api/v1/users";
      let pippi = this.selectedLevel;
      this.http.post(url, {

        fullName: this.fullName,
        emailAddress: this.email,
        userName: this.userName,
        role: 'LEVEL1'

      }, { headers: headers, withCredentials: true }).toPromise().then((data: any) => {
        this.router.navigate(['dashboard']);
      }).catch(e => {
        console.log(e);
      });
    } else {
      this.router.navigate(['login']);
    }
  }

}
