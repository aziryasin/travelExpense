import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})


export class SidebarComponent implements OnInit {
  constructor(public user:UserService, private router:Router) { }

  ngOnInit() {
  }

  logout(){
    this.user.logout();
    localStorage.clear();
    this.router.navigate([''])
  }

}
