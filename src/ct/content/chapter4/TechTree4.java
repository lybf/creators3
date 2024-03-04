package ct.content.chapter4;

import arc.Core;

import arc.struct.Seq;
import mindustry.content.TechTree;
import mindustry.game.Objectives;
import mindustry.type.ItemStack;
import mindustry.type.SectorPreset;

import static ct.Asystem.type.CTTechTree.addToTree;
import static ct.content.CT3CoreBlocks.P4Core;
import static ct.content.chapter4.CT3Blocks4.珊绒透筛器;
import static ct.content.chapter4.CT3Blocks4.珊绒重组器;
import static ct.content.chapter4.CT3Item4.*;
import static ct.content.chapter4.ItemsSMChuaGong.资源炼化器1;
import static ct.content.chapter4.ItemsSMCyeLian.*;
import static ct.content.chapter4.ItemsSMCyeLian.资源冶炼台2;


public class TechTree4 {

    public static float TechTrees4倍率 = 1;
    public static float TechTrees4Blocks倍率 = 1;

    public static void load() {
        Planet4.Planet4.techTree = TechTree.nodeRoot(Core.bundle.format("Planet44"), P4Core, true, () -> {

            SectorPreset mull4 = new SectorPreset("mull4", Planet4.Planet4, 0) {{
                localizedName = "未开放";
                captureWave = 10;
                difficulty = 0;
                alwaysUnlocked = false;
            }};
            /*
             * 资源科技树
             * */

            //原版
        /*            node(黑油精炼台, () -> {
                node(P4Core, () -> {

                });
            });*/
            //
            //自创科技树写法
           /* addToTree(黑油精炼台, P4Core, ItemStack.with(
                            Items.silicon, 200 * 30,
                            Items.thorium, 320 * 30,
                            Items.phaseFabric, 330 * 30,
                            Items.surgeAlloy, 100 * 30
                    ),
                    Seq.with(
                            new Objectives.Research(dimensionT5Reconstructor),//研究
                            new Objectives.SectorComplete(darkGuard),//占领

                    ));*/

            addToTree(棺炆魂矩, P4Core, ItemStack.with(
                    CT3Item4.灵魂丝, 30, CT3Item4.相织物, 30
            ));
            addToTree(赤水魂矩, P4Core, ItemStack.with(
                    棺炆魂矩, 30, CT3Item4.珊绒, 50, CT3Item4.气光散, 50
            ));
            addToTree(止枢魂矩, P4Core, ItemStack.with(
                    赤水魂矩, 30, CT3Item4.沉珠, 80, CT3Item4.灵晶, 80
            ));
            addToTree(绝情魂矩, P4Core, ItemStack.with(
                    止枢魂矩, 30, CT3Item4.灵魂核心, 120, CT3Item4.灵魂网束器, 120
            ));
            addToTree(混仑魂矩, P4Core, ItemStack.with(
                    绝情魂矩, 30, CT3Item4.夜灵珠, 170, CT3Item4.夜灵, 170
            ));
            addToTree(灵屈魂矩, P4Core, ItemStack.with(
                    暗物质, 150, 混仑魂矩, 300, 绝情魂矩, 500, 止枢魂矩, 750, 赤水魂矩, 1100, 棺炆魂矩, 1700
            ));
            //
            addToTree(魂石, P4Core, ItemStack.with(
                    魂石, 30
            ));
            addToTree(魂翠, 魂石, ItemStack.with(
                    魂石, 100
            ));

            //
            addToTree(冥石, P4Core, ItemStack.with(//铁shi
                    冥石, 50
            ));
            addToTree(冥翠, 冥石, ItemStack.with(//铁板
                    冥石, 150
            ));
            addToTree(冥织, 冥石, ItemStack.with(
                    冥翠, 300
            ));
            addToTree(相织物, 冥织, ItemStack.with(
                    魂翠, 50, 冥织, 30
            ));
            addToTree(冥丝, 冥翠, ItemStack.with(//齿轮
                    冥翠, 200
            ));
            addToTree(微相织, 冥丝, ItemStack.with(//电动机
                    冥翠, 300, 冥丝, 200, 相织物, 150, 棺炆魂矩, 60 * TechTrees4倍率
            ));
            addToTree(衬相织, 微相织, ItemStack.with(//电磁涡轮
                    微相织, 150, 相织物, 200, 棺炆魂矩, 400 * TechTrees4倍率, 赤水魂矩, 100 * TechTrees4倍率
            ));
            addToTree(缜相织, 衬相织, ItemStack.with(//超级磁场环
                    衬相织, 250, 粉烟, 500, 冥织, 500, 棺炆魂矩, 1200 * TechTrees4倍率, 赤水魂矩, 300 * TechTrees4倍率
            ));
            addToTree(衬光束, 衬相织, ItemStack.with(//粒子容器
                    衬相织, 100, 气光散, 220, 魂翠, 800, 棺炆魂矩, 1800 * TechTrees4倍率, 赤水魂矩, 800 * TechTrees4倍率
            ));
            addToTree(夜灵核心, 衬光束, ItemStack.with(//奇异物质
                    衬光束, 100, 暗珊绒, 220, 冥翠, 800, 棺炆魂矩, 1500 * TechTrees4倍率, 赤水魂矩, 1300 * TechTrees4倍率, 止枢魂矩, 1000 * TechTrees4倍率
            ));
            addToTree(夜灵珠, 夜灵核心, ItemStack.with(//引力透镜
                    夜灵核心, 100, 沉珠, 220, 棺炆魂矩, 1500 * TechTrees4倍率, 赤水魂矩, 1300 * TechTrees4倍率, 止枢魂矩, 1200 * TechTrees4倍率
            ));
            addToTree(灵魂束缚容器, 衬光束, ItemStack.with(//泯灭约束球
                    衬光束, 100, 灵魂核心, 300, 止枢魂矩, 6000 * TechTrees4倍率, 混仑魂矩, 3000 * TechTrees4倍率
            ));
            addToTree(冥翠块, 冥翠, ItemStack.with(//钢材
                    冥翠, 800, 棺炆魂矩, 120 * TechTrees4倍率
            ));
            //
            addToTree(冤藤, P4Core, ItemStack.with(//煤炭
                    冤藤, 30
            ));
            addToTree(粉烟, 冤藤, ItemStack.with(
                    冤藤, 500, 棺炆魂矩, 120 * TechTrees4倍率
            ));
            addToTree(沉珠, 粉烟, ItemStack.with(
                    粉烟, 150
            ));
            //
            addToTree(怨石, P4Core, ItemStack.with(//石头
                    怨石, 50
            ));
            addToTree(怨石块, 怨石, ItemStack.with(
                    怨石, 150
            ));
            addToTree(怨气集束, 怨石块, ItemStack.with(//玻璃
                    怨石块, 100
            ));
            addToTree(灵境, 怨气集束, ItemStack.with(
                    怨气集束, 120
            ));
            addToTree(魂力激发块, 灵境, ItemStack.with(
                    灵境, 120, 相织物, 100
            ));
            addToTree(怨灵集束, 怨气集束, ItemStack.with(//钛化玻璃
                    怨气集束, 150, 灵珠, 200, 赤水魂矩, 900 * TechTrees4倍率, 止枢魂矩, 500 * TechTrees4倍率
            ));
            //
            addToTree(珊绒, P4Core, null, Seq.with(
                    new Objectives.Research(资源炼化器1)//研究
            ));
            addToTree(暗珊绒, 珊绒, ItemStack.with(//重氢
                    珊绒, 1200
            ), Seq.with(
                    new Objectives.Research(珊绒透筛器)//研究
            ));
            //
            addToTree(赤焰石, P4Core, ItemStack.with(//硅石
                    怨石, 500, 棺炆魂矩, 40 * TechTrees4倍率
            ));
            addToTree(赤焰膏块, 赤焰石, ItemStack.with(//硅板
                    赤焰石, 100, 棺炆魂矩, 120 * TechTrees4倍率
            ));
            addToTree(赤焰膏精, 赤焰膏块, ItemStack.with(
                    赤焰膏块, 200, 棺炆魂矩, 700 * TechTrees4倍率, 赤水魂矩, 600 * TechTrees4倍率
            ));
//
            addToTree(灵魂丝, P4Core, ItemStack.with(//电路板
                    魂翠, 300, 冥翠, 300
            ));
            addToTree(灵魂集束, 灵魂丝, ItemStack.with(
                    魂翠, 300, 赤焰膏块, 100, 棺炆魂矩, 240 * TechTrees4倍率
            ));
            addToTree(灵魂核心, 灵魂集束, ItemStack.with(
                    灵魂集束, 300, 灵魂丝, 100, 棺炆魂矩, 1000 * TechTrees4倍率
            ));

            //
            addToTree(灵石, P4Core, ItemStack.with(//钛石
                    灵石, 30
            ));
            addToTree(灵珠, 灵石, ItemStack.with(
                    灵石, 100, 棺炆魂矩, 240 * TechTrees4倍率, 赤水魂矩, 170 * TechTrees4倍率
            ));
            addToTree(翠灵晶, 灵珠, ItemStack.with(//钛合金
                            灵珠, 200, 冥翠块, 30, 棺炆魂矩, 1200 * TechTrees4倍率, 赤水魂矩, 1100 * TechTrees4倍率, 止枢魂矩, 100 * TechTrees4倍率
                    ),
                    Seq.with(
                            new Objectives.Research(资源炼化器1)//研究

                    ));

            //
            addToTree(网束器, P4Core, ItemStack.with(//塑料
                    粉烟, 30, 棺炆魂矩, 180 * TechTrees4倍率
            ), Seq.with(
                    new Objectives.Research(资源炼化器1)//研究

            ));
            addToTree(气光散, P4Core, ItemStack.with(//石墨烯
                    粉烟, 30, 棺炆魂矩, 500 * TechTrees4倍率
            ), Seq.with(
                    new Objectives.Research(资源炼化器1)//研究

            ));

            //
            addToTree(硅晶体, P4Core, ItemStack.with(//有机晶体
                    粉烟, 150, 棺炆魂矩, 500 * TechTrees4倍率, 赤水魂矩, 500 * TechTrees4倍率
            ), Seq.with(
                    new Objectives.Research(资源炼化器1)//研究

            ));
            addToTree(灵晶, 硅晶体, ItemStack.with(//钛晶石
                    粉烟, 200, 硅晶体, 300, 棺炆魂矩, 750 * TechTrees4倍率, 赤水魂矩, 750 * TechTrees4倍率
            ));
            addToTree(微格晶, 灵晶, ItemStack.with(//卡西米尔晶体
                    灵晶, 200, 气光散, 300, 珊绒, 200, 棺炆魂矩, 1200 * TechTrees4倍率, 赤水魂矩, 1000 * TechTrees4倍率, 止枢魂矩, 900 * TechTrees4倍率
            ));
            addToTree(灵魂转换因子, 微格晶, ItemStack.with(//位面过滤器
                    微格晶, 150, 怨灵集束, 200, 棺炆魂矩, 200 * TechTrees4倍率, 止枢魂矩, 1100 * TechTrees4倍率, 绝情魂矩, 200 * TechTrees4倍率
            ));
            addToTree(夜灵, 灵魂转换因子, ItemStack.with(//量子芯片
                    灵魂转换因子, 200, 灵魂核心, 400, 棺炆魂矩, 800 * TechTrees4倍率, 赤水魂矩, 800 * TechTrees4倍率, 绝情魂矩, 800 * TechTrees4倍率
            ));
//
            addToTree(气丝节束, P4Core, ItemStack.with(//纳米碳管
                    气光散, 200, 灵珠, 200, 棺炆魂矩, 200 * TechTrees4倍率, 赤水魂矩, 800 * TechTrees4倍率, 止枢魂矩, 200 * TechTrees4倍率
            ));
            addToTree(灵魂网束器, 气丝节束, ItemStack.with(//
                    气丝节束, 250, 赤焰膏精, 250, 网束器, 300, 棺炆魂矩, 1100 * TechTrees4倍率, 赤水魂矩, 900 * TechTrees4倍率, 止枢魂矩, 260 * TechTrees4倍率
            ));
            addToTree(混沌晶体, 气丝节束, ItemStack.with(//
                    气丝节束, 250, 赤焰膏精, 250, 网束器, 300, 棺炆魂矩, 1500 * TechTrees4倍率, 止枢魂矩, 300 * TechTrees4倍率
            ));
            addToTree(青羊子, 混沌晶体, ItemStack.with(//
                    混沌晶体, 180, 翠灵晶, 250,
                    夜灵核心, 50, 魔能晶, 120,
                    灵魂束缚容器, 70, 棺炆魂矩, 1100 * TechTrees4倍率, 赤水魂矩, 900 * TechTrees4倍率
            ));
            addToTree(暗物质, 青羊子, ItemStack.with(//
                    青羊子, 1000, 棺炆魂矩, 8000 * TechTrees4倍率, 赤水魂矩, 5000 * TechTrees4倍率, 止枢魂矩, 2500 * TechTrees4倍率, 绝情魂矩, 2500 * TechTrees4倍率, 混仑魂矩, 5500 * TechTrees4倍率
            ));
/****
 * 方块科技树
 * **/
            //--------------------------------资源冶炼台1, 资源冶炼台2,资源冶炼台3;
            addToTree(珊绒透筛器, P4Core, ItemStack.with(//
                    冥翠块, 250,
                    怨石块, 400,
                    怨气集束, 300,
                    灵魂核心, 100,
                    棺炆魂矩, 220 * TechTrees4Blocks倍率, 赤水魂矩, 350 * TechTrees4Blocks倍率
            ));
            addToTree(珊绒重组器, 珊绒透筛器, ItemStack.with(//
                    翠灵晶, 300,
                    混沌晶体, 250,
                    灵魂核心, 120,
                    缜相织, 400,
                    气光散, 200,
                    棺炆魂矩, 1000 * TechTrees4Blocks倍率, 赤水魂矩, 1000 * TechTrees4Blocks倍率, 止枢魂矩, 1000 * TechTrees4Blocks倍率
            ));

            addToTree(资源冶炼台1, P4Core, ItemStack.with(//
                    冥翠块, 250,
                    怨石块, 400,
                    怨气集束, 300,
                    灵魂核心, 100,
                    棺炆魂矩, 220 * TechTrees4Blocks倍率, 赤水魂矩, 350 * TechTrees4Blocks倍率
            ));
            addToTree(资源冶炼台2, 资源冶炼台1, ItemStack.with(//
                    冥翠块, 1,
                    怨石块, 1,
                    怨气集束, 1,
                    灵魂核心, 1,
                    棺炆魂矩, 2500 * TechTrees4Blocks倍率, 止枢魂矩, 1800 * TechTrees4Blocks倍率, 绝情魂矩, 1400 * TechTrees4Blocks倍率
            ));
            addToTree(资源冶炼台3, 资源冶炼台2, ItemStack.with(//
                    冥翠块, 1,
                    怨石块, 1,
                    怨气集束, 1,
                    灵魂核心, 1,
                    棺炆魂矩, 2500 * TechTrees4Blocks倍率, 止枢魂矩, 1800 * TechTrees4Blocks倍率, 绝情魂矩, 1400 * TechTrees4Blocks倍率, 混仑魂矩, 1000 * TechTrees4Blocks倍率
            ));


        });
    }
}
