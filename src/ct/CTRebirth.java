package ct;

import arc.Core;
import arc.Events;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.TextureRegion;
import arc.scene.ui.ImageButton;
import arc.struct.ObjectMap;
import arc.struct.ObjectSet;
import arc.struct.Seq;
import arc.util.Time;
import ct.Asystem.Wave;
import ct.Asystem.WorldDifficulty;
import ct.content.CTFragShader;
import ct.Asystem.type.VXV.SpawnDraw;
import ct.ui.UnemFragment;
import ct.ui.dialogs.CT3InfoDialog;
import ct.ui.dialogs.CT3PlanetDialog;
import ct.Asystem.type.CTResearchDialog;
import ct.content.*;
import ct.content.Effect.NewFx;
import ct.content.chapter1.Item1;
import ct.content.chapter1.chapter1;
import ct.content.chapter2.chapter2;
import ct.content.chapter3.chapter3;
import ct.ui.CT3ClassificationUi;
import ct.ui.dialogs.CT3function;
import mindustry.Vars;
import mindustry.game.EventType;
import mindustry.graphics.Layer;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import mindustry.mod.Scripts;
import mindustry.type.Planet;
import mindustry.type.UnitType;
import mindustry.ui.Styles;
import mindustry.ui.dialogs.BaseDialog;
import mindustry.ui.dialogs.ResearchDialog;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.Sorter;
import mindustry.world.blocks.sandbox.ItemSource;
import mindustry.world.blocks.sandbox.LiquidSource;
import rhino.Context;
import rhino.Scriptable;
import rhino.ScriptableObject;

import java.util.Objects;

import static arc.Core.camera;
import static ct.Asystem.type.VXV.powerShowBlock.loadPowerShow;
import static mindustry.Vars.*;

public class CTRebirth extends Mod {


//

    public CTRebirth() {

        //缩放
        Vars.renderer.minZoom = 0.2F;
        Vars.renderer.maxZoom = 32;
//蓝图大小
        Vars.maxSchematicSize = 128;
        //地图禁用建筑隐藏
        Events.on(EventType.WorldLoadEvent.class, event -> {
                    Vars.state.rules.hideBannedBlocks = true;

                }
        );
        //

    }

    public void loadContent() {
  /*      Vars.mods.locateMod("ct")
                .meta.version += "-" + "[violet]创世神3[] 版本：[yellow]"
                + Vars.mods.getMod("ct").meta.version + "[]";
        */

        // Team.sharded.color.set(0.0F, 153.0F, 255.0F, 64.0F);//黄队伍颜色
        //Team.crux.color.set(79.0F, 181.0F, 103.0F, 255.0F);//红队伍颜色

        //难度修改
        WorldDifficulty.init();//初始化难度buff

        Item1.load();
        //CT3Item4.load();
        // 资源5.load();
        Item0.load();

        CTAttributes.load();
        Floors.load();
        NewFx.load();
        CTR4Unit2.load();//敌对单位。改为通用单位，不限制在章节4了
        chapter1.load();
        chapter2.load();
        chapter3.load();
        //chapter4.load();
        // chapter5.load();
        CTFragShader.load();
        ItemX.load();
        Blocks_z.load();
        SourceCodeModification_Sandbox.load();

        new CT3ClassificationUi();
        Scripts scripts = mods.getScripts();
        Scriptable scope = scripts.scope;
        try {
            Object obj = Context.javaToJS(new CT3ClassificationUi(), scope);
            ScriptableObject.putProperty(scope, "CT3ClassificationUi", obj);
        } catch (Exception var5) {
            ui.showException(var5);
        }

        overrideVersion();//显示版本号
        CreatorsModJS.DawnMods();//JS加载器


    }

