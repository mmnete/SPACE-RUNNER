package game_play

import scalafx.Includes._
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.image.Image
import scalafx.scene.input.KeyEvent
import util.Vec2
import scalafx.scene.paint.Color
import scalafx.scene.media.Media
import java.io.File;
import scalafx.scene.media.MediaPlayer
import java.nio.file.{Files, Paths}
import scalafx.scene.layout.BorderPane
import scalafx.scene.control.Button
import scalafx.scene.layout.VBox
import scalafx.geometry.Insets
import scalafx.scene.text.Text
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.layout.BackgroundImage
import scalafx.scene.layout.Background
import scalafx.scene.layout.StackPane
import scalafx.scene.text.Font
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.canvas.Canvas
import scalafx.scene.canvas.GraphicsContext.sfxGraphicsContext2jfx
import scalafx.scene.paint.Color
import scalafx.scene.paint.Color.sfxColor2jfx
import scalafx.event.ActionEvent._
import scalafx.Includes._
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.scene.image.Image
import scalafx.scene.media.Media
import scalafx.scene.media.MediaPlayer
import java.io.File;
import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import scalafx.scene.text.Font
import scalafx.scene.layout._
import scalafx.scene.control.Label
import scalafx.scene.media.MediaPlayer.Status
import java.io.FileNotFoundException
import scala.io.Source
import java.io.PrintWriter

object SPACE extends JFXApp{
    stage = new JFXApp.PrimaryStage{
     scene = new Scene(600,600){ 
       title = "SPACE RUNNER"
       
       
       var canvas = new Canvas(600,600)
        var g = canvas.getGraphicsContext2D
        
        
        
             def collision(x:Double,y:Double,h:Double,w:Double,x1:Double,y1:Double,h1:Double,w1:Double):Boolean={
       if(x + w >= x1 && y + h >= y1 && (x <= x1 + w1 && y <= y1 + h1))
         {
       
         true
         }
       else if( x + w >= x1 && y == y1 && (x <= x1 + w1 && y == y1)) {
         true
       }
       else if(x + w >= x1 && y1 + h1 >= y && (y + h >= y1 && x <= x1 + w1)){
         true
       }
       else if(x == x1 && y + h >= y1 && (x1 == x && y <= y1 + w))
         {
         true
         }
       else{
         false
       }
      
      }
       
        var background_image = new Image("file:images/bi.jpg")
           var y1 = 0.0
            var score = 0
              var bars = scala.collection.mutable.Buffer[Bar]()
                    var player = new Player(new Image("file:images/space_ship.png"), new Vec2(275,550))
        var right,left,up,down = false
         var timer = AnimationTimer(t => {
              
             g.drawImage(background_image,0.0,y1,600,600)
             g.drawImage(background_image,0.0,y1-600,600,600)
             y1 += 1.0
           if(y1 - 600 >= 0) y1 = 0.0
            bars.append(new Bar(new Vec2(0,-(10+(bars.length-1)*(250))), new Vec2(0,1), new Vec2(scala.util.Random.nextInt(500),0)))
             
        
            for(i<-bars){
              i.move()
              i.display(g)
            }
             bars = bars.filter{x => x.initPos.y < 600}
             
            
             
             onKeyPressed = (e:KeyEvent) => {
               
               if(e.code.toString == "UP") up = true
               if(e.code.toString == "DOWN") down = true
               if(e.code.toString == "LEFT") left = true
               if(e.code.toString == "RIGHT") right = true
      
               
             }
             
              onKeyReleased = (e:KeyEvent) => {
               
               if(e.code.toString == "UP") up = false
               if(e.code.toString == "DOWN") down = false
               if(e.code.toString == "LEFT") left = false
               if(e.code.toString == "RIGHT") right = false
             }
              
              if(up) player.move_up()
              if(down) player.move_down()
              if(right) player.move_right()
              if(left) player.move_left()
              
              player.display(g)
             g.fillText("Score: "+score.toString,20,20,100)
              for(i<-bars)
              {
                if(collision(player.initPos.x,player.initPos.y,50,50,i.initPos.x,i.initPos.y,50,600)){
                  if(player.initPos.x > i.gap.x && player.initPos.x + 50 < i.gap.x + 100){
                    
                    score += 1
                  }else{
                  
                    score -= 1
                    
                    
                  }
                }
              }
             
             
             
           
         })
         timer.start
      
       content = canvas
     }
    }
}