package ct.content;

import ct.Asystem.type.yuan;
import mindustry.content.UnitTypes;
import mindustry.entities.abilities.MoveEffectAbility;

import static ct.Asystem.type.CTColor.C;
import static ct.content.Effect.NewFx.拖尾;
import static ct.content.Effect.NewFx.拖尾圈;

public class yuanban {
    public static void load() {
        UnitTypes.gamma.coreUnitDock = true;
        UnitTypes.alpha.coreUnitDock = true;
        UnitTypes.beta.coreUnitDock = true;

        UnitTypes.gamma.abilities.add(new yuan(30, 120, C("97faff")));
        UnitTypes.beta.abilities.add(new yuan(20, 120, C("97faff")));
        UnitTypes.alpha.abilities.add(new yuan(10, 120, C("97faff")));
        UnitTypes.gamma.abilities.add(new MoveEffectAbility(0f, -7f, C("89f08e"), 拖尾, 4f) {{
            minVelocity = 0.4f;
            rotateEffect = true;
            effectParam = 2;
            rotation = 180;
            teamColor = true;
        }});
        UnitTypes.beta.abilities.add(new MoveEffectAbility(0f, -7f, C("89f08e"), 拖尾圈, 4f) {{
            minVelocity = 1f;//效果的间隔时间
            rotateEffect = true;
            effectParam = 2;
            rotation = 180;
            teamColor = true;
        }});
    }
}
