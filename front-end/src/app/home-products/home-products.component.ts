import { Product } from './../classes/product';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { IProduct } from '../model/iproduct';
import { ICategory } from '../model/icategory';
import { Category } from '../classes/category';

@Component({
    selector: 'eniac-home-products',
    templateUrl: './home-products.component.html',
    styleUrls: ['./home-products.component.scss']
})
export class HomeProductsComponent implements OnInit {

    public readonly productsList: IProduct[] = [
        {
            id: '1',
            name: 'God of War III',
            description: 'Jogo Hack\'n Lash RPG',
            price: 112.5,
            category: {
                id: '1',
                name: 'Jogos'
            }
        },
        {
            id: '2',
            name: 'Grand Thief Auto - GTA',
            description: 'Jogo de Mundo Aberto',
            price: 65.99,
            category: {
                id: '1',
                name: 'Jogos'
            }
        },
        {
            id: '3',
            name: 'Call of Duty - Black Ops II',
            description: 'Jogo FPS',
            price: 189.99,
            category: {
                id: '1',
                name: 'Jogos'
            }
        },
        {
            id: '4',
            name: 'Smartphone A30',
            description: 'Celular Samsung',
            price: 1439.90,
            category: {
                id: '2',
                name: 'Eletrônicos'
            }
        }
    ];

    public readonly categoryList: ICategory[] = [
        {
            id: '1',
            name: 'Jogos'
        },
        {
            id: '2',
            name: 'Eletrônicos'
        }
    ];

    protected Product: Product;
    protected Category: Category;

    constructor(
        public dialog: MatDialog,
        private snackBar: MatSnackBar
    ) {
        this.Product = new Product();
        this.Category = new Category();
    }

    ngOnInit(): void {
        this.productsList.push(...this.Product.AllProducts);
        const categoriesWithProducts = (c: ICategory): ICategory => {
            c.products = this.productsList.filter(p => p.category.id === c.id);
            return c;
        };
        // this.categoryList.push(...this.Category.AllCategories.map(categoriesWithProducts));
        this.categoryList.map(categoriesWithProducts);
    }

    public viewProduct(product: IProduct): void {
        const dialogRef = this.dialog.open(DialogComponent, {
            width: '25%',
            data: product
        });

        dialogRef.afterClosed().subscribe((productAdded: IProduct) => {
            if (productAdded) {
                this.snackBar.open(
                    `${productAdded.name} Adicionado !`,
                    'Fechar', {
                    duration: 2500,
                    horizontalPosition: 'right',
                    verticalPosition: 'top',
                });
            }
        });
    }

}

@Component({
    selector: 'eniac-dialog',
    template: `
    <img mat-card-image src="https://material.angular.io/assets/img/examples/shiba2.jpg" alt="product">
    <mat-dialog-content>
        <h2>
            {{ product.name }}
        </h2>
        <p>
            {{ product.description }}
        </p>
        <small>
            {{ product.price | currency }}
        </small>
    </mat-dialog-content>
    <mat-dialog-actions>
        <button mat-raised-button color="warn" (click)="addOnCart(product)">Adicionar ao Carrinho</button>
        <button mat-raised-button color="accent" (click)="buy(product)">Comprar</button>
    </mat-dialog-actions>
    `
})
export class DialogComponent {
    constructor(
        public dialogRef: MatDialogRef<DialogComponent>,
        @Inject(MAT_DIALOG_DATA) public product: IProduct
    ) { }

    public addOnCart(product: IProduct): void {
        this.dialogRef.close(this.product);
    }

    public buy(product: IProduct): void {

    }
}
