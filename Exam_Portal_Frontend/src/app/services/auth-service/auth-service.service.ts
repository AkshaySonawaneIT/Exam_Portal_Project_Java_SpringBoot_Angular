import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http : HttpClient) { }

  //Register User API
  public registerUser(user:any){
    return this.http.post(`${baseUrl}/user/register`, user);
  }

  //Login User API
  public loginUserAPI(user:any){
    console.log("Login Auth : ");
    console.log(user);
    return this.http.post(`${baseUrl}/user/login`, user, { responseType: 'text' });
  }

  //Get current user
  public getCurrentUser(email:any){
    return this.http.get(`${baseUrl}/getUserByEmail/${email}`);
  }

  //Set JWT Token in local storage
  public setTokenInLocalStorage(token:any){
    localStorage.setItem("Token", token);
  }

  //Get JWT token from local storage
  public getTokenFromLocalStorage(){
    return localStorage.getItem("Token");
  }
}
