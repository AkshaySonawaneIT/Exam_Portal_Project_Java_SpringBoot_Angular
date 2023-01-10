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

  // Get Category By ID
  public getCategoryById(cid:any){
    return this.http.get(`${baseUrl}/category/getCategory/${cid}`, cid);
  }

  // Update category
  public updatecategoryService(category:any){
    return this.http.post(`${baseUrl}/category/updateCategory`, category);
  }

  // Delete Category
  public deleteCategoryService(category:any){
    return this.http.post(`${baseUrl}/category/deleteCategory/${category.cid}`, category.cid);
  }
}
