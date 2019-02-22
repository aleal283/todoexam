import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { HttpClientModule } from '@angular/common/http';

import {FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { UserFormComponent } from './components/user-form/user-form.component';
import { ListUserComponent } from './components/list-user/list-user.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';


import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: '', component: ListUserComponent },
  { path: 'op', component: UserFormComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    ListUserComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
