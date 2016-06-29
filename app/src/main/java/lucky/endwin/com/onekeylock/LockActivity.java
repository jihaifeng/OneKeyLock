package lucky.endwin.com.onekeylock;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jihf on 2016/6/29 0029.
 */
public class LockActivity extends Activity {
    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lockScreen();


    }

    private void lockScreen() {
        // 创建一个Intent
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, LockReceiver.class);
        if (devicePolicyManager.isAdminActive(componentName)) {
            //已经有权限，直接锁屏
            devicePolicyManager.lockNow();
            LockActivity.this.finish();
        } else {
            //没有权限，去设备管理器设置权限
            setManager();
        }
    }

    /**
     * 打开设备管理器设置权限
     */
    private void setManager() {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "oneKeyLock");
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        //onResume中设置首次激活设备管理器后，锁屏
        if (null != devicePolicyManager && null != componentName) {
            if (devicePolicyManager.isAdminActive(componentName)) {
                devicePolicyManager.lockNow();
                LockActivity.this.finish();
            }
        }
        super.onResume();
    }
}
