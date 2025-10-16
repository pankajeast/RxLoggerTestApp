package com.zebra.rxloggertestapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Rx_MainActivity";
    public static RadioButton startRadioBtnFirst;
    public RadioButton stopRadioBtnfirst;
    //public RadioButton copyLogsRadioBtnfirst;
    public RadioButton backupLogsRadioBtnfirst;
    public RadioButton deleteRadioBtnfirst;
    public RadioButton bugreportRadioBtnfirst;
    public RadioButton deployConfigRadioBtnfirst;
    public RadioButton resetToDefaultRadioBtnfirst;

    public TextView textViewSummary;
    public RadioButton startRadioBtnSecond;
    public static RadioButton stopRadioBtnSecond;
    public static RadioButton copyLogsRadioBtnSecond;
    public static RadioButton backupLogsRadioBtnSecond;
    public static RadioButton deleteRadioBtnSecond;
    public static RadioButton bugreportRadioBtnSecond;
    public static RadioButton deployConfigRadioBtnSecond;
    //private static final String CUSTOM_ACTION = "com.example.myapp.CUSTOM_BROADCAST";
    public static final String RX_GET_STATE_STATUS = "com.symbol.rxlogger.intent.action.GET_RX_STATE_STATUS";
    public static final String RX_ENABLE_STATUS = "com.symbol.rxlogger.intent.action.ENABLE_STATUS";
    public static final String RX_DISABLE_STATUS = "com.symbol.rxlogger.intent.action.DISABLE_STATUS";
    public static final String RX_BACKUP_STATUS = "com.symbol.rxlogger.intent.action.BACKUP_NOW_STATUS";
    public static final String RX_DELETE_STATUS = "com.symbol.rxlogger.intent.action.DELETE_LOGS_STATUS";
    public static final String RX_BUGREPORT_STATUS = "com.symbol.rxlogger.intent.action.RX_BUGREPORT_STATUS";
    public static final String RX_DEPLOY_CONFIG_STATUS = "com.symbol.rxlogger.intent.action.DEPLOY_CONFIG_STATUS";
    public final static String RX_RESET_TO_DEFAULT_STATUS = "com.symbol.rxlogger.intent.action.RESET_TO_DEFAULT_STATUS";

    private final static String INTENT_GET_RX_STATE = "com.zebra.rxlogger.intent.action.GET_RX_STATE";
    private final static String INTENT_ENABLE_APPOPS = "com.zebra.rxlogger.intent.action.ENABLE_ZSX";
    private final static String INTENT_DISABLE_APPOPS = "com.zebra.rxlogger.intent.action.DISABLE_ZSX";
    private final static String INTENT_BACKUP_APPOPS = "com.zebra.rxlogger.intent.action.BACKUP_NOW_ZSX";
    private final static String INTENT_DEPLOY_CONFIG_APPOPS = "com.zebra.rxlogger.intent.action.DEPLOY_CONFIG_ZSX";
    private final static String INTENT_DELETE_LOGS_APPOPS = "com.zebra.rxlogger.intent.action.DELETE_LOGS_ZSX";
    private static final String INTENT_RX_BUGREPORT_APPOPS = "com.zebra.rxlogger.intent.action.RX_BUGREPORT_ZSX";
    private static final String INTENT_RX_RESET_TO_DEFAULT = "com.zebra.rxlogger.intent.action.RESET_TO_DEFAULT_ZSX";

    RxBroadcastReceiver broadcastReceiver;
    private IntentFilter intentFilter;

    Button getRxStateBtn;
    Button startBtn;
    Button stopBtn;
    Button copyBtn;
    Button backupBtn;
    Button deleteBtn;
    Button bugreportBtn;
    Button deployConfigBtn;
    Button resetToDefaultBtn;

    public static final String SECURE_MODE = "persist.sys.dd.secure_mode";
    public static final String SECURE_MODE_TRUE = "true";

    private static final String PREFS_NAME = "MyAppPrefs";
    private static final String KEY_CERT_SIGNATURE = "cert_signature";

    public static final String RX_PKG_NAME = "com.symbol.rxlogger";
    public static final String RX_COMPONENT_NAME = "com.symbol.rxlogger.receiver.CopeModeReceiver";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Button
        getRxStateBtn = findViewById(R.id.get_rxlogger_state);
        startBtn = findViewById(R.id.start_rxlogger);
        stopBtn = findViewById(R.id.stop_rxlogger);
        backupBtn = findViewById(R.id.backup_rxlogger_logs);
/*
        copyBtn = findViewById(R.id.copy_rxlogger_logs);
*/
        deleteBtn = findViewById(R.id.delete_rx_logs);
        bugreportBtn = findViewById(R.id.generate_rx_bugreport);
        deployConfigBtn = findViewById(R.id.deploy_config);
        resetToDefaultBtn = findViewById(R.id.reset_to_default);
        textViewSummary = findViewById(R.id.status_text_view);

        getRxStateBtn.setOnClickListener(this);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        backupBtn.setOnClickListener(this);
