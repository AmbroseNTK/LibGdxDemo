package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.RegionInfluencer;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by kiettuannguyen on 18/02/2017.
 */

public class AnimateActor extends BaseActor {
    public float elapseTime;
    public Animation animation;
    public AnimateActor(){
        super();
        elapseTime=0;
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
