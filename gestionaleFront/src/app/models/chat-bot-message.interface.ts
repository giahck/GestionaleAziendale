
export interface UserMessage {
    content: string;
}
export interface Message {
    role: string;
    content: string;
  }
  
  export interface Choice {
    index: number;
    message: Message;
  }
  
  export interface ChatGptResponse {
    from: string;
    choices?: Choice[];
    UserMessage?: UserMessage;

  }
  