/*
        copyBtn.setOnClickListener(this);
*/
        deleteBtn.setOnClickListener(this);
        bugreportBtn.setOnClickListener(this);
        deployConfigBtn.setOnClickListener(this);
        resetToDefaultBtn.setOnClickListener(this);

        broadcastReceiver = new RxBroadcastReceiver(this);

        startRadioBtnFirst = findViewById(R.id.radio_option1_start);
        //startRadioBtnSecond = findViewById(R.id.radio_option2_start);

        stopRadioBtnfirst = findViewById(R.id.radio_option1_stop);
        //stopRadioBtnSecond = findViewById(R.id.radio_option2_stop);

        backupLogsRadioBtnfirst = findViewById(R.id.radio_option1_backup);
        //backupLogsRadioBtnSecond = findViewById(R.id.radio_option2_backup);*/

        //copyLogsRadioBtnfirst = findViewById(R.id.radio_option1_copy);
        //copyLogsRadioBtnSecond = findViewById(R.id.radio_option2_copy);

        deleteRadioBtnfirst = findViewById(R.id.radio_option1_delete);
        //deleteRadioBtnSecond = findViewById(R.id.radio_option2_delete);

        bugreportRadioBtnfirst = findViewById(R.id.radio_option1_bugreport);
        //bugreportRadioBtnSecond = findViewById(R.id.radio_option2_bugreport);


        deployConfigRadioBtnfirst = findViewById(R.id.radio_option1_deploy_config);

        resetToDefaultRadioBtnfirst = findViewById(R.id.radio_option1_reset_to_default);
        //deployConfigRadioBtnSecond = findViewById(R.id.radio_option2_deploy_config);

    }

    @Override
    protected void onResume() {
        super.onResume();
        intentFilter = new IntentFilter();
        intentFilter.addAction(RX_ENABLE_STATUS);
        intentFilter.addAction(RX_DISABLE_STATUS);
        intentFilter.addAction(RX_BACKUP_STATUS);
        intentFilter.addAction(RX_BUGREPORT_STATUS);
        intentFilter.addAction(RX_DELETE_STATUS);
        intentFilter.addAction(RX_DEPLOY_CONFIG_STATUS);
        intentFilter.addAction(RX_GET_STATE_STATUS);
        intentFilter.addAction(RX_RESET_TO_DEFAULT_STATUS);
        broadcastReceiver = new RxBroadcastReceiver(this);
        registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String action = null;
        String permission;
        UserManager userManager = (UserManager) getApplicationContext().getSystemService(Context.USER_SERVICE);
        UserHandle userHandle = android.os.Process.myUserHandle();
        int id = v.getId();
        if (id == R.id.get_rxlogger_state) {
            action = INTENT_GET_RX_STATE;
        } else if (id == R.id.start_rxlogger) {
            Log.d(TAG, "start rxlogger ");
            //startRadioBtnFirst.setChecked(true);
            action = INTENT_ENABLE_APPOPS;
        } else if (id == R.id.stop_rxlogger) {
            Log.d(TAG, "stop rxlogger ");
            //stopRadioBtnfirst.setChecked(true);
            action = INTENT_DISABLE_APPOPS;
        } else if (id == R.id.backup_rxlogger_logs) {
            Log.d(TAG, "backup rxlogger "+isSecureMode());
            //action = INTENT_BACKUP_APPOPS;
            //backupLogsRadioBtnfirst.setChecked(true);
            showPasswordDialogAndSendIntent(INTENT_BACKUP_APPOPS);
        } else if (id == R.id.delete_rx_logs) {
            Log.d(TAG, "delete rxlogger  ");
            action = INTENT_DELETE_LOGS_APPOPS;
        } else if (id == R.id.generate_rx_bugreport) {
            Log.d(TAG, "bugreport rxlogger ");
            action = INTENT_RX_BUGREPORT_APPOPS;
        } else if (id == R.id.deploy_config) {
            Log.d(TAG, "deploy rxlogger ");
            action = INTENT_DEPLOY_CONFIG_APPOPS;
        } else if (id == R.id.reset_to_default) {
            Log.d(TAG, "reset to default ");
            action = INTENT_RX_RESET_TO_DEFAULT;
        }

        if (action != null) {
            intent.setAction(action);
            intent.setComponent(new ComponentName(RX_PKG_NAME, RX_COMPONENT_NAME));
            //sendBroadcast(intent);
            getApplicationContext().sendBroadcastAsUser(intent, userHandle);
            Log.d(TAG, "Broadcast  sent with action: " + action);
        }

    }

    public static boolean isSecureMode() {
        boolean securePropertyFlag = false;
        String securePropertyValue = "";
        try {
            Class<?> systemProperty = Class.forName("android.os.SystemProperties");
            Method getProperty = systemProperty.getDeclaredMethod("get", String.class, String.class);

            securePropertyValue = (String) getProperty.invoke(systemProperty, SECURE_MODE, "default");
            if (securePropertyValue.equals(SECURE_MODE_TRUE)){
                securePropertyFlag = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
            Log.e(TAG, "Exception = "+e.toString());
        }
        return securePropertyFlag;
    }

    // Method to validate password
    private boolean isPasswordValid(String password) {
        if (password.length() < 8) return false;
        if (!password.matches(".*[A-Z].*")) return false;
        if (!password.matches(".*[a-z].*")) return false;
        if (!password.matches(".*\\d.*")) return false;
        if (!password.matches(".*[@#_$%^&+=!-].*")) return false;
        return true;
    }


    // Method to show the password dialog
    private void showPasswordDialogAndSendIntent(final String action) {
        // Create an EditText to input the password
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_password, null);

        /*final EditText passwordInput = new EditText(this); // Assuming 'this' is a valid Context
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);*/

        // Setup EditText and TextView
        final EditText passwordInput = dialogView.findViewById(R.id.passwordInput);
        final TextView passwordHint = dialogView.findViewById(R.id.passwordHint);
        final ImageView passwordToggle = dialogView.findViewById(R.id.passwordToggle);
        final CheckBox keepCopyCheckbox = dialogView.findViewById(R.id.keepCopyCheckbox);

        // Set the password hint
        passwordHint.setText(getString(R.string.password_strength_str));

        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.enter_password_heading))
                .setMessage(getString(R.string.enter_password_desc))
                .setView(dialogView);

        // Create the dialog
        final AlertDialog dialog = builder.setPositiveButton(getString(R.string.ok_str), null) // Set to null to handle enabling/disabling manually
                .setNegativeButton(getString(R.string.cancel_str), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Log.d(TAG, getString(R.string.password_input_cancel));
                    }
                }).create();
        dialog.show();

        // Initially disable the "OK" button
        final Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        okButton.setEnabled(false);

        // Add a TextWatcher to the EditText to monitor changes
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Enable the "OK" button only if the password is valid
                okButton.setEnabled(isPasswordValid(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordInput.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.eye_open);
                } else {
                    passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordToggle.setImageResource(R.drawable.twotone_password_24);
                }
                // Move cursor to the end of the text
                passwordInput.setSelection(passwordInput.length());
            }
        });


        // Set the click listener for the "OK" button separately
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = passwordInput.getText().toString();
                boolean keepACopy = keepCopyCheckbox.isChecked();
                Intent intent = new Intent();
                intent.setAction(action);
                intent.putExtra(getString(R.string.broadcast_password), password);
                intent.putExtra(getString(R.string.broadcast_keep_a_copy), keepACopy);
                intent.setComponent(new ComponentName(RX_PKG_NAME, RX_COMPONENT_NAME));
                sendBroadcast(intent);
                Log.d(TAG, "Broadcast sent with action: " + action + " and password: " + password);
                dialog.dismiss();
            }
        });
    }

    public static String getCallerSignature(Context pContext) {
        String[] packageNamesForUid = pContext.getPackageManager().getPackagesForUid(Binder.getCallingUid());
        String callerSignature = null;
        try {
            Signature sig = pContext.getPackageManager().getPackageInfo(packageNamesForUid[0], PackageManager.GET_SIGNATURES).signatures[0];
            if (sig != null) {
                byte[] data = Base64.encode(sig.toByteArray(), Base64.DEFAULT);
                String signature = new String(data, StandardCharsets.UTF_8);
                callerSignature = signature.replaceAll("\\s+", "");
                Log.d(TAG, "shivin::caller signature:" + callerSignature);
            }
        } catch (Exception e) {
            callerSignature = null;
            Log.e(TAG, "exception in getting application signature");
        }
        return callerSignature;
    }

    public byte[] decodeCallerSignature(String encodedSignature) {
        try {
            // Decode the Base64 string
            byte[] decodedBytes = Base64.decode(encodedSignature, Base64.DEFAULT);
            return decodedBytes;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Error decoding Base64 string", e);
            return null;
        }
    }

    public static void saveSignatureToPrefs(Context context, byte[] decodedBytes) {
        if (decodedBytes == null) {
            Log.e(TAG, "Decoded bytes are null, cannot save to SharedPreferences.");
            return;
        }
        // Convert byte array to Base64 string
        String encodedString = Base64.encodeToString(decodedBytes, Base64.DEFAULT);
        // Get SharedPreferences editor
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Save the encoded string
        editor.putString(KEY_CERT_SIGNATURE, encodedString);
        editor.apply();
        Log.d(TAG, "shivin::Signature saved to SharedPreferences.");
    }

}