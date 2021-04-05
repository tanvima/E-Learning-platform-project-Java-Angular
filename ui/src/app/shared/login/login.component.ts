import { Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TooltipPosition } from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { UserService } from 'src/app/user/user.service';
import { AuthenticationService } from 'src/app/utilities/authentication.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {


  emailRegx = /^(([^<>+()\[\]\\.,;:\s@"-#$%&=]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,3}))$/;
  phoneRegx = ("^((\\+91-?)|0)?[0-9]{10}$")
  isOTP = false;
  forgotPass = false
  forgotPassOTP = false
  currentIndex = 0
  otpmsg!: any
  errMsg: string = "";
  resetPass = false
  getOTP = false
  email = ""
  allUser!: any
  errMsgLogin: String = "";
  errMsgEmail: String = '';
  username = ''
  requestAdmin=false
  noOfAttempts=0
  user:any
  errMsgPassword=''
  errMsgLoginReg=''
  hide = true;
  constructor(private authservice: AuthenticationService, private router: Router, private us: UserService,
    public dialogRef: MatDialogRef<LoginComponent>) { }

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  registrationForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.pattern(this.emailRegx)]),
    mobileNo: new FormControl('', [Validators.required, Validators.pattern(this.phoneRegx)]),
    prime: new FormControl(''),

  });

  otpForm = new FormGroup({
    otp: new FormControl('', [Validators.required, Validators.minLength(6)]),

  });

  getEmailForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.pattern(this.emailRegx)])

  });

  forgotOTPForm = new FormGroup({
    otp: new FormControl('')
  });

  resetPassForm = new FormGroup({
    password: new FormControl('')
  });

  positionOptions: TooltipPosition[] = ['below', 'above', 'left', 'right'];
  position = new FormControl(this.positionOptions[2]);
  ngOnInit(): void {
    this.noOfAttempts=0
    this.us.getAllUser().subscribe(
      (data: any) => {
        this.allUser = data
      }
    )
  }
  loginUser() {
    this.user=this.allUser.find((user: any) => user.username == this.loginForm.value.username)
    if (this.user!=null) {
      if(this.user.status == 'unblock' && (this.user.noOfAttempts<=3 && this.noOfAttempts<3)) {
        this.authservice.authneticate(this.loginForm.value.username, this.loginForm.value.password)
                .subscribe(
                  (data: any) => {
                    this.dialogRef.close();
                    this.us.clearNoOfAttempts(this.loginForm.value.username).subscribe((data) => {
                    })

                    this.authservice.updateUserRole(data.roles[0])
                 
                    if (data.roles[0] === 'ROLE_admin') {
                     
                      
                      this.router.navigate([{outlets: {admin: 'adminhome'}}])
                      
                    }
                   else if (data.roles[0] === 'ROLE_user') {
                     
                      this.authservice.updateCartSizeData()
                      this.router.navigate(['/home'])
                    }
                  }, (err: any) => {
                    this.us.updateNoOfAttempts(this.loginForm.value.username).subscribe((data) => {
                      this.errMsgPassword="Invalid password"
                      this.noOfAttempts=data.msg
                    
                   
                      if(data.msg ==3){
                       
                        this.us.blockUser((this.loginForm.value.username)).subscribe((data)=>{
                          this.user.staus='block'
                        })
                      }
                    })
                  })
      } else {
            this.requestAdmin=true;

          }
    } else {
      this.errMsgLogin = "Username does not exist"
     
    }
  }

  registerUser() {
    if (this.registrationForm.valid && this.errMsgLoginReg == '' && this.errMsgEmail == '') {
      let user = this.registrationForm.value
      this.username = this.registrationForm.value.username
      if (user.prime) {
        this.us.addUser(user, "prime").subscribe((data) => {
          this.isOTP = true
          this.us.sendOTP(this.registrationForm.value.email).subscribe((data) => {
            this.otpmsg = data.msg;
           

          })
        })
      } else {
        this.us.addUser(user, "regular").subscribe((data) => {
          this.isOTP = true
          this.us.sendOTP(this.registrationForm.value.email).subscribe((data) => {
            this.otpmsg = data.msg;
          })
        })
      }
    }
  }

  verifyOTP() {

    if (this.otpForm.value.otp === this.otpmsg) {
      
      this.us.activateAccount(this.username).subscribe((data) => {
       
        this.ngOnInit()
      })
      this.registrationForm.reset()
      this.loginForm.reset()
      this.otpForm.reset()
      this.isOTP = false
      this.ngOnInit()
      this.errMsg=''
      this.currentIndex = 0
    } else {
      this.errMsg = "Wrong OTP"
    
    }

  }

  openForgotPass() {
    this.forgotPass = true
  }

  generateOTP() {
    if(this.errMsgEmail==''){

      this.forgotPassOTP = !this.forgotPassOTP
      this.getOTP = !this.getOTP
      this.us.sendOTP(this.getEmailForm.value.email).subscribe((data) => {
        this.otpmsg = data.msg;
        this.email = this.getEmailForm.value.email
        
        
        this.getEmailForm.reset()
  
      })
    }else{
      this.getEmailForm.reset()
      this.errMsgEmail="Enter existing email"
    }
  }

  verifyForgotOTP() {
    if (this.forgotOTPForm.value.otp === this.otpmsg) {
    
      this.resetPass = !this.resetPass
      this.getOTP = !this.getOTP
      this.forgotOTPForm.reset()
      this.isOTP = false
      this.currentIndex = 0
    } else {
      this.errMsg = "Wrong OTP"
     
    }
  }

  resetPassword() {
    //Call api to change password
    this.us.resetPassword(this.email, (this.resetPassForm.value)).subscribe(
      (data: any) => {
        this.forgotPassOTP = !this.forgotPassOTP
        this.forgotPass = !this.forgotPass
        this.resetPass = false
        this.getOTP = false

      }
    )

  }


  checkUsername() {
    if (this.allUser.find((user: any) => user.username == this.registrationForm.value.username)) {
      this.errMsgLoginReg = "Username exists"
    }
    else {
      this.errMsgLoginReg = ''
    }
  }

  checkEmail() {
   
    if (this.allUser.find((user: any) => user.email == this.registrationForm.value.email)) {

      this.errMsgEmail = "Email Exists"
    }
    else {
      this.errMsgEmail = ""
    }
  }
  checkEmailOTP(){
    
    if (this.allUser.find((user: any) => user.email == this.getEmailForm.value.email)) {

      this.errMsgEmail = ""
    }
    else {
      this.errMsgEmail = "Email does not exist"
    }
  }

  requestAdminfun(){
   this.us.requestAdmin(this.user.username).subscribe((data)=>{
     this.dialogRef.close();
   })
    
  }
}
