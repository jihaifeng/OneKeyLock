package lucky.endwin.com.onekeylock;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by jihf on 2016/6/29 0029.
 */
public class LockReceiver extends DeviceAdminReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("onReceive","onReceive" + intent);
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        Log.e("onEnable","激活设备管理器");
        super.onEnabled(context, intent);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Log.e("onDisabled","取消激活设备管理器");
        super.onDisabled(context, intent);
    }
}
