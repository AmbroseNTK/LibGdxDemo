package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

import java.util.HashMap;

/**
 * Created by kiettuannguyen on 18/02/2017.
 */

public class AnimateActor extends BaseActor {
    public float elapseTime;
    public Animation animation;
    private String activeName;
    private Animation activeAnim;
    private HashMap<String,Animation> animationHashMap;
    public AnimateActor(){
        super();
        elapseTime=0;
        activeAnim=null;
        activeName=null;
        animationHashMap=new HashMap<String, Animation>();
    }
    public void storageAnimtion(String name, Animation anim){
        animationHashMap.put(name, anim);
        if(activeAnim==null){
            setActiveAnimation(name);
        }
    }
    public void storageAnimation(String name, Texture tex)
    {
        TextureRegion reg = new TextureRegion(tex);
        TextureRegion[] frames = { reg };
        Animation anim = new Animation(1.0f, frames);
        storageAnimtion(name, anim);
    }
    public void setActiveAnimation(String name)
    {
        if ( !animationHashMap.containsKey(name) )
        {
            System.out.println("No animation: " + name);
            return;
        }
        // no need to set animation if already running
        if ( activeName.equals(name) )
            return;
        activeName = name;
        activeAnim = animationHashMap.get(name);
        elapseTime = 0;
        Texture tex=(Texture)animation.getKeyFrame(0);
        setWidth( tex.getWidth() );
        setHeight( tex.getHeight() );
    }
    public String getAnimationName()
    {
        return activeName;
    }
    public AnimateActor(Texture texture){
        super(texture);
        elapseTime=0;
    }
    public void setAnimation(Animation a){
        TextureRegion t=(TextureRegion)a.getKeyFrame(0);
        setTexture(t.getTexture());
        animation=a;
    }
    public void act(float dT){
        super.act(dT);
        elapseTime +=dT;
        if(velocity.x!=0||velocity.y!=0)
            setRotation(MathUtils.atan2(velocity.y,velocity.x)*MathUtils.radiansToDegrees);
    }
    public void draw(Batch batch, float parentAlpha){
        textureRegion.setRegion((TextureRegion) animation.getKeyFrame(elapseTime));
        super.draw(batch,parentAlpha);
    }

}