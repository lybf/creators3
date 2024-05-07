package ct.Asystem.type.factory;

import arc.Core;
import arc.scene.event.Touchable;
import arc.scene.ui.Label;
import arc.scene.ui.Slider;
import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import ct.Asystem.type.VXV.EntityDraw;
import mindustry.Vars;
import mindustry.game.Teams;
import mindustry.gen.Building;
import mindustry.logic.LAccess;
import mindustry.type.ItemStack;
import mindustry.ui.Styles;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.meta.Stat;
import mindustry.world.meta.StatValues;

import static arc.Core.bundle;

/*
 *@Author:LYBF
 *@Date  :2024/4/9
 *@Desc  :双向核心工厂，入料为核心，出料至核心
 */
public class DuplexCoreGenericCrafter extends CoreGenericCrafter {

    //定义初始最少核心物品限制
    public int defMinResLimit = 10;

    //该变量无需手动改变
    public int lastMinResLimit = 10;

    public DuplexCoreGenericCrafter(String name) {
        super(name);
        configurable = true;
        saveConfig = true;
        try {
            config(Integer.class, (DuplexCoreGenericCrafterBuilding build, Integer integer) -> {
                if (defMinResLimit == build.minResLimit) return;
                build.minResLimit = Math.max(defMinResLimit, 0);
                if (lastMinResLimit != build.minResLimit) build.minResLimit = lastMinResLimit;
            });
        } catch (Exception e) {
        }
    }


    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.powerCapacity);
        stats.remove(Stat.powerUse);
        stats.remove(Stat.input);
        for (var c : consumers) {
            if (c instanceof ConsumeItems) {
                boolean booster = c.booster;
                ConsumeItems c2 = (ConsumeItems) c;
                stats.add(booster ? Stat.booster : Stat.input, stats.timePeriod < 0 ? StatValues.items(c2.items) : StatValues.items(stats.timePeriod, c2.items));
            } else {
                c.display(stats);
            }
        }
    }


    public class DuplexCoreGenericCrafterBuilding extends CoreGenericCrafterBuilding {
        public int minResLimit = 0;

        @Override
        public void displayConsumption(Table table) {
            Building coreBuild = teamCore();
            table.left();
            for (var cons : this.block.consumers) {
                if (!cons.optional || !cons.booster) {
                    if (cons instanceof ConsumeItems)
                        cons.build(coreBuild, table);
                    else
                        cons.build(this, table);
                }
            }

        }

        @Override
        public void dumpOutputs() {
            if (outputItems != null && timer(timerDump, dumpTime / timeScale)) {
                for (ItemStack output : outputItems) {
                    dump(output.item);
                }
            }

            if (outputLiquids != null) {
                for (int i = 0; i < outputLiquids.length; i++) {
                    int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

                    dumpLiquid(outputLiquids[i].liquid, 2f, dir);
                }
            }
        }

        @Override
        public void craft() {
            if (whetherItemsCanConsume()) {
                consume();
                if (outputItems != null) {
                    if (!whetherItemsCanConsume()) return;
                    for (var output : outputItems) {
                        for (int i = 0; i < output.amount; i++) {
                            EntityDraw.addItemIcon(x, y, output.item.fullIcon, output.amount, 0.3f);
                            if (items.get(output.item) < getMaximumAccepted(output.item))
                                offload(output.item);
                        }
                    }
                }
                if (wasVisible) {
                    craftEffect.at(x, y);
                }
                progress %= 1f;
            } else {
                progress = 0;
            }
        }

        public boolean whetherItemsCanConsume() {
            Building coreBuild = teamCore();
            if (coreBuild == null) return false;
            for (var cons : this.block.consumers) {
                if (cons instanceof ConsumeItems) {
                    ItemStack[] items = ((ConsumeItems) cons).items;
                    if (!checkItemsCount(items)) return false;
                }
            }
            return true;
        }

        private boolean checkItemsCount(ItemStack[] itemStacks) {
            Building coreBuild = teamCore();
            if (coreBuild == null) return false;
            for (ItemStack itemStack : itemStacks) {
                if (!coreBuild.items().has(itemStack.item) || coreBuild.items().get(itemStack.item) < minResLimit) {
                    return false;
                }
            }
            return true;
        }


        @Override
        public void updateConsumption() {
            Building build = teamCore();
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
            Building coreBuild = teamCore();
            if (coreBuild == null) return;
            for (var cons : this.block.consumers) {
                if (cons instanceof ConsumeItems) {
                    ItemStack[] items = ((ConsumeItems) cons).items;
                    if (checkItemsCount(items)) cons.trigger(coreBuild);
                } else {
                    cons.trigger(this);
                }
            }

        }

        @Override
        public boolean shouldConsume() {
            Building build = teamCore();
            if (outputItems != null) {
                for (var output : outputItems) {
                    if (build.items.get(output.item) + output.amount > build.getMaximumAccepted(output.item)) {
                        return false;
                    }
                }
            }
            return enabled;
        }

        @Override
        public Object config() {
            return minResLimit;
        }


        @Override
        public void configure(Object value) {
            if (value instanceof Integer) {
                minResLimit = (int) value;
                lastMinResLimit = minResLimit;
            }
            super.configure(value);
        }

        @Override
        public double sense(LAccess sensor) {
            if (sensor == LAccess.config) {
                return (double) config();
            }
            return super.sense(sensor);
        }


        public Building teamCore() {
            Teams.TeamData teamData = Vars.state.teams.getOrNull(team);
            return teamData == null ? this : teamData.core();
        }


        @Override
        public void buildConfiguration(Table table) {
            super.buildConfiguration(table);
            layout(table);
        }


        public void layout(Table table1) {
            Table table = new Table();
            Slider slider = new Slider(1, 100000, 1, false);
            slider.setValue(minResLimit);
            Label value = new Label("", Styles.outlineLabel);
            Table content = new Table();
            //minreslimit字段在bundle定义，无此字段时显示为“最小资源限制”
            content.add(bundle.get("minreslimit", "最小资源限制"), Styles.outlineLabel).left().growX().wrap();
            content.add(value).padLeft(10f).right();
            content.margin(3f, 33f, 3f, 33f);
            content.touchable = Touchable.disabled;
            slider.changed(() -> {
                int value1 = (int) slider.getValue();
                configure(value1);
                defMinResLimit = value1;
                value.setText(">" + value1);
                this.minResLimit = value1;
            });
            slider.change();
            table.stack(slider, content).width(Math.min(Core.graphics.getWidth() / 1.2f, 460f)).left().padTop(4f).get();
            table.row();
            table1.add(table);
        }

        @Override
        public byte version() {
            return 2;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.i(Math.max(minResLimit, 0));
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            minResLimit = read.i();
        }
    }


}
