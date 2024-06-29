import { ChatGptResponse, UserMessage } from './../../models/chat-bot-message.interface';
import { ChangeDetectorRef, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TiketService } from '../../service/tiket.service';
import { animate, style, transition, trigger } from '@angular/animations';
import { log } from 'console';
@Component({
  selector: 'app-chat-tiket',
  templateUrl: './chat-tiket.component.html',
  styleUrl: './chat-tiket.component.scss',
  animations: [
    trigger('messageAnimation', [
      transition(':enter', [
        style({ transform: 'translateY(100%)', opacity: 0 }),
        animate('0.5s ease-out', style({ transform: 'translateY(0)', opacity: 1 }))
      ])
    ])
  ]
  
})
export class ChatTiketComponent implements OnInit,OnDestroy{
  activeOpzion: boolean = false;
  requestMessage: string = '';
  sendeMessage: string = '';
  descrizione: string = '';
  @ViewChild('chatContainer') private chatContainer!: ElementRef;
  message:ChatGptResponse[]=[];
  @ViewChild('messageInput') messageInput!: ElementRef;
  @ViewChild('messageTextarea') messageTextarea!: ElementRef<HTMLTextAreaElement>;
  constructor(private tiketService: TiketService, private cdr: ChangeDetectorRef) {}

  ngAfterViewChecked() {
    this.scrollToBottom();
    this.scrollToBottomText();
  }
  scrollToBottom(): void {
    try {
      this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
    } catch(err) { }
  }
  scrollToBottomText(): void {
    try {
      this.messageTextarea.nativeElement.scrollTop = this.messageTextarea.nativeElement.scrollHeight;
    } catch(err) { }
  }
  ngOnDestroy(): void {
    this.tiketService.disconnect();
  }

  ngOnInit(): void {
    this.tiketService.Connect();
    this.tiketService.getMess$().subscribe((newMessages: ChatGptResponse[]) => {
      this.message = newMessages;
     // this.cdr.detectChanges();
    });

  }

  sendMessage(text:string): void {
    this.messageInput.nativeElement.value = '';
    if (text.trim() === '') {
      return;
    }
    console.log('Invio messaggio:', text);
    const message = { content: text, from: 'User' };
    this.tiketService.sendMessage(message);
    const userMessage: UserMessage = { content: text };
    const userMessag: ChatGptResponse = { from: 'User', UserMessage: userMessage };
    this.message.push(userMessag);
    this.tiketService.setUtMess(this.message);
   // this.messageInput.nativeElement.value = '';
  }
  activePanel(): void {
    this.activeOpzion = !this.activeOpzion; // Alterna tra true e false
  }
  aggiornaDescrizione(nuovaDescrizione: string){
    this.descrizione = nuovaDescrizione;
  }
  sendTextareaMessage(descrizione: string){

      const message = { content: descrizione, from: 'User' };
    this.tiketService.sendMessage(message);
    const userMessage: UserMessage = { content: descrizione };
    const userMessag: ChatGptResponse = { from: 'User', UserMessage: userMessage };
    
    this.message.push(userMessag);
    this.tiketService.setUtMess(this.message);
    this.descrizione = '';

  }

}
