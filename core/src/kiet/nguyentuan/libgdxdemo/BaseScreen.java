package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by kiettuannguyen on 19/02/2017.
 */

public abstract class BaseScreen implements Screen,InputProcessor {
    protected Game game;
    protected Stage mainStage;
    protected Stage uiStage;
    public final Vector2 view = new Vector2(640, 480);
    private boolean paused;

    public BaseScreen(Game g){

        game=g;
        mainStage=new Stage(new FitViewport(view.x,view.y));
        uiStage=new Stage(new FitViewport(view.x,view.y));
        setPaused(false);
        InputMultiplexer inputMultiplexer=new InputMultiplexer(this,uiStage,mainStage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        create();
    }
    public abstract void create();
    public abstract void update(float dT);

    public void show() {

    }

    public void render(float dT){
        uiStage.act(dT);
        if(!isPaused()){
            mainStage.act(dT);
            update(dT);
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    public void resize(int width,int height){
        mainStage.getViewport().update(width,height,true);
        uiStage.getViewport().update(width,height,true);
    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {

    }

    public boolean keyDown(int keycode) {
        return false;
    }

    public boolean keyUp(int keycode) {
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    public boolean scrolled(int amount) {
        return false;
    }
}

