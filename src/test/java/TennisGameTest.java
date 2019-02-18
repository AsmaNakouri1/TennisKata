import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class TennisGameTest {

    @Test
    public void should_display_zero_score_when_the_game_starts(){
        TennisGame tennisGame = new TennisGame();

        assertThat(tennisGame.getScoreGame()).isEqualTo("0 _ 0");
    }

    @Test
    @Parameters({
            "0, 0, 0 _ 0",
            "1, 0, 15 _ 0",
            "2, 0, 30 _ 0",
            "2, 1, 30 _ 15",
            "3, 1, 40 _ 15",
            "3, 2, 40 _ 30",
            "3, 3, 40 _ 40",
            "0, 3, 0 _ 40"
    })
    public void should_display_right_when_the_number_of_each_player_wins_is_lower_then_3(int firstPlayerWins, int secondPlayerWins, String gameScore) {

        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins,secondPlayerWins,tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo(gameScore);

    }


    @Test
    @Parameters({
            "1, 3, 0",
            "1, 3, 1",
            "1, 3, 2",
            "2, 0, 3",
            "2, 1, 3",
            "2, 2, 3",

    })
    public void should_display_zero_zero_as_game_score_when_a_player_wins_4_points_and_has_tow_more_points_than_the_other(int player, int firstPlayerWins, int secondPlayerWins) {
        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins,secondPlayerWins,tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo("0 _ 0");
    }


    @Test
    @Parameters({
            "1 , ADV _ 40",
            "2 , 40 _ ADV",
    })
    public void should_display_ADV_for_the_player_how_wins_one_point_and_the_score_was_40_40(int player, String gameScore) {
        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(3,3,tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo(gameScore);
    }


    @Test
    @Parameters({
            "1 , 3, 4",
            "2 , 4, 3"
    })
    public void should_display_DEUCE_Deuce_when_the_player_how_has_40_as_score_wins_one_point_and_the_score_was_40_to_ADV(int player, int firstPlayerWins, int secondPlayerWins) {
        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins, secondPlayerWins,tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo("DEUCE _ DEUCE");
    }

    @Test
    @Parameters({
            "1, 4, 3",
            "2, 3, 4",
            "2, 4, 5",
            "1, 5, 4"
    })
    public void should_display_zero_zero_as_game_score_when_the_tow_players_win_more_than_three_points_and_the_difference_between_the_number_of_points_is_equal_to_2(int player, int firstPlayerWins, int secondPlayerWins) {
        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins,secondPlayerWins,tennisGame);

        playerWinsOnePoint(player,tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo("0 _ 0");

    }

    private void updatePlayersWins(int firstPlayerWins, int secondPlayerWins, TennisGame tennisGame){
        for (int i = 0; i < firstPlayerWins; i++)
            tennisGame.firstPlayerWinsOnePoint();

        for (int i = 0; i < secondPlayerWins; i++)
            tennisGame.secondPlayerWinsOnePoint();
    }

    private void playerWinsOnePoint(int player, TennisGame tennisGame) {
        if(player ==1)  tennisGame.firstPlayerWinsOnePoint();
        if(player ==2)  tennisGame.secondPlayerWinsOnePoint();
    }

}