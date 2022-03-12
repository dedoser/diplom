import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import { CheckoutComponent } from './components/checkout/checkout.component';
import { TaskListComponent } from './components/task-list/task-list.component';
import {RouterModule, Routes} from "@angular/router";

const router: Routes = [];

@NgModule({
  declarations: [
    AppComponent,
    CheckoutComponent,
    TaskListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(router)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
