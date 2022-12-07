import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http : HttpClient) { }

  public loginStatusSubject = new Subject<boolean>();

  //Register User API
  public registerUser(user:any){
    return this.http.post(`${baseUrl}/user/register`, user);
  }

  //Login User API
  public loginUserAPI(user:any){
    //console.log("Login Auth : ");
    // console.log(user);
    return this.http.post(`${baseUrl}/user/login`, user, { responseType: 'text' });
  }

  //Get current user
  public getCurrentUser(email:any){
    return this.http.get(`${baseUrl}/user/getUserByEmail/${email}`);
  }

  //Set JWT Token in local storage
  public setTokenInLocalStorage(token:any){
    localStorage.setItem("Token", token);
  }

  //Get JWT token from local storage
  public getTokenFromLocalStorage(){
    return localStorage.getItem("Token");
  }

  //Set User details in local storage
  public setUserDetails(user:any){
    localStorage.setItem("User", user);
    this.loginStatusSubject.next(true);
  }

  //Get User details from local storage
  public getUserDetails(){
    let userStr = localStorage.getItem("User");
    if(userStr!=null){
      return JSON.parse(userStr);
    }
  }

  // Check user is logged in
  public isLoggedIn(){
    let tokenStr = localStorage.getItem("Token");
    console.log("Token Str : "+tokenStr);
    if(tokenStr==undefined || tokenStr=='' || tokenStr==null){
      return false;
    }
    else{
      return true;
    }
  }

  // Logout || remove token from local storage
  public logout(){
    localStorage.removeItem("Token");
    this.removeUserDetails();
  }

  // Get User Role
  public getUserRole(){
    let user = this.getUserDetails();
    return user.role;
  }

  // Remove User Details
  public removeUserDetails(){
    localStorage.removeItem("User");
  }
}
