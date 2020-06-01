import { ICategory } from '../model/icategory';

export class Category {

    protected readonly categories: ICategory[] = [];

    public get AllCategories(): ICategory[] {
        const categories = JSON.parse(window.localStorage.getItem('categories'));
        return (categories && Array.isArray(categories)) ? categories : [] ;
    }
}
