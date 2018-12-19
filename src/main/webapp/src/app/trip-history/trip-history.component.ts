import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import { UserService } from '../user.service';


@Component({
  selector: 'app-trip-history',
  templateUrl: './trip-history.component.html',
  styleUrls: ['./trip-history.component.scss']
})


export class TripHistoryComponent implements OnInit {
  namePre:boolean=true;
  dataSource:any;
  displayedColumns: string[] = ['projects','costCenter','startDate', 'endDate','edit','duplicate','delete','download'];

  constructor( private httpClient:HttpClient, private user:UserService) { }

  ngOnInit() {
    this.getAlltrips();
    this.user.setEditEnabled(false);
  }

  getAlltrips(){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/getTripByEmp/"+localStorage.getItem('empNo'))
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

  selectRow(row){
    console.log(row['_id']);
  }
  
  deleteTrip(row){
    this.httpClient.delete("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/deleteTrip/"+row['_id'])
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

  duplicateTrip(row){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/duplicateTrip/"+row['_id'])
    .subscribe(
      (data:any)=>{
          this.dataSource=data.object;
          console.log("Trip Duplicated");
      },
      error=>{
          console.log("Error ",error);
      }
    );
    this.ngOnInit();
  }

  editTrip(row){
    localStorage.setItem("tripId",row['_id']);
  }

  startTrip(){
    this.httpClient.get("http://cmbtrnfs01.corpnet.ifsworld.com:5020/db/startTrip")
    .subscribe(
      (data:any)=>{
          console.log("Travel Info list",data)
          localStorage.setItem('tripId',data.object);
      },
      error=>{
          console.log("Error ",error);
      }
    );
  }

  downloadTrip(row){
    this.httpClient.post("http://cmbtrnfs01.corpnet.ifsworld.com:5020/reportGenerate/final?id="+row['_id']+"&empNo="+localStorage.getItem('empNo'),{})
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

}
