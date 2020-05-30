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
            name: 'Motorola G8',
            price: 1259.9,
            description: 'Smartphone 3gb ram 32gb de Armazenamento',
            photo: './../../assets/photos/celular-motorola-g8.jpg',
            category: {
                id: '1',
                name: 'Celular'
            }
        },
        {
            id: '2',
            name: 'Multilaser E Dual',
            price: 455.85,
            description: 'Smartphone 2gb ram 8gb de Armazenamento',
            photo: './../../assets/photos/celular-multilaser-e-dual.jpg',
            category: {
                id: '1',
                name: 'Celular'
            }
        },
        {
            id: '3',
            name: 'Samsung A30s',
            price: 1450.99,
            description: 'Smartphone 4gb ram 64gb de Armazenamento',
            photo: './../../assets/photos/celular-samsung-a30s.jpg',
            category: {
                id: '1',
                name: 'Celular'
            }
        },
        {
            id: '11',
            name: 'Samsung A30s',
            price: 1450.99,
            description: 'Smartphone 4gb ram 64gb de Armazenamento',
            photo: './../../assets/photos/celular-samsung-a30s.jpg',
            category: {
                id: '1',
                name: 'Celular'
            }
        },
        {
            id: '4',
            name: 'Samsung J8',
            price: 759.65,
            description: 'Smartphone 2gb ram 16gb Armazenamento',
            photo: './../../assets/photos/celular-samsung-j8.jpg',
            category: {
                id: '1',
                name: 'Celular'
            }
        },
        {
            id: '5',
            name: 'Lenovo i5',
            price: 2450,
            description: 'Notebook i5 8th 8gb ram 1tb HD',
            photo: './../../assets/photos/notebook-lenovo-i5.jpg',
            category: {
                id: '2',
                name: 'Notebook'
            }
        },
        {
            id: '6',
            name: 'Samsung i5',
            price: 2679.99,
            description: 'Notebook i5 8th 8gb ram 1tb HD',
            photo: './../../assets/photos/notebook-samsung-i5.jpg',
            category: {
                id: '2',
                name: 'Notebook'
            }
        },
        {
            id: '7',
            name: 'Samsung i7',
            price: 3665.49,
            description: 'Notebook i7 8th 16gb ram 1tb HD',
            photo: './../../assets/photos/notebook-samsung-i7.jpg',
            category: {
                id: '2',
                name: 'Notebook'
            }
        },
        {
            id: '8',
            name: 'AOC Full HD',
            price: 1654.99,
            description: 'Televisão Full HD 32 polegadas',
            photo: './../../assets/photos/tv-aoc-fullhd.jpg',
            category: {
                id: '3',
                name: 'Televisão'
            }
        },
        {
            id: '9',
            name: 'LG UHD',
            price: 2450,
            description: 'Televisão UHD 42 polegadas',
            photo: './../../assets/photos/tv-lg-uhd.jpg',
            category: {
                id: '3',
                name: 'Televisão'
            }
        },
        {
            id: '10',
            name: 'Philco 4k',
            price: 2335.99,
            description: 'Televisão Full HD 4k 40 polegadas',
            photo: './../../assets/photos/tv-philco-4k.jpg',
            category: {
                id: '3',
                name: 'Televisão'
            }
        },
    ];

    public readonly categoryList: ICategory[] = [
        {
            id: '1',
            name: 'Celular'
        },
        {
            id: '2',
            name: 'Notebook'
        },
        {
            id: '3',
            name: 'Televisão'
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
        this.categoryList.push(...this.Category.AllCategories);
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
    <img mat-card-image src='https://material.angular.io/assets/img/examples/shiba2.jpg' alt='product'>
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
        <button mat-raised-button color='warn' (click)='addOnCart(product)'>Adicionar ao Carrinho</button>
        <button mat-raised-button color='accent' (click)='buy(product)'>Comprar</button>
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
