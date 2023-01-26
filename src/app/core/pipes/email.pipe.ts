import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'email'
})
export class EmailPipe implements PipeTransform {

  transform(value: string, ...args: string[]): string {
    return value.substring(1, value.indexOf("@"));
  }

}
