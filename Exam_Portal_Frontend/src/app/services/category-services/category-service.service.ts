import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {

  constructor(private http : HttpClient) { }

  // Get All Categories
  public getCategory(){
    return this.http.get(`${baseUrl}/category/getAllCategory`);
  }

  // Add Category
  public addCategoryService(category:any){
    return this.http.post(`${baseUrl}/category/addCategory`, category);
  }
}
