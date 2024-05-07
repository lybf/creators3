package ct.Asystem.type.BulletType;

import mindustry.entities.bullet.PointLaserBulletType;
import mindustry.gen.Bullet;

public class JiGuangFanWeiShangHaiBulletType extends PointLaserBulletType {
    public JiGuangFanWeiShangHaiBulletType() {
        splashDamage = 30;
        splashDamageRadius = 30f;
    }

    @Override
    public void update(Bullet b) {
        if (b.timer.get(0, damageInterval)) {
            createSplashDamage(b, b.aimX, b.aimY);
            despawnEffect.at(b.x, b.y, b.rotation(), hitColor);
        }
        super.update(b);
    }
}
