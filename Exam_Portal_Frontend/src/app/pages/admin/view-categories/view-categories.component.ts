import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from 'src/app/services/auth-service/auth-service.service';
import { CategoryServiceService } from 'src/app/services/category-services/category-service.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent implements OnInit {

  constructor(public service: CategoryServiceService) { }

  categories: any = [{}];

  ngOnInit(): void {
    this.service.getCategory().subscribe((data: any) => {
      console.log(data);
      //console.log(data[0].cid);
      this.categories = data;
    },
      (msg: any) => {
        console.log(msg);
        Swal.fire("Error in loading categories");
      })
  }
}
