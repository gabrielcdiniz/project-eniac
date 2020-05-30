import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BRAZIL_UF_STATES, SearchCepService } from '../services/search-cep.service';
import { IUser } from './../model/iuser';

@Component({
    selector: 'eniac-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

    public formRegister: FormGroup;

    private user: IUser;

    constructor(
        private searchCepService: SearchCepService,

        private formBuilder: FormBuilder,
    ) {
        this.formRegister = this.formBuilder.group({
            name: [null, Validators.required],
            cpf: [null, Validators.required],
            email: [null, [Validators.required, Validators.email]],
            password: [null, [Validators.required, Validators.pattern('^([a-z0-9]{8,})$')]],
            street: [null, Validators.required],
            complement: null,
            city: [null, Validators.required],
            state: [null, Validators.required],
            zipcode: [null, Validators.required],
            neighborhood: [null, Validators.required],
        });
    }

    ngOnInit(): void {
    }

    /** Search the Address with BuscaCep API */
    public searchCep(text: string): void {
        text = text.trim().replace(/\D/gmi, '');
        if (text !== null && text !== undefined) {
            const valid = /^[0-9]{8}$/gmi;

            if (valid.test(text)) {
                this.searchCepService.searchCep(text)
                    .subscribe(
                        cep => {
                            this.formRegister.patchValue({
                                country: 'Brasil',
                                state: BRAZIL_UF_STATES[cep.uf],
                                city: cep.localidade,
                                neighborhood: cep.bairro,
                                street: cep.logradouro,
                                complement: cep.complemento
                            });
                        },
                        error => {
                            console.error(error);
                        }
                    );
            }
        }
    }

    public async send(): Promise<void> {
        const {
            name,
            cpf,
            email,
            password,
            street,
            complement,
            city,
            state,
            zipcode,
            neighborhood
        } = this.formRegister.controls;

        this.user = {
            name: (name.value).toString(),
            cpf: (cpf.value).toString(),
            email: (email.value).toString(),
            password: (password.value).toString(),
            street: (street.value).toString(),
            complement: (complement.value).toString(),
            city: (city.value).toString(),
            state: (state.value).toString(),
            zipcode: (zipcode.value).toString(),
            neighborhood: (neighborhood.value).toString(),
            createdAt: (new Date().toLocaleString())
        };

        /**
         * 1. Validar se o CPF está sendo utilizado
         *    TRUE:  Levantar uma mensagem (snackBar) informando que o CPF já está sendo usado e parar fluxo
         *    FALSE: Continua o fluxo
         *
         * 2. Adicionar um objeto (do tipo IUser) no LocalStorage
         */
        this.validateEmail()
            .then(user => {
                if (!user) {
                    window.localStorage.setItem(`${encodeURI(this.user.email)}`, JSON.stringify(this.user));
                } else if (user.cpf === this.user.cpf) {
                    alert('Usuário já existe com o CPF informado !');
                } else {
                    alert('Usuário já existe !');
                }
            })
            .catch(err => {
                alert('Ops ! Um erro aconteceu. Tente novamente.');
                console.error('err :>> ', err);
            });

    }

    private validateEmail(): Promise<IUser> {
        return new Promise((resolve, reject) => {
            const { email } = this.user;
            let user: IUser;
            try {
                user = JSON.parse(window.localStorage.getItem(`${encodeURI(email)}`));
            } catch (e) {
                reject(e);
                return;
            }

            if (user) {
                resolve(user);
            } else {
                resolve(null);
            }
        });
    }

}
