import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Session } from '../models';
import { Observable, last, lastValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  baseUrl : string = "http://localhost:8080/game"

  constructor(private httpClient : HttpClient) { }

  // game master initialises the session
  initialiseSession():Promise<Session>{
    const url: string = this.baseUrl+"/initialise"
    return lastValueFrom(this.httpClient.get<Session>(url))
  }

  // constantly poll the server to wait for players
  masterWaitingRoom(id:string):Observable<Session>{
    const params: HttpParams = new HttpParams().set("id",id)
    const url: string = this.baseUrl+"/waitingroom"
    return this.httpClient.get<Session>(url,{params:params})
  }

  startGame(id:string){
    const params: HttpParams = new HttpParams().set("id",id)
    const url: string = this.baseUrl+"/startgame"
    return lastValueFrom(this.httpClient.get<any>(url,{params:params}))
  }

  testWaitingRoom():Observable<any>{
    const url: string = this.baseUrl+"/waitingroom"
    return this.httpClient.get<any>(url)
  }

  playerEnterWaitingRoom(id:string){
    const url: string = this.baseUrl+"/playerenter"
    const params: HttpParams = new HttpParams().set("id",id)
    return lastValueFrom(this.httpClient.get<any>(url,{params:params}))
  }

  playerInWaitingRoom(id:string){
    const url: string = this.baseUrl+"/playerwaiting"
    const params: HttpParams = new HttpParams().set("id",id)
    return this.httpClient.get<any>(url, {params:params})
  }
}
