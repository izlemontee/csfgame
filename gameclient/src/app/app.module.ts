import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GamemasterComponent } from './gamemaster/gamemaster.component';
import { GameplayerComponent } from './gameplayer/gameplayer.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { MainComponent } from './main/main.component';


const appRoutes: Routes =[
  {path:"", component:MainComponent},
  {path:"master",component:GamemasterComponent},
  {path:"player",component:GameplayerComponent},

  {path:"*", redirectTo:"/", pathMatch:"full"}

]
@NgModule({
  declarations: [
    AppComponent,
    GamemasterComponent,
    GameplayerComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
