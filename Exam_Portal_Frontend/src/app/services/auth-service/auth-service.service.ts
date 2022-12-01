import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  constructor(private http : HttpClient) { }

  //Register User
  public registerUser(user:any){
    return this.http.post(`${baseUrl}/user/register`, user);
  }

  public loginUserAPI(user:any){
    return null;
  }
}
