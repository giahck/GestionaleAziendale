import { StatoMaschineEnum } from '../../../../models/machin/machine.interface';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-menu-create',
  templateUrl: './menu-create.component.html',
  styleUrl: './menu-create.component.scss'
})
export class MenuCreateComponent implements OnInit{
  addMachineForm!: FormGroup;
  statoMaschineEnum = StatoMaschineEnum;
  statoMaschineValues = Object.values(StatoMaschineEnum); // Ottieni i valori dell'enum
  isFormSubmitted = false;
  imageUrl: string | ArrayBuffer | null = null;
  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.addMachineForm = this.fb.group({
      nomeMacchina: ['', Validators.required],
      marca: [''],
      matricola: [''],
      modello: [''],
      dataAcquisto: [new Date().toISOString().split('T')[0]],
      statoMaschin: ['', Validators.required],
      description: ['', Validators.required],
      photo: ['', Validators.required]
    });
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
      console.log(this.addMachineForm.value);
      // Implementa la logica per inviare il form
    } else {
      console.log('Il form non è valido.',this.addMachineForm.value);
    }
  }
}
