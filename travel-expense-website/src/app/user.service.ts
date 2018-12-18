import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private isUserLoggedIn:boolean;
  private isEditEnabled:boolean=false;
  public navBarPos:any;

  constructor() { 
    
  }

  setUserLoggedIn(val:boolean){
    this.isUserLoggedIn=val;
  }

  getUserLoggedIn(){
    if(localStorage.getItem('empNo')!=null){
      this.setUserLoggedIn(true);
    }
    return this.isUserLoggedIn;
  }

  logout(){
    console.log('logging out');
    this.isUserLoggedIn=false;
  }

  setEditEnabled(val:boolean){
    this.isEditEnabled=val;
  }
  
  getEditEnabled(){
    return this.isEditEnabled;
  }
}
