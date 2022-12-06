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

  constructor(private service : AuthServiceService, private snack : MatSnackBar){}

  ngOnInit(): void {}
  
  loginForm = new FormGroup({
    email : new FormControl("akshaysonawane6376@gmail.com", [Validators.required]),
    password : new FormControl("Akshay@123", [Validators.required]),
  });

  loginUser(){
    console.log("Log In : ");
    console.log(this.loginForm.value);
    
    this.service.loginUserAPI(this.loginForm.value).subscribe(
      (result:any)=>{
        console.log("Success : ")
        console.log(result);
        this.service.setTokenInLocalStorage(result);
        Swal.fire("Login Successful..");
        this.getCurrentUserDetails();
      },
      (error:any)=>{
        console.log("Error : ")
        console.log(error);
        Swal.fire(error.error);
      }
    )
  }

  getCurrentUserDetails(){
    console.log("Inside getCurrentUserDetails : ");
    this.service.getCurrentUser(this.loginForm.value.email).subscribe(
      (result:any)=>{
        console.log("Success...");
        console.log(result);
      },
      (error:any)=>{
        console.log("Error...");
        console.log(error);
      }
    )
  }


  onClickReset(){
    this.loginForm.reset();
  }
}
