import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { IUser } from './../model/iuser';

@Component({
    selector: 'eniac-authentication',
    templateUrl: './authentication.component.html',
    styleUrls: ['./authentication.component.scss']
})
export class AuthenticationComponent implements OnInit {

    public formLogin: FormGroup;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private snackBar: MatSnackBar,
    ) {
        this.formLogin = this.formBuilder.group({
            email: [null, [Validators.required, Validators.email]],
            password: [null, [Validators.required, Validators.pattern('^([a-z0-9]{8,})$')]],
        });
    }

    ngOnInit(): void {
    }

    public login(): void {
        this.validateLogin()
            .then(logged => {
                if (logged.status) {
                    window.localStorage.setItem('logged', JSON.stringify(logged.user));
                    this.router.navigate(['/'])
                        .then(() => {
                            location.href = this.router.url;
                            location.reload();
                        });
                }
            })
            .catch(err => {
                this.showMessage('Ops ! Um erro aconteceu. Tente novamente.');
                console.error('err :>> ', err);
            });
    }

    private validateLogin(): Promise<UserLogged> {
        return new Promise((resolve, reject) => {
            try {
                const {
                    email,
                    password
                } = this.formLogin.controls;

                let user: any = window.localStorage.getItem(`${encodeURI(email.value)}`);
                if (!user) {
                    this.showMessage('Este usuário não existe');
                    resolve({ status: false, user: undefined });
                } else {
                    user = JSON.parse(user) as IUser;
                    if (user.password === password.value) {
                        resolve({ status: true, user });
                    } else {
                        this.showMessage('Senha incorreta');
                        resolve({ status: false, user: undefined });
                    }
                }
            } catch (e) {
                console.log('error :>> ', e);
                reject(e);
            }
        });
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

export interface UserLogged {
    status: boolean;
    user: IUser;
}
