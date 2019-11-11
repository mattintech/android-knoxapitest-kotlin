# Knox API Test for Android

Knox API Test is an application designed to help ISVs & Developers get started with Samsung's Knox APIs with Kotlin. It serves as both a sample and a testing application to validate Knox APIs using the older DeviceAdmin framework.

See the [Documentation](https://docs.samsungknox.com/dev/knox-sdk/index.htm) for getting started with the Samsung Knox SDK.

[Knox SDK API Reference for Android](https://docs.samsungknox.com/devref/knox-sdk/reference/packages.html) is a great resource for learning about all available Samsung APIs for Android.

## Getting Started
This sample uses the Gradle build system. To build this project, use the "gradlew assemble" command or use "Import Project" in Android Studio.

In order to begin working with the Samsung Android APIs you will need to have a valid developer license. The [Get A License](https://docs.samsungknox.com/dev/common/tutorial-get-a-license.htm) a great resource for learning about the license require for your application.

Once you have obtained your developer license update the Knox LicenseAndAdminReceiver class.

You will also need to obtain the most current version of the KnoxSdk.jar and place it into the app/libs folder. (remember to sync your project after adding the jar file)

[Click here](https://docs.samsungknox.com/dev/knox-sdk/install-sdk.htm) for detailed steps on how to obtain the KnoxSdk.jar.

## Support
If you've found an error in this sample, or have a suggestion please file an issue:
https://github.com/mattintech/android-knoxapitest-kotlin/issues

Patches are encouraged, and may be submitted by forking this project and submitting a pull request through GitHub.

## License
Licensed under the MIT license. See the LICENSE file for details.

## How to make contributions?
Please read and follow the steps in the CONTRIB file.