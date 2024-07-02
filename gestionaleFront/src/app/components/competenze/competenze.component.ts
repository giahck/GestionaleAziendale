import { UsersService } from './../../service/users.service';
import { MachinaCompetenza } from './../../models/machin/machina-competenza.interface';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { MachinsService } from '../../service/machins.service';
import { Subscription } from 'rxjs';
import { UserDati } from '../../models/user-dati.interface';
@Component({
  selector: 'app-competenze',
  templateUrl: './competenze.component.html',
  styleUrl: './competenze.component.scss',
})
export class CompetenzeComponent implements OnInit,OnDestroy {
  showSuccessAlert = false;
  showErrorAlert = false;
  macchine!: MachinaCompetenza[];
  competenzaForm!: FormGroup;
  usersSubscription!:Subscription;
 // usersSubscription!:Subscription;
 // state!: StatoRegister;
  constructor(
    private fb: FormBuilder,
    private authSrv: AuthService,
    private maschine:MachinsService,
    private usersSrv: UsersService,
  ) {}
 
  ngOnInit(): void {
    this.maschine.getMachine().subscribe(
      (machines: MachinaCompetenza[]) => {
        this.macchine = machines;
        console.log('Macchine:', this.macchine);
      },
      (error) => {
        this.handleError(error);
      }
    );
      this.usersSubscription=this.usersSrv.getUserDati$().subscribe((userDati:UserDati[]) => {
      //this.userDati = userDati;
        console.log('UserDati:', userDati);
      }
    );
    this.competenzaForm = this.fb.group({
      nomeCompetenza: ['', Validators.required],
      descrizione: ['', Validators.required],
      machineId: ['', Validators.required],
      livello: [, Validators.required],
    });
  }
  ngOnDestroy():void{
    this.usersSubscription.unsubscribe();
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
          this.showErrorAlert = true;
          setTimeout(() => (this.showErrorAlert = false), 5000);
          this.competenzaForm.markAllAsTouched();
        }
      );
    } else {
      this.competenzaForm.markAllAsTouched();
    }
  }
  handleError(error: any): void {
 
    console.error('Si è verificato un errore:', error);
  }
  closeAlert() {
    this.showSuccessAlert = false;
    this.showErrorAlert = false;
  }
}
