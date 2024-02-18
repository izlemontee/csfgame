import { Component, OnInit, inject } from '@angular/core';
import { GameService } from '../game.service';
import { Session } from '../../models';
import { Subscription, interval, startWith, switchMap } from 'rxjs';

@Component({
  selector: 'app-gamemaster',
  templateUrl: './gamemaster.component.html',
  styleUrl: './gamemaster.component.css'
})
export class GamemasterComponent implements OnInit{

  private gameServer = inject(GameService)

  inWaitingRoom: boolean = false
  sessionId!: string
  players : number =0

  sub !: Subscription

  initialiseSession(){
    var sessionPromise: Promise<Session>= this.gameServer.initialiseSession()
    sessionPromise.then(
      value =>{
        this.sessionId = value.id
        this.players = value.players
        this.inWaitingRoom = true
        console.log("sessionid",this.sessionId)
        console.log("players: ",this.players)
        this.waitingRoom()
      }
    ).catch(
      error =>{
        console.log("Server not responding")
      }
    )
  }
  ngOnInit(): void {
    // this.sub = interval(1000)
    //   .pipe(
    //     startWith(0),
    //     switchMap(()=>this.gameServer.testWaitingRoom())
    //   ).subscribe(
    //     res => console.log("ok"),
    //     err=>console.log("error")
    //   )
  }

  waitingRoom(){
    this.sub = interval(1000)
      .pipe(
        startWith(0),
        switchMap(()=>this.gameServer.masterWaitingRoom(this.sessionId))
      ).subscribe(
        res => {console.log("waiting room id: ",res.id), 
          console.log("waiting room players: ",res.players)},
        err=>console.log("error")
      )
  }

  startGame(){
    this.inWaitingRoom = false
    this.sub.unsubscribe()
    console.log("start")
  }

}
