import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuCreatePartiComponent } from './menu-create-parti.component';

describe('MenuCreatePartiComponent', () => {
  let component: MenuCreatePartiComponent;
  let fixture: ComponentFixture<MenuCreatePartiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuCreatePartiComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuCreatePartiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
