import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activate',
  templateUrl: './activate.component.html',
  styleUrls: ['./activate.component.css']
})
export class ActivateComponent implements OnInit {


  userName : any;
  uniqueKey : any;
  pass : any;

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(params => {
        this.userName = params.get('userName');
        this.uniqueKey =params.get('uniqueKey');
      });
  }

  activate() {
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('Accept', '*/*');
    let url = "http://localhost:8080/api/v1/users";

    this.http.put(url, {

      userName: this.userName,
      uniqueKey: this.uniqueKey,
      pass: this.pass
      
    }, { headers: headers, withCredentials: true }).toPromise().then((data: any) => {
      this.router.navigate(['login']);
    }).catch(e => {
      console.log(e);
    });
  }

}
