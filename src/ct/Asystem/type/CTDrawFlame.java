package ct.Asystem.type;

import arc.graphics.g2d.Draw;
import mindustry.gen.Building;
import mindustry.graphics.Layer;
import mindustry.world.draw.DrawFlame;

public class CTDrawFlame extends DrawFlame {
    @Override
    public void draw(Building build) {
        if (build.warmup() > 0f && flameColor.a > 0.001f) {
            Draw.z(Layer.block + 0.01f);
            Draw.alpha(build.warmup());
            Draw.rect(top, build.x, build.y);
        }
    }

    @Override
    public void drawLight(Building build) {
    }
}
