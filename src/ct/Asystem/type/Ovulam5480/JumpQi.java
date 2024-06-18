package ct.Asystem.type.Ovulam5480;

import arc.Core;
import arc.scene.style.TextureRegionDrawable;
import arc.scene.ui.layout.Table;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.gen.Call;
import mindustry.gen.Icon;
import mindustry.net.Packets;
import mindustry.world.Block;

//跳波器
public class JumpQi extends Block {
    public TextureRegionDrawable icon;

    public JumpQi(String name) {
        super(name);
        update = true;
        sync = true;
        configurable = true;
        solid = false;//固体
        targetable = false;//被单位攻击
        size = 3;
        buildType = JumpQiBuild::new;
    }

    @Override
    public void init() {
        super.init();
        //必须在这里初始化
        icon = Icon.right;
    }

    public static class JumpQiBuild extends Building {

        @Override
        public void buildConfiguration(Table table) {
            table.defaults().width(150f);
            table.button(Core.bundle.get("ct.JumpQi"), () -> Call.adminRequest(Vars.player, Packets.AdminAction.wave, null));
        }
    }
}
