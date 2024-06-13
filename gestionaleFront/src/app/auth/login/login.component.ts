import { Competenze } from './../../models/competenze.interface';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent implements OnInit {
  competenzaForm!: FormGroup;
  loginForm!: FormGroup;
  registerForm!: FormGroup;
  popupVisible: boolean = false;
  competenze:boolean = false;
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

    this.competenzaForm = this.fb.group({
      nomeCompetenza: ['', Validators.required],
      descrizione: ['', Validators.required],
      idRisorsa: ['', Validators.required],
      livello: [ , Validators.required],
    });
  }

  onLoginSubmit(): void {
    if (this.loginForm.valid) {
      this.closePopup();
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
     /*  const formData = { ...this.registerForm.value };
      if (Array.isArray(formData.ruoloId)) {
        formData.ruoloId = formData.ruoloId.map(Number);
      } else {
        formData.ruoloId = [Number(formData.ruoloId)]; // Trasforma il valore in un array se non lo è
      } */
      console.log('Register form value: ', this.registerForm.value);
      this.authSrv.register(this.registerForm.value).subscribe(
        () => {
          this.closePopup();
          setTimeout(() => {
            this.registerForm.reset();
            this.closePopup();
            this.competenze=!this.competenze;

          }, 2000);
        },
        (error) => {
          alert(error);
        }
      );
    }
  }
  // Custom validator function to check if password and repeatPassword match
  passwordMatchValidator(formGroup: FormGroup) {
    const password = formGroup.get('password')?.value;
    const repeatPassword = formGroup.get('repeatPassword')?.value;

    if (password !== repeatPassword) {
      formGroup.get('repeatPassword')?.setErrors({ passwordMismatch: true });
    } else {
      formGroup.get('repeatPassword')?.setErrors(null);
    }
  }
  closePopup(): void {
    this.popupVisible = !this.popupVisible; // Nascondi il popup
  }
  onCompetenzaSubmit(): void {
    console.log('Dati della competenza da inviare:', this.competenzaForm.value);
    if (this.competenzaForm.valid) {
      // Invia il form solo se è valido
      const formData = { ...this.competenzaForm.value };
      formData.idRisorsa = Number(formData.idRisorsa);
      formData.livello = Number(formData.livello);
      formData.usersId = Array.isArray(formData.usersId) ? formData.usersId.map(Number): [Number(formData.usersId)];

      console.log('Dati della competenza da inviare:', formData);
      this.closePopup();
      setTimeout(() => {
        this.closePopup();
        this.competenzaForm.reset();
      }, 2000);
    } else {
      // Segna tutti i campi come toccati per mostrare gli errori di validazione
      this.competenzaForm.markAllAsTouched();
    }
  }
}
