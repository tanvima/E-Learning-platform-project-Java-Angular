import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'primeprice'
})
export class PrimepricePipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    return value*0.7
  }

}
