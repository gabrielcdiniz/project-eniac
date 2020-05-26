import { Component, OnInit } from '@angular/core';
import { SearchCepService, BRAZIL_UF_STATES } from '../services/search-cep.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'eniac-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  public formRegister: FormGroup;

  constructor(
    private searchCepService: SearchCepService,

    private formBuilder: FormBuilder,
  ) {
    this.formRegister = this.formBuilder.group({
      name: [null, Validators.required],
      cpf: [null, Validators.required],
      email: [null, Validators.required],
      password: [null, Validators.required],
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

  public onlyNumbers(text: string): void {
    const value = text.trim().replace(/\D/gmi, '');
    let zipcode = value;

    if (value.length > 5) {
      zipcode = zipcode.substring(0, 5) + '-' + zipcode.substring(5);

      if (zipcode.length > 9) {
        zipcode = zipcode.substring(0, 9);
      }
    }

    this.formRegister.patchValue({
      zipcode
    });
  }

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

}
