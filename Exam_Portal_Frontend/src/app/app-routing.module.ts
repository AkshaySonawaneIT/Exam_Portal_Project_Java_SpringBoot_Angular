import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuard } from './guard/admin.guard';
import { UserGuard } from './guard/user.guard';
import { AddCategoriesComponent } from './pages/admin/add-categories/add-categories.component';
import { AddQuizzesComponent } from './pages/admin/add-quizzes/add-quizzes.component';
import { AdminDashboardComponent } from './pages/admin/admin-dashboard/admin-dashboard.component';
import { UpdateCategoryComponent } from './pages/admin/update-category/update-category.component';
import { UpdateQuizComponent } from './pages/admin/update-quiz/update-quiz.component';
import { ViewCategoriesComponent } from './pages/admin/view-categories/view-categories.component';
import { ViewQuestionsComponent } from './pages/admin/view-questions/view-questions.component';
import { ViewQuizzesComponent } from './pages/admin/view-quizzes/view-quizzes.component';
import { WelcomeComponent } from './pages/admin/welcome/welcome.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { RegisterComponent } from './pages/register/register.component';
import { UserDashboardComponent } from './pages/user/user-dashboard/user-dashboard.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent,
    pathMatch:'full'
  },
  {
    path:'register',
    component: RegisterComponent,
    pathMatch:'full'
  },
  {
    path:'login',
    component: LoginComponent,
    pathMatch:'full'
  },
  {
    path:'admin-dashboard',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard],
    children:[
      {
        path: '',
        component: WelcomeComponent,
      },
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path: 'categories',
        component: ViewCategoriesComponent
      },
      {
        path: 'addCategories',
        component: AddCategoriesComponent
      },
      {
        path: 'quizzes',
        component: ViewQuizzesComponent
      },
      {
        path: 'addQuiz',
        component: AddQuizzesComponent
      },
      {
        path: 'quiz/:qid',
        component: UpdateQuizComponent
      },
      {
        path:'category/:cid',
        component: UpdateCategoryComponent
      },
      {
        path: 'questions/:qid/:title',
        component: ViewQuestionsComponent
      }
    ]
  },
  {
    path:'user-dashboard',
    component: UserDashboardComponent,
    pathMatch:'full',
    canActivate: [UserGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
