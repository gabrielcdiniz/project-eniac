import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthenticationComponent } from './authentication/authentication.component';
import { HomeProductsComponent } from './home-products/home-products.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
  {
    path: 'login',
    component: AuthenticationComponent
  },
  {
    path: 'cart',
    component: CartComponent
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
