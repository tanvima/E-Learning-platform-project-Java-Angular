import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';


@Component({
  selector: 'app-unblockuser',
  templateUrl: './unblockuser.component.html',
  styleUrls: ['./unblockuser.component.scss']
})
export class UnblockuserComponent implements OnInit {

  blockedUsers:any;
  headers=["Sr. No","Name", "Email"];
  no_of_attempts:number=0;
  constructor(private as:AdminService,private router:Router) { }

  ngOnInit(): void {
    this.as.getAllBlockUser().subscribe(res => {
      this.blockedUsers = res;
      if(this.blockedUsers==null){
        throw Error("No users found")
      }
    })

  }
  getAllBlockUser(){
    this.as.getAllBlockUser().subscribe(res => {
      this.blockedUsers = res;

    })

  }

  onUnblock(id:number)
  {
   
    this.as.unblockUser(id)
    .subscribe({
      next: () => {
        this.getAllBlockUser()
       
      }
    })
 
  }


}
