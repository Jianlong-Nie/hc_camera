
package com.hc.camera;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.io.File;

public class RNCameraModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;
  public CameraManager mCameraManager;
  public RNCameraModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
    mCameraManager = new CameraManager(reactContext);
  }

  @Override
  public String getName() {
    return "RNCamera";
  }
  @Override
  public void onCatalystInstanceDestroy() {
    super.onCatalystInstanceDestroy();
    mCameraManager.teardownPreview();
  }

  private File getTmpDirPath() {
    return getCurrentActivity().getExternalCacheDir();
  }

  @ReactMethod
  public void shoot(String fileName, Promise promise) {
    File dest = new File(getTmpDirPath(), fileName);
    mCameraManager.takePicture(dest, promise);
  }
  @ReactMethod
  public void removeTmpPicture(String fileName, Promise promise) {
    File target = getTmpDirPath();
    try {
      if (target.exists()) {
        File[] files = target.listFiles();
        for (File f : files) {
          f.delete();
        }
        promise.resolve(null);
      } else {
        promise.reject(new Exception("Could not remove the file --> " + target.getAbsolutePath()));
      }
    } catch (Exception e) {
      e.printStackTrace();
      promise.reject(e);
    }
  }
}