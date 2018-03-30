import { Injectable, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Response, Headers, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import 'rxjs/add/observable/fromPromise';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { OAuthService, OAuthStorage } from 'angular-oauth2-oidc';
import { ErrorObservable } from 'rxjs/observable/ErrorObservable';
import { catchError, retry } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class AuthenticationService {

    // internal server error is here
    public serverError: string;

    // event will notify all subscribers when user login/logout
    public authChanged: EventEmitter<any> = new EventEmitter();

    public constructor(private oauthService: OAuthService,
        private httpClient: HttpClient,
        private router: Router) { }

    // login user using oauth2 oidc lib
    public login(login: string, password: string): Promise<any> {
        return this.oauthService.fetchTokenUsingPasswordFlow(login, password)
        .then((resp: any) => {
            const user = { name: resp.user_name, id: resp.user_id, isAdmin: resp.is_admin };
            localStorage.setItem('user', JSON.stringify(user)); // save info about user
            this.authChanged.emit();
        });
    }

    // logout user using oauth2 oidc lib
    public logout(): void {
        localStorage.removeItem('user');
        this.oauthService.logOut();
        this.authChanged.emit();
        this.router.navigate(['/']);
    }

    // refresh user using oauth2 oidc lib
    public refreshToken(): Promise<object> {
            return this.oauthService.refreshToken();
    }

    // login of current user or null
    public get userName(): string {
        return this.user ? this.user.name : null;
    }

     // id of current user or null
    public get userId(): string {
        return this.user ? this.user.id : null;
    }

    // check wether current user is admin
    public get isAdmin(): boolean {
        return this.user ? JSON.parse(this.user.isAdmin) : false;
    }

    // current access token or null
    public get token(): string {
        return this.oauthService.getAccessToken();
    }

    // checks state of token
    public get isTokenExpire(): boolean {
        const expireDate = this.oauthService.getAccessTokenExpiration();
        const currentDate = (new Date).getTime();
        return currentDate >= expireDate;
    }

    // userinfo in json format
    private get user(): any {
        return JSON.parse(localStorage.getItem('user'));
    }

    public registration(email: string,
        login: string,
        password: string,
        confirmPassword: string): Observable<any> {
        return this.httpClient.post<any>('api/account/register',
            { login: login, password: password});
    }

    public confirm(id: string, token: string): Observable<any> {
        return this.httpClient.post<any>('api/account/AccountConfirm',
            { code: token, id: id });
    }
}
