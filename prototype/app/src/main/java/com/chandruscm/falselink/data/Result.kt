/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chandruscm.falselink.data

import android.net.Uri

/**
 * Verification result holder.
 * - Safe : Verified Safe (HTTPS & NORMAL)
 * - Dangerous : Verified Dangerous (!HTTP || !NORMAL)
 * - Error : Verification failed
 *
 * @param uri - Final redirected/expanded Uri
 * @param website - Website after verification
 * @param error - Error thrown during Network I/O
 */
sealed class Result<T> {
    class Safe<T>(val uri: Uri? = null) : Result<T>()
    class Dangerous<T>(val uri: Uri?, val website: Website) : Result<T>()
    class Error<T>(val error: Throwable? = null) : Result<T>()
}
