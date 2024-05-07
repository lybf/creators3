package ct.Asystem.type.power;

import arc.Events;
import mindustry.game.EventType;
import mindustry.type.Item;
import mindustry.world.blocks.power.NuclearReactor;
import mindustry.world.blocks.storage.CoreBlock;

/*
 *@Author:LYBF
 *@Date  :2024/3/27
 *@Desc  :核心电厂 消耗核心物资产电
 *        CoreNuclearReactor ,consume core items to production power
 */
public class CoreNuclearReactor extends NuclearReactor {


    public int timerTakeItem = timers++;
    /*
     *每次从核心拿取物资的数量
     *The amount of items taken from the core at a time
     */
    public int itemCountPer = 1;

    /*
     *每次从核心拿取物资的间隔
     */
    public float takeItemDelay = 60f;

    public CoreNuclearReactor(String name) {
        super(name);
    }

    @Override
    public void setBars() {
        super.setBars();
       // removeBar("heat");
    }

    public class CoreNuclearReactorBuild extends NuclearReactorBuild {


        @Override
        public void updateTile() {
            if (power.status > 0.99f) {//有电时可以从核心取得燃料
                if (timer(timerTakeItem, takeItemDelay / timeScale)) {
                    if (consPower != null) consPower.requestedPower(this);
                    for (int i = 0; i < itemCountPer; i++)
                        this.takeItemFromCore(fuelItem);
                }
            }

            if (this.items.has(fuelItem)) {//自身有燃料时发电

                int fuel = items.get(fuelItem);//燃料有多少（核心）
                float fullness = (float) fuel / itemCapacity;
                if (fuel > 0 && enabled) {
                    heat += fullness * heating * Math.min(delta(), 4f);
                    productionEfficiency = (float) fuel / itemCapacity;//
                    if (timer(timerFuel, itemDuration / timeScale)) {
                        this.consume();
                    }
                } else {
                    productionEfficiency = 0f;
                }
                if (heat > 0) {
                    float maxUsed = Math.min(liquids.currentAmount(), heat / coolantPower);
                    heat -= maxUsed * coolantPower;
                    liquids.remove(liquids.current(), maxUsed);
                }
                if (heat >= 0.999f) {
                    Events.fire(EventType.Trigger.thoriumReactorOverheat);
                    kill();
                }
            }
        }

        private void takeItemFromCore(Item fuelItem) {
            CoreBlock.CoreBuild coreBuild = team.core();
            if (coreBuild == null) return;
            if (coreBuild.items().has(fuelItem) && coreBuild.items().get(fuelItem) > 0 && this.acceptItem(coreBuild, fuelItem)) {
                this.handleItem(coreBuild, fuelItem);
                coreBuild.items().remove(fuelItem, 1);
            }
        }

        @Override
        public boolean shouldExplode() {
            return warmup() >= explosionMinWarmup;
        }

        @Override
        public float getPowerProduction() {
            return powerProduction * productionEfficiency;
        }


    }
}
