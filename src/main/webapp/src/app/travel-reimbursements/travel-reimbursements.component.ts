import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { HttpClient } from "@angular/common/http";
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-travel-reimbursements',
  templateUrl: './travel-reimbursements.component.html',
  styleUrls: ['./travel-reimbursements.component.scss']
})
export class TravelReimbursementsComponent implements OnInit {
  travelReimForm:FormGroup;
  add:boolean;
  edit:boolean;
  addBtn:String="Add";
  dataSource:any;
  displayedColumns: string[] = ['description','transactDate', 'transactCurrency', 'transactAmount','currentRateLKR','currentRateSEK','edit','delete'];
  travelReimDesc:string;
  transactDateReim:Date;
  transactCurrencyReim:string;
  transactAmountReim:number;
  currentRateLKRReim:number;
  currentRateSEKReim:number;
  reimList:any[];
  constructor(private httpClient:HttpClient,private user:UserService) {
    this.travelReimForm=this.createFormGroup();
   }

  ngOnInit() {
    this.user.setEditEnabled(true);
    this.getReimbursements();
    this.add=false;
  }

  createFormGroup(){
    return new FormGroup({
      description:new FormControl('',[Validators.required]),
      transactDate:new FormControl('',[Validators.required]),
      currencyCode:new FormControl('',[Validators.required]),
      transactAmount:new FormControl('',[Validators.required]),
      currentRateLKR:new FormControl('',[Validators.required]),
      currentRateSEK:new FormControl('',[Validators.required])
    });
  }

  get f(){ return this.travelReimForm.controls;}

  generateReportFinal(){
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/reportGenerate/final?id="+localStorage.getItem('tripId')+"&empNo="+localStorage.getItem('empNo'),{})
      .subscribe(
        data => {
            console.log("Final Report generation is successful ", data);
            window.open('http://cmbtrnfs01.corpnet.ifsworld.com:8080/travelExpense/Travel_Expense_Filled.xlsx','_self');
        },
        error => {
            console.log("Error", error);
        }
    ); 
}

getRateLKR(code:string){
  console.log(code);
  this.getRateSEK(code);
this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convert?date="+this.transactDateReim.toISOString()+"&baseCurrency=LKR&quoteCurrency="+code)
.subscribe(
  (data:any)=>{
    console.log("Get rate in LKR successful",data);
    if(data.rate!=0){
      this.currentRateLKRReim=data.rate;
    }
    
  },
  error =>{
    console.log("Error ",error);
  }
);
}


getRateSEK(code:string){
this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convertSEK?currencyCode="+code+"&date="+this.transactDateReim.toISOString())
.subscribe(
  (res:any) =>{
    console.log("Get rate in SEK successful",res);
    if(res.rate!=0){
      this.currentRateSEKReim=res.rate;
    }
    
  },
  error =>{
    console.log("Error ",error);
  }
);
}

getReimbursements(){
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getExpenseReimbursements/"+localStorage.getItem('tripId'))
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Expense Reimbursements",data)
    },
    error=>{
        console.log("Error ",error);
    }
  );
}

addValue(){
  this.add=true;
  this.changeLabelName("Add");
}

enableEdit(row){
  localStorage.setItem('recordId',row['id']);
  this.travelReimDesc=row['description'];
  this.transactDateReim=new Date(row['transactDate']);
  this.transactCurrencyReim=row['transactCurrency'];
  this.transactAmountReim=row['transactAmount'];
  this.currentRateLKRReim=row['currentRateLKR'];
  this.currentRateSEKReim=row['currentRateSEK'];
  this.add=true;
  this.edit=true;
  this.changeLabelName("Edit");
}

cancel(){
  this.add=false;
}
addTravelExpense(){
    
  if(this.edit){
    this.editTravelExpense();
    return ;
  }

  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/addTravelExpense?id="+localStorage.getItem('tripId'),{
  "description" : this.travelReimDesc,
  "transactCurrency" : this.transactCurrencyReim,
  "transactDate" : this.transactDateReim.toISOString(),
  "transactAmount" : this.transactAmountReim,
  "currentRateLKR" : this.currentRateLKRReim,
  "currentRateSEK" : this.currentRateSEKReim
  })
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Travel Expense Added",data);
        
  this.add=false;
    },
    error=>{
        console.log("Error ",error);
    }
  );
}

editTravelExpense(){
  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editTravelExpense?id="+localStorage.getItem('tripId')+"&recordId="+localStorage.getItem('recordId'),{
    "description" : this.travelReimDesc,
  "transactCurrency" : this.transactCurrencyReim,
  "transactDate" : this.transactDateReim.toISOString(),
  "transactAmount" : this.transactAmountReim,
  "currentRateLKR" : this.currentRateLKRReim,
  "currentRateSEK" : this.currentRateSEKReim
      })
      .subscribe(
        (data:any)=>{
            this.dataSource=data.object;
            console.log("Travel Expense Reimbursements Edited",data);
            localStorage.removeItem('recordId');
            this.add=false;
            this.edit=false;
        },
        error=>{
            console.log("Error ",error);
        }
      );
}

deleteTravelExpense(row){
  this.httpClient.delete("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/deleteTravelExpense?id="+localStorage.getItem('tripId')+"&expenseId="+row['id'])
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Travel Expense Reimbursements",data)
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
