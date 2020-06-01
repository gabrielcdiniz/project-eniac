import { IProduct } from '../model/iproduct';

export class Cart {

    protected readonly products: IProduct[] = [];

    public get AllProducts(): IProduct[] {
        const products = JSON.parse(window.localStorage.getItem('cart'));
        return (products && Array.isArray(products)) ? products : [] ;
    }

    public newProduct(newProduct: IProduct): void {
        const currentProducts = this.AllProducts;
        if (currentProducts) {
            this.products.push(...currentProducts, newProduct);
        } else {
            this.products.push(newProduct);
        }
        window.localStorage.setItem('cart', JSON.stringify(this.products));
    }
}
