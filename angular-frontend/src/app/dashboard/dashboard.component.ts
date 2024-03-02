import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
declare const require: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  usersData: any;
  userRole: any;

  constructor(private router: Router, private http: HttpClient, private cookieService: CookieService) { }

  ngOnInit(): void {
    this.userRole = this.cookieService.get("access-level");
    this.checkIfSessionIsActive();
    this.getUsers();
    // this.enableDelete();
  }

  ngAfterViewInit(): void {
    const main = require('./js/main.js');
    this.enableCreate();
  }

  enableDelete() {
    if(this.userRole == 'LEVEL3') {
      const deleteColumnElement: HTMLElement | null = document.getElementById('deleteBtnClm');
      if (deleteColumnElement) {
        const definitelyAnElement = deleteColumnElement;
        deleteColumnElement.setAttribute("style", "visibility: visible;");
      }
      // for(let user of this.usersData) {
      //   var id = document.getElementById("delete-"+user.userName);
      //   if(id != null)
      //   id.setAttribute("style", "visibility: visible;");
      // }
      
    }
  }

  enableCreate() {
    if(this.userRole == 'LEVEL3') {
      const createButton: HTMLElement | null = document.getElementById('createBtn');
      if (createButton) {
        const definitelyAnElement = createButton;
        createButton.setAttribute("style", "visibility: visible;");
      }
    }
  }

  getUsers() {
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/users";
    this.http.get(url,  { headers: headers, withCredentials: true }).toPromise().then((users: any) => {
      this.usersData = users;
    }).catch(e => {
      console.log(e);
    });
  }

  checkIfSessionIsActive() {
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/token/isSessionActive";
    this.http.get(url,  { headers: headers, withCredentials: true }).toPromise().then((isActive: any) => {
      if (!isActive) {
        this.router.navigate(['login']);
      }
    }).catch(e => {
      console.log(e);
    });
  }

  delete(userName: String) {
    const headers = new HttpHeaders()
    .set('content-type', 'application/json')
    .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/users/" + userName;
    this.http.delete(url,  { headers: headers, withCredentials: true }).toPromise().then((data: any) => {
      window.location.reload();
    }).catch(e => {
      console.log(e);
    });
  }

  createUser() {
    this.router.navigate(['register']);
  }

  logout() {
    const headers = new HttpHeaders()
    .set('content-type', 'application/x-www-form-urlencoded')
    .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/token/invalidate";
    this.http.get(url,  { headers: headers, withCredentials: true }).toPromise().then((data: any) => {
      this.cookieService.delete('access-level');
      this.router.navigate(['login']);
    }).catch(e => {
      console.log(e);
    });
  }

}
