
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PriorityTicketEnum, StatoTicketEnum, Ticket } from '../../../models/tiket-add.interface';
import { StatoMaschineEnum } from '../../../models/machin/machine.interface';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { UsersService } from '../../../service/users.service';
import { RichiesteGetAllService } from '../../../service/richieste-get-all.service';
import { MachineDto, PartDto, UserMachineDto } from '../../../models/user-machine-dto.interface';
import { TiketService } from '../../../service/tiket.service';

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
  showModel = false;
  flip: string = 'inactive';
  ticketForm!: FormGroup;
  priorities = Object.values(PriorityTicketEnum);
  statoTicket = Object.values(StatoTicketEnum);
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
  selectedParts: PartDto[] = [];
  filteredParts: PartDto[] = [];
  filteredUserMachines: UserMachineDto[] = [];
  userMachine : UserMachineDto[] = [];
  selectedMachine: MachineDto | null = null;
  selectedPart: PartDto | null = null;
  searchTerm: string = '';
  constructor(private fb: FormBuilder, private userSrv:UsersService,private usMachine:RichiesteGetAllService,private tiketSrv:TiketService) {
   
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
    this.usMachine.userMachine$.subscribe((userMachine: UserMachineDto[]) => {
      if (!userMachine || userMachine.length === 0) {
        this.usMachine.getUserMachine();
      } else {
        this.userMachine = userMachine;
        this.filteredUserMachines = userMachine;
       // console.log('userMachine', userMachine);
      }
    });
  }
  onStatoChange(newValue: string) {
  //  console.log('Nuovo stato:', newValue);
    if (this.flip == 'active') {
      this.flip = 'inactive';
    }
  }
  onStatoChangein(newValue: string) {
    //console.log('Nuovo stato:', newValue);
    if (this.flip == 'inactive') {
      this.flip = 'active';
    }
  }
  toggleFlip() {
    this.flip = (this.flip == 'inactive') ? 'active' : 'inactive';
  }

  onSubmit() {
    this.ticketForm.patchValue({ userId: this.user.id });
    if (this.ticketForm.valid) {
      console.log('Ticket aggiunto:', this.ticketForm.value);
      
       this.ticket = { ...this.ticketForm.value };
      console.log('Ticket aggiunto:', this.ticket);
     this.tiketSrv.postTiketAdd(this.ticketForm.value).subscribe(
        (ticket: Ticket[]) => {
          this.ticketForm.reset();
        }
      );   
    }
  }
  addMacchina() {
    this.ticketForm.patchValue({ partIds: this.selectedParts.map(part => part.partId) });
    this.showModel = false;
  }


  selectMachine(machine: MachineDto) {
    this.selectedMachine = machine;
    this.selectedPart = null; // Reset selected part
  }

  selectPart(part: PartDto) {
    const partId = part.partId;
    const index = this.selectedParts.findIndex(selectedPart => selectedPart.partId === part.partId);
    if (index > -1) 
      this.selectedParts.splice(index, 1);
    else
    this.selectedParts.push(part);
 //console.log('selectedParts', this.selectedParts);
    this.selectedPart = part;
 // console.log('selectedmas', this.selectedMachine);
  }
  isSelectedPart(part: PartDto): boolean {
    return this.selectedParts.some(selectedPart => selectedPart.partId === part.partId);
  }
  search() {
    if (this.searchTerm.trim()) {
      const searchTermLower = this.searchTerm.toLowerCase();
      this.filteredUserMachines = this.userMachine.map(userMachine => ({
        ...userMachine,
        machines: userMachine.machines.filter(machine =>
          machine.nomeMacchina?.toLowerCase().includes(searchTermLower)
        )
      }));
    } else {
      this.filteredUserMachines = [...this.userMachine];
    }
  }

    openModal() {
      this.selectedMachine = null;
      this.selectedParts = [];
    this.showModel = true;
    
  }

  closeModal() {
    this.showModel = false;
    this.selectedMachine = null;
    this.selectedPart = null;
    this.selectedParts = [];
    document.body.classList.remove('modal-open');
  }
}

