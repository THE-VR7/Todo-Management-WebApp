import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TodoDataService } from '../service/data/todo-data.service';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: boolean,
    public targetDate: Date
  ){

  }
}

@Component({
  selector: 'app-todos-list',
  templateUrl: './todos-list.component.html',
  styleUrls: ['./todos-list.component.css']
})

export class TodosListComponent implements OnInit {

  todos!: Todo[];

  message!: string;

//  todos = [
//     new Todo(1, 'Learn to Dance', false, new Date()),
//     new Todo(2, 'Become an Expert at Angular', false, new Date()),
//     new Todo(3, 'Visit India', false, new Date())
    // {id : 1, description : },
    // {id : 2, description : ''},
    // {id : 3, description : 'Visit India'}
  // ];

  // todo = {
  //     id : 1,
  //     description: 'Learn to Dance'
  // }


  constructor(
    private router : Router,
    private todoService : TodoDataService
  ) { }

  ngOnInit(): void {
    this.todoService.retrieveAllTodos('in28minutes').subscribe(
      res => {
        console.log(res);
        this.todos = res;
      }
    )
  }

  deleteTodo(id: any) {
    console.log(`delete todo ${id}` )
    this.todoService.deleteTodo('in28minutes', id).subscribe (
      response => {
        console.log(response);
        this.message = `Delete of Todo ${id} Successful!`;
        this.refreshTodos();
      }
    )
  }

  refreshTodos(){
    this.todoService.retrieveAllTodos('in28minutes').subscribe(
      response => {
        console.log(response);
        this.todos = response;
      }
    )
  }

  updateTodo(id: any) {
    console.log(`update ${id}`)
    this.router.navigate(['todos',id])
  }

  addTodo() {
    this.router.navigate(['todos',-1])
  }


}
