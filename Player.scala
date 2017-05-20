package game_play

import util.Vec2
import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext

class Player(var img:Image, var initPos:Vec2){
  
  
  def move_up(){
    if(initPos.y - 5 >= 0) initPos -= new Vec2(0,5)
  }
  def move_down(){
    if(initPos.y + 5 <= 550) initPos += new Vec2(0,5)
  }
  def move_left(){
     if(initPos.x - 5 >= 0) initPos -= new Vec2(5,0)
  }
  def move_right(){
      if(initPos.x + 5 <= 550)initPos += new Vec2(5,0)
  }
  
  def display(g:GraphicsContext){
    g.drawImage(img,initPos.x,initPos.y,50,50)
  }
  
}