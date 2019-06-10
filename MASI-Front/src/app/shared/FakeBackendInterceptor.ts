import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {


    return of(null).pipe(mergeMap(()  => {
      if (request.url.endsWith('/api/position/mod') && request.method === 'PUT') {
        const responseData = {
          token: 2,
          Succeeded: true
        };
        return of(new HttpResponse({status: 200, body: responseData}));
      }
      if (request.url.endsWith('/api/User/Register') && request.method === 'POST') {
        const responseData = {
          token: 2,
          Succeeded: true
        };
        return of(new HttpResponse({status: 200, body: responseData}));
      }
      if (request.url.endsWith('/api/User/Login') && request.method === 'POST') {
        const accessToken = {
           token: 2
        };
        return of(new HttpResponse({status: 200, body: accessToken}));
      }
      if (request.url.endsWith('/api/User/Language') && request.method === 'PUT') {
        const data = {
           token: 2,
           language: 'POL'
        };
        return of(new HttpResponse({status: 200, body: data}));
      }
      if (request.url.endsWith('/api/User/GetUserClaims') && request.method === 'GET') {
        const data = {
          name: 'Jon Snow',
          email: 'JonSnow@gmail.com',
          token: 2,
          role: 'RED',
          language: 'POL'
        };
        return of(new HttpResponse({status: 200, body: data}));
      }
      if (request.url.endsWith('/api/User/getAll') && request.method === 'GET') {
        const data =
          { list:
            [{
              id: 1,
              name: 'Jon Snow',
              email: 'JonSnow@gmail.com',
              role: 'MOD',
            },
              {
                id: 2,
                name: 'Sansa Stark',
                email: 'SansaStark@gmail.com',
                role: 'RED',
              },
              {
                id: 3,
                name: 'Tyrion Lannister',
                email: 'TyrionLanister@gmail.com',
                role: 'RED',
              },
              {
                id: 4,
                name: 'Margaery Tyrell',
                email: 'MargaeryT@gmail.com',
                role: 'CAN',
              },
              {
                Id: 5,
                name: 'Rob Stark',
                email: 'RobStark@gmail.com',
                role: 'CAN',
              }],
          token: 3
      };
        return of(new HttpResponse({status: 200, body: data}));
      }
      if (request.url.endsWith('/api/tests/getAll') && request.method === 'GET') {
        const data = {
            list:
            [{
              id: 1,
              name: 'Tester Competency Test',
              redactor: 'John Smith',
              position: 'Tester',
              candidate: '',
              versions: [{
                language: 'ENG',
                questions: [{
                  type: 'OPEN',
                  description: 'What is your timeline for implementing/ purchasing this type of service/ product?',
                  answer: ''
                },
                  {
                  type: 'CHOOSE',
                  description: 'Who else is involved in this decision?',
                  options: ['answer a', 'answer b', 'answer c', 'answer d'],
                    answer: ''
                },
                  {
                  type: 'SCALE',
                  description: 'What do you see as the next action steps?',
                  scale: [0, 10],
                    answer: ''
                },
                  {
                  type: 'NUMBER',
                  description: 'How do you measure that?',
                    answer: ''
                }]
              }]
            },
              {
              id: 1,
              name: 'QA Competency Test',
              redactor: 'Jon Snow',
              position: 'Programmer',
              candidate: '',
              versions: [{
                language: 'ENG',
                questions: [{
                  type: 'OPEN',
                  description: 'What is your timeline for implementing/ purchasing this type of service/ product?',
                  answer: ''
                },
                  {
                  type: 'CHOOSE',
                  description: 'Who else is involved in this decision?',
                    options: ['answer a', 'answer b', 'answer c', 'answer d'],
                    answer: ''
                },
                  {
                  type: 'SCALE',
                  description: 'What do you see as the next action steps?',
                  scale: [0, 10],
                    answer: ''
                },
                  {
                  type: 'NUMBER',
                  description: 'How do you measure that?',
                    answer: ''
                }]
              },
                {
                language: 'POL',
                questions: [{
                  type: 'OPEN',
                  description: 'What is your timeline for implementing/ purchasing this type of service/ product?',
                  answer: ''
                },
                  {
                    type: 'CHOOSE',
                    description: 'Who else is involved in this decision?',
                    options: ['answer a', 'answer b', 'answer c', 'answer d'],
                    answer: ''
                  },
                  {
                    type: 'SCALE',
                    description: 'What do you see as the next action steps?',
                    scale: [0, 10],
                    answer: ''
                  },
                  {
                    type: 'NUMBER',
                    description: 'How do you measure that?',
                    answer: ''
                  }]
              }
              ]
            }],
          token: 3
      };
        return of(new HttpResponse({status: 200, body: data}));
      }
      if (request.url.endsWith('/api/position/getAll') && request.method === 'GET') {
        const data =
          { list:
              [{
                id: 1,
                name: 'Accountant',
                active: true,
              },
                {
                id: 2,
                name: 'Manager',
                active: true,
              },
                {
                id: 3,
                name: 'Programmer',
                active: true,
              },
                {
                id: 4,
                name: 'Tester',
                active: false,
              },
                {
                id: 5,
                name: 'QA',
                active: false,
              }],
            token: 3
          };
        return of(new HttpResponse({status: 200, body: data}));
      }
    }));
  }
}

export let fakeBackendProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};
