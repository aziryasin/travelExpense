import { Injectable, Inject } from '@angular/core';
import { SESSION_STORAGE, StorageService } from 'angular-webstorage-service';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(@Inject(SESSION_STORAGE) private storage: StorageService) { }

  public storeOnLocalStorage(profileName:string) : void {
    const currentDetail=this.storage.get('local_profile') || [];

    currentDetail.push({
        name:profileName
    });

    this.storage.set('local_profile',currentDetail)
  }
}
