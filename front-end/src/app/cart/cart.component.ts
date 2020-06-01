import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Product } from '../classes/product';
import { IProduct } from '../model/iproduct';
import { Cart } from './../classes/cart';
import { FormArray, AbstractControl, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
    selector: 'eniac-cart',
    templateUrl: './cart.component.html',
    styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

    public readonly productsList: IProduct[] = [];
    public readonly prices: number[] = [];
    
    protected Cart: Cart;
    protected Product: Product;
    
    constructor(
        private snackBar: MatSnackBar,
        private router: Router
    ) {
        this.Product = new Product();
        this.Cart = new Cart();
    }

    public ngOnInit(): void {
        this.productsList.push(...this.Cart.AllProducts);
        this.prices.push(...this.productsList.map(i => i.price));
    }

    public removeItem(product: IProduct): void {
        this.productsList.splice(this.productsList.findIndex(i => i.id === product.id), 1);
        const newItems = this.Cart.AllProducts.filter(i => i.id !== product.id);
        window.localStorage.setItem('cart', JSON.stringify(newItems));
        location.reload();
        this.showMessage(`${product.name} removido`);
    }

    public finish(): void {
        window.localStorage.removeItem('cart');
        this.router.navigate(['/'])
            .then(() => {
                location.href = this.router.url;
                location.reload();
                this.showMessage('Compra Efetuada com Sucesso');
            });
    }

    public checkValue(index: number, qt: number): void {
        const product = this.productsList[index];
        const finalPrice = (product.price * qt);
        this.prices[index] = finalPrice;
    }

    public get getFinalPrice(): number {
        let finalPrice = 0;
        finalPrice = this.prices.reduce((add, current) => add + current, 0);
        return finalPrice;
    }

    private showMessage(message: string): void {
        this.snackBar.open(
            `${message} !`,
            'Fechar', {
            duration: 2500,
            horizontalPosition: 'right',
            verticalPosition: 'top',
        });
    }

}
