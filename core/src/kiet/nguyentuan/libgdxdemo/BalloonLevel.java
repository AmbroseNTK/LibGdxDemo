package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by kiettuannguyen on 19/02/2017.
 */

public class BalloonLevel extends BaseScreen {
    private BaseActor background;

    private float spawnTimer;
    private float spawnInterval;

    private int popped;
    private int escaped;
    private int clickCount;

    private Label poppedLabel;
    private Label escapedLabel;
    private Label hitRatioLabel;

    final Vector2 map=new Vector2(640,480);
    public BalloonLevel(Game g){
        super(g);
    }
    public void create(){
        background =new BaseActor(new Texture("sky.jpg"));
        spawnTimer=0f;
        spawnInterval=0.5f;
        BitmapFont font=new BitmapFont();
        Label.LabelStyle style=new Label.LabelStyle(font, Color.NAVY);

        popped=0;
        poppedLabel=new Label("Popped: 0",style);
        poppedLabel.setFontScale(2);
        poppedLabel.setPosition(20,440);

        escaped=0;
        escapedLabel=new Label("Escaped: 0",style);
        escapedLabel.setFontScale(2);
        escapedLabel.setPosition(220,440);

        clickCount=0;
        hitRatioLabel=new Label("Hit Ratio: ---",style);
        hitRatioLabel.setFontScale(2);
        hitRatioLabel.setPosition(420,440);

        mainStage.addActor(background);
        uiStage.addActor(poppedLabel);
        uiStage.addActor(escapedLabel);
        uiStage.addActor(hitRatioLabel);

    }
    public void update(float dT){
        spawnTimer+=dT;
        if(spawnTimer>spawnInterval){
            spawnTimer-=spawnInterval;
            final Balloon b=new Balloon();
            b.addListener(new InputListener(){
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    popped++;
                    b.remove();
                    return true;
                }
            });
            mainStage.addActor(b);
        }
        for(Actor a:mainStage.getActors()){
            if(a.getX()>map.x||a.getY()>map.y){
                escaped++;
                a.remove();
            }
        }
        poppedLabel.setText("Popped: "+popped);
        escapedLabel.setText("Escaped: "+escaped);
        if(clickCount>0){
            int percent=(int)(100f*popped/clickCount);
            hitRatioLabel.setText("Hit Ratio: "+percent+"%");
        }
    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        clickCount++;
        return false;
    }
}
