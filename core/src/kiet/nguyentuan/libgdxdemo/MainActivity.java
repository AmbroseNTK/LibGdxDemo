package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ArraySelection;
import com.badlogic.gdx.utils.Array;

public class MainActivity extends ApplicationAdapter {

	Stage mainStage;

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

	@Override
	public void create () {
		mouseTexture=new Texture("mouse.png");
		cheeseTexture=new Texture("cheese.png");
		floorTexture=new Texture("tiles.jpg");
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

		mainStage=new Stage();
		mainStage.addActor(floor);
		mainStage.addActor(cheese);
		mainStage.addActor(mouse);
		mainStage.addActor(winPanel);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.8f,0.8f,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float dT=Gdx.graphics.getDeltaTime();
		mainStage.act(dT);
		checkKeyboard();
		checkWin();
		mainStage.draw();
		if(win)
			winPanel.setVisible(true);
	}
	
	@Override
	public void dispose () {
		mouseTexture.dispose();
		cheeseTexture.dispose();
		floorTexture.dispose();
		winTexture.dispose();
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
	}
	private void checkWin(){
		cheeseRec=cheese.getBoundingRectange();
		mouseRec=mouse.getBoundingRectange();
		if(cheeseRec.contains(mouseRec))
			win=true;
	}
}
