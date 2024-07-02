import { machine } from 'node:os';
import { StatusMachineService } from './../../../service/status-machine.service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { StatusMachine } from '../../../models/machin/status-machine.interface';
import { StatoMaschineEnum } from '../../../models/machin/machine.interface';

@Component({
  selector: 'app-status-maschine',
  templateUrl: './status-maschine.component.html',
  styleUrl: './status-maschine.component.scss'
})
export class StatusMaschineComponent implements OnInit, OnDestroy{
  machineStatus: StatusMachine[]=[];
  constructor(private machineStatusSrv: StatusMachineService) { }

  ngOnInit(): void {
    this.machineStatusSrv.getMachineStatus().subscribe((data: StatusMachine[]) => {
      console.log(data);
      this.machineStatus = this.sortMachineStatus(data);
    });
    this.machineStatusSrv.Connect();

  }
  sortMachineStatus(statusList: StatusMachine[]): StatusMachine[] {
    const order = [
      StatoMaschineEnum.GUASTA,
      StatoMaschineEnum.MANUTENZIONE,
      StatoMaschineEnum.ATTIVA,
      StatoMaschineEnum.INATTIVA
    ];
    return statusList.sort((a, b) => order.indexOf(a.statoMaschine) - order.indexOf(b.statoMaschine));
  }
  ngOnDestroy(): void {
    this.machineStatusSrv.Disconnect();
  }

}
