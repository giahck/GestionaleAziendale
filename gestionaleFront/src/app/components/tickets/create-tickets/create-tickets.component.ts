import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PriorityTicketEnum, StatoTicketEnum, Ticket } from '../../../models/tiket-add.interface';
import { StatoMaschineEnum } from '../../../models/machin/machine.interface';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { UsersService } from '../../../service/users.service';

@Component({
  selector: 'app-create-tickets',
  templateUrl: './create-tickets.component.html',
  styleUrl: './create-tickets.component.scss',
  animations: [
    trigger('flipState', [
      state('active', style({
        transform: 'rotateY(179deg)'
      })),
      state('inactive', style({
        transform: 'rotateY(0)'
      })),
      transition('active => inactive', animate('500ms ease-out')),
      transition('inactive => active', animate('500ms ease-in'))
    ])
  ]
})

export class CreateTicketsComponent {
  flip: string = 'inactive';
  ticketForm!: FormGroup;
  priorities = Object.values(PriorityTicketEnum);
  statoTicket = Object.values(StatoMaschineEnum);
  user!: any;
  ticket: Ticket = {
    descrizione: '',
    note: '',
    priority: PriorityTicketEnum.ALTA,
    stato: StatoTicketEnum.APERTO,
    dataApertura: new Date(new Date().setHours(new Date().getHours() + 2)).toISOString().slice(0, 16),    dataChiusura: null,
    dataScadenza: '',
    dataRisposta: '',
    dataUltimaModifica: '',
    partIds: [],
    userId: 0
  };
  constructor(private fb: FormBuilder, private userSrv:UsersService) {
   
  }

  ngOnInit(): void {
    this.ticketForm = this.fb.group({
      descrizione: ['', Validators.required],
      note: [''],
      priority: [PriorityTicketEnum.MEDIA, Validators.required],
      stato: ['', Validators.required],
      dataApertura: ['', Validators.required],
      dataChiusura: [''],
      dataScadenza: ['', Validators.required],
      dataRisposta: [''],
      dataUltimaModifica: ['', Validators.required],
      partIds: ['', Validators.required],
      userId: ['', Validators.required]
    });
    this.user= this.userSrv.getUserIdFromLocalStorage();

  }
  toggleFlip() {
    this.flip = (this.flip == 'inactive') ? 'active' : 'inactive';
  }

  onSubmit() {
    if (this.ticketForm.valid) {
      this.ticket = { ...this.ticketForm.value };
      console.log(this.ticket);
      
    }
  }
}

