package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

/**
 * Created by kiettuannguyen on 19/02/2017.
 */

public class CheeseLevel extends BaseScreen {

    Texture mouseTexture;
    Texture cheeseTexture;
    Texture floorTexture;
    Texture winTexture;

    AnimateActor mouse;
    BaseActor cheese;
    BaseActor floor;
    BaseActor winPanel;

    boolean win;

    Rectangle cheeseRec;
    Rectangle mouseRec;

    Animation animation;

    Label timeLabel;
    float timeElapsed;

    final Vector2 map=new Vector2(800,800);

    Camera camera;

    public CheeseLevel(Game g){
        super(g);
    }

    public void create() {
        mouseTexture=new Texture("mouse.png");
        cheeseTexture=new Texture("cheese.png");
        floorTexture=new Texture("tiles-800-800.jpg");
        winTexture=new Texture("you-win.png");

        mouse=new AnimateActor(mouseTexture);
        mouse.setPosition(20,20);
        cheese=new BaseActor(cheeseTexture);
        cheese.setPosition(400,300);
        floor=new BaseActor(floorTexture);
        floor.setPosition(0,0);
        winPanel=new BaseActor(winTexture);
        winPanel.setPosition(170,60);
        winPanel.setVisible(false);

        win=false;

        TextureRegion[] frame=new TextureRegion[4];
        for(int i=0;i<4;i++){
            String fileName="mouse"+i+".png";
            Texture t=new Texture(fileName);
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frame[i]=new TextureRegion(t);
        }
        Array<TextureRegion> framesArray=new Array<TextureRegion>(frame);
        animation=new Animation(0.1f,framesArray, Animation.PlayMode.LOOP_PINGPONG);

        mouse.setAnimation(animation);
        mouse.setOrigin(mouse.getWidth()/2f,mouse.getHeight()/2f);

        timeElapsed=0;
        BitmapFont font=new BitmapFont();
        String text="Time: 0";
        Label.LabelStyle style=new Label.LabelStyle(font, Color.NAVY);
        timeLabel=new Label(text,style);
        timeLabel.setFontScale(2);
        timeLabel.setPosition(500,440);

        mainStage=new Stage();
        mainStage.addActor(floor);
        mainStage.addActor(cheese);
        mainStage.addActor(mouse);

        uiStage=new Stage();
        uiStage.addActor(timeLabel);
        uiStage.addActor(winPanel);

        camera=mainStage.getCamera();


    }
    public void update(float dT){
        checkKeyboard();
        checkWin();
        if(win)
            winPanel.setVisible(true);
        else{
            timeElapsed+=dT;
            timeLabel.setText("Time: "+(int)timeElapsed);
        }
        checkRender();
    }
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.M)
            game.setScreen(new CheeseMenu(game));
        if(keycode ==Input.Keys.P) {
            if(isPaused()) {
                setPaused(false);
                return false;
            }
            setPaused(true);
        }
        return false;
    }
    private void checkKeyboard(){
        mouse.velocity=new Vector2(0,0);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            mouse.velocity.set(mouse.velocity.x-200,mouse.velocity.y);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            mouse.velocity.set(mouse.velocity.x+200,mouse.velocity.y);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            mouse.velocity.set(mouse.velocity.x,mouse.velocity.y+200);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            mouse.velocity.set(mouse.velocity.x,mouse.velocity.y-200);
        if(Gdx.input.isKeyPressed(Input.Keys.M))
            game.setScreen(new CheeseMenu(game));
    }
    private void checkWin() {
        cheeseRec = cheese.getBoundingRectange();
        mouseRec = mouse.getBoundingRectange();
        if (!win && cheeseRec.contains(mouseRec)) {
            win = true;
            winPanel.addAction(Actions.sequence(Actions.alpha(0),Actions.show(),Actions.fadeIn(2), Actions.forever(Actions.sequence(Actions.color(new Color(1,0,0,1),1),Actions.color(new Color(0,0,1,1),1)))));
            cheese.addAction(Actions.parallel(Actions.alpha(1),Actions.rotateBy(360f,1),Actions.scaleTo(0,0,2),Actions.fadeOut(1)));
        }
    }
    private void checkRender(){
        mouse.setX(MathUtils.clamp(mouse.getX(),0,map.x-mouse.getWidth()));
        mouse.setY(MathUtils.clamp(mouse.getY(),0,map.y-mouse.getHeight()));
        camera.position.set(mouse.getX()+mouse.getOriginX(),mouse.getY()+mouse.getOriginY(),0);
        camera.position.x=MathUtils.clamp(camera.position.x,view.x/2,map.x-view.x/2);
        camera.position.y=MathUtils.clamp(camera.position.y,view.y/2,map.y-view.y/2);
        camera.update();
    }
}
