package creators;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.MultiEffect;
import mindustry.graphics.Pal;

public class CreatorsBullets {
    public static BulletType damageLightning = new BulletType(0.0001f, 0f){{
        lifetime = Fx.lightning.lifetime;
        hitEffect = Fx.hitLancer;
        despawnEffect = Fx.none;
        status = StatusEffects.shocked;
        statusDuration = 10f;
        hittable = false;
    }};

    public static BulletType artilleryDense = new ArtilleryBulletType(3f, 20, "shell"){{
        hitEffect = Fx.flakExplosion;
        knockback = 0.8f;
        lifetime = 80f;
        width = height = 11f;
        collidesTiles = false;
        splashDamageRadius = 25f * 0.75f;
        splashDamage = 33f;
    }};

    public static BulletType artilleryPlasticFrag = new BasicBulletType(2.5f, 10, "bullet"){{
        width = 10f;
        height = 12f;
        shrinkY = 1f;
        lifetime = 15f;
        backColor = Pal.plastaniumBack;
        frontColor = Pal.plastaniumFront;
        despawnEffect = Fx.none;
        collidesAir = false;
    }};

    public static BulletType artilleryPlastic = new ArtilleryBulletType(3.4f, 20, "shell"){{
        hitEffect = Fx.plasticExplosion;
        knockback = 1f;
        lifetime = 80f;
        width = height = 13f;
        collidesTiles = false;
        splashDamageRadius = 35f * 0.75f;
        splashDamage = 45f;
        fragBullet = artilleryPlasticFrag;
        fragBullets = 10;
        backColor = Pal.plastaniumBack;
        frontColor = Pal.plastaniumFront;
    }};

    public static BulletType artilleryHoming = new ArtilleryBulletType(3f, 20, "shell"){{
        hitEffect = Fx.flakExplosion;
        knockback = 0.8f;
        lifetime = 80f;
        width = height = 11f;
        collidesTiles = false;
        splashDamageRadius = 25f * 0.75f;
        splashDamage = 33f;
        reloadMultiplier = 1.2f;
        ammoMultiplier = 3f;
        homingPower = 0.08f;
        homingRange = 50f;
    }};

    public static BulletType artilleryIncendiary = new ArtilleryBulletType(3f, 20, "shell"){{
        hitEffect = Fx.blastExplosion;
        knockback = 0.8f;
        lifetime = 80f;
        width = height = 13f;
        collidesTiles = false;
        splashDamageRadius = 25f * 0.75f;
        splashDamage = 35f;
        status = StatusEffects.burning;
        statusDuration = 60f * 12f;
        frontColor = Pal.lightishOrange;
        backColor = Pal.lightOrange;
        makeFire = true;
        trailEffect = Fx.incendTrail;
        ammoMultiplier = 4f;
    }};

    public static BulletType artilleryExplosive = new ArtilleryBulletType(2f, 20, "shell"){{
        hitEffect = Fx.blastExplosion;
        knockback = 0.8f;
        lifetime = 80f;
        width = height = 14f;
        collidesTiles = false;
        ammoMultiplier = 4f;
        splashDamageRadius = 45f * 0.75f;
        splashDamage = 55f;
        backColor = Pal.missileYellowBack;
        frontColor = Pal.missileYellow;

        status = StatusEffects.blasted;
    }};

    public static BulletType flakGlassFrag = new BasicBulletType(3f, 5, "bullet"){{
        width = 5f;
        height = 12f;
        shrinkY = 1f;
        lifetime = 20f;
        backColor = Pal.gray;
        frontColor = Color.white;
        despawnEffect = Fx.none;
        collidesGround = false;
    }};

    public static BulletType flakLead = new FlakBulletType(4.2f, 3){{
        lifetime = 60f;
        ammoMultiplier = 4f;
        shootEffect = Fx.shootSmall;
        width = 6f;
        height = 8f;
        hitEffect = Fx.flakExplosion;
        splashDamage = 27f * 1.5f;
        splashDamageRadius = 15f;
    }};

    public static BulletType flakScrap = new FlakBulletType(4f, 3){{
        lifetime = 60f;
        ammoMultiplier = 5f;
        shootEffect = Fx.shootSmall;
        reloadMultiplier = 0.5f;
        width = 6f;
        height = 8f;
        hitEffect = Fx.flakExplosion;
        splashDamage = 22f * 1.5f;
        splashDamageRadius = 24f;
    }};

    public static BulletType flakGlass = new FlakBulletType(4f, 3){{
        lifetime = 60f;
        ammoMultiplier = 5f;
        shootEffect = Fx.shootSmall;
        reloadMultiplier = 0.8f;
        width = 6f;
        height = 8f;
        hitEffect = Fx.flakExplosion;
        splashDamage = 25f * 1.5f;
        splashDamageRadius = 20f;
        fragBullet = flakGlassFrag;
        fragBullets = 6;
    }};

