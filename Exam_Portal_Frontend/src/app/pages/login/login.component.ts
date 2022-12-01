import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private authService : AuthServiceService, private snack : MatSnackBar){}

  ngOnInit(): void {}
  
  loginForm = new FormGroup({
    email : new FormControl("akshaysonawane6376@gmail.com", [Validators.required]),
    password : new FormControl("Akshay@123", [Validators.required]),
  });

  loginUser(){
    console.log(this.loginForm.value);
  }

  onClickReset(){
    this.loginForm.reset();
  }
}
