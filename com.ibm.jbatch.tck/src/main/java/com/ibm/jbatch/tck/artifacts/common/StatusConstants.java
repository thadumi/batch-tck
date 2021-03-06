/*
 * Copyright 2012 International Business Machines Corp.
 *
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.jbatch.tck.artifacts.common;

public interface StatusConstants {
    public final String GOOD_JOB_EXIT_STATUS = "JOB:ES:Good";
    public final String GOOD_STEP_EXIT_STATUS = "STEP:ES:Good";
    public final String SPECIAL_EXIT_STATUS = "STEP:ES:Special";

    // Thought is that this should never happen in a successful test, even one in which failure is expected.
    public final String UNEXPECTED = "ES: TCK Test Failure : unexpected";

    public final String A_STEP_RAN = "StepRan;";
}
