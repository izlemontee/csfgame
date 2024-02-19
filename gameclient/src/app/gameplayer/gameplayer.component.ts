import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Subscription, interval, startWith, switchMap } from 'rxjs';
import { GameService } from '../game.service';

@Component({
  selector: 'app-gameplayer',
  templateUrl: './gameplayer.component.html',
  styleUrl: './gameplayer.component.css'
})
export class GameplayerComponent implements OnInit{

  private gameServer = inject(GameService)
  private fb = inject(FormBuilder)
  
  sub !: Subscription

  findingNewRoom: boolean = true
  roomCodeForm !: FormGroup
  gamestarted: boolean = false

  roomid!: string

  ngOnInit(): void {
      this.roomCodeForm = this.createFindRoomForm()
  }

  createFindRoomForm():FormGroup{
    var group = this.fb.group({
      roomid:this.fb.control<string>('')
    })
    return group
  }

  submitRoomId(){
    var id = this.roomCodeForm.value['roomid']
    this.roomid = id
    var promise: Promise<any> = this.gameServer.playerEnterWaitingRoom(this.roomid)
    promise.then(
      ()=>{
        console.log("got in the room")
        this.inWaitingRoom()
      }
    ).catch(
      ()=>{
        console.log("room not found")
        alert("room not found")
        this.roomCodeForm = this.createFindRoomForm()
      }
    )
  }

  inWaitingRoom(){
    this.findingNewRoom = false
    this.sub = interval(1000)
      .pipe(
        startWith(0),
        switchMap(()=>this.gameServer.playerInWaitingRoom(this.roomid))
      ).subscribe(
        res =>{
          var gamestarted = res.gamestarted
          this.gamestarted = gamestarted
          console.log("game started",gamestarted)
        }
      )

  }

}
