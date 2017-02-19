package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by kiettuannguyen on 18/02/2017.
 */

public class BaseActor extends Actor {
    public TextureRegion textureRegion;
    public Rectangle boundary;
    public Vector2 velocity;
    public BaseActor(){
        super();
        textureRegion=new TextureRegion();
        boundary=new Rectangle();
        velocity=new Vector2(0,0);
    }
    public BaseActor(Texture texture){
        super();
        textureRegion=new TextureRegion();
        boundary=new Rectangle();
        velocity=new Vector2(0,0);
        setTexture(texture);
    }
    public void setTexture(Texture texture){
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        textureRegion.setRegion(texture);
    }
    public Rectangle getBoundingRectange(){
        boundary.set(getX(),getY(),getWidth(),getHeight());
        return boundary;
    }
    public void act(float dT){
        super.act(dT);
        moveBy(velocity.x*dT,velocity.y*dT);
    }
    public void draw(Batch batch, float parentAlpha){
        Color c=getColor();
        batch.setColor(c.r,c.g,c.b,c.a);
        if(isVisible())
            batch.draw(textureRegion,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());

    }
}
