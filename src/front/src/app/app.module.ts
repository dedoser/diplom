import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { CheckoutComponent } from './components/checkout/checkout.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {RouterModule, Routes} from "@angular/router";
import { UserListComponent } from './components/user-list/user-list.component';
import { TaskComponent } from './components/task/task.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from "@angular/material/icon";

const router: Routes = [
  {path: "tasks", component: TaskListComponent},
  {path: "users", component: UserListComponent},
  {path: "task/:id", component: TaskComponent},
  {path: "**", redirectTo:"/tasks", pathMatch: "full"}
];

@NgModule({
  declarations: [
    AppComponent,
    CheckoutComponent,
    TaskListComponent,
    UserListComponent,
    TaskComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(router),
    MatButtonModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
