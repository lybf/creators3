package ct.Asystem.type.power;

import arc.Events;
import arc.math.Mathf;
import arc.scene.ui.layout.Table;
import arc.util.Time;
import mindustry.game.EventType;
import mindustry.gen.Building;
import mindustry.type.ItemStack;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.consumers.ConsumeItems;

/*
 *@Author:LYBF
 *@Date  :2024/4/18
 */
public class CoreConsumeGenerator extends ConsumeGenerator {
    public CoreConsumeGenerator(String name) {
        super(name);
    }


    public class CoreConsumeGeneratorBuild extends ConsumeGeneratorBuild {
        public float warmup, totalTime, efficiencyMultiplier = 1f;

        public Building teamCore() {
            return team.core();
        }

        @Override
        public void displayConsumption(Table table) {
            Building coreBuild = teamCore() == null ? this : teamCore();
            table.left();
            for (var cons : this.block.consumers) {
                if (!cons.optional || !cons.booster) {
                    if (cons instanceof ConsumeItems) {
                        cons.build(coreBuild, table);
                    } else {
                        cons.build(this, table);
                    }
                }
            }
        }

        @Override
        public void updateEfficiencyMultiplier() {
            Building building = teamCore() == null ? this : teamCore();
            if (filterItem != null) {
                float m = filterItem.efficiencyMultiplier(building);
                if (m > 0) efficiencyMultiplier = m;
            } else if (filterLiquid != null) {
                float m = filterLiquid.efficiencyMultiplier(this);
                if (m > 0) efficiencyMultiplier = m;
            }
        }

        @Override
        public void updateTile() {
            boolean valid = efficiency > 0;
            warmup = Mathf.lerpDelta(warmup, valid ? 1f : 0f, warmupSpeed);
            productionEfficiency = efficiency * efficiencyMultiplier;
            totalTime += warmup * Time.delta;
            if (valid && Mathf.chanceDelta(effectChance)) {
                generateEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
            }
            if (whetherItemsCanConsume() && valid && generateTime <= 0f) {
                consume();
                consumeEffect.at(x + Mathf.range(generateEffectRange), y + Mathf.range(generateEffectRange));
                generateTime = 1f;
            }

            if (outputLiquid != null) {
                float added = Math.min(productionEfficiency * delta() * outputLiquid.amount, liquidCapacity - liquids.get(outputLiquid.liquid));
                liquids.add(outputLiquid.liquid, added);
                dumpLiquid(outputLiquid.liquid);
                if (explodeOnFull && liquids.get(outputLiquid.liquid) >= liquidCapacity - 0.01f) {
                    kill();
                    Events.fire(new EventType.GeneratorPressureExplodeEvent(this));
                }
            }
            generateTime -= delta() / itemDuration;
        }


        @Override
        public void updateConsumption() {
            Building build = teamCore() == null ? this : teamCore();
            if (!block.hasConsumers || cheating()) {
                potentialEfficiency = enabled && productionValid() ? 1.0F : 0.0F;
                efficiency = optionalEfficiency = shouldConsume() ? potentialEfficiency : 0.0F;
                updateEfficiencyMultiplier();
                return;
            }
            if (!enabled) {
                potentialEfficiency = efficiency = optionalEfficiency = 0.0F;
                return;
            }
            boolean update = shouldConsume() && productionValid();
            float minEfficiency = 1.0F;
            efficiency = optionalEfficiency = 1.0F;
            for (var cons : block.nonOptionalConsumers) {
                minEfficiency = Math.min(minEfficiency, cons.efficiency((cons instanceof ConsumeItems) ? build : this));
            }
            for (var cons : block.optionalConsumers) {
                optionalEfficiency = Math.min(optionalEfficiency, cons.efficiency((cons instanceof ConsumeItems) ? build : this));
            }
            efficiency = minEfficiency;
            optionalEfficiency = Math.min(optionalEfficiency, minEfficiency);
            potentialEfficiency = efficiency;
            if (!update) {
                efficiency = optionalEfficiency = 0.0F;
            }
            updateEfficiencyMultiplier();
            if (update && efficiency > 0) {
                for (var cons : block.updateConsumers) {
                    cons.update((cons instanceof ConsumeItems) ? build : this);
                }
            }
        }

        @Override
        public void consume() {
            Building building = teamCore();
            if (building == null) building = this;//防止空指针异常
            for (var cons : this.block.consumers) {
                if (cons instanceof ConsumeItems) {
                    ItemStack[] items = ((ConsumeItems) cons).items;
                    if (checkItemsCount(items)) cons.trigger(building);
                } else {
                    cons.trigger(this);
                }
            }
        }

        public boolean whetherItemsCanConsume() {
            Building building = teamCore();
            if (building == null) building = this;
            for (var cons : this.block.consumers) {
                if (cons instanceof ConsumeItems) {
                    ItemStack[] items = ((ConsumeItems) cons).items;
                    if (!checkItemsCount(items)) return false;
                }
            }
            return true;
        }

        //检查核心物品是否够消耗
        public boolean checkItemsCount(ItemStack[] itemStacks) {
            Building coreBuild = teamCore();
            if (coreBuild == null) return false;
            for (ItemStack itemStack : itemStacks) {
                if (!coreBuild.items().has(itemStack.item) || coreBuild.items().get(itemStack.item) < itemStack.amount) {
                    return false;
                }
            }
            return true;
        }

    }
}
