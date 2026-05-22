package com.peak.renegade.game.index;

import com.peak.renegade.api.game.level.GameLevel;
import com.peak.renegade.core.Renegade;
import com.peak.renegade.core.index.RenegadeRegistries;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface GameLevels {
    List<GameLevel> LEVELS = new ArrayList<>();

    // LOBBY
    GameLevel LOBBY = registerLevel("lobby");

    // PROLOGUE
    GameLevel CORE = registerLevel("core");
    GameLevel READY_OR_NOT = registerLevel("ready_or_not");
    GameLevel HERE_I_COME = registerLevel("here_i_come");

    // INTO THE BLAZE
    GameLevel SEIZE_THE_DAY = registerLevel("seize_the_day");
    GameLevel ROT_IN_HELL = registerLevel("rot_in_hell");
    GameLevel CARPE_DIEM = registerLevel("carpe_diem");
    GameLevel SHADED_FLOWERS = registerLevel("shaded_flowers");

    // EVER DOWNWARD
    GameLevel AMID_THE_SULPHURIC_SEA = registerLevel("amid_the_sulphuric_sea");
    GameLevel CARRY_FORTH = registerLevel("carry_forth");
    GameLevel BURN_IN_AGONY = registerLevel("burn_in_agony");
    GameLevel OVER_AND_UPWARD = registerLevel("over_and_upward");

    // FIRE WHEN READY
    GameLevel WHERE_NOBODY_SEES = registerLevel("where_nobody_sees");
    GameLevel THE_RED_ROOMS = registerLevel("the_red_rooms");
    GameLevel FORWARD = registerLevel("forward");
    GameLevel CASTLEBREAKER = registerLevel("castlebreaker");

    // SPARROWFLIGHT
    GameLevel TAKE_TO_THE_SKIES = registerLevel("take_to_the_skies");
    GameLevel OVER_AN_EVENT_HORIZON = registerLevel("over_an_event_horizon");
    GameLevel BECOME_THE_HURRICANE = registerLevel("become_the_hurricane");
    GameLevel TEMPEST = registerLevel("tempest");

    // MORRIGAN
    GameLevel SEE_NO_EVIL = registerLevel("see_no_evil");
    GameLevel HEAR_NO_EVIL = registerLevel("hear_no_evil");
    GameLevel BE_NO_EVIL = registerLevel("be_no_evil");
    GameLevel DO_NO_EVIL = registerLevel("do_no_evil");

    // CRESCENDO
    GameLevel SINK_IN_SUFFERING = registerLevel("sink_in_suffering");
    GameLevel LETS_PLAY_A_GAME = registerLevel("lets_play_a_game");
    GameLevel BLACKFIRE_BACKFIRE = registerLevel("blackfire_backfire");
    GameLevel BURN_IT_DOWN = registerLevel("burn_it_down");

    // DIURNAL WAYFINDER
    GameLevel DIE_WITH_A_SMILE = registerLevel("die_with_a_smile");
    GameLevel SWEET_SPACE_SONG = registerLevel("sweet_space_song");
    GameLevel HORRORFILLED_CAROUSEL = registerLevel("horrorfilled_carousel");
    GameLevel SEE_YOU_DOWN_BELOW = registerLevel("see_you_down_below");

    // FINALE
    GameLevel ETHOS = registerLevel("ethos");
    GameLevel PATHOS = registerLevel("pathos");
    GameLevel LOGOS = registerLevel("logos");

    // CREDITS
    GameLevel CREDITS = registerLevel("credits");

    static GameLevel registerLevel(String name) {
        GameLevel built = new GameLevel(name);
        LEVELS.add(built);
        return built;
    }

    static void index() {
        LEVELS.forEach(gameLevel -> {
            Registry.register(RenegadeRegistries.GAME_LEVEL, Renegade.id(gameLevel.name()), gameLevel);
        });
    }

    static String createTranslationKey(GameLevel level) {
        return "level." + level.name();
    }

    static void pairWithLang(FabricLanguageProvider.TranslationBuilder translationBuilder) {
        LEVELS.forEach(level -> {
            translationBuilder.add(createTranslationKey(level), MiscUtils.formatString(level.name()));
        });
    }
}
