import { Component, OnInit } from '@angular/core';
import {UserService} from '../../shared_service/user.service';
import {User} from '../../user';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {
  users: User[];

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    // this.userService.getUsers().subscribe((users) => { console.log(users); } );
  this.userService.getUsers().subscribe(data => { this.users = data; });
  }

  deleteUser(user) {
    this.userService.deleteUser(user.id).subscribe(data => {
      this.users.splice(this.users.indexOf(user), 1);
    });
  }
  // usado para navegar
  updateUser(user) {
    this.userService.setter(user);
    this.router.navigate(['/op']);
  }
  updateState(user) {
    this.userService.setter(user);
    this.userService.updateUser(this.userService.getter()).subscribe(data => {
      console.log(data);
      this.router.navigate(['/']);
    });
  }
  newUser() {
    const user = new User();
    this.userService.setter(user);
    this.router.navigate(['/op']);
  }




}
