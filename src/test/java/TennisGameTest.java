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
            "0, 0, 15 _ 0",
            "1, 0, 30 _ 0",
            "1, 1, 30 _ 15",
            "2, 1, 40 _ 15"
    })
    public void should_display_right_when_the_number_of_each_player_wins_is_lower_then_3_and_the_first_player_wins_one_point(int firstPlayerWins, int secondPlayerWins, String gameScore) {

        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins,secondPlayerWins,tennisGame);

        tennisGame.firstPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreGame()).isEqualTo(gameScore);

    }

    @Test
    @Parameters({
            "3, 1, 40 _ 30",
            "3, 2, 40 _ 40",
            "0, 2, 0 _ 40"
    })
    public void should_display_right_when_the_number_of_each_player_wins_is_lower_then_3_and_the_second_player_wins_one_point(int firstPlayerWins, int secondPlayerWins, String gameScore) {

        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(firstPlayerWins,secondPlayerWins,tennisGame);

        tennisGame.secondPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreGame()).isEqualTo(gameScore);

    }

    @Test
    @Parameters({
            "1, 3, 0",
            "1, 3, 1",
            "1, 3, 2",
            "2, 0, 3",
            "2, 1, 3",
            "2, 2, 3"
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
            "2 , 40 _ ADV"
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
    public void should_display_DEUCE_DUECE_when_the_player_how_has_40_as_score_wins_one_point_and_the_score_was_40_to_ADV(int player, int firstPlayerWins, int secondPlayerWins) {
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

    @Test
    public void should_display_zero_score_for_the_set_when_the_game_starts() {
        TennisGame tennisGame = new TennisGame();

        updatePlayersWins(0, 0,tennisGame);

        assertThat(tennisGame.getScoreSet()).isEqualTo("0 _ 0");
    }



    @Test
    @Parameters({
            "1, 0, 0",
            "2, 0, 0",
            "1, 1, 0",
            "2, 1, 1",
            "1, 4, 3",
            "2, 5, 5"
    })
    public void should_display_the_same_score_set_when_a_player_wins_one_point_but_does_not_win_the_game(int player, Integer scoreSetFirstPlayer, Integer scorSetSecondPlayer){
        StringBuilder score =new StringBuilder();
        score.append(scoreSetFirstPlayer.toString());
        score.append(" _ ");
        score.append(scorSetSecondPlayer.toString());

        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(scoreSetFirstPlayer, scorSetSecondPlayer, tennisGame);
        updatePlayersWins(1, 1,tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getScoreSet()).isEqualTo(score.toString());
    }

    @Test
    @Parameters({
            "0, 0, 1 _ 0",
            "2, 3, 3 _ 3",
            "4, 0, 5 _ 0"
    })
    public void should_display_right_score_set_when_the_first_player_wins_one_game(int firstPlayerScoreSet, int secondPlayerScoreSet, String gameScore) {

        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet, tennisGame);
        updatePlayersWins(3,2,tennisGame);

        tennisGame.firstPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreSet()).isEqualTo(gameScore);

    }

    @Test
    @Parameters({
            "1, 0, 1 _ 1",
            "1, 1, 1 _ 2",
            "1, 2, 1 _ 3",
            "1, 3, 1 _ 4"

    })
    public void should_display_right_score_set_when_the_second_player_wins_one_game(int firstPlayerScoreSet, int secondPlayerScoreSet, String gameScore) {

        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet, tennisGame);
        updatePlayersWins(2,3,tennisGame);

        tennisGame.secondPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreSet()).isEqualTo(gameScore);

    }

    @Test
    @Parameters({
            "1",
            "2"
    })
    public void should_display_6_6_as_set_score_when_the_score_set_was_6_6_and_one_of_the_players_wins_one_point(Integer player){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);

        playerWinsOnePoint(player,tennisGame);

        assertThat(tennisGame.getScoreSet()).isEqualTo("6 _ 6");
    }

    @Test
    @Parameters({
            "1",
            "2"
    })
    public void should_display_zero_zero_as_game_score_when_the_score_set_was_6_6_and_one_of_the_players_wins_one_point(Integer player){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);

        playerWinsOnePoint(player,tennisGame);

        assertThat(tennisGame.getScoreGame()).isEqualTo("0 _ 0");
    }

    @Test
    @Parameters({
            "0, 0, 1 _ 0",
            "1, 1, 2 _ 1",
            "2, 2, 3 _ 2",
            "3, 2, 4 _ 2",
            "4, 2, 5 _ 2",
            "5, 2, 6 _ 2",
            "9, 9, 10 _ 9"
    })
    public void should_adds_one_tie_break_point_to_the_score_of_the_first_player_when_he_wins_one_point_and_the_score_set_is_6_6(Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayeTow, String score){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayeTow, tennisGame);

        tennisGame.firstPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreTieBreakPoint()).isEqualTo(score);

    }

    @Test
    @Parameters({
            "1, 0, 1 _ 1",
            "3, 1, 3 _ 2",
            "5, 2, 5 _ 3",
            "9, 8, 9 _ 9"
    })
    public void should_adds_one_tie_break_point_to_the_score_of_the_second_player_when_he_wins_one_point_and_the_score_set_is_6_6(Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow, String score){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        tennisGame.secondPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreTieBreakPoint()).isEqualTo(score);

    }

    @Test
    @Parameters({
            "1, 6, 4",
            "1, 8, 7",
            "2, 4, 6",
            "2, 8, 9"
    })
    public void should_display_zero_zero_as_tie_break_score_when_one_player_wins_the_tie_break(int player, Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getScoreTieBreakPoint()).isEqualTo("0 _ 0");

    }

    @Test
    @Parameters({
            "6, 4",
            "8, 7"
    })
    public void should_display_7_6_as_score_set_when_the_first_player_wins_the_tie_break(Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        tennisGame.firstPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreSet()).isEqualTo("7 _ 6");
    }

    @Test
    @Parameters({
            "4, 6",
            "7, 8"
    })
    public void should_display_6_7_as_score_set_when_the_second_player_wins_the_tie_break( Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        tennisGame.secondPlayerWinsOnePoint();

        assertThat(tennisGame.getScoreSet()).isEqualTo("6 _ 7");
    }

    @Test
    @Parameters({
            "6,5",
            "5,5",
            "5,4",
            "2,4",
            "3,4"

    })
    public void should_display_the_match_has_not_finished_yet_when_no_one_wins_the_tennis_game (int firstPlayerScoreSet, int secondPlayerScoreSet){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet, tennisGame);
        updatePlayersWins(2,3, tennisGame);
        tennisGame.secondPlayerWinsOnePoint();
        assertThat(tennisGame.getTheWinner()).isEqualTo("the match has not finished yet");
    }

    @Test
    @Parameters({
            "5,6",
            "5,5",
            "4,5",
            "4,2",
            "3,4"

    })

    public void name (int firstPlayerScoreSet, int secondPlayerScoreSet){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet, tennisGame);
        updatePlayersWins(2,3, tennisGame);
        tennisGame.firstPlayerWinsOnePoint();

        assertThat(tennisGame.getTheWinner()).isEqualTo("the match has not finished yet");
    }

    @Test
    @Parameters({
            "5,6",
            "4,5",
            "3,5",
            "2,5",
            "1,5",
            "0,5"
    })
    public void should_display_player_tow_as_winner_when_he_wins_at_least_6_set_and_the_difference_between_the_score_set_of_the_players_is_at_least_2(int firstPlayerScoreSet, int secondPlayerScoreSet){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet,tennisGame);
        updatePlayersWins(2,3, tennisGame);
        tennisGame.secondPlayerWinsOnePoint();
        assertThat(tennisGame.getTheWinner()).isEqualTo("Player Tow");
    }

    @Test
    @Parameters({
            "6,5",
            "5,4",
            "5,3",
            "5,2",
            "5,1",
            "5,0"
    })
    public void should_display_player_One_as_winner_when_he_wins_at_least_6_set_and_the_difference_between_the_score_set_of_the_players_is_at_least_2(int firstPlayerScoreSet, int secondPlayerScoreSet){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(firstPlayerScoreSet, secondPlayerScoreSet,tennisGame);
        updatePlayersWins(3,2, tennisGame);
        tennisGame.firstPlayerWinsOnePoint();
        assertThat(tennisGame.getTheWinner()).isEqualTo("Player One");
    }

    @Test
    @Parameters({
            "1, 6, 5",
            "2, 5, 6"
    })
    public void should_display_the_player_how_wins_the_tie_break_as_a_winner(Integer player, Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow ){
        String winner =null;
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        playerWinsOnePoint(player, tennisGame);

        if(player ==1) winner ="Player One";
        if(player ==2) winner ="Player Tow";

        assertThat(tennisGame.getTheWinner()).isEqualTo(winner);
    }

    @Test
    @Parameters({
            "1, 5, 3",
            "2, 2, 4",
            "1, 6, 6",
            "1, 6, 7",
            "2, 6, 6",
            "2, 7, 6"
    })
    public void name(Integer player, Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow ){
        TennisGame tennisGame = new TennisGame();

        updatePlayersScoreSet(6, 6,tennisGame);
        updatePlayersScoreTieBreak(scoreTieBreakPlayerOne, scoreTieBreakPlayerTow, tennisGame);

        playerWinsOnePoint(player, tennisGame);

        assertThat(tennisGame.getTheWinner()).isEqualTo("the match has not finished yet");
    }


    private void updatePlayersScoreTieBreak(Integer scoreTieBreakPlayerOne, Integer scoreTieBreakPlayerTow, TennisGame tennisGame) {
        for (int i = 0; i < scoreTieBreakPlayerOne; i++)
            tennisGame.scoreTieBreakPlayerOne++;
        for (int i = 0; i < scoreTieBreakPlayerTow; i++)
            tennisGame.scoreTieBreakPlayerTow++;
    }

    private void updatePlayersWins(int firstPlayerWins, int secondPlayerWins, TennisGame tennisGame){
        for (int i = 0; i < firstPlayerWins; i++)
            //tennisGame.
            tennisGame.playerOneGameWins ++;

        for (int i = 0; i < secondPlayerWins; i++)
            tennisGame.playerTowGameWins++;
    }

    private void playerWinsOnePoint(int player, TennisGame tennisGame) {
        if(player ==1)  tennisGame.firstPlayerWinsOnePoint();
        if(player ==2)  tennisGame.secondPlayerWinsOnePoint();
    }

    private void updatePlayersScoreSet(int scoreSetPlayerOne, int scoreSetPlayerTow, TennisGame tennisGame) {

        for(int i = 0 ; i<scoreSetPlayerOne ; i++)
            tennisGame.scoreSetPlayerOne++;

        for(int i = 0 ; i<scoreSetPlayerTow ; i++)
            tennisGame.scoreSetPlayerTow++;
    }
}