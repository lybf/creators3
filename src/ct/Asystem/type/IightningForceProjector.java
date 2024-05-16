package ct.Asystem.type;

import arc.math.Mathf;
import arc.math.geom.Intersector;
import arc.math.geom.Vec2;
import arc.struct.Seq;
import mindustry.Vars;
import mindustry.entities.bullet.BulletType;
import mindustry.entities.bullet.LightningBulletType;
import mindustry.gen.Groups;
import mindustry.world.blocks.defense.ForceProjector;

//雷劫仪
public class IightningForceProjector extends ForceProjector {
    //间隔时间
    public float 间隔Time = 30f;
    //劈里啪啦总时间, 不要超过间隔时间
    public float bilibiliTime = 20f;
    //产生的子弹
    public BulletType bullet = new LightningBulletType() {{
        damage = 7f;
        lightningLength = 6;
    }};

    public IightningForceProjector(String name) {
        super(name);
        radius = 30 * 8;
    }

    public class IightningForceProjectorBuilding extends ForceBuild {
        public float 间隔Timer;
        public float bilibiliTimer, preTime;
        public Seq<Vec2> pos = new Seq<>();
        public int ampint;

        //这个盾不需要防御
        @Override
        public void deflectBullets() {
        }

        @Override
        public void updateTile() {
            super.updateTile();

            if (间隔Timer < 间隔Time) 间隔Timer += edelta();
            else {
                pos.clear();
                Groups.unit.intersect(x - realRadius(), y - realRadius(), realRadius() * 2f, realRadius() * 2f, unit -> {
                    if (unit.team != team && unit.type.targetable && Intersector.isInRegularPolygon(sides, x, y, realRadius(), shieldRotation, unit.x, unit.y)) {
                        间隔Timer = 0;
                        pos.add(new Vec2(unit.x, unit.y));
                    }
                });
                Vars.indexer.eachBlock(null, x, y, realRadius() * 2f, building -> building.team != team && building.block.targetable, building -> {
                    if (Intersector.isInRegularPolygon(sides, x, y, realRadius(), shieldRotation, building.x, building.y)) {
                        间隔Timer = 0;
                        pos.add(new Vec2(building.x, building.y));
                    }
                });
                ampint = pos.size;
            }
            if (!pos.isEmpty()) {
                preTime = bilibiliTimer;
                for (bilibiliTimer += edelta(); preTime < bilibiliTimer; preTime += bilibiliTime / ampint) {
                    if (pos.isEmpty()) break;
                    Vec2 vec2 = pos.first();
                    bullet.create(this, vec2.x, vec2.y, Mathf.random(360));
                    pos.remove(vec2);
                }
            }
        }
    }
}
