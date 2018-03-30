import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class AnonymousGuard implements CanActivate {
  constructor(private authenticateService: AuthenticationService, private router: Router) { }

  // redirect authenticated user, when he tries to get such pages as login, register, etc.
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (!this.authenticateService.userName) {
      return true;
    }
    this.router.navigate(['/login']);
  }
}
