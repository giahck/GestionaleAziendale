import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { Router } from '@angular/router';
import { StatoRegister } from '../../models/stato-register.interface';
import { MachinsService } from '../../service/machins.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-competenze',
  templateUrl: './competenze.component.html',
  styleUrl: './competenze.component.scss',
})
export class CompetenzeComponent implements OnInit {
  showSuccessAlert = false;
  showErrorAlert = false;
  competenzaForm!: FormGroup;
 // usersSubscription!:Subscription;
 // state!: StatoRegister;
  constructor(
    private fb: FormBuilder,
    private authSrv: AuthService,
    private router: Router,
    private maschine:MachinsService
  ) {}
  skip(){
    this.authSrv.setState({
      competenze: false,
      popupVisible: false,
      id: null
    });
  }
  ngOnInit(): void {


    this.competenzaForm = this.fb.group({
      nomeCompetenza: ['', Validators.required],
      descrizione: ['', Validators.required],
      idRisorsa: ['', Validators.required],
      livello: [, Validators.required],
    });
  }
  onCompetenzaSubmit(): void {
    const userString = localStorage.getItem('user');
    console.log('Dati della competenza da inviare:', this.competenzaForm.value);
    if (this.competenzaForm.valid&&userString) {
      const userObject = JSON.parse(userString);
      const userId = userObject.id;
      const formData = { ...this.competenzaForm.value };
      formData.idRisorsa = Number(formData.idRisorsa);
      formData.livello = Number(formData.livello);
      formData.usersId = [userId];
      console.log('Dati della competenza da inviare:', formData);

      console.log('Dati della competenza da inviare:', formData);
      //this.popupVisible = true;
      // this.competenzaForm.reset();
      this.authSrv.competenze(formData).subscribe(
        (response) => {
          if (response) {
            this.competenzaForm.reset();
            this.competenzaForm.patchValue({
              idRisorsa: '',
              livello: '',
            });
          }

          this.showSuccessAlert = true;

          setTimeout(() => (this.showSuccessAlert = false), 5000);
        },
        (error) => {
          this.handleError(error);

          // Mostra l'alert di errore
          this.showErrorAlert = true;

          // Nascondi l'alert dopo 5 secondi (opzionale)
          setTimeout(() => (this.showErrorAlert = false), 5000);

          this.competenzaForm.markAllAsTouched();
        }
      );
    } else {
      // Segna tutti i campi come toccati per mostrare gli errori di validazione
      this.competenzaForm.markAllAsTouched();
    }
  }
  handleError(error: any): void {
    // Qui puoi gestire diversi tipi di errori in modi diversi
    // Ad esempio, potresti voler mostrare un messaggio all'utente
    console.error('Si è verificato un errore:', error);
  }
  closeAlert() {
    this.showSuccessAlert = false;
    this.showErrorAlert = false;
  }
}
