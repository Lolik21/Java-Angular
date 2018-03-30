import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule, NgForm } from '@angular/forms';
import { LoginModel } from '../models/login-model';
import { validationMessages } from './validation-messages';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login-form',
    templateUrl: 'login-form.component.html',
    styleUrls: ['login-form.component.css', '../styles.css']
})
export class LoginFormComponent implements OnInit {
    loginForm: FormGroup;
    login: LoginModel = new LoginModel();
    serverError: string;
    isLoading: boolean;


    inputLogin: any;
    inputPassword: any;
    eyeFirstLeft: any;
    eyeFirstRight: any;
    eyeSecondLeft: any;
    eyeSecondRight: any;

    // json for validator
    formErrors = { 'login': '', 'password': '' };

    constructor(private fb: FormBuilder, private authenticationService: AuthenticationService, private router: Router) { }

    ngOnInit() {
        this.buildForm();
        this.inputLogin = document.querySelector('#login');
        this.inputPassword = document.querySelector('#password');
    }

    // register validators for fields
    buildForm() {
        this.loginForm = this.fb.group({
            'login': [this.login.login, [
                Validators.required
            ]],
            'password': [this.login.password, [
                Validators.required]]
        });

        this.loginForm.valueChanges
            .subscribe(data => this.onValueChange(data));

        this.onValueChange();
    }

    onValueChange(data?: any) {
        this.serverError = '';

        if (!this.loginForm) { return; }
        const form = this.loginForm;

        for (const field in this.formErrors) {
            if (this.formErrors.hasOwnProperty(field)) {
                this.formErrors[field] = '';
                const control = form.get(field);
                if (control && control.dirty && !control.valid) {
                    const message = validationMessages[field];
                    for (const key in control.errors) {
                        if (control.errors.hasOwnProperty(key)) {
                            this.formErrors[field] += message[key] + '';
                        }
                    }
                }
            }
        }
    }

    // try to login user
    onSubmit(ngForm: NgForm) {
        this.isLoading = true;

        this.authenticationService.login(ngForm.controls['login'].value, ngForm.controls['password'].value)
            .then(() => {
                this.router.navigate(['/wishlist']);
            },
                err => {
                    this.isLoading = false;
                    const message = err.error ? err.error.error : null;

                    if (err.status === 400 && message !== 'Internal Server Error') {
                        this.serverError = 'Неверный логин или пароль!';
                    } else {
                        this.serverError = 'Связь с сервером прервана';
                    }
                });
    }

    onCheck(): string {
        if (this.serverError !== '') {
            return 'serverError';
        }
    }
}

