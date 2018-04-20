package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.InputCommand;
import tw.core.Game;
import tw.views.GameView;

import java.io.IOException;

import static org.mockito.Mockito.*;

/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private GameController gameController;
    private Game game;
    private GameView gameView;
    private InputCommand inputCommand;

    @Before
    public void setUp() throws Exception {
        game = mock(Game.class);
        gameView = mock(GameView.class);
        gameController = new GameController(game, gameView);
        inputCommand = mock(InputCommand.class);
    }

    @Test
    public void testWhenBeginGame() throws IOException{
        gameController.beginGame();
        verify(gameView).showBegin();
    }

    @Test
    public void testWhenCheckCoutinueReturnTrue() throws IOException{
        when(game.checkCoutinue()).thenReturn(true).thenReturn(false);
        gameController.play(inputCommand);
        verify(game, times(1)).guessHistory();
    }

    @Test
    public void testWhenCheckCoutinueReturnFalse() throws IOException{
        when(game.checkCoutinue()).thenReturn(false);
        gameController.play(inputCommand);
        verify(gameView, times(1)).showGameStatus(game.checkStatus());
    }


}