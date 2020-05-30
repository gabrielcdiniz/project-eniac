import { ICategory } from './icategory';

export interface IProduct {
    id: string;
    name: string;
    price: number;
    description: string;
    photo: string;
    category: ICategory;
}