    public static BulletType fragGlassFrag = new BasicBulletType(3f, 5, "bullet"){{
        width = 5f;
        height = 12f;
        shrinkY = 1f;
        lifetime = 20f;
        backColor = Pal.gray;
        frontColor = Color.white;
        despawnEffect = Fx.none;
    }};

    public static BulletType fragPlasticFrag = new BasicBulletType(2.5f, 10, "bullet"){{
        width = 10f;
        height = 12f;
        shrinkY = 1f;
        lifetime = 15f;
        backColor = Pal.plastaniumBack;
        frontColor = Pal.plastaniumFront;
        despawnEffect = Fx.none;
    }};

    public static BulletType fragGlass = new FlakBulletType(4f, 3){{
        ammoMultiplier = 3f;
        shootEffect = Fx.shootSmall;
        reloadMultiplier = 0.8f;
        width = 6f;
        height = 8f;
        hitEffect = Fx.flakExplosion;
        splashDamage = 18f * 1.5f;
        splashDamageRadius = 16f;
        fragBullet = fragGlassFrag;
        fragBullets = 4;
        explodeRange = 20f;
        collidesGround = true;
    }};

    public static BulletType fragPlastic = new FlakBulletType(4f, 6){{
        splashDamageRadius = 40f;
        splashDamage = 25f * 1.5f;
        fragBullet = fragPlasticFrag;
        fragBullets = 6;
        hitEffect = Fx.plasticExplosion;
        frontColor = Pal.plastaniumFront;
        backColor = Pal.plastaniumBack;
        shootEffect = Fx.shootBig;
        collidesGround = true;
        explodeRange = 20f;
    }};

    public static BulletType fragExplosive = new FlakBulletType(4f, 5){{
        shootEffect = Fx.shootBig;
        ammoMultiplier = 5f;
        splashDamage = 26f * 1.5f;
        splashDamageRadius = 60f;
        collidesGround = true;

        status = StatusEffects.blasted;
        statusDuration = 60f;
    }};

    public static BulletType fragSurge = new FlakBulletType(4.5f, 13){{
        ammoMultiplier = 5f;
        splashDamage = 50f * 1.5f;
        splashDamageRadius = 38f;
        lightning = 2;
        lightningLength = 7;
        shootEffect = Fx.shootBig;
        collidesGround = true;
        explodeRange = 20f;
    }};

    public static BulletType missileExplosive = new MissileBulletType(3.7f, 10){{
        width = 8f;
        height = 8f;
        shrinkY = 0f;
        splashDamageRadius = 30f;
        splashDamage = 30f * 1.5f;
        ammoMultiplier = 5f;
        hitEffect = Fx.blastExplosion;
        despawnEffect = Fx.blastExplosion;

        status = StatusEffects.blasted;
        statusDuration = 60f;
    }};

    public static BulletType missileIncendiary = new MissileBulletType(3.7f, 12){{
        frontColor = Pal.lightishOrange;
        backColor = Pal.lightOrange;
        width = 7f;
        height = 8f;
        shrinkY = 0f;
        homingPower = 0.08f;
        splashDamageRadius = 20f;
        splashDamage = 20f * 1.5f;
        makeFire = true;
        ammoMultiplier = 5f;
        hitEffect = Fx.blastExplosion;
        status = StatusEffects.burning;
    }};

    public static BulletType missileSurge = new MissileBulletType(3.7f, 18){{
        width = 8f;
        height = 8f;
        shrinkY = 0f;
        splashDamageRadius = 25f;
        splashDamage = 25f * 1.4f;
        hitEffect = Fx.blastExplosion;
        despawnEffect = Fx.blastExplosion;
        ammoMultiplier = 4f;
        lightningDamage = 10;
        lightning = 2;
        lightningLength = 10;
    }};

    public static BulletType standardCopper = new BasicBulletType(2.5f, 9){{
        width = 7f;
        height = 9f;
        lifetime = 60f;
        shootEffect = Fx.shootSmall;
        smokeEffect = Fx.shootSmallSmoke;
        ammoMultiplier = 2;
    }};

    public static BulletType standardDense = new BasicBulletType(3.5f, 18){{
        width = 9f;
        height = 12f;
        reloadMultiplier = 0.6f;
        ammoMultiplier = 4;
        lifetime = 60f;
    }};

    public static BulletType standardThorium = new BasicBulletType(4f, 29, "bullet"){{
        width = 10f;
        height = 13f;
        shootEffect = Fx.shootBig;
        smokeEffect = Fx.shootBigSmoke;
        ammoMultiplier = 4;
        lifetime = 60f;
    }};

    public static BulletType standardHoming = new BasicBulletType(3f, 12, "bullet"){{
        width = 7f;
        height = 9f;
        homingPower = 0.1f;
        reloadMultiplier = 1.5f;
        ammoMultiplier = 5;
        lifetime = 60f;
    }};

