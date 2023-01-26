import { Pipe, PipeTransform } from '@angular/core';
import { SubscriptionLog } from 'rxjs/internal/testing/SubscriptionLog';

@Pipe({
  name: 'description'
})
export class DescriptionPipe implements PipeTransform {

  transform(value: string, ...args: string[]): string {
    return value.substring(0, 130);
  }

}
