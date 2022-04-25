package hankdev.app.xposedhookdemo

import android.util.Log
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class MyModule : IXposedHookLoadPackage {

    companion object {
        private const val TAG = "MyModule"
    }

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam?) {
        Log.i(TAG, "Loaded app: ${lpparam?.packageName}")

        XposedHelpers.findAndHookMethod(
            "com.leaf.and.aleaf.SimpleVpnService.c",
            lpparam?.classLoader,
            "invoke",
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    Log.i(TAG, "beforeHookedMethod: ")

                    param?.let {
                        Log.i(TAG, it.method.name)
                        Log.i(TAG, it.thisObject.toString())
                    }
                }

                override fun afterHookedMethod(param: MethodHookParam?) {
                    Log.i(TAG, "afterHookedMethod: ")

                    param?.let {
                        Log.i(TAG, it.method.name)
                    }
                }
            }
        )
    }

}