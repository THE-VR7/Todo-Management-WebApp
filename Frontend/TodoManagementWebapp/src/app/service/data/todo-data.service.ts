import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_URL } from 'src/app/app.constant';
import { Todo } from 'src/app/todos-list/todos-list.component';

@Injectable({
  providedIn: 'root'
})
export class TodoDataService {
  constructor(    
    private http:HttpClient
    ) { }

    retrieveAllTodos(username: any) {
      return this.http.get<Todo[]>(`${API_URL}/users/${username}/todos`);
      //console.log("Execute Hello World Bean Service")
    }

    deleteTodo(username: any, id: any){
      return this.http.delete(`${API_URL}/users/${username}/todos/${id}`);
    }
  
    retrieveTodo(username: any, id: any){
      return this.http.get<Todo>(`${API_URL}/users/${username}/todos/${id}`);
    }
  
    updateTodo(username: any, id: any, todo: any){
      return this.http.put(`${API_URL}/users/${username}/todos/${id}`, todo);
    }
  
    createTodo(username: any, todo: any){
      return this.http.post(`${API_URL}/users/${username}/todos`, todo);
    }

}
