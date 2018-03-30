import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { TiketsComponent } from './tikets/tikets.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { AddTicketFormComponent } from './add-ticket-form/add-ticket-form.component';
import { AddBusFormComponent } from './add-bus-form/add-bus-form.component';
import { AddCityFormComponent } from './add-city-form/add-city-form.component';
import { AddRoleFormComponent } from './add-role-form/add-role-form.component';
import { AppRoutingModule } from './/app-routing.module';


@NgModule({
  declarations: [
    AppComponent,
    TiketsComponent,
    NavigationBarComponent,
    LoginFormComponent,
    RegistrationFormComponent,
    AddTicketFormComponent,
    AddBusFormComponent,
    AddCityFormComponent,
    AddRoleFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent, TiketsComponent]
})
export class AppModule { }
