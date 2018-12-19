import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { HttpClient} from "@angular/common/http";



@Component({
  selector: 'app-travel-info',
  templateUrl: './travel-info.component.html',
  styleUrls: ['./travel-info.component.scss']
})

export class TravelInfoComponent implements OnInit {
  travelInfoForm:FormGroup;
  add:boolean;
  edit:boolean;
  addBtn:String="Add";
  displayedColumns: string[] = ['purpose', 'country', 'days', 'currencyCode','dailyAllowance','currentRateLKR','currentRateSEK','edit','delete'];
  purpose:string;
  country:string;
  days:number;
  currencyCodeTravel:string;
  dailyAllowance:number;
  dailyAllowancePl:number;
  currentRateLKRinfo:number;
  currentRateSEKinfo:number;
  dataSource:any;
  dailyIf:boolean;
  constructor(private httpClient:HttpClient,private user:UserService) {
    this.travelInfoForm=this.createFormGroup();
   }

   
  ngOnInit() {
    this.user.setEditEnabled(true);
    this.getTravelInfo();
    this.add=false;
    this.edit=false;
    this.dailyIf=false;
    this.getDailyAllowance(this.country);
    this.getRateLKR(this.currencyCodeTravel);
    this.getRateSEK(this.currencyCodeTravel);
  }

  createFormGroup(){
    return new FormGroup({
      purpose:new FormControl('',[Validators.required]),
      country:new FormControl('',[Validators.required]),
      days:new FormControl('',[Validators.required]),
      dailyAllowance:new FormControl('',[Validators.required]),
      currencyCode:new FormControl('',[Validators.required]),
      currentRateLKR:new FormControl('',[Validators.required]),
      currentRateSEK:new FormControl('',[Validators.required])
    });
  }

  get f(){ return this.travelInfoForm.controls;}

  addValue(){
    this.changeLabelName("Add");
    this.add=true;
  }

  enableEdit(row){
    localStorage.setItem('recordId',row['id']);
    this.purpose=row['purpose'];
    this.country=row['country'];
    this.days=row['days'];
    this.dailyAllowance=row['dailyAllowance'];
    this.currencyCodeTravel=row['currencyCode'];
    this.currentRateLKRinfo=row['currentRateLKR'];
    this.currentRateSEKinfo=row['currentRateSEK'];
    this.add=true;
    this.edit=true;
    this.changeLabelName("Edit");
  }

  cancel(){
    this.add=false;
  }

  checkValue(){
    if(this.dailyAllowancePl!=0){
      this.dailyIf=true;
    }
  }
  saveTravelInfo(){
    this.add=false;
    this.getDailyAllowance(this.country);
    this.getRateLKR(this.currencyCodeTravel)
    this.getRateSEK(this.currencyCodeTravel)


  }

  getTravelInfo(){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getTravelInfo/"+localStorage.getItem('tripId'))
    .subscribe(
      (data:any)=>{
          this.dataSource=data.object;
          console.log("Travel Info list",data)
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

  getRateLKR(code:string){
      console.log(code);
      this.getRateSEK(code);
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convert?date="+localStorage.getItem('startDate')+"&baseCurrency=LKR&quoteCurrency="+code)
    .subscribe(
      (data:any)=>{
        console.log("Get rate in LKR successful",data);
        if(data.rate!=0){
          this.currentRateLKRinfo=data.rate;
        }
        
      },
      error =>{
        console.log("Error ",error);
      }
    );
  }

  
  getRateSEK(code:string){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convertSEK?currencyCode="+code+"&date="+localStorage.getItem('startDate'))
    .subscribe(
      (res:any) =>{
        console.log("Get rate in SEK successful",res);
        if(res.rate!=0){
        this.currentRateSEKinfo=res.rate;
        }
      },
      error =>{
        console.log("Error ",error);
      }
    );
  }
  

  getDailyAllowance(country:string){
    if(country==this.currencyCodeTravel){
      country=this.country;
    }
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/dailyAllowance?date="+localStorage.getItem('startDate')+"&country="+country+"&currencyCode="+this.currencyCodeTravel)
    .subscribe(
      (data:any)=>{
        console.log("Get daily Allowance",data);
        this.dailyAllowancePl=data.allowance;
        this.checkValue();
      },
      error =>{
        console.log("Error ",error);
      }
    );

    
  }

  deleteTravelInfo(row){
    this.httpClient.delete("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/deleteTravelInfo?id="+localStorage.getItem('tripId')+"&recordId="+row['id'])
    .subscribe(
      (data:any)=>{
          this.dataSource=data.object;
          console.log("Trip Deleted")
      },
      error=>{
          console.log("Error ",error);
      }
    );
    this.ngOnInit();
  }

  addTravelInfo(){
    
    if(this.edit){
      this.editTravelInfo();
      return ;
    }
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/addTravelInfo?id="+localStorage.getItem('tripId'),{
    "purpose" : this.purpose,
    "country" : this.country,
    "currencyCode" : this.currencyCodeTravel,
    "days" : this.days,
    "dailyAllowance" : this.dailyAllowance,
    "currentRateLKR" : this.currentRateLKRinfo,
    "currentRateSEK" : this.currentRateSEKinfo
    })
    .subscribe(
      (data:any)=>{
          this.dataSource=data.object;
          console.log("Travel Info Added",data)
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

  editTravelInfo(){
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editTravelInfo?id="+localStorage.getItem('tripId')+"&recordId="+localStorage.getItem('recordId'),{
        "purpose" : this.purpose,
        "country" : this.country,
        "currencyCode" : this.currencyCodeTravel,
        "days" : this.days,
        "dailyAllowance" : this.dailyAllowance,
        "currentRateLKR" : this.currentRateLKRinfo,
        "currentRateSEK" : this.currentRateSEKinfo
        })
        .subscribe(
          (data:any)=>{
              this.dataSource=data.object;
              console.log("Travel Info Edited",data);
              localStorage.removeItem('recordId');
              this.add=false;
              this.edit=false;
          },
          error=>{
              console.log("Error ",error);
          }
        );
  }
  changeLabelName(val:String){
    this.addBtn=val;
  }
}
