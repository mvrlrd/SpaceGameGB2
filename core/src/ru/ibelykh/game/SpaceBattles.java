//package ru.ibelykh.game;
//
//import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//
//public class SpaceBattles extends ApplicationAdapter {
//	SpriteBatch batch;
//	Texture img;
//
//	@Override
//	public void create () {
//		batch = new SpriteBatch();
//		img = new Texture("starsky.jpg");
//
//		Vector2 v1 = new Vector2(1f,4f);
//		Vector2 v2 = new Vector2(0f,-1f);
//		v1.add(v2);
//		System.out.println("v1.x= "+v1.x+"  v1.y= "+v1.y);
//		System.out.println(v1.nor()+"    "+v1.nor().len());
//
//	}
//
//	@Override
//	public void render () {
//		Gdx.gl.glClearColor(0.2f, 0.2f, 0.7f, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//		batch.begin();
////		batch.setColor(0.8f,0.44f,0.6f,0.23f);
//		batch.draw(img, 0, 0);
//		batch.end();
//	}
//
//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
//}
