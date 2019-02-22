import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
  private baseUrl = 'http://localhost:8080/upload';

  constructor(private http: HttpClient) { }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {

    const dataForm: FormData = new FormData();
    dataForm.append('file', file);

    console.log(dataForm);

    const req = new HttpRequest('POST', this.baseUrl + '/postfile', dataForm, { reportProgress: true, responseType: 'json'});
    // console.log(this.http.request(req));
    return this.http.request(req);
  }


  pushFileToStorageRefresh(arch: File, file: string): Observable<HttpEvent<{}>> {

    const dataForm: FormData = new FormData();
    dataForm.append('arch', arch);
    dataForm.append('file', file);

    console.log(dataForm);

    const req = new HttpRequest('POST', this.baseUrl + '/postfilerefresh', dataForm, { reportProgress: true, responseType: 'json'});
    // console.log(this.http.request(req));
    return this.http.request(req);
  }




  // GetById
  getFileId(): Observable<any> {
    return this.http.get(this.baseUrl + '/idFileStore');
  }

  getFiles(): Observable<any> {
    return this.http.get('/getallfiles');
  }

}
