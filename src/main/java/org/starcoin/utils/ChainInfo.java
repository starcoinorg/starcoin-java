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
package org.starcoin.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChainInfo {


    //  MAIN("main", "", 1),
    public static final ChainInfo DEFAULT_BARNARD = new ChainInfo("barnard", "https://barnard-seed.starcoin.org", 251);
    public static final ChainInfo DEFAULT_DEV = new ChainInfo("dev", "https://localhost", 254);

    private String name;
    private String url;
    private int chainId;

}
