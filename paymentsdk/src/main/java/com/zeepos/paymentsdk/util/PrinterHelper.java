package com.zeepos.paymentsdk.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.ParcelUuid;
import android.util.Log;
import android.util.Printer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.Vector;

public class PrinterHelper {
    Context context;
    private static OutputStream outputStream;

    public static PrinterHelper getInstance(Context context) {
        return new PrinterHelper(context);
    }

    public PrinterHelper(Context context) {
        this.context = context;
    }
    private static UUID SPP_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public void setOutputStream(String printerId, String printerType) {
        if (PrinterType.BLUETOOTH.equals(printerType)){
            BluetoothSocket mBluetoothSocket = null;
            try {
                if (outputStream == null) {
                    BluetoothDevice mBluetoothDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(printerId);
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                            SPP_UUID = mBluetoothDevice.getUuids()[0].getUuid();
                        } else {
                            Class cl = Class.forName("android.bluetooth.BluetoothDevice");
                            Class[] params = {};
                            Method method = cl.getMethod("getUuids", params);
                            Object[] args = {};
                            SPP_UUID = ((ParcelUuid[]) method.invoke(mBluetoothDevice, args))[0].getUuid();
                        }
                    } catch (Exception e1) {
                    }
                    mBluetoothSocket = createBluetoothSocket(mBluetoothDevice, SPP_UUID);
                    mBluetoothSocket.connect();

                    outputStream = mBluetoothSocket.getOutputStream();
                }
            }catch (Exception e){

            }
        }

    }

    private static BluetoothSocket createBluetoothSocket(BluetoothDevice device, UUID uuid)
            throws IOException {
        if (Build.VERSION.SDK_INT >= 10) {
            try {
                final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", new Class[]{UUID.class});
                return (BluetoothSocket) m.invoke(device, SPP_UUID);
            } catch (Exception e) {
            }
        }
        return device.createRfcommSocketToServiceRecord(SPP_UUID);
    }


    protected void printBill() {

        try {
            byte[] printformat = new byte[]{0x1B, 0x21, 0x03};
            outputStream.write(printformat);


            printCustom("Fair Group BD", 2, 1);
            printCustom("Pepperoni Foods Ltd.", 0, 1);
//            printPhoto(R.drawable.ic_icon_pos);
            printCustom("H-123, R-123, Dhanmondi, Dhaka-1212", 0, 1);
            printCustom("Hot Line: +88000 000000", 0, 1);
            printCustom("Vat Reg : 0000000000,Mushak : 11", 0, 1);
            String dateTime[] = getDateTime();
            printText(leftRightAlign(dateTime[0], dateTime[1]));
            printText(leftRightAlign("Qty: Name", "Price "));
            printCustom(new String(new char[32]).replace("\0", "."), 0, 1);
            printText(leftRightAlign("Total", "2,0000/="));
            printNewLine();
            printCustom("Thank you for coming & we look", 0, 1);
            printCustom("forward to serve you again", 0, 1);
            printNewLine();
            printNewLine();

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void printDemo() {
        //print command
        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //print title
            printUnicode();
            //print normal text
            printNewLine();
            printText("     >>>>   Thank you  <<<<     "); // total 32 char in a single line
            //resetPrint(); //reset printer
            printUnicode();
            printNewLine();
            printNewLine();

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //print custom
    private void printCustom(String msg, int size, int align) {
        //Print config "mode"
        byte[] cc = new byte[]{0x1B, 0x21, 0x03};  // 0- normal size text
        //byte[] cc1 = new byte[]{0x1B,0x21,0x00};  // 0- normal size text
        byte[] bb = new byte[]{0x1B, 0x21, 0x08};  // 1- only bold text
        byte[] bb2 = new byte[]{0x1B, 0x21, 0x20}; // 2- bold with medium text
        byte[] bb3 = new byte[]{0x1B, 0x21, 0x10}; // 3- bold with large text
        try {
            switch (size) {
                case 0:
                    outputStream.write(cc);
                    break;
                case 1:
                    outputStream.write(bb);
                    break;
                case 2:
                    outputStream.write(bb2);
                    break;
                case 3:
                    outputStream.write(bb3);
                    break;
            }

            switch (align) {
                case 0:
                    //left align
                    outputStream.write(PrinterCommand.ESC_ALIGN_LEFT);
                    break;
                case 1:
                    //center align
                    outputStream.write(PrinterCommand.ESC_ALIGN_CENTER);
                    break;
                case 2:
                    //right align
                    outputStream.write(PrinterCommand.ESC_ALIGN_RIGHT);
                    break;
            }
            outputStream.write(msg.getBytes());
            outputStream.write(PrinterCommand.LF);
            //outputStream.write(cc);
            //printNewLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //print photo
    public void printPhoto(Bitmap bmp) {
        try {

            if (bmp != null) {
                byte[] command = Utils.decodeBitmap(bmp);
                outputStream.write(PrinterCommand.PRINT_BAR_CODE_1);
                outputStream.write(PrinterCommand.ESC_FONT_COLOR_DEFAULT);
                printText(command);
            } else {
                Log.e("Print Photo error", "the file isn't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PrintTools", "the file isn't exists");
        }
    }

    //print unicode
    public void printUnicode() {
        try {
            outputStream.write(PrinterCommand.ESC_ALIGN_CENTER);
            printText(Utils.UNICODE_TEXT);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //print new line
    private void printNewLine() {
        try {
            outputStream.write(PrinterCommand.FEED_LINE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void resetPrint() {
        try {
            outputStream.write(PrinterCommand.ESC_FONT_COLOR_DEFAULT);
            outputStream.write(PrinterCommand.FS_FONT_ALIGN);
            outputStream.write(PrinterCommand.ESC_ALIGN_LEFT);
            outputStream.write(PrinterCommand.ESC_CANCEL_BOLD);
            outputStream.write(PrinterCommand.LF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //print text
    private void printText(String msg) {
        try {
            // Print normal text
            outputStream.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //print byte[]
    private void printText(byte[] msg) {
        try {
            // Print normal text
            outputStream.write(msg);
            printNewLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String leftRightAlign(String str1, String str2) {
        String ans = str1 + str2;
        if (ans.length() < 31) {
            int n = (31 - str1.length() + str2.length());
            ans = str1 + new String(new char[n]).replace("\0", " ") + str2;
        }
        return ans;
    }


    private String[] getDateTime() {
        final Calendar c = Calendar.getInstance();
        String dateTime[] = new String[2];
        dateTime[0] = c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        dateTime[1] = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
        return dateTime;
    }


    public static class MapItem<A>{
        private A[] key;

        public MapItem(A... key) {
            this.key = key;
        }

        public <B> HashMap<A,B> value(B... value){
            HashMap<A, B> map = new LinkedHashMap<A, B>();
            for(int x = 0; x<key.length;++x){
                map.put(key[x], value[x]);
            }
            return map;
        }
    }

    public static <A> MapItem<A> map(A... e) {
        return new MapItem<A>(e);
    }


    public static byte[] toByte(Vector<Byte> command) {
        byte[] c = new byte[command.size()];
        for (int x = 0; x < command.size(); ++x) {
            c[x] = command.get(x);
        }
        return c;
    }
}
