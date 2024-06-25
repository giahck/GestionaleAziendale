import { Subscription } from 'rxjs';
import { UserDati } from './../../../../models/user-dati.interface';
import { Machine, StatoMaschineEnum } from '../../../../models/machin/machine.interface';
import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from '../../../../service/users.service';
import { MachinsService } from '../../../../service/machins.service';

@Component({
  selector: 'app-menu-create',
  templateUrl: './menu-create.component.html',
  styleUrl: './menu-create.component.scss'
})
export class MenuCreateComponent implements OnInit,OnDestroy{
  addMachineForm!: FormGroup;
  statoMaschineEnum = StatoMaschineEnum;
  statoMaschineValues = Object.values(StatoMaschineEnum); // Ottieni i valori dell'enum
  isFormSubmitted = false;
  imageUrl: string | ArrayBuffer | null = null;
  userDati: UserDati[]= [];
  usersSubscription!:Subscription;
  showModal: boolean = false;
  selectedUserId!: number;
  selectedMachineId!: number;
  selectedCompetenzaId!: number;
  partSelectedId!:number;
  constructor(private machineSrv:MachinsService, private fb: FormBuilder,private userDatisrv:UsersService) {}

  ngOnInit(): void {
      this.usersSubscription=this.userDatisrv.getUserDati$().subscribe((userDati:UserDati[]) => {
      this.userDati = userDati;
      //  console.log('UserDati:', this.userDati);
      }
    );
    this.addMachineForm = this.fb.group({
      nomeMacchina: ['', Validators.required],
      marca: [''],
      matricola: [''],
      modello: [''],
      dataAcquisto: [new Date().toISOString().split('T')[0]],
      statoMaschine: ['', Validators.required],
      description: ['', Validators.required],
      photo: ['', Validators.required],
      competenzaId: [null] ,
    });
  }
  ngOnDestroy():void{
    this.usersSubscription.unsubscribe();
  }
  get statoMaschin() {
    return this.addMachineForm.get('statoMaschin');
  }
  onFileChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files[0]) {
      const file = input.files[0];
      this.addMachineForm.patchValue({
        photo: file
      });
      this.addMachineForm.get('photo')?.updateValueAndValidity();

      const reader = new FileReader();
      reader.onload = () => {
        this.imageUrl = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  onMachineSubmit() {
    this.isFormSubmitted = true;
    if (this.addMachineForm.valid) {
      this.showModal = true;
      console.log(this.addMachineForm.value);
    } else {
      console.log('Il form non è valido.',this.addMachineForm.value);
    }
  }
  closeModal() {
    this.showModal = false;
  }
  get selectedUser() {
    return  this.userDati.find(user => user.id ===+ this.selectedUserId);
  }
  onCompetenzaClick(idCompetenze:number) {
    this.selectedCompetenzaId = idCompetenze;
   // console.log('Competenza selezionata:', this.selectedCompetenzaId)
  }
  addMacchina(): void {
    if (this.selectedUserId) {
      this.addMachineForm.patchValue({ competenzaId: this.selectedCompetenzaId });
      console.log('Form:', this.addMachineForm.value);
        } 
  
      const machineData = { ...this.addMachineForm.value };
      delete machineData.photo;
  
      const formData = new FormData();
      formData.append('genericMachineDto', new Blob([JSON.stringify(machineData)], { type: 'application/json' }));
  
      const photoFile = this.addMachineForm.get('photo')?.value;
      if (photoFile) {
        formData.append('photo', photoFile);
      }
      this.machineSrv.postMaschine(formData).subscribe(
        (machine: Machine) => {
          console.log('Macchina aggiunta:', machine);
          this.machineSrv.getMachines();
          this.closeModal();
        },
        (error) => {
          console.error('Errore durante l\'aggiunta della macchina:', error);
        }
      );
  }
}
