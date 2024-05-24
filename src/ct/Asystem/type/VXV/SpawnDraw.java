package ct.Asystem.type.VXV;

import arc.Events;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.graphics.Layer;
import mindustry.world.Tile;

/*
 *@Date  :2024/5/13
 */
public class SpawnDraw {

    public static boolean enable = false;

    public static void init() {
        Events.run(EventType.Trigger.draw, () -> {
            if (!enable) return;
            Draw.draw(Layer.flyingUnit + 2.5f, draw);
        });
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        SpawnDraw.enable = enable;


    }

    static Runnable draw = () -> {
        //0 陆军
        //1 蜘蛛
        //2 海军
        for (int i = 0; i <= 2; i++) {

            for (var tile : Vars.spawner.getSpawns()) {
                Draw.z(Layer.flyingUnit + 2.5f);//+ 2.5f);
                Lines.stroke(2, Vars.state.rules.waveTeam.color);
                Draw.color(Vars.state.rules.waveTeam.color, Mathf.absin(Time.time, 8.0F, 1.0F));
                while (true) {
                    Tile nextTile = Vars.pathfinder.getTargetTile(tile, Vars.pathfinder.getField(Vars.state.rules.waveTeam, i, 0));
                    if (nextTile == null || tile == nextTile) {
                        break;
                    }
                    Lines.dashLine(tile.worldx(), tile.worldy(), nextTile.worldx(), nextTile.worldy(),
                            (int) (Mathf.len(nextTile.worldx() - tile.worldx(), nextTile.worldy() - tile.worldy()) / 4f));
                    tile = nextTile;
                }
                Draw.reset();
            }

        }
    };

}