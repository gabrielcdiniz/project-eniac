import { IProduct } from './iproduct';
export interface ICategory {
    id: string;
    name: string;
    products?: IProduct[];
}
