import { ChatGptResponse, UserMessage } from './../../models/chat-bot-message.interface';
import { ChangeDetectorRef, Component, ElementRef, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TiketService } from '../../service/tiket.service';
@Component({
  selector: 'app-chat-tiket',
  templateUrl: './chat-tiket.component.html',
  styleUrl: './chat-tiket.component.scss',
  
})
export class ChatTiketComponent implements OnInit,OnDestroy{
  requestMessage: string = '';
  sendeMessage: string = '';
  @ViewChild('chatContainer') private chatContainer!: ElementRef;
  message:ChatGptResponse[]=[];
  
 
  constructor(private tiketService: TiketService, private cdr: ChangeDetectorRef) {}

  ngAfterViewChecked() {
    this.scrollToBottom();
  }
  scrollToBottom(): void {
    try {
      this.chatContainer.nativeElement.scrollTop = this.chatContainer.nativeElement.scrollHeight;
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
  
    console.log('Invio messaggio:', text);
    const message = { content: text, from: 'User' };
    this.tiketService.sendMessage(message);
    const userMessage: UserMessage = { content: text };
    const userMessag: ChatGptResponse = { from: 'User', UserMessage: userMessage };
    this.message.push(userMessag);
    this.tiketService.setUtMess(this.message);
  }

}
