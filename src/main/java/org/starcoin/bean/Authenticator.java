/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.starcoin.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Authenticator {
    @JSONField(name = "Ed25519")
    Ed25519 ed25519;

    public MultiEd25519 getMultiEd25519() {
        return multiEd25519;
    }

    public void setMultiEd25519(MultiEd25519 multiEd25519) {
        this.multiEd25519 = multiEd25519;
    }

    @JSONField(name = "MultiEd25519")
    MultiEd25519 multiEd25519;

    public Ed25519 getEd25519() {
        return ed25519;
    }

    public void setEd25519(Ed25519 ed25519) {
        this.ed25519 = ed25519;
    }

    @Override
    public String toString() {
        return "Authenticator{" +
                "ed25519=" + ed25519 +
                ", multiEd25519=" + multiEd25519 +
                '}';
    }
}
