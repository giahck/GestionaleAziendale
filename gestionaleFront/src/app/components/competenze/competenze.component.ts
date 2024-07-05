import { UsersService } from './../../service/users.service';
import { MachinaCompetenza } from './../../models/machin/machina-competenza.interface';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth/auth.service';
import { MachinsService } from '../../service/machins.service';
import { Subscription } from 'rxjs';
import { UserDati } from '../../models/user-dati.interface';
import { log } from 'console';
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
  userDati: UserDati[] = [];
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
      //  console.log('Macchine:', this.macchine);
      },
      (error) => {
        this.handleError(error);
      }
    );
      this.usersSubscription=this.usersSrv.getUserDati$().subscribe((userDati:UserDati[]) => {
      this.userDati = userDati;
     //   console.log('UserDati:', userDati);
      }
    );
    this.competenzaForm = this.fb.group({
      nomeCompetenza: ['', Validators.required],
      descrizione: ['', Validators.required],
      machineId: ['',],
      livello: [, Validators.required],
      userId: [, ],
    });
  }
  ngOnDestroy():void{
    this.usersSubscription.unsubscribe();
  }
  onCompetenzaSubmit(): void {
    console.log('Dati della competenza da inviare:', this.competenzaForm.value);
    if (this.competenzaForm.valid) {
      const formData = { ...this.competenzaForm.value };
      //formData.machineId = Number(formData.machineId);
      formData.livello = Number(formData.livello);
      if (formData.userId)
      formData.usersId = [Number(formData.userId)];
      console.log('Dati della competenza da inviare:', formData);

      //this.popupVisible = true;
       this.competenzaForm.reset();
      this.authSrv.competenze(formData).subscribe(
        (response) => {
          if (response) {
         //   console.log('Competenza aggiunta:', response);
            this.competenzaForm.reset();
            this.competenzaForm.patchValue({
              machineId: '',
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
      this.showErrorAlert = true;
      setTimeout(() => (this.showErrorAlert = false), 5000);
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
