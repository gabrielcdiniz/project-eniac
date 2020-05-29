import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
    selector: 'eniac-home-products',
    templateUrl: './home-products.component.html',
    styleUrls: ['./home-products.component.scss']
})
export class HomeProductsComponent implements OnInit {

    constructor(
        public dialog: MatDialog,
        private snackBar: MatSnackBar
    ) { }

    ngOnInit(): void {
    }

    public viewProduct(): void {
        const dialogRef = this.dialog.open(DialogComponent, {
            width: '25%',
            data: {}
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.snackBar.open(
                    'Produto Adicionado !',
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
            Nome do Produto
        </h2>
        <small>
            Preço R$ 25,99.
        </small>
    </mat-dialog-content>
    <mat-dialog-actions>
        <button mat-button (click)="close()">Adicionar ao Carrinho</button>
        <button mat-button [mat-dialog-close]="true">Comprar</button>
    </mat-dialog-actions>
    `
})
export class DialogComponent {
    constructor(
        public dialogRef: MatDialogRef<DialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any
    ) { }

    public close(): void {

        this.dialogRef.close(true);
    }
}
