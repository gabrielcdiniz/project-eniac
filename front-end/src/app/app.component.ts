import { IUser } from './model/iuser';
import { Component } from '@angular/core';

@Component({
    selector: 'eniac-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    public title = 'front-end';
    public user: IUser;

    public login(user: IUser): void {
        this.user = user;
    }
}
