import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { register } from 'module';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  registerForm: FormGroup;
  
  constructor(private fb: FormBuilder,private authSrv: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      rememberMe: [false]
    });

    this.registerForm = this.fb.group({
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
      ruoloId: [[], Validators.required], 
      terms: [false, Validators.requiredTrue],
      sesso: ['', [Validators.required]]
    }, { validator: this.passwordMatchValidator });
  }

  ngOnInit(): void {}

  onLoginSubmit(): void {
    if (this.loginForm.valid) {
      // Handle login
      console.log('Login form value:', this.loginForm.value);
      this.authSrv.login(this.loginForm.value).subscribe(
        () => {
          alert('Login completed!');
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
      const formData = { ...this.registerForm.value }; 
      if (Array.isArray(formData.ruoloId)) {
        formData.ruoloId = formData.ruoloId.map(Number);
      } else {
        formData.ruoloId = [Number(formData.ruoloId)]; // Trasforma il valore in un array se non lo è
      }
      console.log('Register form value: ', formData	);
      this.authSrv.register(formData).subscribe(
        () => {
          alert('Registration completed!');
          this.router.navigate(['/login']);
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
}