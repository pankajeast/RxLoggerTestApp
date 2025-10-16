# RxLoggerTestApp

This application serves as an integration reference to showcase how to:

1. Use sending intents to send commands to the RxLogger module.
2. Receive intents using a broadcast receiver and view the results of the sent commands.

## Permissions

To use this functionality, the app requires the following permission:

```xml
<uses-permission android:name="com.zebra.permission.ACCESS_RXLOGGER" />
```

Ensure this permission is added to your `AndroidManifest.xml` file.

## Usage

1. **Sending Commands**: Use intents to send commands to the RxLogger module.
2. **Receiving Results**: Implement a broadcast receiver to handle and display the results of the commands.

Refer to the code for detailed implementation.

## Intents

### Sending Intents
Below is a list of all the intents that can be sent to the RxLogger module:

- **Intent 1**: `<intent details>`
- **Intent 2**: `<intent details>`
- Add more intents as applicable.

### Broadcast Receiver Intents
Below is a list of all the intents that can be received via the broadcast receiver:

- **Intent 1**: `<intent details>`
- **Intent 2**: `<intent details>`
- Add more intents as applicable.
