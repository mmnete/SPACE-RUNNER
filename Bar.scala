package game_play

import util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

class Bar(var initPos:Vec2,var Vel:Vec2, var gap: Vec2) {
  
  
  def move(){
    initPos += Vel
  }
  
  def display(g:GraphicsContext){
    g.setFill(Color.White)
    g.fillRect(0,initPos.y,gap.x,50)
    g.fillRect(gap.x+100,initPos.y,600,50)
    
  }
  
  
}