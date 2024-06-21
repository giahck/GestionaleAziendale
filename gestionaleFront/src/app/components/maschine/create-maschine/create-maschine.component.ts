import { Machine, Part, Piece } from './../../../models/machin/machine.interface';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MachinsService } from '../../../service/machins.service';
import { Subscription } from 'rxjs';
import { log } from 'console';

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

  constructor(private machineSrv: MachinsService) { }

  ngOnInit(): void {
    this.machinesSubscription = this.machineSrv.machines$.subscribe(
      (machines: Machine[]) => {
        this.machine = machines;
        console.log('Machines in component:', this.machine);
        this.extractPartsAndPieces();
      }
    );
    this.machineSrv.getMachines();
  }

  ngOnDestroy(): void {
    this.machinesSubscription.unsubscribe();
  }

  private extractPartsAndPieces(): void {
    const partsMap = new Map<number, Part>();
    const piecesMap = new Map<number, Piece>();
    this.machine.forEach(machine => {
      machine.parts.forEach(part => {
        partsMap.set(part.id, part);
        part.pieces.forEach(piece => {
          piecesMap.set(piece.id, piece);
        });
      });
    });

    this.parts = Array.from(partsMap.values());
    this.pieces = Array.from(piecesMap.values());
  }

 
  addMachine() {
    console.log('Add machine');
    this.showAddMachine = !this.showAddMachine;
  }
}