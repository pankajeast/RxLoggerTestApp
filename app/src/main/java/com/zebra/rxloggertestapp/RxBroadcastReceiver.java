package com.zebra.rxloggertestapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.RadioButton;
import android.widget.Toast;

public class RxBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "Rx_BroadcastReceiver";
    private RadioButton radioButton;
    private MainActivity mainActivity;

    private static final String RX_GET_STATE_STATUS = "com.symbol.rxlogger.intent.action.GET_RX_STATE_STATUS";
    private static final String RX_ENABLE_STATUS = "com.symbol.rxlogger.intent.action.ENABLE_STATUS";
    private static final String RX_DISABLE_STATUS = "com.symbol.rxlogger.intent.action.DISABLE_STATUS";
    private static final String RX_BACKUP_STATUS = "com.symbol.rxlogger.intent.action.BACKUP_NOW_STATUS";
    private static final String RX_DELETE_STATUS = "com.symbol.rxlogger.intent.action.DELETE_LOGS_STATUS";
    private static final String RX_BUGREPORT_STATUS = "com.symbol.rxlogger.intent.action.RX_BUGREPORT_STATUS";
    private static final String DEPLOY_CONFIG_STATUS = "com.symbol.rxlogger.intent.action.DEPLOY_CONFIG_STATUS";
    private static final String RESET_TO_DEFAULT_STATUS = "com.symbol.rxlogger.intent.action.RESET_TO_DEFAULT_STATUS";

    public RxBroadcastReceiver(){

    }
    public RxBroadcastReceiver(MainActivity activity) {
        this.mainActivity = activity;
    }

    private RxBroadcastReceiver rxBroadcastReceiver;
    private IntentFilter filter;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive :: action :: "+intent.getAction());
        if (intent == null){
            Toast.makeText(context, "intent is  null", Toast.LENGTH_SHORT).show();
        }
        String action_str, status_str, message_str, filepath_str;
        int result_int = -1;
        int error_code =  -1;
        String summary = "";
        String action = intent.getAction();
        if (action.equalsIgnoreCase(RX_ENABLE_STATUS)) {
            //Toast.makeText(context, "RxLogger is started", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_enable_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Enable ::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus:  " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + (( result_int != -1) ?  result_int : -1 );

                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.startRadioBtnFirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.startRadioBtnFirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        }else if (action.equalsIgnoreCase(RX_DISABLE_STATUS)) {
            //Toast.makeText(context, "RxLogger is stoped", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_disable_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Disable ::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus:  " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + (( result_int != -1) ?  result_int : -1 );
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.stopRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.stopRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
                }
        } else if (action.equalsIgnoreCase(RX_BACKUP_STATUS)) {
            //Toast.makeText(context, "RxLogger backup", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_backupnow_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                filepath_str = intent.getStringExtra(context.getString(R.string.intent_filepath));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Backup ::status :: "+status_str+" file path :: "+filepath_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + (result_int)+
                            "\nFileName: " + ((filepath_str != null) ? filepath_str : "");
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.backupLogsRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.backupLogsRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        } else if (action.equalsIgnoreCase(RX_BUGREPORT_STATUS)) {
            Toast.makeText(context, "RxBugreport is completed", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_bugreport_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Bugreport ::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus:  " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + ( result_int );
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.bugreportRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.bugreportRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        } else if (action.equalsIgnoreCase(RX_DELETE_STATUS)) {
            //Toast.makeText(context, "Logs are deleted", Toast.LENGTH_SHORT).show();
            String rxLogTypeVal;
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_delete_logs_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                rxLogTypeVal = intent.getStringExtra(context.getString(R.string.intent_delete_log_type));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Delete logs ::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + ( result_int )+
                            "\nLog Type Deleted: " + (rxLogTypeVal != null ? rxLogTypeVal : "");
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.deleteRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.deleteRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        } else if (action.equalsIgnoreCase(RESET_TO_DEFAULT_STATUS)) {
            //Toast.makeText(context, "Reset to default", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_reset_to_default);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Reset to default::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus:  " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + ( result_int );
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.resetToDefaultRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.resetToDefaultRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        } else if (action.equalsIgnoreCase(DEPLOY_CONFIG_STATUS)) {
            //Toast.makeText(context, "config file deployed", Toast.LENGTH_SHORT).show();
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_deploy_config_rx);
                status_str = intent.getStringExtra(context.getString(R.string.intent_status));
                message_str = intent.getStringExtra(context.getString(R.string.intent_message));
                result_int = intent.getIntExtra(context.getString(R.string.intent_result), -1);
                error_code = intent.getIntExtra(context.getString(R.string.intent_error_code), -1);
                Log.d(TAG, "Deploy Config::status :: "+status_str+" msg :: "+message_str+" result code :: "+result_int+" error code :: "+error_code);
                if (status_str != null && !(status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success)))) {
                    summary = "Action: " + action_str +
                            "\nStatus: " + (status_str != null ? status_str : "") +
                            "\nResult Code: " + result_int  +
                            "\nError Message: " + (message_str != null ? message_str : "") +
                            "\nError Code: " + (error_code != -1 ? error_code : "");
                } else {
                    summary = "Action: " + action_str +
                            "\nStatus:  " + (status_str != null ? status_str : "")+
                            "\nResult Code: " + ( result_int );
                }
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
                if (status_str != null) {
                    if (status_str.equalsIgnoreCase(context.getString(R.string.intent_status_success))) {
                        this.mainActivity.deployConfigRadioBtnfirst.setButtonDrawable(R.drawable.radio_checked_green);
                    } else {
                        this.mainActivity.deployConfigRadioBtnfirst.setButtonDrawable(R.drawable.radio_unchecked_red);
                    }
                }
            }
        } else if (action.equalsIgnoreCase(RX_GET_STATE_STATUS)) {
            //Toast.makeText(context, "RxLogger state status received", Toast.LENGTH_SHORT).show();
            int rx_state_code;
            String rx_state_msg;
            if (this.mainActivity != null) {
                action_str = context.getString(R.string.textview_status_rx_state);
                rx_state_code = intent.getIntExtra(context.getString(R.string.intent_rx_state_code), -1);
                rx_state_msg = intent.getStringExtra(context.getString(R.string.intent_rx_state_msg));
                summary = "Action: " + action_str +
                        "\nStatus: " + ((rx_state_code != -1) ? rx_state_code : -1) +
                        "\nMessage: " + (rx_state_msg != null ? rx_state_msg : "");
                this.mainActivity.textViewSummary.setText(summary);
                Log.d(TAG, "Summary :: " + summary);
            }
        }
    }
}
