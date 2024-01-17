package com.plampay.sdk.demo;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import java.io.File;

public class Utils {

    public static void clearCache(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 清除cookie
                CookieManager.getInstance().removeAllCookies(null);
            } else {
                CookieSyncManager.createInstance(context);
                CookieManager.getInstance().removeAllCookie();
                CookieSyncManager.getInstance().sync();
            }

            WebView webView = new WebView(context);
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();

            File cacheFile = new File(context.getCacheDir().getParent() + "/app_webview");
            clearCacheFolder(cacheFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static int clearCacheFolder(File dir) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child);
                    }
                    if (child.delete()) {
                        deletedFiles++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;

}
}
