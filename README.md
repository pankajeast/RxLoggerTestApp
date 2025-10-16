# RxLoggerTestApp

This application serves as an integration reference to showcase how to:

1. Send commands to the RxLogger module using intents.
2. Receive intents using a broadcast receiver and view the results of the sent commands.

## Permissions

To use this functionality, the app requires the following permission:

```xml
<uses-permission android:name="com.zebra.permission.ACCESS_RXLOGGER" />
```

Ensure this permission is added to your `AndroidManifest.xml` file.

## Grant Permission

To grant ACCESS_RXLOGGER permission, create MXMF xml file using the following snippet and submit on device:

```xml
<wap-provisioningdoc>
  <characteristic version="14.2" type="AccessMgr">
    <parm name="PermissionAccessAction" value="1" />
    <parm name="PermissionAccessPermissionName" value="com.zebra.permission.ACCESS_RXLOGGER" />
    <parm name="PermissionAccessPackageName" value="com.zebra.rxloggertestapp" />
    <parm name="PermissionAccessSignature" value="application_Signature" />
  </characteristic>
</wap-provisioningdoc>
```

Ensure this file uses, target apk's packageName & signature for `PermissionAccessPackageName` & `PermissionAccessSignature`.

## Usage

1. **Sending Commands**: Use intents to send commands to the RxLogger module.
2. **Receiving Results**: Implement a broadcast receiver to handle and display the results of the commands.

Refer to the code for detailed implementation.

## COPE Mode Requirements

In COPE (Corporate Owned, Personally Enabled) mode, when sending intents to the RxLogger module, you must specify the component name to ensure the intent is properly delivered to the RxLogger receiver.

**Required Component Details:**
- **PACKAGE_NAME**: `com.symbol.rxlogger`
- **CLASS_NAME**: `com.symbol.rxlogger.receiver.CopeModeReceiver`

**Implementation Example:**

```java
Intent intent = new Intent();
intent.setAction("com.zebra.rxlogger.intent.action.ENABLE_ZSX");
intent.setComponent(new ComponentName(RX_PKG_NAME, RX_COMPONENT_NAME));
sendBroadcast(intent);
```

Where:
- `RX_PKG_NAME = "com.symbol.rxlogger"`
- `RX_COMPONENT_NAME = "com.symbol.rxlogger.receiver.CopeModeReceiver"`

## Intents

### Sending Intents
Below is a list of all the intents that can be sent to the RxLogger module:

- **com.zebra.rxlogger.intent.action.GET_RX_STATE**: Retrieve the current state of the RxLogger.
- **com.zebra.rxlogger.intent.action.ENABLE_ZSX**: Enable the RxLogger.
- **com.zebra.rxlogger.intent.action.DISABLE_ZSX**: Disable the RxLogger.
- **com.zebra.rxlogger.intent.action.BACKUP_NOW_ZSX**: Trigger an immediate backup of logs.
- **com.zebra.rxlogger.intent.action.DEPLOY_CONFIG_ZSX**: Deploy a new configuration to the RxLogger.
- **com.zebra.rxlogger.intent.action.DELETE_LOGS_ZSX**: Delete existing logs.
- **com.zebra.rxlogger.intent.action.RX_BUGREPORT_ZSX**: Generate a bug report.
- **com.zebra.rxlogger.intent.action.RESET_TO_DEFAULT_ZSX**: Reset the RxLogger to its default configuration.

### Broadcast Receiver Intents
Below is a list of all the intents that can be received via the broadcast receiver:

- **com.symbol.rxlogger.intent.action.GET_RX_STATE_STATUS**: Broadcast result for retrieving the current state of the RxLogger.
- **com.symbol.rxlogger.intent.action.ENABLE_STATUS**: Broadcast result for enabling the RxLogger status.
- **com.symbol.rxlogger.intent.action.DISABLE_STATUS**: Broadcast result for disabling the RxLogger status.
- **com.symbol.rxlogger.intent.action.BACKUP_NOW_STATUS**: Broadcast result for triggering an immediate backup of logs.
- **com.symbol.rxlogger.intent.action.DELETE_LOGS_STATUS**: Broadcast result for deleting existing logs.
- **com.symbol.rxlogger.intent.action.RX_BUGREPORT_STATUS**: Broadcast result for generating a bug report.
- **com.symbol.rxlogger.intent.action.DEPLOY_CONFIG_STATUS**: Broadcast result for deploying a new configuration to the RxLogger.
- **com.symbol.rxlogger.intent.action.RESET_TO_DEFAULT_STATUS**: Broadcast result for resetting the RxLogger to its default configuration.
