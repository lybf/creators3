package ct.Asystem.type.VXV;


import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.math.geom.Rect;
import arc.util.Time;
import mindustry.Vars;
import mindustry.ai.types.CommandAI;
import mindustry.entities.abilities.ShieldRegenFieldAbility;
import mindustry.gen.Unit;
import mindustry.world.Tile;

import static mindustry.Vars.content;

//TODO 破晓单位寻路显示
public class CTFreeBar {
    private static float max1 = 0;
    public static Rect PlayerRect = new Rect();

    static {
        max1 = ((ShieldRegenFieldAbility) content.units().copy().filter(ut -> ut.abilities.find(abil -> abil instanceof ShieldRegenFieldAbility) != null).sort(ut -> ((ShieldRegenFieldAbility) ut.abilities.find(abil -> abil instanceof ShieldRegenFieldAbility)).max).peek().abilities.find(abil -> abil instanceof ShieldRegenFieldAbility)).max;
    }

    public static void draw(Unit unit) {
        if (unit.dead()) return;

        if (Core.settings.getBool("单位寻路显示") && Core.settings.getInt("单位寻路显示长度") > 0 && !unit.isFlying() && !unit.isPlayer()) {
            if (!(unit.controller() instanceof CommandAI)) {
                Draw.z(66.0F);
                Tile tile = unit.tileOn();
                Draw.reset();

                for (int tileIndex = 1; tileIndex <= Core.settings.getInt("单位寻路显示长度"); ++tileIndex) {
                    Tile nextTile = Vars.pathfinder.getTargetTile(tile, Vars.pathfinder.getField(unit.team, unit.pathType(), 0));
                    if (nextTile == null) {
                        break;
                    }

                    if (PlayerRect.contains(nextTile.worldx(), nextTile.worldy())) {
                        Lines.stroke((float) Core.settings.getInt("单位寻路显示线宽"));
                        Draw.color(unit.team.color, Color.lightGray, Mathf.absin(Time.time, 8.0F, 1.0F));
                        Lines.dashLine(tile.worldx(), tile.worldy(), nextTile.worldx(), nextTile.worldy(), (int) (Mathf.len(nextTile.worldx() - tile.worldx(), nextTile.worldy() - tile.worldy()) / 4.0F));
                    }
                    tile = nextTile;
                }

                Draw.reset();
            }
        }

        if (!PlayerRect.contains(unit.x, unit.y)) {
        }


    }
}
