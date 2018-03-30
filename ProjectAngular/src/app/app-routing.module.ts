import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginFormComponent } from './login-form/login-form.component';

@NgModule({
  imports: [RouterModule.forRoot([
      {
          path: '',
          redirectTo: 'login',
          pathMatch: 'full'
      },
      {
          path: 'login',
          component: LoginFormComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'changepassword',
          component: ChangePasswordFormComponent,
          canActivate: [AuthenticatedGuard]
      },
      {
          path: 'registration',
          component: RegistrationFormComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'successfulregistration',
          component: SuccessfulRegistrationComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'confirm',
          component: ConfirmFormComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'forgotpassword',
          component: ForgotFormComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'resetpassword',
          component: ResetFormComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'succesfully',
          component: SuccesfullySentComponent,
          canActivate: [AnonymousGuard]
      },
      {
          path: 'wishlist',
          component: WishlistComponent,
          canActivate: [AuthenticatedGuard]
      },
      {
          path: 'adding-good',
          component: AddingGoodComponent,
          canActivate: [AuthenticatedGuard]
      },
      {
          path: 'logs',
          component: LogWrapperComponent,
          canActivate: [AdminGuard]
      },
      {
          path: 'logs/:page',
          component: LogWrapperComponent,
          canActivate: [AdminGuard]
      },
      {
          path: 'adminpanel/:page',
          component: UserTableComponent,
          canActivate: [AdminGuard]
      },
  ])],
  exports: [RouterModule] // делаем re-export модуля для использования директив при маршрутизации
})
export class AppRoutingModule {}
