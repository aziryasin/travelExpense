import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { UserService } from '../user.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-new-trip',
  templateUrl: './new-trip.component.html',
  styleUrls: ['./new-trip.component.scss']
})


export class NewTripComponent implements OnInit {
  saved:boolean=false;
  newTripForm:FormGroup;
  public allowCustom: boolean = true;
  public listItems: Array<string> = ["COSAC","COSBA","COSBS","COSCC","COSCHD","COSCIS","COSDCS","COSIT","COSLG","COSLOC","LKCON","LKEST","LKRDFAM","LKRDFIN","LKRDGAM","LKRDHRM","LKSUP","LKTRAIN","RDFI","RDHR","RDMF","RDMP","RDPC","RDPJ","RDRS","RDSA","RDSAMEX","RDTEMASS","RDSC","RDTE"];
  projects:string;
  projectIds:string;
  costCenter:string;
  startDate:Date;
  endDate:Date;
  constructor( private httpClient:HttpClient,private user:UserService) { 
    this.newTripForm=this.createFormGroup();
  }

  ngOnInit() {
    this.user.navBarPos="newTrip"
    this.getTripGeneralInfo(localStorage.getItem('tripId'));
    this.user.setEditEnabled(true);
  }

  createFormGroup(){
    return new FormGroup({
      projectNames:new FormControl('',[Validators.required]),
      projectIds:new FormControl('',[Validators.required]),
      costCenter:new FormControl('',[Validators.required]),
      startDate:new FormControl('',[Validators.required]),
      endDate:new FormControl('',[Validators.required])
    });
  }

  get f(){ return this.newTripForm.controls;}

  getTripGeneralInfo(tripId){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getTrip/"+tripId)
    .subscribe(
      (data:any)=>{
          try{
            if(data.object.tripGeneralInfo!=null){
              this.saved=true;
            this.projects=data.object.tripGeneralInfo.projectNames;
            this.projectIds=data.object.tripGeneralInfo.projectIDs;
            this.costCenter=data.object.tripGeneralInfo.costCenter;
            this.startDate=new Date(data.object.tripGeneralInfo.startDate);
            this.endDate=new Date(data.object.tripGeneralInfo.endDate);
            localStorage.setItem('startDate',this.newTripForm.controls.startDate.value.toISOString());
            }
            
          }catch(exception){
            console.log(exception);
          }
          
      },
      error=>{
          console.log("Error",error);
      }
    );
  }

  editGeneralInfo(){
    this.saved=true;
    localStorage.setItem('startDate',this.startDate.toISOString());
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/editGeneralInfo?id="+localStorage.getItem('tripId')+"&empNo="+localStorage.getItem('empNo'),{
      "projectNames":this.projects,
      "projectIDs":this.projectIds,
      "costCenter":this.costCenter,
      "startDate":this.startDate.toISOString(),
      "endDate":this.endDate.toISOString()
    })
    .subscribe(
      (data:any)=>{
          console.log("Trip GeneralInfo Edited",data)
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

}
