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
    this.machineStatusSrv.Connect();
    this.machineStatusSrv.getStatusMachine$().subscribe((data: StatusMachine[]) => {
      console.log(data);
      this.machineStatus = this.sortMachineStatus(data);
    });

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
  downloadPdf(pdfContent: string): void {
    // Decode Base64 to binary
    const binaryPdf = atob(pdfContent);

    // Convert binary to Uint8Array
    const bytes = new Uint8Array(binaryPdf.length);
    for (let i = 0; i < binaryPdf.length; i++) {
      bytes[i] = binaryPdf.charCodeAt(i);
    }

    // Create Blob object from Uint8Array
    const blob = new Blob([bytes], { type: 'application/pdf' });

    // Generate a unique file name (you can customize this as needed)
    const fileName = `machine_status_${new Date().getTime()}.pdf`;

    // Create a temporary URL for the Blob object
    const url = window.URL.createObjectURL(blob);

    // Create a link element to trigger the download
    const a = document.createElement('a');
    a.href = url;
    a.download = fileName;
    document.body.appendChild(a); // Append anchor to body
    a.click(); // Trigger the download
    document.body.removeChild(a); // Remove anchor from body once done

    // Clean up the temporary URL created for the Blob
    window.URL.revokeObjectURL(url);
  }

}
