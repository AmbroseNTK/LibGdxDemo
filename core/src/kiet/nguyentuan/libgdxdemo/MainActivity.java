package kiet.nguyentuan.libgdxdemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainActivity extends Game {

	public void create () {
		BalloonLevel balloonLevel=new BalloonLevel(this);
		setScreen(balloonLevel);
	}
}
