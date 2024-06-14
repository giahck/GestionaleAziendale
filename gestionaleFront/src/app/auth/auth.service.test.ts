import { TestBed } from '@angular/core/testing';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AuthService]
    });
    authService = TestBed.inject(AuthService);
  });

  it('should confirm email and update the emailConfirmedSubject', () => {
    // Arrange
    const emailConfirmedStateSpy = spyOn(authService.getEmailConfirmedState(), 'next');

    // Act
    authService.confirmEmail();

    // Assert
    expect(emailConfirmedStateSpy).toHaveBeenCalledWith(true);
  });
});