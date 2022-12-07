import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private service : AuthServiceService, private snack : MatSnackBar, private router: Router){}

  ngOnInit(): void {}
  
  loginForm = new FormGroup({
    email : new FormControl("akshaysonawane1958@gmail.com", [Validators.required]),
    password : new FormControl("1234", [Validators.required]),
  });

  loginUser(){
    //console.log("Log In : ");
    //console.log(this.loginForm.value);
    
    this.service.loginUserAPI(this.loginForm.value).subscribe(
      (result:any)=>{
        // console.log("Success : ")
        // console.log(result);
        this.service.setTokenInLocalStorage(result);
        this.getCurrentUserDetails();
        Swal.fire("Login Successful..");
      },
      (error:any)=>{
        // console.log("Error : ")
        // console.log(error);
        Swal.fire(error.error);
      }
    )
  }

  getCurrentUserDetails(){
    // console.log("Inside getCurrentUserDetails : ");
    this.service.getCurrentUser(this.loginForm.value.email).subscribe(
      (result:any)=>{
        // console.log("Success...");
        // console.log(result);
        this.service.setUserDetails(JSON.stringify(result));
        if(this.service.getUserRole()=="admin"){
          //window.location.href = '/admin-dashboard';
          this.router.navigate(['admin-dashboard']);
          this.service.loginStatusSubject.next(true);
        }
        else if(this.service.getUserRole()=="user"){
          //window.location.href = '/user-dashboard';
          this.router.navigate(['user-dashboard']);
          this.service.loginStatusSubject.next(true);
        }
        else{
          this.service.logout();
          location.reload();
        }
      },
      (error:any)=>{
        // console.log("Error...");
        // console.log(error);
      }
    )
  }


  onClickReset(){
    this.loginForm.reset();
  }
}
