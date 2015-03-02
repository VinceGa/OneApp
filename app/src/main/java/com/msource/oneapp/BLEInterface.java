package com.msource.oneapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.content.Context;

import android.webkit.JavascriptInterface;
import android.widget.Toast;
import android.os.Handler;
import android.app.Service;
/**
 * Created by vgao on 2/28/2015.
 */
public class BLEInterface {
    Context mContext;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothScanner;
    private Handler mHandler;
    private boolean mScanning;
    private static final long SCAN_PERIOD = 10000;

    /** Instantiate the interface and set the context */
    BLEInterface(Context c) {
        this.mContext = c;
        final BluetoothManager bluetoothManager =
                (BluetoothManager)mContext.getSystemService(mContext.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
    }

    public void scanLeDevice(final boolean enable){
        if (enable) {
            mBluetoothScanner = mBluetoothAdapter.getBluetoothLeScanner();
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothScanner.stopScan(mLeScanCallback);

                }
            }, SCAN_PERIOD);

            mScanning = true;


            mBluetoothScanner.startScan(mLeScanCallback);
        } else {
            mScanning = false;

            mBluetoothScanner.stopScan(mLeScanCallback);
        }
    }
    // Device scan callback.
    private ScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                           // mLeDeviceListAdapter.addDevice(device);
                           // mLeDeviceListAdapter.notifyDataSetChanged();
                        }
                    });
                }
            };

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