    //public UnemFragment u=new UnemFragment();
    @Override
    public void init() {
        //显示怪物路径
        SpawnDraw.init();
        SpawnDraw.setEnable(true);
        //区块名显示
        Vars.ui.planet = new CT3PlanetDialog();
        //跳波惩罚
        new Wave();
        Events.on(EventType.ClientLoadEvent.class, e -> {
            CT3InfoDialog.show();//开屏显示
            loadPowerShow();//电力显示方块
            CT3选择方块显示图标(); //选择方块显示图标
            ctUpdateDialog.load();//更新检测
            // Timer.schedule(CTUpdater::checkUpdate, 4);//檢測更新 旧版
            //new Wave();   //跳波惩罚


            //首页主功能按钮
            CT3function.show();
            ImageButton imagebutton = CreatorsIcon("function", Styles.defaulti, CT3function.功能图标UI);
            Vars.ui.menuGroup.fill(t -> {
                if (mobile) {
                    t.add(imagebutton).update(b -> b.color.fromHsv(Time.time % 360, 1, 1)).size(50);//手机
                    t.bottom();
                } else {
                    t.add(imagebutton).update(b -> b.color.fromHsv(Time.time % 360, 1, 1)).size(120.0f);//电脑
                    t.left().bottom();
                }
            });

        });
        //动态logo
        try {
            Class arc = Class.forName("mindustry.arcModule.ARCVars");
        } catch (ClassNotFoundException e) {

            Vars.ui.menufrag = new UnemFragment();
            new UnemFragment().build(ui.menuGroup);
        }
        //科技树全显
        CTResearchDialog dialog = new CTResearchDialog();
        ResearchDialog research = Vars.ui.research;
        research.shown(() -> {
            dialog.show();
            Objects.requireNonNull(research);
            Time.runTask(1.0F, research::hide);
        });

    }

    public static void overrideVersion() {
        for (int i = 0; i < Vars.mods.list().size; i++) {
            Mods.LoadedMod mod = Vars.mods.list().get(i);
            if (mod != null) {
                mod.meta.description = Core.bundle.get("mod.ct.version") + mod.meta.version + "\n\n" + mod.meta.description;
            }
        }
    }

    public static boolean CTBlockBool = true;//原版蓝图系统解锁
    public static ObjectMap<Block, Block> CTBlock = new ObjectMap<>();

    public static void setPlanet(Planet planet, String[] names) {
        planet.ruleSetter = r -> {
            // planet.hiddenItems.addAll(Items.serpuloItems);
            var B = new ObjectSet<Block>();
            for (var b : content.blocks()) {
                if (b.minfo.mod == null) {
                    B.add(b);
                    continue;
                }
                boolean yes = true;
                for (var name : names) {
                    if (Objects.equals(b.minfo.mod.meta.name, name) || Objects.equals(b.minfo.mod.name, name)) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    B.add(b);
                }
            }
            r.bannedBlocks.addAll(B);
            var U = new ObjectSet<UnitType>();
            for (var u : content.units()) {
                if (u.minfo.mod == null) {
                    U.add(u);
                    continue;
                }
                boolean yes = true;
                for (var name : names) {
                    if (Objects.equals(u.minfo.mod.meta.name, name) || Objects.equals(u.minfo.mod.name, name)) {
                        yes = false;
                        break;
                    }
                }
                if (yes) {
                    U.add(u);
                }
            }
            r.bannedUnits.addAll(U);
            r.showSpawns = true;//显示单位刷出点
        };
    }

    public final static Seq<Runnable> BlackListRun = new Seq<>();

    public Seq<String> BaiMingDan = new Seq<>();


    //选择方块显示图标
    public void CT3选择方块显示图标() {
        Events.run(EventType.Trigger.draw, () -> {
            if (Vars.ui != null) {
                indexer.eachBlock(null, camera.position.x, camera.position.y, (30 * tilesize), b -> true, b -> {
                    if (b instanceof LiquidSource.LiquidSourceBuild) {
                        var source = (LiquidSource.LiquidSourceBuild) b;
                        if (source.config() != null) {
                            Draw.z(Layer.block + 1);
                            Draw.rect(source.config().fullIcon, b.x, b.y, 3, 3);
                        }
                    }
                    if (b instanceof ItemSource.ItemSourceBuild) {
                        var source = (ItemSource.ItemSourceBuild) b;
                        if (source.config() != null) {
                            Draw.z(Layer.block + 1);
                            Draw.rect(source.config().fullIcon, b.x, b.y, 3, 3);
                        }
                    }
                    if (b instanceof Sorter.SorterBuild) {
                        var sorter = (Sorter.SorterBuild) b;
                        if (sorter.config() != null) {
                            Draw.z(Layer.block + 1);
                            Draw.rect(sorter.config().fullIcon, b.x, b.y, 3, 3);
                        }
                    }
                });
            }
        });
    }


    public static ImageButton CreatorsIcon(String IconName, ImageButton.ImageButtonStyle imageButtonStyle, BaseDialog dialog) {
        TextureRegion A = Core.atlas.find("ct-" + IconName);

        ImageButton buttonA = new ImageButton(A, imageButtonStyle);
        buttonA.clicked(dialog::show);
        return buttonA;
    }


}
//Vars.state.rules.unitAmmo = true;开启单位消耗子弹
//Vars.ui.editor.save();保存地图 控制台