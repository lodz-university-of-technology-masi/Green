import {Pipe, PipeTransform} from '@angular/core';
import translate, { setCORS } from "google-translate-api-browser";
setCORS("http://cors-anywhere.herokuapp.com/");

@Pipe({ name: 'trans' })
export class TranslatePipe implements PipeTransform {
  transform(data: string): any {
    let lang = 'ENG';
    if(localStorage.getItem('language'))
      lang = localStorage.getItem('language');
    if(lang === 'ENG')
      lang = 'en';
    if(lang === 'POL')
      lang = 'pl';
    return translate(data, { to: lang })
      .then(res => {
        return res
      })
      .catch(err => {
        return data;
      });
  }
}

export interface PipeTransform {
  transform(value: any, ...args: any[]): any;
}
