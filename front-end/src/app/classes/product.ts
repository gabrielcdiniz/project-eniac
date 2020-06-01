import { IProduct } from '../model/iproduct';

export class Product {

    protected readonly products: IProduct[] = [];

    public get AllProducts(): IProduct[] {
        const products = JSON.parse(window.localStorage.getItem('products'));
        return (products && Array.isArray(products)) ? products : [] ;
    }
}
