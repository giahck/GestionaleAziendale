import { Competenze } from './../../models/competenze.interface';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { get } from 'http';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  
  competenzaForm!: FormGroup;
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  state = {
    popupVisible: false,
    competenze: false,
    id: null as number | null
  };
  
  constructor(
    private fb: FormBuilder,
    private authSrv: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
  //  if (typeof sessionStorage !== 'undefined') {
      const storedState = sessionStorage.getItem('authState');
      console.log(storedState,'storedState');
      if (storedState) {
        this.state = JSON.parse(storedState);
      }
   // }
    console.log(this.authSrv.state$,'state$ ');
    console.log(this.state)
    this.authSrv.state$.subscribe((state) => {
      this.state = state;
      console.log('state: ', state);
    });
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

    this.competenzaForm = this.fb.group({
      nomeCompetenza: ['', Validators.required],
      descrizione: ['', Validators.required],
      idRisorsa: ['', Validators.required],
      livello: [ , Validators.required],
    });
  }

  onLoginSubmit(): void {
    if (this.loginForm.valid) {
      
      this.authSrv.login(this.loginForm.value).subscribe(
        
        (response) => {
          if (response) 
          this.router.navigate(['/']);
        },
        (error) => {
          // alert(error);
          console.error(error);
        }
      );
    }
  }

  onRegisterSubmit(): void {
    console.log('Register form value:', this.registerForm.value);
    if (this.registerForm.valid) {
      console.log('Register form value: ', this.registerForm.value);
     // this.popupVisible = true;
      this.state.popupVisible = true; 
      this.authSrv.register(this.registerForm.value).subscribe(
        (response) => {
          this.state.id = response; 
          this.authSrv.setState(this.state);
       //  this.id = response;
        },
        (error) => {
          alert(error);
        }
      );
    }
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

  onCompetenzaSubmit(): void {
    console.log('Dati della competenza da inviare:', this.competenzaForm.value);
    if (this.competenzaForm.valid) {
      const formData = { ...this.competenzaForm.value };
      formData.idRisorsa = Number(formData.idRisorsa);
      formData.livello = Number(formData.livello);
      formData.usersId = Array.isArray(formData.usersId) ? formData.usersId.map(Number): [Number(formData.usersId)];

      console.log('Dati della competenza da inviare:', formData);
        //this.popupVisible = true; 
        this.competenzaForm.reset();
        this.authSrv.competenze(this.competenzaForm.value).subscribe(
        
          (response) => {
            if (response) 
            this.router.navigate(['/login']);
          },
          (error) => {
            // alert(error);
            console.error(error);
          }
        );
    
    } else {
      // Segna tutti i campi come toccati per mostrare gli errori di validazione
      this.competenzaForm.markAllAsTouched();
    }
  }
  checkEmailConfirmedState(): void {
    this.authSrv.getEmailConfirmedState().subscribe(
      (confirmed) => {
        if(confirmed===true){
          this.state.competenze = confirmed;
       this.state.popupVisible = false;
        this.authSrv.setState(this.state);
      }else{
          this.state.competenze = confirmed;
          this.state.popupVisible = true;
        } 
      },
      (error) => {
        console.error('Error getting email confirmation state:', error);
  
      }
    );
  }
}
