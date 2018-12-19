import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {
  
  submitted = false;
  loginForm:FormGroup;
  registerForm:FormGroup;
  username:string;
  empNo:number;
  password:string="";
  password2:string="";
  login:boolean=true;
  register:boolean=false;
  constructor( private httpClient:HttpClient,private user:UserService,private router:Router) {
    this.loginForm=this.createLoginFormGroup();
    this.registerForm=this.createRegisterFormGroup();
   }

  ngOnInit() {
    this.user.setUserLoggedIn(false);
  }

  createLoginFormGroup(){
    return new FormGroup({
      username:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required])
    });
  }

  createRegisterFormGroup(){
    return new FormGroup({
      username:new FormControl('',[Validators.required]),
      empNo:new FormControl('',[Validators.required]),
      password:new FormControl('',[Validators.required]),
      password2:new FormControl('',[Validators.required])
    });
  }

  get fl() { return this.loginForm.controls; }
  get fr() { return this.registerForm.controls;}


  loginEnable(){
    this.login=true;
    this.register=false;
  }

  registerEnable(){
    this.login=false;
    this.register=true;
  }

  loginUser(){
      this.submitted=true;
      this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/login/"+this.username,this.password)
      .subscribe(
        (data:any)=>{
          
          console.log(data);
          
          if(data.status=="Login Success"){
            this.user.setUserLoggedIn(true);
            localStorage.setItem('empNo',data.object.empNo);
            localStorage.setItem('name',data.object.employeeName);
            localStorage.setItem('startDate',new Date().toISOString())
            this.router.navigate(['tripHistory']);
          }else{
            alert("Login Failed. Check your credentials");
          }
          
        },
        error=>{
          console.error(error);
          
        }
      );
  }

  registerUser(){
    this.submitted=true;
    console.log(this.empNo);
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/register",{
          "username":this.username,
          "password":this.password,
          "empNo":this.empNo
    })
    .subscribe(
      (data:any)=>{
        console.log(data);
        if(data.status=="Registration Success"){
          this.loginEnable();
          alert("Registered Successfully. Login with your credentials")
        }else{
          alert("Registration Failed "+data.description);
        }
      },
      error=>{
        console.error(error);
        
      }
    );
}
  

}
