package com.tinyrender.androidgame.rollemup.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.androidgame.rollemup.RollEmUp;

public class GameScreen extends InputAdapter implements Screen {	
	final static float MAX_VELOCITY = 10f;
	RollEmUp game;	
	World world;
	
	Body player;
	Fixture playerFixture;
	OrthographicCamera cam;
	Box2DDebugRenderer renderer;
	
	Vector3 point = new Vector3();
	Vector2 last = null;

	public GameScreen(RollEmUp g) {
		game = g;
		cam = new OrthographicCamera(28, 20);
		Gdx.input.setInputProcessor(this);
	}

	private void createWorld() {
		Body ground = createEdge(-100.0f, 0, 100.0f, 0, 0, 0.4f);
		player = createPlayer(BodyType.DynamicBody, 1.5f, 0, 12.0f, 20.0f);
 
		for(int i = 0; i < 20; i++) {
			Body circle = createCircle(BodyType.DynamicBody, (float)Math.random() * 0.5f + 0.2f, 3);
			circle.setTransform((float)Math.random() * 10f - (float)Math.random() * 10f, (float)Math.random() * 10 + 6, (float)(Math.random() * 2 * Math.PI));
		}
	}
	
	private Body createEdge(float x1, float y1, float x2, float y2, float density, float friction) {
        BodyDef bd = new BodyDef();
        Body body = world.createBody(bd);

        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        
        FixtureDef fd = new FixtureDef();
        fd.friction = friction;
        fd.density = density;
        fd.shape = shape;
        body.createFixture(fd);
        
        shape.dispose();
        
		return body;
	}

	private Body createEdge(float x1, float y1, float x2, float y2) {
		BodyDef def = new BodyDef();
		Body box = world.createBody(def);
 
		EdgeShape edge = new EdgeShape();		
		edge.set(new Vector2(0, 0), new Vector2(x2 - x1, y2 - y1));
		box.createFixture(edge, 0);
		box.setTransform(x1, y1, 0);
		edge.dispose();
 
		return box;
	}

	private Body createCircle(BodyType type, float radius, float density) {
		BodyDef def = new BodyDef();
		def.type = type;
		Body box = world.createBody(def);
 
		CircleShape poly = new CircleShape();
		poly.setRadius(radius);
		box.createFixture(poly, density);
		poly.dispose();
 
		return box;
	}

	private Body createPlayer(BodyType bodyType, float radius, float x, float y, float density) {
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(x, y);
		Body body = world.createBody(bd);
 
		CircleShape shape = new CircleShape();		
		shape.setRadius(radius);
		FixtureDef fd = new FixtureDef();
		fd.shape = shape;
		fd.density = density;
		fd.friction = 1.0f;
		playerFixture = body.createFixture(fd);
		shape.dispose();
 
		return body;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void render(float deltaTime) {
		world.step(Gdx.graphics.getDeltaTime(), 5, 2);
		
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.update();
		cam.apply(Gdx.gl11);
		
		renderer.render(world, cam.combined);
 
		Vector2 vel = player.getLinearVelocity();
		Vector2 pos = player.getPosition();
		cam.project(point.set(pos.x, pos.y, 0));
 
		// cap max velocity on x		
		if(Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			player.setLinearVelocity(vel.x, vel.y);
		}
 
		if(Gdx.input.getAccelerometerY() <= -0.2f && vel.x > -MAX_VELOCITY) {
			player.applyLinearImpulse(Gdx.input.getAccelerometerY() * 9.0f, 0, pos.x, pos.y);
		}
		
		if(Gdx.input.getAccelerometerY() >= 0.2f && vel.x < MAX_VELOCITY) {
			player.applyLinearImpulse(Gdx.input.getAccelerometerY() * 9.0f, 0, pos.x, pos.y);
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		world = new World(new Vector2(0, -20), true);		
		renderer = new Box2DDebugRenderer();
		createWorld();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}
 
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointerId, int button) {
		cam.unproject(point.set(x, y, 0));
		
		if(button == Input.Buttons.LEFT) {
			if(last == null) {
				last = new Vector2(point.x, point.y);
			} else {
				createEdge(last.x, last.y, point.x, point.y);
				last.set(point.x, point.y);
			}
		} else {
			last = null;
		}
 
		return false;
	}

}