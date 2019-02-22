import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/user';
import {Router} from '@angular/router';
import {UserService} from '../../shared_service/user.service';
import { UploadFileService } from 'src/app/shared_service/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { AngularWaitBarrier } from 'blocking-proxy/built/lib/angular_wait_barrier';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {
  user: User;
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  idFile: any = {} ; // string[];

  constructor(private userService: UserService, private router: Router, private uploadService: UploadFileService) {
  }

  ngOnInit() {
    this.user = this.userService.getter();
  }
  processForm() {
  
    if (this.user.id === undefined) {
      this.user.idfile = this.idFile.rta;
      this.userService.createUser(this.user).subscribe(user => { /*this.user = data as User;*/
        console.log(user);
        this.router.navigate(['/']);
      });
    } else {
      this.user.idfile = this.idFile.rta;
      this.userService.updateUser(this.user).subscribe(data => {/*this.user = data as User; */
        console.log(data);
        this.router.navigate(['/']);
      });
    }
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.upload();
  }

  selectFileUpdate(event){
    this.selectedFiles = event.target.files;
    this.uploadRefresh();
  }

  uploadRefresh(){
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    console.log("quiero enviar..-.");
    console.log(this.user.idfile);
    this.uploadService.pushFileToStorageRefresh(this.currentFileUpload, this.user.idfile).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log(this.idFile.rta);
        this.getIdFile();
        console.log('El archivo se adjuntó correctamente ' );
      }
    });
    this.selectedFiles = undefined;
  }

  upload() {
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);
    this.uploadService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log(this.idFile.rta);
        this.getIdFile();
        console.log('El archivo se adjuntó correctamente ' );
      }
    });
    this.selectedFiles = undefined;
  }

  private getIdFile() {
    return this.uploadService.getFileId().subscribe ( data => {this.idFile = data; console.log(data)}) ;
  }
}
