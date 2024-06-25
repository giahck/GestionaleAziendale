import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Machine, Part } from '../../../../models/machin/machine.interface';
import { PezziEpartiService } from '../../../../service/pezzi-eparti.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-menu-create-pezzi',
  templateUrl: './menu-create-pezzi.component.html',
  styleUrl: './menu-create-pezzi.component.scss'
})
export class MenuCreatePezziComponent implements OnInit, OnDestroy{
  addPezziForm!: FormGroup;
  isFormSubmitted = false;
  showSuccessAlert = false;
  showErrorAlert = false;
  messageAlert:String = '';
  showModal: boolean = false;
  parti:Part[]=[];
  @Input() selectedMachineId: Machine | null = null;
  private subscriptions: Subscription = new Subscription();
  constructor(private fb: FormBuilder, private pezziPartSrv:PezziEpartiService) {}
  ngOnInit(): void {
      this.addPezziForm = this.fb.group({
      nomeParte: ['', Validators.required],
      descrizione: ['', Validators.required],
      note: [''],
      quantityParts: [1, Validators.required],
      machineId: [null],
      });
      
  }
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
  onPezziSubmit(){
    console.log('Selected machine:', this.selectedMachineId);
    this.isFormSubmitted = true;
    if (this.addPezziForm.valid && this.selectedMachineId) {
      this.showSuccessAlert = true;
      this.addPezziForm.patchValue({machineId: this.selectedMachineId.id});
/*       this.addPezziForm.patchValue({machine: this.selectedMachineId}); */
      this.parti.push({...this.addPezziForm.value, machine: this.selectedMachineId});
      setTimeout(() => (this.showSuccessAlert = false), 5000);
      this.addPezziForm.reset({quantityParts:1,nomeParte:'',descrizione:'',note:''});
     // console.log('Parti:', this.parti);
    }else if(!this.selectedMachineId && this.addPezziForm.invalid){
      this.showErrorAlert = true;
      this.messageAlert = 'Seleziona una macchina a cui vui assegnare la parte\nCompilare tutti i campi obbligatori segnati in rosso';
      setTimeout(() => (this.showErrorAlert = false), 5000);
    }
    else if (!this.selectedMachineId) {
      this.showErrorAlert = true;
      this.messageAlert = 'Seleziona una macchina a cui vui assegnare la parte';
      setTimeout(() => (this.showErrorAlert = false), 5000);
    }else if (this.addPezziForm.invalid) {
      this.showErrorAlert = true;
      this.messageAlert = 'Compilare tutti i campi obbligatori segnati in rosso';
      setTimeout(() => (this.showSuccessAlert = false), 5000);
    }

  }
  addPartSrv(){
   this.showModal=true;
  }
  closeModal() {
    this.showModal = false;
  }
  addPatiSave() {
    if (this.parti.length > 0) {
      console.log('Parti:', this.parti);
      this.showModal = false;
      const submitSubscription=this.pezziPartSrv.postParti(this.parti).subscribe((machine: Machine[]) => {
        //console.log('Machine:', machine);
        this.parti = [];
        this.showSuccessAlert = true;
        setTimeout(() => (this.showSuccessAlert = false), 5000);
      }, error => {
        this.messageAlert = 'Si è verificato un errore durante il salvataggio delle parti';
        this.showErrorAlert = true;
        setTimeout(() => (this.showErrorAlert = false), 5000);
      });
      this.subscriptions.add(submitSubscription);
    }
  }
  closeAlert() {
    this.showSuccessAlert = false;
    this.showErrorAlert = false;
  }
}
