import { ICategory } from './icategory';

export interface IProduct {
    id: string;
    name: string;
    price: number;
    description: string;
    category: ICategory;
}
