import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  isLoggedIn = false;
  username = null;

  constructor(public service: AuthServiceService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.service.isLoggedIn();
    this.username = this.service.getUserDetails().name.split(' ')[0];
    this.service.loginStatusSubject.asObservable().subscribe(data => {
      this.isLoggedIn = this.service.isLoggedIn();
      this.username = this.service.getUserDetails().name.split(' ')[0];
    })
  }

  logout() {
    this.service.logout();
    this.router.navigate(['login']);
    this.service.loginStatusSubject.next(false);
  }
}
