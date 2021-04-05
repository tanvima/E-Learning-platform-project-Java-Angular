import{Course} from '../interface/course'
export interface Category {
    categoryId:number,
    categoryName:string,
    categoryDesc:string,
    categoryLogo:string,

    courses : Array<Course>,

}
