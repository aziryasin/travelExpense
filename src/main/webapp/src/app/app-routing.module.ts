import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';
import { NewTripComponent } from './new-trip/new-trip.component';
import { TripHistoryComponent } from './trip-history/trip-history.component';
import { TravelInfoComponent } from './travel-info/travel-info.component';
import { AdvancesGivenComponent } from './advances-given/advances-given.component';
import { TravelCostsComponent } from './travel-costs/travel-costs.component';
import { TravelReimbursementsComponent } from './travel-reimbursements/travel-reimbursements.component';
import { LoginComponent } from './login/login.component';
import { AuthGuard } from './auth.guard';
export const routes: Routes = [
  {
    path:'',
    component:LoginComponent
  },
  {
    path:'profile',
    canActivate:[AuthGuard],
    component:ProfileComponent
  },
  {
    path:'newTrip',
    component:NewTripComponent
  },
  {
    path:'tripHistory',
    component:TripHistoryComponent
  },
  {
    path:'newTrip/travelInfo',
    component:TravelInfoComponent
  },
  {
    path:'newTrip/advancesGiven',
    component:AdvancesGivenComponent
  },
  {
    path:'newTrip/travelCosts',
    component:TravelCostsComponent
  },
  {
    path:'newTrip/reimbursements',
    component:TravelReimbursementsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
