package ct.Asystem.type.power;

import mindustry.content.Items;
import mindustry.type.Item;
import mindustry.world.blocks.power.ImpactReactor;
import mindustry.world.blocks.storage.CoreBlock;

/*
 *@Author:LYBF
 *@Date  :2024/3/27
 *@Desc  :核心电厂 消耗核心物资产电
 *        CoreImpactReactor ,consume core items to production power
 */
public class CoreImpactReactor extends ImpactReactor {

    public int timerTakeItem = timers++;

    public Item fuelItem = Items.blastCompound;

    /*
     *每次从核心拿取物资的数量
     *The amount of items taken from the core at a time
     */
    public int itemCountPer = 1;

    /*
     *每次从核心拿取物资的间隔
     */
    public float takeItemDelay = 60f;

    public CoreImpactReactor(String name) {
        super(name);
    }


    public class CoreImpactReactorBuild extends ImpactReactorBuild {


        @Override
        public void updateTile() {
            if (power.status > 0.99f) {//有电则直接从核心取燃料
                if (timer(timerTakeItem, takeItemDelay)) {
                    for (int i = 0; i < itemCountPer; i++) {
                        takeFromCore(fuelItem);
                    }
                }
            }
            super.updateTile();
        }


        private void takeFromCore(Item fuelItem) {
            CoreBlock.CoreBuild core = team.core();
            if (core == null) return;
            if (core.items().has(fuelItem) && core.items().get(fuelItem) > 0 && this.acceptItem(core, fuelItem)) {
                this.handleItem(core, fuelItem);
                core.items().remove(fuelItem, 1);
            }
        }
    }
}