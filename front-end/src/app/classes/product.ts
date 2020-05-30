import { IProduct } from '../model/iproduct';

export class Product {

    protected readonly products: IProduct[];

    public get AllProducts(): IProduct[] {
        const products = JSON.parse(window.localStorage.getItem('products'));
        return (products && Array.isArray(products)) ? products : [] ;
    }

    public set newProduct(newProduct: IProduct) {
        newProduct.id = (Date.now()).toString();
        const currentProducts = this.AllProducts;
        if (currentProducts) {
            this.products.push(...currentProducts, newProduct);
        } else {
            this.products.push(newProduct);
        }
        window.localStorage.setItem('', JSON.stringify(this.products));
    }
}
