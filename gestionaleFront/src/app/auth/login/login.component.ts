import { Competenze } from './../../models/competenze.interface';
import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { StatoRegister } from '../../models/stato-register.interface';
import { log } from 'console';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {

  errorMessage = '';
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  state!:StatoRegister;
  validazione=false;
  colesePP=true;
  constructor(
    private fb: FormBuilder,
    private authSrv: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
  
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false],
    });

    this.registerForm = this.fb.group(
      {
        nome: ['', Validators.required],
        cognome: ['', Validators.required],
        email: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        repeatPassword: ['', [Validators.required, Validators.minLength(6)]],
        dataDiNascita: ['', Validators.required],
        comuneDiNascita: ['', Validators.required],
        codiceFiscale: ['', Validators.required],
        telefono: ['', Validators.required],
        indirizzo: ['', Validators.required],
        cap: ['', Validators.required],
        provincia: ['', Validators.required],
        terms: [false, Validators.requiredTrue],
        sesso: ['', [Validators.required]],
      },
      { validator: this.passwordMatchValidator }
    );

    this.authSrv.state$.subscribe((state) => {
      this.state = state;
     // console.log('State:', this.state);
    });
    this.checkRememberMe();
  }

  onLoginSubmit(): void {
    if (this.loginForm.valid) {
     // const { email, password, rememberMe } = this.loginForm.value;
      this.authSrv.login(this.loginForm.value).subscribe(
        
        (response) => {
          if (response) 
          this.router.navigate(['/']);
        },
        (error) => {
          // alert(error);
          this.handleError(error);
        }
      );
    }
  }
  private checkRememberMe(): void {
    if (this.authSrv.isLoggedIn()) {
      this.router.navigate(['/']); // Reindirizza se l'utente è già autenticato
    }
  }
  onRegisterSubmit(): void {
    //console.log('Register form value:', this.registerForm.value);
    if (this.registerForm.valid) {
   //   console.log('Register form value: ', this.registerForm.value);
     // this.popupVisible = true;
     this.errorMessage = 'Esegui la verifica dopo la registrazione clicca qui';
      this.state.popupVisible = true; 
      this.authSrv.register(this.registerForm.value).subscribe(
        (response) => {
          this.validazione=true;
          this.colesePP=false;
          this.state.id = response; 

          this.authSrv.setState(this.state);
       //  this.id = response;
        },
        (error) => {
          this.registerForm.reset();
          this.state.popupVisible = false; 
          this.handleError(error);
        }
      );
    }
  }
  closePopup(){
    this.state.popupVisible = false;
    this.authSrv.setState(this.state);
  }
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const repeatPassword = formGroup.get('repeatPassword')?.value;
    if (password !== repeatPassword) {
      formGroup.get('repeatPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('repeatPassword')?.setErrors(null);
    }
  }

  
  checkEmailConfirmedState(): void {
   // this.colesePP=true;
    this.authSrv.getEmailConfirmedState(this.registerForm.value).subscribe(
      (confirmed) => {
        if(confirmed===true){
          this.validazione=false;
         /*  this.state.competenze = confirmed; */
       this.state.popupVisible = false;
      //  this.authSrv.setState(this.state);
         
      }else{
          /* this.state.competenze = confirmed; */
          this.state.popupVisible = true;
        } 
      },
      (error) => {
        this.handleError(error);
  
      }
    );
  }
  handleError(error: any): void {
    console.error('Si è verificato un errore:', error.message);
    this.errorMessage = error.message; // Salva il messaggio di errore
        
  }
}
