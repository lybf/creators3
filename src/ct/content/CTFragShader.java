package ct.content;

import arc.files.Fi;
import arc.graphics.gl.Shader;
import mindustry.Vars;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Shaders;
import mindustry.world.blocks.environment.Floor;

import static mindustry.graphics.Shaders.getShaderFi;


public class CTFragShader {
    public static Fi shaders = Vars.mods.locateMod("ct").root.child("CTshaders");
    public static CacheLayer.ShaderLayer m =//幻液的效果
            new CacheLayer.ShaderLayer(new Shaders.SurfaceShader(getShaderFi("screenspace.vert").readString(),
                    shaders.child("cryofluid2.frag").readString()));
    public static CacheLayer.ShaderLayer 紫色冷却液效果 =//TD
            new CacheLayer.ShaderLayer(new Shaders.SurfaceShader(getShaderFi("screenspace.vert").readString(),
                    shaders.child("Floor1.frag").readString()));

    public static CacheLayer.ShaderLayer weizhi =//??
            new CacheLayer.ShaderLayer(new Shaders.SurfaceShader(getShaderFi("screenspace.vert").readString(),
                    shaders.child("cryofluid1.frag").readString()));

    static {
        CacheLayer.add(m, 紫色冷却液效果, weizhi);
    }

    public static void load() {
/*        new Floor("DT-LiquidFloor") {{
            drownTime = 10f;
            variants = 0;
            isLiquid = false;
            emitLight = true;
            lightRadius = 25f;
            // lightColor = C("cc44ff").cpy().a(0.7f);
            //cacheLayer = new CacheLayer.ShaderLayer(紫色Floor);
            cacheLayer = weizhi;
        }};*/
    }
}
