import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { UserService } from '../user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';



@Component({
  selector: 'app-travel-costs',
  templateUrl: './travel-costs.component.html',
  styleUrls: ['./travel-costs.component.scss']
})
export class TravelCostsComponent implements OnInit {
  travelCostForm:FormGroup;
  add:boolean;
  edit:boolean;
  addBtn:String="Add";
  dataSource:any;
  displayedColumns: string[] = ['description','transactDate', 'transactCurrency', 'transactAmount','currentRateLKR','currentRateSEK','edit','delete'];
  travelCostDesc:string;
  transactDateCost:Date;
  transactCurrencyCost:string;
  transactAmountCost:number;
  currentRateLKRCost:number;
  currentRateSEKCost:number;
  constructor(private httpClient:HttpClient,private user:UserService) {
    this.travelCostForm=this.createFormGroup();
  }

  ngOnInit() {
    this.user.setEditEnabled(true);
    
    this.getTravelCosts();
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

  get f(){ return this.travelCostForm.controls;}


  getRateLKR(code:string){
    console.log(code);
    this.getRateSEK(code);
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convert?date="+this.transactDateCost.toISOString()+"&baseCurrency=LKR&quoteCurrency="+code)
  .subscribe(
    (data:any)=>{
      console.log("Get rate in LKR successful",data);
      if(data.rate!=0){
        this.currentRateLKRCost=data.rate;
      }
     
    },
    error =>{
      console.log("Error ",error);
    }
  );
}


getRateSEK(code:string){
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/currency/convertSEK?currencyCode="+code+"&date="+this.transactDateCost.toISOString())
  .subscribe(
    (res:any) =>{
      console.log("Get rate in SEK successful",res);
      if(res.rate!=0){
        this.currentRateSEKCost=res.rate;
      }
      
    },
    error =>{
      console.log("Error ",error);
    }
  );
}

getTravelCosts(){
  this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getTravelCosts/"+localStorage.getItem('tripId'))
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

enableEdit(row){
  localStorage.setItem('recordId',row['id']);
  this.travelCostDesc=row['description'];
  this.transactDateCost=new Date(row['transactDate']);
  this.transactCurrencyCost=row['transactCurrency'];
  this.transactAmountCost=row['transactAmount'];
  this.currentRateLKRCost=row['currentRateLKR'];
  this.currentRateSEKCost=row['currentRateSEK'];
  this.add=true;
  this.edit=true;
  this.changeLabelName("Edit");
}

cancel(){
  this.add=false;
}

  
addTravelCost(){
    
  if(this.edit){
    this.editTravelCost();
    return ;
  }

  this.add=false;
  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/addTravelCost?id="+localStorage.getItem('tripId'),{
  "description" : this.travelCostDesc,
  "transactCurrency" : this.transactCurrencyCost,
  "transactDate" : this.transactDateCost.toISOString(),
  "transactAmount" : this.transactAmountCost,
  "currentRateLKR" : this.currentRateLKRCost,
  "currentRateSEK" : this.currentRateSEKCost
  })
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Travel Cost Added",data)
    },
    error=>{
        console.log("Error ",error);
    }
  );
}

editTravelCost(){
  this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editTravelCost?id="+localStorage.getItem('tripId')+"&recordId="+localStorage.getItem('recordId'),{
    "description" : this.travelCostDesc,
    "transactCurrency" : this.transactCurrencyCost,
    "transactDate" : this.transactDateCost.toISOString(),
    "transactAmount" : this.transactAmountCost,
    "currentRateLKR" : this.currentRateLKRCost,
    "currentRateSEK" : this.currentRateSEKCost
      })
      .subscribe(
        (data:any)=>{
            this.dataSource=data.object;
            console.log("Travel Cost Edited",data);
            localStorage.removeItem('recordId');
            this.add=false;
            this.edit=false;
        },
        error=>{
            console.log("Error ",error);
        }
      );
}

deleteTravelCost(row){
  this.httpClient.delete("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/deleteTravelCost?id="+localStorage.getItem('tripId')+"&recordId="+row['id'])
  .subscribe(
    (data:any)=>{
        this.dataSource=data.object;
        console.log("Travel Cost",data)
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
