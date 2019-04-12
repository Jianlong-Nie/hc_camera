package com.reactlibrary;

import android.content.Context;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

import javax.annotation.Nullable;

public class PreviewViewManager extends SimpleViewManager<SurfaceView> {

    private static final String TAG_NAME = "PreviewViewManager";

    @Override
    public String getName() {
        return TAG_NAME;
    }

    @Override
    protected SurfaceView createViewInstance(ThemedReactContext reactContext) {
        return new SurfaceView(reactContext);
    }


    @Override
    public void onDropViewInstance(SurfaceView view) {
        Context ctx = view.getContext();
        Assertions.assertCondition(ctx instanceof ReactContext, "Should Under React Context");
        ReactContext reactContext = (ReactContext) ctx;
        RNCameraModule module = reactContext.getNativeModule(RNCameraModule.class);
        module.mCameraManager.teardownPreview();
        super.onDropViewInstance(view);
    }

    private static final int COMMAND_PREVIEW_SETUP = 1;
    private static final int COMMAND_PREVIEW_TEARDOWN = 2;

    @Nullable
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
            "setupPreview",
            COMMAND_PREVIEW_SETUP,
            "teardownPreview",
            COMMAND_PREVIEW_TEARDOWN
        );
    }

    @Override
    public void receiveCommand(SurfaceView root, int commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        Context ctx = root.getContext();
        Assertions.assertCondition(ctx instanceof ReactContext, "Should Under React Context");
        ReactContext reactContext = (ReactContext) ctx;
        RNCameraModule module = reactContext.getNativeModule(RNCameraModule.class);
        switch (commandId) {
            case COMMAND_PREVIEW_SETUP: {
                module.mCameraManager.setupPreview(root.autoFitTextureView);
            } break;
            case COMMAND_PREVIEW_TEARDOWN: {
                module.mCameraManager.teardownPreview();
            } break;
            default: Assertions.assertCondition(false, "No COMMAND FOR ID: " + commandId);
        }
    }

}
