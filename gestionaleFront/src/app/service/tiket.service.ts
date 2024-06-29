import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import SockJS from 'sockjs-client';
import { Message, Stomp } from '@stomp/stompjs';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import {  ChatGptResponse } from '../models/chat-bot-message.interface';
import { log } from 'console';


@Injectable({
  providedIn: 'root'
})
export class TiketService  {
 stompClient: any;
  topic: string = '/user/topic/messages';
  apiUrl=environment.apiURL;
  private responseSubject = new BehaviorSubject<ChatGptResponse[]>([]);
  Mess$: Observable<ChatGptResponse[]> = this.responseSubject.asObservable();
 
  getMess$(): Observable<ChatGptResponse[]> {
    return this.Mess$;
  }
  setMess(message: ChatGptResponse | ChatGptResponse[]): void {
     // console.log("Messaggio ricevuto:", message);
      const currentMessages = this.responseSubject.value;
      const updatedMessages = [...currentMessages, ...(Array.isArray(message) ? message : [message])];
      console.log("Messaggio ricevuto:", updatedMessages);
      this.responseSubject.next(updatedMessages);
  }
  setUtMess(message:ChatGptResponse[]): void {
    this.responseSubject.next(message);
  }

  Connect() {
    const socket = new SockJS(`${this.apiUrl}chat`);
    this.stompClient = Stomp.over(socket);

    this.stompClient.connect({}, (frame:any) => {
      console.log("Connesso al WebSocket:", frame);
      this.stompClient.subscribe(this.topic, (message:any) => {
        console.log("Messaggio ricevuto:",);
        if (message.body) {
         // const parsedMessage = JSON.parse(message.body);
          const parsedMessage = JSON.parse(message.body) as ChatGptResponse;
         // console.log("Messaggio ricevuto:", message.body);
          this.setMess(parsedMessage);

          //console.log("Messaggio ricevuto:", parsedMessage);
          // this.setMess(parsedMessage);
        } 
      }, (error:any) => {
        console.error("Errore nella sottoscrizione:", error);
      });
    }, (error:any) => {
      console.error("Errore nella connessione WebSocket:", error);
    });
  }

  sendMessage(message: any) {
    console.log("Invio messaggio:", message);
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send("/app/greet", {}, JSON.stringify(message));
    } else {
      console.error("Non connesso. Impossibile inviare il messaggio.");
    }
  }
  disconnect() {
    if (this.stompClient !== null) {
        this.stompClient.disconnect(() => {
            console.log("Disconnected from WebSocket");
        }, (error: any) => {
            console.error("WebSocket connection closed with error:", error);
        });
    }
}
}


/* export class TiketService  {

 stompClient: any;
  topic: string = '/topic/messages';
  apiUrl=environment.apiURL;
  Connect() {
    let vs = SockJS(`${this.apiUrl}chat`);
    this.stompClient = Stomp.over(vs);
    this.stompClient.connect({}, () => {
      console.log("Connesso al WebSocket");
      this.stompClient.subscribe(this.topic, (message: any) => {
        if (message.body) {
          console.log("Messaggio ricevuto:", message.body);
        } else if (message.binaryBody) {
          // Converti il corpo binario in stringa
          const stringBody = new TextDecoder().decode(message.binaryBody);
          // Deserializza la stringa in un oggetto JSON
          const jsonBody = JSON.parse(stringBody);
          console.log("Messaggio ricevuto (binario):", jsonBody);
        }
      }, (error: any) => {
        console.error("Errore nella sottoscrizione:", error);
      });
    }, (error: any) => {
      console.error("Errore nella connessione WebSocket:", error);
    });
  }

  sendMessage(message: any) {
    console.log("Invio messaggio:", message);
    if (this.stompClient && this.stompClient.connected) {
      this.stompClient.send("/app/greet", {}, JSON.stringify(message));
    } else {
      console.error("Non connesso. Impossibile inviare il messaggio.");
    }
  }
 
} */