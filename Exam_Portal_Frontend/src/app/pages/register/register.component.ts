import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
 
  constructor(private authService : AuthServiceService, private snack : MatSnackBar){}

  ngOnInit(): void {}
  
  
  registerForm = new FormGroup({
    name : new FormControl("Akshay Sonawane", [Validators.required]),
    email : new FormControl("akshaysonawane6376@gmail.com", [Validators.required, Validators.pattern('[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}')]),
    contactNumber : new FormControl("+918274888888", [Validators.required, Validators.pattern('(([+][(]?[0-9]{1,3}[)]?)|([(]?[0-9]{4}[)]?))\s*[)]?[-\s\.]?[(]?[0-9]{1,3}[)]?([-\s\.]?[0-9]{3})([-\s\.]?[0-9]{3,4})')]),
    password : new FormControl("Akshay@123", [Validators.required, Validators.pattern('(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&].{8,}')]),
  });

  get nameValidator(){
    return this.registerForm.get('name');
  }

  get emailValidator(){
    return this.registerForm.get('email');
  }

  get passwordValidator(){
    return this.registerForm.get('password');
  }

  get phoneNumberValidator(){
    return this.registerForm.get('contactNumber');
  }

  registerUser(){
    console.log(this.registerForm.value);
    this.authService.registerUser(this.registerForm.value).subscribe(
      (data:any)=>{
        console.log(data);
        // alert(data.message);
        
        //*********** Snack Bar Code ****//
        // this.snack.open(data.message, "Close",{
        //   duration:3000
        // });

        //****Sweet Alert******//
        //Swal.fire("Success", data.message, "success");
        Swal.fire(data.message);
      },
      (msg:any)=>{
        console.log(msg);
        // alert(msg.error.message);
        
        //**** Snack Bar *****/
        // this.snack.open(msg.error.message, "Close",{
        //   duration:3000,
        //   verticalPosition:'bottom', //top
        //   // horizontalPosition: 'right'
        // });
      
        //****Sweet Alert *****/
        //Swal.fire("Error", msg.error.message, "error");
        Swal.fire(msg.error.message);
      }
    )
  }

  onClickReset(){
    this.registerForm.reset();
  }
}
