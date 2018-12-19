import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpClient} from "@angular/common/http";
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-advances-given',
  templateUrl: './advances-given.component.html',
  styleUrls: ['./advances-given.component.scss']
})
export class AdvancesGivenComponent implements OnInit {
  advancesGivenForm:FormGroup;
  edit:boolean;
  add:boolean;
  addBtn:String="Add";
  dataSource:any;
  displayedColumns: string[] = ['transactDate', 'transactCurrency', 'transactAmount','currentRateLKR','edit','delete'];
  transactDateAdv:Date;
  transactCurrencyAdv:string;
  transactAmountAdv:number;
  currentRateLKRadv:number;
  constructor(private httpClient:HttpClient,private user:UserService) {
    this.advancesGivenForm=this.createFormGroup();
   }

  ngOnInit() {
    this.user.setEditEnabled(true);
    this.getAdvances();
    this.add=false;
  }

  createFormGroup(){
    return new FormGroup({
      transactDate:new FormControl('',[Validators.required]),
      currencyCode:new FormControl('',[Validators.required]),
      transactAmount:new FormControl('',[Validators.required]),
      currentRateLKR:new FormControl('',[Validators.required])
    });
  }

  get f(){ return this.advancesGivenForm.controls;}

  enableEdit(row){
    localStorage.setItem('recordId',row['id']);
    this.transactDateAdv=new Date(row['transactDate']);
    this.transactCurrencyAdv=row['transactCurrency'];
    this.transactAmountAdv=row['transactAmount'];
    this.currentRateLKRadv=row['currentRateLKR'];
    this.add=true;
    this.edit=true;
    this.changeLabelName("Edit");
  }

  getRateLKR(code:string){
    console.log(code);
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convert?date="+this.transactDateAdv.toISOString()+"&baseCurrency=LKR&quoteCurrency="+code)
  .subscribe(
    (data:any)=>{
      console.log("Get rate in LKR successful",data);
      if(data.rate!=0){
        this.currentRateLKRadv=data.rate;
      }
      
    },
    error =>{
      console.log("Error ",error);
    }
  );
}

getAdvances(){
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getAdvancesGiven/"+localStorage.getItem('tripId'))
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Advances Given list",data)
    },
    error=>{
        console.log("Error ",error);
    }
  );
}

addValue(){
  this.changeLabelName("Add");
  this.add=true;
}

cancel(){
  this.add=false;
}

addAdvancesGiven(){

  if(this.edit){
    this.editAdvancesGiven();
    return ;
  }

  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/addAdvancesGiven?id="+localStorage.getItem('tripId'),{
  "transactCurrency" : this.transactCurrencyAdv,
  "transactDate" : this.transactDateAdv.toISOString(),
  "transactAmount" : this.transactAmountAdv,
  "currentRateLKR" : this.currentRateLKRadv
  })
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Advances Given Added",data);
        this.add=false;
    },
    error=>{
        console.log("Error ",error);
    }
  );
}

editAdvancesGiven(){
  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editAdvancesGiven?id="+localStorage.getItem('tripId')+"&recordId="+localStorage.getItem('recordId'),{
    "transactCurrency" : this.transactCurrencyAdv,
    "transactDate" : this.transactDateAdv.toISOString(),
    "transactAmount" : this.transactAmountAdv,
    "currentRateLKR" : this.currentRateLKRadv
      })
      .subscribe(
        (data:any)=>{
            this.dataSource=data.object;
            console.log("Advances Given Edited",data);
            localStorage.removeItem('recordId');
            this.add=false;
            this.edit=false;
        },
        error=>{
            console.log("Error ",error);
        }
      );
}

deleteAdvancesGiven(row){
  console.log(row);
  this.httpClient.delete("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/deleteAdvancesGiven?id="+localStorage.getItem('tripId')+"&recordId="+row['id'])
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Advances Given",data)
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
