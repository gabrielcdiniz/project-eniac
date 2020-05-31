import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxMaskModule } from 'ngx-mask';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthenticationComponent } from './authentication/authentication.component';
import { CartComponent } from './cart/cart.component';
import { DialogComponent, HomeProductsComponent } from './home-products/home-products.component';
import { CurrencyPipe } from './pipes/currency.pipe';
import { RegisterComponent } from './register/register.component';
import { ToolbarComponent } from './toolbar/toolbar.component';

@NgModule({
    declarations: [
        AppComponent,
        AuthenticationComponent,
        ToolbarComponent,
        HomeProductsComponent,
        CartComponent,
        RegisterComponent,
        DialogComponent,
        CurrencyPipe,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        NgxMaskModule.forRoot(),

        MatListModule,
        MatCardModule,
        MatInputModule,
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatBadgeModule,
        MatMenuModule,
        MatGridListModule,
        MatDividerModule,
        MatTableModule,
        MatDialogModule,
        MatSnackBarModule,
        MatExpansionModule
    ],
    exports: [
        MatListModule,
        MatCardModule,
        MatInputModule,
        MatIconModule,
        MatButtonModule,
        MatToolbarModule,
        MatBadgeModule,
        MatMenuModule,
        MatGridListModule,
        MatDividerModule,
        MatTableModule,
        MatDialogModule,
        MatSnackBarModule,
        MatExpansionModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
