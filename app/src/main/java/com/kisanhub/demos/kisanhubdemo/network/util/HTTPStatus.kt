package com.kisanhub.demos.kisanhubdemo.network.util

object HTTPStatus {
    const val SUCCESS = 200
    const val CREATED = 201
    const val NO_CONTENT = 204

    const val NOT_MODIFIED = 304

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val PAGE_NOT_FOUND = 404
    const val REQUEST_TIME_OUT = 408
    const val CONFLICTS = 409

    const val INTERNAL_SERVER_ERROR = 500
    const val GATE_WAY_TIME_OUT = 504
}