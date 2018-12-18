import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { UserService } from '../user.service';
import { FormGroup,Validators, FormControl } from '@angular/forms';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})



export class ProfileComponent implements OnInit {
  profileForm:FormGroup;
  profileName:string;
  organizationalUnit:string;
  empNo:any;
  epfNo:any;
  edit:boolean;
  constructor( private httpClient:HttpClient,private user:UserService) {
    this.profileForm=this.createFormGroup();
   }
  @ViewChild ('form') form;
  ngOnInit() {
    this.getEmployeeInfo();
    this.edit=false;
    this.user.setEditEnabled(false);
  }

  createFormGroup(){
    return new FormGroup({
      profileName:new FormControl('',[Validators.required]),
      organizationalUnit:new FormControl('',[Validators.required]),
      empNo:new FormControl('',[Validators.required]),
      epfNo:new FormControl('',[Validators.required])
    });
  }

  get f(){ return this.profileForm.controls;}

  saveProfile(){
    this.edit=false;
    localStorage.setItem('empNo',this.empNo);
    localStorage.setItem('name',this.profileName);
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editProfile?id="+localStorage.getItem('tripId'),{
      "employeeName":this.profileName,
      "organizationalUnit":this.organizationalUnit,
      "epfNo":this.epfNo,
      "empNo":this.empNo
    })
    .subscribe(
      (data:any)=>{
          console.log("GeneralInfo Edited",data)
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

  editProfile(){
    this.edit=true;
  }

  cancel(){
    this.edit=false;
    this.ngOnInit();
  }

  getEmployeeInfo(){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getEmployeeInfo/"+localStorage.getItem('empNo'))
    .subscribe(
      (data:any)=>{
          console.log("Employee Info",data)
          this.empNo=data.object.empNo;
          this.profileName=data.object.employeeName;
          this.epfNo=data.object.epfNo;
          this.organizationalUnit=data.object.organizationalUnit;
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

}
