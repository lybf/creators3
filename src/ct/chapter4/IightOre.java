package ct.chapter4;

import arc.graphics.Color;
import mindustry.graphics.Drawf;
import mindustry.type.Item;
import mindustry.world.Tile;
import mindustry.world.blocks.environment.OreBlock;
//没用到本类
public class IightOre extends OreBlock {
    public IightOre(String name, Item ore) {
        super(name);
        this.localizedName = ore.localizedName;
        this.itemDrop = ore;
        this.variants = 3;
        this.mapColor.set(ore.color);
        this.useColor = true;

        this.lightColor = Color.white.cpy();
        this.emitLight = false;
        this.lightRadius = 60.0F;
    }

    public void drawEnvironmentLight(Tile tile) {
        Drawf.light(tile.worldx(), tile.worldy(), this.lightRadius, this.lightColor, this.lightColor.a);
    }
    public void init() {
        if (this.emitLight) {
            this.clipSize = Math.max(this.clipSize, this.lightRadius * 2.0F);
        }
    }
}
