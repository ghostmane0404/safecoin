package com.almazbekov.udf

class GenericTypeNameFactory {
    fun create(classWithGeneric: Class<*>): String {
        val hasSuperClass = classWithGeneric.genericInterfaces.isEmpty()
        val className = if (hasSuperClass) {
            classWithGeneric.genericSuperclass.toString()
        } else {
            classWithGeneric.genericInterfaces.first().toString()
        }

        return className.subSequence(
            className.indexOfFirst { i -> i == '<' } + 1,
            className.indexOfFirst { i -> i == '>' },
        ).toString()
    }
}
