package ct.ui.dialogs;

import arc.Core;
import arc.graphics.Color;
import arc.scene.ui.TextButton;
import arc.util.Align;
import arc.util.Time;
import ct.Asystem.WorldDifficulty;
import ct.ctUpdateDialog;
import mindustry.Vars;
import mindustry.gen.Icon;
import mindustry.mod.Mods;
import mindustry.ui.dialogs.BaseDialog;

import static mindustry.Vars.ui;


//首页
public class CT3InfoDialog {
    public static BaseDialog ct3info;

    public static void show() {

        // String framer = Core.bundle.format("framer");
        Mods.LoadedMod mod = Vars.mods.getMod("ct");
        String version = mod.meta.version;
        String MODname = Core.bundle.format("planet.ct3.ModName");
        String QQ群2 = "https://jq.qq.com/?_wv=1027&k=oygqLbJ5";
        String TD网盘 = "https://pan.baidu.com/s/1WJ2ZrehLvl8m17bl6-RbGQ?pwd=bjt3";

        ct3info = new BaseDialog("[yellow]Creators[#7bebf2] " + version + "[] Adapt 146+" + "\n策划:9527，贴图:皴皲，处理器逻辑指导:咕咕点心\nQQ群:909130592") {{
            //更新检查
            buttons.button(Core.bundle.format("发现更新"), (ctUpdateDialog::show)).size(150, 64);

            addCloseListener();//按esc关闭
            buttons.defaults().size(210, 64);
            buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
            buttons.button(Core.bundle.get("difficulty.game", "难度设置"), (() -> {//游戏难度设置
                new SettingDifficultyDialog().onDifficutyChange(e -> {
                    ui.settings.game.sliderPref(
                            "游戏难度", 3, 0, 5, 1, i -> Core.bundle.format("Difficulty-" + i)
                    );
                    Core.settings.get("游戏难度", true);
                    new WorldDifficulty().init();
                }).show();
            })).size(150, 64);
            cont.pane((table -> {
                table.add(MODname).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
                table.row();

                table.image().color(Color.valueOf("69dcee")).fillX().height(3).pad(3);
                table.row();

                table.image(Core.atlas.find("ct-CT-logo", Core.atlas.find("clear"))).height(290).width(587).pad(3);
                table.row();

//                   table.add(TZ).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
//                   table.row();

                table.add(version + "[]" + " _更新内容:").left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
                table.row();

                table.add(Core.bundle.format("ct3-update")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
                table.row();

                table.add(Core.bundle.format("ct3-notice")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
                table.row();
            })).grow().center().maxWidth(770).row();
            String CT3framer = Core.bundle.format("CT3framer");
            buttons.button(CT3framer, (() -> {
                new BaseDialog("[yellow]Creators[#7bebf2] " + version + "\n" + CT3framer + "\nQQ群:909130592") {{
                    addCloseListener();//按esc关闭
                    buttons.defaults().size(210, 64);
                    buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
                    cont.pane((table -> {
                        table.add(Core.bundle.format("CT3framer_txt")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);
                        table.row();
                        table.button(Core.bundle.format("画大饼"), (() -> {
                            new BaseDialog("") {{
                                cont.pane((table -> {
                                    table.add(Core.bundle.format("未来大饼")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left);

                                })).row();
                                addCloseListener();//按esc关闭
                                defaults().size(210, 64);
                                buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
                            }}.show();
                        })).size(510, 64).row();
                    }));
                }}.show();
            })).size(150, 64);
            buttons.button("DLC", Icon.github, (() -> {
                new BaseDialog("[yellow]Creators[#7bebf2] " + version + "\n" + CT3framer + "\nQQ群:909130592") {{
                    addCloseListener();//按esc关闭
                    buttons.defaults().size(210, 64);
                    buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
                    cont.pane((table -> {
                        table.button("@CT3HX", (() -> {//幻想
                            new BaseDialog("[yellow]Creators[#7bebf2] " + version + "\n" + CT3framer + "\nQQ群:909130592") {{
                                addCloseListener();//按esc关闭
                                //defaults().size(210, 64);
                                buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
                                cont.pane((a -> {
                                    a.add(Core.bundle.format("CT3HX说明")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left).row();
                                    a.image().color(Color.valueOf("69dcee")).fillX().height(3).pad(3).row();
                                    a.button(Core.bundle.format("QQ群2"), (() -> {
                                        if (!Core.app.openURI(QQ群2)) {
                                            Vars.ui.showErrorMessage("@linkfail");
                                            Core.app.setClipboardText(QQ群2);
                                        }
                                    })).update(b -> b.color.fromHsv(Time.time % 360, 1, 1)).size(250.0f, 50).row();
                                }));
                            }}.show();
                        })).size(150, 50);
                        table.button("@CT3TD", (() -> {//塔防
                            new BaseDialog("[yellow]Creators[#7bebf2] " + version + "\n" + CT3framer + "\nQQ群:909130592") {{
                                addCloseListener();//按esc关闭
                                //defaults().size(210, 64);
                                buttons.button("@close", (this::hide)).size(100, 64);//关闭按钮
                                cont.pane((c -> {
                                    c.add(Core.bundle.format("CT3TD说明")).left().growX().wrap().width(620).maxWidth(620).pad(4).labelAlign(Align.left).row();
                                    c.image().color(Color.valueOf("69dcee")).fillX().height(3).pad(3).row();
                                    c.button(Core.bundle.format("QQ群2"), (() -> {
                                        if (!Core.app.openURI(QQ群2)) {
                                            Vars.ui.showErrorMessage("@linkfail");
                                            Core.app.setClipboardText(QQ群2);
                                        }
                                    })).update(b -> b.color.fromHsv(Time.time % 360, 1, 1)).size(250.0f, 50).row();
                                }));
                            }}.show();
                        })).size(150, 50).row();
                        table.image().color(Color.valueOf("69dcee")).fillX().height(3).pad(3).padLeft(0).row();
                        table.button(Core.bundle.format("QQ群2"), (() -> {
                            if (!Core.app.openURI(QQ群2)) {
                                Vars.ui.showErrorMessage("@linkfail");
                                Core.app.setClipboardText(QQ群2);
                            }
                        })).update(b -> b.color.fromHsv(Time.time % 360, 1, 1)).size(250.0f, 50).padLeft(0).row();

                    /* table.button(Core.bundle.format("TD网盘"), (() -> {
                            if (!Core.app.openURI(TD网盘)) {
                                Vars.ui.showErrorMessage("@linkfail");
                                Core.app.setClipboardText(TD网盘);
                            }
                        })).size(510, 64).row();*/

                    }));
                }}.show();
            })).tooltip("更多可游玩内容").with(b -> {
                TextButton.TextButtonStyle s = new TextButton.TextButtonStyle(b.getStyle());
                s.fontColor = b.color;
                b.setStyle(s);
            }).size(150, 64).update(b -> b.color.fromHsv(Time.time % 360, 1, 1));
        }};
        ct3info.show();
    }
}
