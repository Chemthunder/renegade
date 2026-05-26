package com.peak.renegade.game;

import com.peak.renegade.api.game.level.GameLayer;
import com.peak.renegade.game.index.GameItems;
import com.peak.renegade.game.index.GameLayers;
import com.peak.renegade.game.index.GameLevels;

/**
 * @author Chemthunder
 */
public class GameInit {

    public static void bootstrap() {
        GameLayers.index();
        GameLevels.index();

        GameItems.index();
    }
}
