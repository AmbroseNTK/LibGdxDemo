package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainActivity extends ApplicationAdapter {
	SpriteBatch batch;
	Texture mouseTexture;
	Texture cheeseTexture;
	Texture floorTexture;
	Texture winTexture;

	Sprite mouse;
	Sprite cheese;

	boolean win;
	@Override
	public void create () {
		batch = new SpriteBatch();
		mouseTexture=new Texture("mouse.png");
		cheeseTexture=new Texture("cheese.png");
		floorTexture=new Texture("tiles.jpg");
		winTexture=new Texture("you-win.png");
		mouse=new Sprite(mouseTexture);
		mouse.setPosition(20,20);
		cheese=new Sprite(cheeseTexture);
		cheese.setPosition(400,300);
		win=false;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		checkKeyboard();
		checkWin();
		batch.begin();
		mouse.draw(batch);
		cheese.draw(batch);
		if(win)
			batch.draw(winTexture,170,60);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mouseTexture.dispose();
		cheeseTexture.dispose();
		floorTexture.dispose();
		winTexture.dispose();
	}
	private void checkKeyboard(){
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
			mouse.setX(mouse.getX()-1);
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
			mouse.setX(mouse.getX()+1);
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			mouse.setY(mouse.getY()+1);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			mouse.setY(mouse.getY()-1);
	}
	private void checkWin(){
		if((mouse.getX()>cheese.getX())
				&&(mouse.getX()+mouseTexture.getWidth()<cheese.getX()+cheeseTexture.getWidth())
				&&(mouse.getY()>cheese.getY())
				&&(mouse.getY()+mouseTexture.getHeight()<cheese.getY()+cheeseTexture.getHeight()))
			win=true;
	}
}
