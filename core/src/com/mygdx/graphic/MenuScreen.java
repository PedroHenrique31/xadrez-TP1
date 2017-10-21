package com.mygdx.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.ChessBoard;
import com.mygdx.game.Util;
import com.mygdx.pieces.King;
import com.mygdx.pieces.Pawn;

/**
 * Created by felipecosta on 10/2/17.
 */
public class MenuScreen implements Screen{

    Chess game;
    ChessBoard cb;
    EventHandler eventHandler;
    BoardDrawer board;
    OrthographicCamera camera;
    Pawn pawn;
    King king;

    public MenuScreen(Chess game, EventHandler eventHandler, ChessBoard cb) {
        this.game = game;
        this.cb = cb;
        this.eventHandler=eventHandler;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Util.SCREEN_WIDTH, Util.SCREEN_HEIGHT);
        board = new BoardDrawer(cb, game.sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.5f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*Esse trecho de código deixa a renderização mais lenta, pois os cliques do mouse eram interpre
        * tados como multiplos cliques
        * */
        Gdx.graphics.setContinuousRendering(false);
        if(Gdx.graphics.getDeltaTime()>5)
            Gdx.graphics.requestRendering();

        eventHandler.listen();


        camera.update();
        game.sb.setProjectionMatrix(camera.combined);


        game.sb.begin();

        /*Imprime as texturas em camadas (ordem importa)*/
        board.drawBoard();
        eventHandler.pathHighLighter();
        board.drawPieces();

        game.sb.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        board.dispose();
        game.dispose();
    }
}