    public static BulletType standardIncendiary = new BasicBulletType(3.2f, 16, "bullet"){{
        width = 10f;
        height = 12f;
        frontColor = Pal.lightishOrange;
        backColor = Pal.lightOrange;
        status = StatusEffects.burning;
        hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);

        ammoMultiplier = 5;

        splashDamage = 10f;
        splashDamageRadius = 22f;

        makeFire = true;
        lifetime = 60f;
    }};

    public static BulletType standardDenseBig = new BasicBulletType(7.5f, 50, "bullet"){{
        hitSize = 4.8f;
        width = 15f;
        height = 21f;
        shootEffect = Fx.shootBig;
        ammoMultiplier = 4;
        reloadMultiplier = 1.7f;
        knockback = 0.3f;
    }};

    public static BulletType standardThoriumBig = new BasicBulletType(8f, 80, "bullet"){{
        hitSize = 5;
        width = 16f;
        height = 23f;
        shootEffect = Fx.shootBig;
        pierceCap = 2;
        pierceBuilding = true;
        knockback = 0.7f;
    }};

    public static BulletType standardIncendiaryBig = new BasicBulletType(7f, 70, "bullet"){{
        hitSize = 5;
        width = 16f;
        height = 21f;
        frontColor = Pal.lightishOrange;
        backColor = Pal.lightOrange;
        status = StatusEffects.burning;
        hitEffect = new MultiEffect(Fx.hitBulletSmall, Fx.fireHit);
        shootEffect = Fx.shootBig;
        makeFire = true;
        pierceCap = 2;
        pierceBuilding = true;
        knockback = 0.6f;
        ammoMultiplier = 3;
        splashDamage = 15f;
        splashDamageRadius = 24f;
    }};

    public static BulletType fireball = new FireBulletType(1f, 4);

    public static BulletType basicFlame = new BulletType(3.35f, 17f){{
        ammoMultiplier = 3f;
        hitSize = 7f;
        lifetime = 18f;
        pierce = true;
        collidesAir = false;
        statusDuration = 60f * 4;
        shootEffect = Fx.shootSmallFlame;
        hitEffect = Fx.hitFlameSmall;
        despawnEffect = Fx.none;
        status = StatusEffects.burning;
        keepVelocity = false;
        hittable = false;
    }};

    public static BulletType pyraFlame = new BulletType(4f, 60f){{
        ammoMultiplier = 6f;
        hitSize = 7f;
        lifetime = 18f;
        pierce = true;
        collidesAir = false;
        statusDuration = 60f * 10;
        shootEffect = Fx.shootPyraFlame;
        hitEffect = Fx.hitFlameSmall;
        despawnEffect = Fx.none;
        status = StatusEffects.burning;
        hittable = false;
    }};

    public static BulletType waterShot = new LiquidBulletType(Liquids.water){{
        knockback = 0.7f;
        drag = 0.01f;
    }};

    public static BulletType cryoShot = new LiquidBulletType(Liquids.cryofluid){{
        drag = 0.01f;
    }};

    public static BulletType slagShot = new LiquidBulletType(Liquids.slag){{
        damage = 4;
        drag = 0.01f;
    }};

    public static BulletType oilShot = new LiquidBulletType(Liquids.oil){{
        drag = 0.01f;
    }};

    public static BulletType heavyWaterShot = new LiquidBulletType(Liquids.water){{
        lifetime = 49f;
        speed = 4f;
        knockback = 1.7f;
        puddleSize = 8f;
        orbSize = 4f;
        drag = 0.001f;
        ammoMultiplier = 0.4f;
        statusDuration = 60f * 4f;
        damage = 0.2f;
    }};

    public static BulletType heavyCryoShot = new LiquidBulletType(Liquids.cryofluid){{
        lifetime = 49f;
        speed = 4f;
        knockback = 1.3f;
        puddleSize = 8f;
        orbSize = 4f;
        drag = 0.001f;
        ammoMultiplier = 0.4f;
        statusDuration = 60f * 4f;
        damage = 0.2f;
    }};

    public static BulletType heavySlagShot = new LiquidBulletType(Liquids.slag){{
        lifetime = 49f;
        speed = 4f;
        knockback = 1.3f;
        puddleSize = 8f;
        orbSize = 4f;
        damage = 4.75f;
        drag = 0.001f;
        ammoMultiplier = 0.4f;
        statusDuration = 60f * 4f;
    }};

    public static BulletType heavyOilShot = new LiquidBulletType(Liquids.oil){{
        lifetime = 49f;
        speed = 4f;
        knockback = 1.3f;
        puddleSize = 8f;
        orbSize = 4f;
        drag = 0.001f;
        ammoMultiplier = 0.4f;
        statusDuration = 60f * 4f;
        damage = 0.2f;
    }};
}
