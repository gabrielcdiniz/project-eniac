import { Pipe, PipeTransform } from '@angular/core';

export type Locales = 'en-US' | 'pt-BR';

@Pipe({
    name: 'currency'
})
export class CurrencyPipe implements PipeTransform {

    transform(value: number, locale: Locales = 'pt-BR'): string {
        const style = 'currency';
        const currency = (locale === 'pt-BR') ? 'BRL' : 'USD';

        return value.toLocaleString(locale, {
            style,
            currency
        });
    }

}
