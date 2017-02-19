package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by kiettuannguyen on 19/02/2017.
 */
public class CheeseMenu extends BaseScreen {


    public CheeseMenu(Game g) {
        super(g);
    }
    public void create(){
        BaseActor background=new BaseActor(new Texture("tiles-menu.jpg"));
        BaseActor titleText=new BaseActor(new Texture("cheese-please.png"));
        titleText.setPosition(20,100);
        BitmapFont font=new BitmapFont();
        String text="Press S to start, M for main menu";
        Label.LabelStyle style=new Label.LabelStyle(font, Color.YELLOW);
        Label instructions=new Label(text,style);
        instructions.setFontScale(2);
        instructions.setPosition(100,50);
        instructions.addAction(Actions.forever(Actions.sequence(Actions.color(new Color(1,1,0,1),0.5f),Actions.delay(0.5f),Actions.color(new Color(0.5f,0.5f,0,1),0.5f))));

        uiStage=new Stage();
        uiStage.addActor(background);
        uiStage.addActor(titleText);
        uiStage.addActor(instructions);

    }
    public void update(float dT){

    }
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.S)
            game.setScreen(new CheeseLevel(game));
        return false;
    }
}
