
//Java核心系统
function CreatorsPackage(name) {
	var p = Packages.rhino.NativeJavaPackage(name, Vars.mods.mainLoader());
	Packages.rhino.ScriptRuntime.setObjectProtoAndParent(p, Vars.mods.scripts.scope)
	return p
}
var CreatorsJavaPack = CreatorsPackage('ct')
importPackage(CreatorsJavaPack)
importPackage(CreatorsJavaPack.Asystem)
importPackage(CreatorsJavaPack.Asystem.dialogs)
importPackage(CreatorsJavaPack.Asystem.type)
importPackage(CreatorsJavaPack.content)
importPackage(CreatorsJavaPack.ui)
importPackage(CreatorsJavaPack.Asystem.type.VXV)
importPackage(CreatorsJavaPack.ui.dialogs)
importPackage(CreatorsJavaPack.content.chapter4)


CreatorsModJS.RunName.add("ct")
CreatorsModJS.DawnRun.add(run(() => {
	require('变速');
	require('nihility');//虚无护盾
// require('UnitBlocks');
// require('环保生产厂');
// require('zuantou');
// require('104单位工厂');
// require('测试');
/* let mod = Vars.mods.locateMod("dlc2");
 if (mod == null) {
	 let bd = Vars.mods.locateMod("ct");
	 let fi = bd.root.child("mod")
		 .child("CT3_5.jar");
	 Vars.mods.importMod(fi);
 };*/
}));
UnitTypes.gamma.speed = 7;
UnitTypes.beta.speed = 5.5;
UnitTypes.alpha.speed = 4;
UnitTypes.gamma.armor = 6;
UnitTypes.beta.armor = 4;
UnitTypes.alpha.armor = 2;

