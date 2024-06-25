import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Machine, Part, Piece } from '../../../../models/machin/machine.interface';
import { AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { PezziEpartiService } from '../../../../service/pezzi-eparti.service';

@Component({
  selector: 'app-menu-create-parti',
  templateUrl: './menu-create-parti.component.html',
  styleUrl: './menu-create-parti.component.scss',
})
export class MenuCreatePartiComponent implements OnInit, OnDestroy{
  @Input() partSelectedId: Part | null = null;

  addPartiForm!: FormGroup;
  showSuccessAlert = false;
  showErrorAlert = false;
  showModal: boolean = false;
  pezzi: Piece[] = [];
  messageAlert: String = '';
  isFormSubmitted = false;
  
  private subscriptions: Subscription = new Subscription()
  constructor(private fb: FormBuilder,private pezziPartSrv:PezziEpartiService) {}
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
  ngOnInit(): void {
    this.addPartiForm = this.fb.group({
      nomePezzo: ['', Validators.required],
      descrizione: ['', Validators.required],
      seriale: [ ,this.isNumericValidator],
      materiale: [''],
      quantityPiece: [1, Validators.required],
      partsId: [null],
    });
  }

  onPezziSubmit() {
    this.isFormSubmitted = true;
    if (this.addPartiForm.valid && this.partSelectedId) {
      console.log('Selected part:', this.addPartiForm);
      this.addPartiForm.patchValue({ partsId: this.partSelectedId?.id });
      this.pezzi.push({
        ...this.addPartiForm.value,
        part: this.partSelectedId,
      });
      console.log(this.pezzi);
      this.addPartiForm.reset({ quantityPiece: 1 });
      this.showSuccessAlert = true;
      setTimeout(() => (this.showSuccessAlert = false), 5000);
    } else if (!this.partSelectedId && this.addPartiForm.invalid) {
      this.showErrorAlert = true;
      this.messageAlert =
        'Seleziona una macchina a cui vui assegnare la parte\nCompilare tutti i campi obbligatori segnati in rosso';
      setTimeout(() => (this.showErrorAlert = false), 5000);
    } else if (!this.partSelectedId) {
      this.showErrorAlert = true;
      this.messageAlert =
        'Seleziona una macchina e una parte a cui vui assegnare la parte\n se la macchina e sprovista di parte CLICK SU ADD PARTI';
      setTimeout(() => (this.showErrorAlert = false), 5000);
    } else if (this.addPartiForm.invalid) {
      this.showErrorAlert = true;
      this.messageAlert =
        'Compilare tutti i campi obbligatori segnati in rosso';
      setTimeout(() => (this.showSuccessAlert = false), 5000);
    }
  }

  addPezziSrv() {
    this.showModal = true;
  }
  addPezziSave() {
    if (this.pezzi.length > 0) {
      this.showModal = false;
      console.log('Pezzi:', this.pezzi);
      const submitSubscription=this.pezziPartSrv.postPezzi(this.pezzi).subscribe((machine: Machine[]) => {
       // console.log('Machine:', machine);
        this.pezzi = [];
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
  closeModal() {
    this.showModal = false;
  }
  closeAlert() {
    this.showSuccessAlert = false;
    this.showErrorAlert = false;
  }
    
    isNumericValidator(control: AbstractControl): ValidationErrors | null {
      const value = control.value;
      if (value && !isNaN(Number(value))) {
        return null; 
      }
      return { notNumeric: true }; 
    }
}
