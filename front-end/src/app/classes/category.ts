import { ICategory } from '../model/icategory';

export class Category {

    protected readonly categories: ICategory[];

    public get AllCategories(): ICategory[] {
        const categories = JSON.parse(window.localStorage.getItem('categories'));
        return (categories && Array.isArray(categories)) ? categories : [] ;
    }

    public set newCategory(newCategory: ICategory) {
        newCategory.id = (Date.now()).toString();
        const currentCategories = this.AllCategories;
        if (currentCategories) {
            this.categories.push(...currentCategories, newCategory);
        } else {
            this.categories.push(newCategory);
        }
        window.localStorage.setItem('', JSON.stringify(this.categories));
    }
}
