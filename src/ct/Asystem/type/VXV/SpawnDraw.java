package ct.Asystem.type.VXV;

import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.util.Time;
import mindustry.Vars;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.world.Tile;


import static mindustry.Vars.spawner;
import static mindustry.Vars.state;

public class SpawnDraw {
    private static final Color[] spawnColor = new Color[]{Pal.command, Pal.sapBullet, Pal.place, Pal.regen};

    private static void draw() {
        //0 陆军
        //1 蜘蛛
        //2 海军
        for (var i = 0; i <= 2; i++) {
            for (var tile : spawner.getSpawns()) {
                Draw.z(Layer.flyingUnit + 2.5f);
                while (true) {
                    Tile nextTile = Vars.pathfinder.getTargetTile(tile, Vars.pathfinder.getField(state.rules.waveTeam, i, 0));
                    if (nextTile == null || tile == nextTile) {
                        break;
                    }

                    //用于仅显示玩家视野里的draw
                    if (new Rect().contains(nextTile.worldx(), nextTile.worldy())) {
                        Lines.stroke(1);
                        Draw.color(state.rules.waveTeam.color, spawnColor[i], Mathf.absin(Time.time, 8.0F, 1.0F));
                        Lines.dashLine(tile.worldx(), tile.worldy(), nextTile.worldx(), nextTile.worldy(), (int) (Mathf.len(nextTile.worldx() - tile.worldx(), nextTile.worldy() - tile.worldy()) / 4.0F));
                    }

                    tile = nextTile;
                }

                Draw.reset();
            }
        }

        //空军寻路
        /*for (var tile : spawner.getSpawns()) {
            Draw.z(Layer.flyingUnit + 2.5f);
            while (true) {
                Tile nextTile = Vars.pathfinder.getTargetTile(tile, Vars.pathfinder.getField(state.rules.waveTeam, 3, 0));
                if (nextTile == null || tile == nextTile) {
                    break;
                }

                if (PlayerRect.contains(nextTile.worldx(), nextTile.worldy())) {
                    Lines.stroke(1);
                    Draw.color(state.rules.waveTeam.color, spawnColor[3], Mathf.absin(Time.time, 8.0F, 1.0F));
                    Lines.dashLine(tile.worldx(), tile.worldy(), nextTile.worldx(), nextTile.worldy(), (int) (Mathf.len(nextTile.worldx() - tile.worldx(), nextTile.worldy() - tile.worldy()) / 4.0F));
                }
                tile = nextTile;
            }

            Draw.reset();
        }*/
    }
}