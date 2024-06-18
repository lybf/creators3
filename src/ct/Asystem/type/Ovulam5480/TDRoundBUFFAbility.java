package ct.Asystem.type.Ovulam5480;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import mindustry.content.Fx;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;
import mindustry.type.StatusEffect;

//塔防单位的状态仪
public class TDRoundBUFFAbility extends StatusFieldAbility {
    public static Color color1;

    public TDRoundBUFFAbility(StatusEffect effect, float duration, float reload, float range, Color color) {
        super(effect, duration, reload, range);
        activeEffect = Fx.none;
        duration = 2;//持续时间
        reload = 1;//装填时间
        color1 = color;
    }

    public String localized() {

        return Core.bundle.get("cttd.RoundBUFFAbility");
    }

    ;

    @Override
    public void draw(Unit unit) {
        Draw.color(color1);
        Draw.z(Layer.shields);
        Fill.circle(unit.x, unit.y, range);
        Draw.reset();
    }
}