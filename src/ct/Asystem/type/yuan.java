package ct.Asystem.type;


import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Mathf;
import arc.util.Time;
import mindustry.entities.abilities.Ability;
import mindustry.gen.Unit;
import mindustry.graphics.Layer;

import static ct.Asystem.type.CTColor.C;

public class yuan extends Ability {
    //半径
    public float radius = 100f / 2;
    //时间间隔
    public float radiusTime = 90f;
    //显示时间
    public float showshowTime = 40f;
    //颜色
    public Color color = C("97fdff");
    //粗细倍率
    public float multi = 0.25f;

    public yuan(float 范围, float 间隔, Color 颜色) {
        radius = 范围;
        showshowTime = 间隔;
        color = 颜色;
        display = false;

    }


    @Override
    public void draw(Unit unit) {
        float progress = (Time.time % radiusTime) / radiusTime;
        float showProgress = (Time.time % radiusTime) / showshowTime;

        Draw.z(Layer.effect);

        Lines.stroke(Math.min(4f, (1 - progress) * 16f) * multi);
        color.a(Mathf.clamp((1 - showProgress) * 4f, 0f, 1f));
        Draw.color(color);

        Lines.circle(unit.x, unit.y, progress * radius);
        Draw.reset();
        Lines.stroke(1f);
    }

}


