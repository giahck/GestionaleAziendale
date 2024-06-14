import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-email-verified',
  templateUrl: './email-verified.component.html',
  styleUrl: './email-verified.component.scss'
})
export class EmailVerifiedComponent /* implements OnInit  */{
 /*  verificationToken!: string;
  confermaEmail: boolean=true;
  constructor(private route: ActivatedRoute, private authSrv: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.verificationToken = params['token'];
      console.log('Token received:', this.verificationToken);
  

      if (this.verificationToken) {
        this.authSrv.verifyEmail(this.verificationToken).subscribe(
          () => {
            this.confermaEmail = false; // Non più in attesa di conferma
            // Reindirizza alla pagina di login dopo 2 secondi
            setTimeout(() => {
              
              this.router.navigate(['/login']);
            }, 20000);
          },
          (error) => {
            console.error('Error verifying email:', error);
            this.confermaEmail = false; // Non più in attesa di conferma
            // Gestisci errori di verifica dell'email, se necessario
          }
        );
      } else {
        console.error('Verification token not found.');
        this.confermaEmail = false; // Non più in attesa di conferma
        // Gestisci il caso in cui il token non sia presente nell'URL
      }
    });
  } */

}
