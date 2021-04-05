import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Profile } from 'src/app/interface/profile';
import { AuthenticationService } from 'src/app/utilities/authentication.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-editprofile',
  templateUrl: './editprofile.component.html',
  styleUrls: ['./editprofile.component.scss']
})
export class EditprofileComponent implements OnInit {
  user!: any
  currentIndex=0
  editForm = new FormGroup({
    name: new FormControl(''),
    address: new FormControl(''),
    profession: new FormControl(''),
    education: new FormControl(''),
    mobileNo: new FormControl('')
  });
  profile:Profile={
    profileid:null,
    address :'',
    education:'',
    profession:''
  };
  
  constructor(private dialogRef: MatDialogRef<EditprofileComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private us: UserService, private authservice: AuthenticationService) { }

  ngOnInit(): void {
   
    this.us.getUserById(this.data.userid).subscribe((data) => {
      this.user = data
     
      if (this.user.profile != null) {
        this.editForm = new FormGroup({
          name: new FormControl(this.user.name),
          mobileNo: new FormControl(this.user.mobileNo),
          address: new FormControl(this.user.profile.address),
          profession: new FormControl(this.user.profile.profession),
          education: new FormControl(this.user.profile.education),

        })
      }else{
        this.editForm = new FormGroup({
          name: new FormControl(this.user.name),
          mobileNo: new FormControl(this.user.mobileNo),
          address: new FormControl(''),
          profession: new FormControl(''),
          education: new FormControl(''),

        })
      }
    })
    }


  editProfile() {
   
    this.profile.address = this.editForm.value.address;
    this.profile.education = this.editForm.value.education;
    this.profile.profession = this.editForm.value.profession;

    if(this.user.profile!=null){
      this.profile.profileid=this.user.profile.profileid
    }
    this.user.profile = this.profile
    this.user.name = this.editForm.value.name;
    this.user.mobileNo = this.editForm.value.mobileNo;
  
    this.us.updateProfile(this.user).subscribe((data)=>{
      
      this.dialogRef.close();

    })
  }

  closeForm(){
    this.dialogRef.close();
  }
}
