import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { TaskListComponent } from './components/task-list/task-list.component';
import {RouterModule, Routes} from "@angular/router";
import { TaskComponent } from './components/task/task.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TaskFormComponent } from './components/task-form/task-form.component';

const router: Routes = [
  {path: "task-create", component: TaskFormComponent},
  {path: "tasks", component: TaskListComponent},
  {path: "task/:id", component: TaskComponent},
  {path: "**", redirectTo:"/tasks", pathMatch: "full"}
];

@NgModule({
  declarations: [
    AppComponent,
    TaskListComponent,
    TaskComponent,
    TaskFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(router),
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
