import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration} from '@angular/platform-browser';
import { HttpClientModule, provideHttpClient, withFetch } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { ReactiveFormsModule } from '@angular/forms';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { TokenInterceptor } from './auth/token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { MdbModalModule } from 'mdb-angular-ui-kit/modal';
import { EmailVerifiedComponent } from './auth/email-verified/email-verified.component';
import { CompetenzeComponent } from './components/competenze/competenze.component';
/* import { TicketsComponent } from './components/tickets/tickets.component'; */
/* import { CreateTicketsComponent } from './components/tickets/create-tickets/create-tickets.component'; */
/* import { MaschineComponent } from './components/maschine/maschine.component'; */
/* import { CreateMaschineComponent } from './components/maschine/create-maschine/create-maschine.component';
import { StatusMaschineComponent } from './components/maschine/status-maschine/status-maschine.component'; */
import { SettingComponent } from './components/setting/setting.component';
import { ChatTiketComponent } from './components/chat-tiket/chat-tiket.component';
import { SublevelMenuComponent } from './components/nav-bar/sublevel-menu.component';
import { TiketsRouterModule } from './components/tickets/tikets-router.module';
import { MashineModule } from './components/maschine/maschine.module';
import { MashineRouteModule } from './components/maschine/maschine-route.module';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    NavBarComponent,
    EmailVerifiedComponent,
    CompetenzeComponent,
    /* TicketsComponent, */
   /*  CreateTicketsComponent, */
   /*  MaschineComponent, */
   /*  CreateMaschineComponent,
    StatusMaschineComponent, */
    SettingComponent,
    ChatTiketComponent,
    SublevelMenuComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MdbDropdownModule,
    MdbRippleModule,
    MdbCollapseModule,
    ReactiveFormsModule,
    MdbFormsModule,
    MdbTabsModule,
    HttpClientModule,
    MdbModalModule,
    TiketsRouterModule,
    MashineModule,
    MashineRouteModule,
    
  ],
  providers: [
    provideClientHydration(),
    
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true,
    },
    provideHttpClient(withFetch())
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }