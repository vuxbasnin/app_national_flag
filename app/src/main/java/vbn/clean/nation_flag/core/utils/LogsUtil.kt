package vbn.clean.nation_flag.core.utils

import timber.log.Timber

class LogsUtil : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber}),${element.methodName}"
    }
}