import { Component, Input, OnInit } from '@angular/core';
import { IUser } from './../model/iuser';

@Component({
    selector: 'eniac-toolbar',
    templateUrl: './toolbar.component.html',
    styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit {

    @Input()
    public logged: IUser;

    public items: string;

    constructor() { }

    public ngOnInit(): void {
        const cartItems = window.localStorage.getItem('cart');
        if (cartItems) {
            this.items = JSON.parse(cartItems).length.toString();
        }
        
        const user = window.localStorage.getItem('logged');
        if (user) {
            this.logged = JSON.parse(user);
        }
    }

    public logout(): void {
        window.localStorage.removeItem('logged');
        this.logged = undefined;
    }

}
