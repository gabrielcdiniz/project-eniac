import { HomeProductsComponent } from './home-products/home-products.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthenticationComponent } from './authentication/authentication.component';

import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';

const routes: Routes = [
  {
    path: 'login',
    component: AuthenticationComponent
  },
  {
    path: '',
    component: HomeProductsComponent
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
