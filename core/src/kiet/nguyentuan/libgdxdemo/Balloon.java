package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by kiettuannguyen on 19/02/2017.
 */

public class Balloon extends BaseActor {
    public float speed;
    public float amplitude;
    public float oscillation;
    public float initalY;
    public float time;

    public int offsetX;

    public Balloon(){
        super(new Texture("red-balloon.png"));
        speed=80* MathUtils.random(0.5f,2.0f);
        amplitude=50* MathUtils.random(0.5f,2.0f);
        oscillation=0.01f* MathUtils.random(0.5f,2.0f);
        initalY=120* MathUtils.random(0.5f,2.0f);
        time=0;
        offsetX=-100;
        setX(offsetX);
    }
    public void act(float dT){
        super.act(dT);
        time +=dT;
        float xPos = speed * time - 100;
        float yPos = initalY + amplitude * MathUtils.sin(oscillation * xPos * 6.28f / 640);
        setPosition( xPos, yPos );
    }

}
