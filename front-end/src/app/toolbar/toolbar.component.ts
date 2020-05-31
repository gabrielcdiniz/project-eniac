import { IUser } from './../model/iuser';
import { Component, OnInit, Input, AfterViewInit } from '@angular/core';

@Component({
    selector: 'eniac-toolbar',
    templateUrl: './toolbar.component.html',
    styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent implements OnInit, AfterViewInit {

    @Input()
    public logged: IUser;

    constructor() {}

    public ngOnInit(): void {
    }

    public ngAfterViewInit(): void {
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
