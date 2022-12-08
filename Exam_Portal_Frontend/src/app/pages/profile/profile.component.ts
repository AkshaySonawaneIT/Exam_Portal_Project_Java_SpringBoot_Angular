import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  
  constructor(public service : AuthServiceService){}

  user : any = '';
  ngOnInit(): void {
    this.user = this.service.getUserDetails();
  }

}
