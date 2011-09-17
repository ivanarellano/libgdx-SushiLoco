package com.tinyrender.androidgame.rollemup.screens;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.tinyrender.androidgame.rollemup.RollEmUp;

public class GameScreen extends InputAdapter implements Screen {	
	final static float MAX_VELOCITY = 15f;		
   	
	RollEmUp game;
	World world;
	Vector2 gravity;
	
	Body ground;
	Fixture playerFixture;
	Body player;
	Vector2 vel;
	Vector2 pos;
	
	Fixture platform;
	
	OrthographicCamera cam;
	Box2DDebugRenderer renderer;
	Vector3 point = new Vector3();
	
	public GameScreen(RollEmUp g) {
		game = g;
		gravity = new Vector2();
		cam = new OrthographicCamera(48, 32);
		Gdx.input.setInputProcessor(this);
	}
	
	private void createWorld() {		
		ground = createEdge(-100.0f, 0, 100.0f, 0);
		player = createCircle(BodyType.DynamicBody, 1.5f, 0, 12.0f, 20.0f, true);
		
		createBox(BodyType.StaticBody, 0, 10.0f, 3.0f, 0.5f);
	}
	
	private Body createEdge(float x1, float y1, float x2, float y2) {
        BodyDef bd = new BodyDef();
        Body body = world.createBody(bd);

        EdgeShape shape = new EdgeShape();
        shape.set(x1, y1, x2, y2);
        FixtureDef fd = new FixtureDef();
        fd.friction = 0.5f;
        fd.density = 0;
        fd.shape = shape;
        body.createFixture(fd);
        shape.dispose();
        
		return body;
	}
	
	private Body createBox(BodyType bodyType, float x, float y, float hx, float hy) {
        BodyDef bd = new BodyDef();
        bd.type = bodyType;
        bd.position.set(x, y);
        Body body = world.createBody(bd);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(hx, hy);
        platform = body.createFixture(shape, 0);
        
		return body;
	}
	
	private Body createCircle(BodyType bodyType, float radius, float x, float y, float density, boolean isPlayer) {
		BodyDef bd = new BodyDef();
		bd.type = bodyType;
		bd.position.set(x, y);
		Body body = world.createBody(bd);			
 
		CircleShape shape = new CircleShape();		
		shape.setRadius(radius);
		if(isPlayer) {
			FixtureDef fd = new FixtureDef();
			fd.shape = shape;
			fd.density = density;
			fd.friction = 0.8f;
			playerFixture = body.createFixture(fd);
			shape.dispose();
		} else {
			body.createFixture(shape, density);
			shape.dispose();
		}
		
		return body;
	}

	@Override
	public void dispose() {
		world.dispose();
		renderer.dispose();
		
		world = null;
		renderer = null;
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
		gravity = gravity.set(0, -10.0f);
		world = new World(gravity, true);		
		renderer = new Box2DDebugRenderer();
		
		createWorld();
	}

	@Override
	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		world.step(Gdx.graphics.getDeltaTime(), 5, 2);
		cam.position.set(player.getPosition().x, player.getPosition().y, 0);
		cam.update();
		cam.apply(Gdx.gl11);
		renderer.render(world, cam.combined);
		
		vel = player.getLinearVelocity();
		pos = player.getPosition();
 
		// cap max velocity on x		
		if(Math.abs(vel.x) > MAX_VELOCITY) {			
			vel.x = Math.signum(vel.x) * MAX_VELOCITY;
			player.setLinearVelocity(vel.x, vel.y);
		}
		
		if(Gdx.input.getAccelerometerY() <= -0.2f && vel.x > -MAX_VELOCITY) {
			player.applyLinearImpulse(Gdx.input.getAccelerometerY() * 6.0f, 0, pos.x, pos.y);
		}
		
		if(Gdx.input.getAccelerometerY() >= 0.2f && vel.x < MAX_VELOCITY) {
			player.applyLinearImpulse(Gdx.input.getAccelerometerY() * 6.0f, 0, pos.x, pos.y);
		}
 
		cam.project(point.set(pos.x, pos.y, 0));
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
		return false;
	}
}
