package ct.Asystem.type.VXV;

import arc.Core;
import arc.graphics.g2d.TextureRegion;
import arc.math.geom.Vec2;
import arc.scene.actions.Actions;
import arc.scene.event.Touchable;
import arc.scene.ui.layout.Table;
import arc.util.Align;
import mindustry.Vars;
import mindustry.ui.Styles;

public class EntityDraw {
    /*private static final String InventoryItem = "tempInventoryItem-";
    private static final String InventoryItemTimer = "tempInventoryItemTimer";

    public static void addItemIcon(Building build){
        if(!build.block.hasItems || build.items == null) return;

        if(build instanceof Conveyor.ConveyorBuild) return;

        if(build instanceof TnByteCodeTools.SuperInvokes invokes){
            if(getObjectMapFloat(build, InventoryItemTimer) >= 60f) {
                setObjectMapFloat(build, InventoryItemTimer, 0f);

                for (Item item : Vars.content.items()) {
                    String itemName = InventoryItem + item.name;

                    int oldValue = getObjectMapInt(build, itemName);
                    int newValue = build.items.get(item);

                    if (oldValue == newValue) {
                        continue;
                    }

                    addItemIcon(build.x, build.y, item.uiIcon, newValue - oldValue, 0.1f);
                    setObjectMapInt(build, itemName, newValue);
                }
            }else {
                addObjectMapFloat(build, InventoryItemTimer, Time.delta);
            }
        }
    }*/

    public static void addItemIcon(float x, float y, TextureRegion region, int value, float speed) {
        if (value == 0) return;

        float MaxY = 88f;

        float[] runY = {MaxY};

        var table = new Table(Styles.none).margin(4);
        table.touchable = Touchable.disabled;
        table.update(() -> {
            if (Vars.state.isMenu()) table.remove();

            runY[0] -= speed;

            table.setColor(1, 1, 1, runY[0] / MaxY);

            Vec2 v = Core.camera.project(x, y + MaxY - runY[0]);
            table.setPosition(v.x, v.y, Align.center);
        });
        table.actions(Actions.delay(2.5f), Actions.remove());
        table.image(region).style(Styles.outlineLabel);

        String s = "";
        if (value > 0) {
            s = "  + " + value;
        } else {
            s = "[red]" + "   " + value + "[]";
        }
        table.add(s).style(Styles.outlineLabel);

        table.pack();
        table.act(0f);
        Core.scene.root.addChildAt(0, table);

        table.getChildren().first().act(0f);
    }
}
