import { Machine, Part, Piece } from './../../../models/machin/machine.interface';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MachinsService } from '../../../service/machins.service';
import { Subscription } from 'rxjs';
import { log } from 'node:console';

@Component({
  selector: 'app-create-maschine',
  templateUrl: './create-maschine.component.html',
  styleUrl: './create-maschine.component.scss'
})
export class CreateMaschineComponent implements OnInit,OnDestroy{
  machine: Machine[] = [];
  parts: Part[] = [];
  pieces: Piece[] = [];
  machinesSubscription!: Subscription;
  showAddMachine: boolean = false;
  showPezzi: boolean = false;
  selectedMachineId: Machine | null = null;
  constructor(private machineSrv: MachinsService) { }

  ngOnInit(): void {
    this.machineSrv.getMachines();
    this.machinesSubscription = this.machineSrv.machines$.subscribe(
      (machines: Machine[]) => {
        this.machine = machines;
      //  console.log('Machines in component:', this.machine);
        this.extractPartsAndPieces();
      }
    );
  }

  ngOnDestroy(): void {
    this.machinesSubscription.unsubscribe();
  }

  private extractPartsAndPieces(): void {
    const partsMap = new Map<number, Part>();
    const piecesMap = new Map<number, Piece>();
  
    
    if (this.machine) {
      this.machine.forEach(machine => {
        if (machine.parts) {
          machine.parts.forEach(part => {
            partsMap.set(part.id, part);
            if (part.pieces) {
              part.pieces.forEach(piece => {
                piecesMap.set(piece.id, piece);
              });
            }
          });
        }
      });
    }
  
    this.parts = Array.from(partsMap.values());
    this.pieces = Array.from(piecesMap.values());
  }
  addParti()
  {
    this.showPezzi = !this.showPezzi;
    this.showAddMachine = false;
  }
  addPezzi(){
    
  }
 
  addMachine() {
 //   console.log('Add machine');
    this.showAddMachine = !this.showAddMachine;
    this.showPezzi = false;
  }
  machineSelected(id: Machine | null) {
    this.selectedMachineId = id;
    console.log('Machine selected:', this.selectedMachineId);
  }

}