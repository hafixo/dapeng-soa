/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dapeng.registry;

/**
 * Created by tangliu on 2016/2/16.
 */
public enum ConfigKey {

    Thread("thread"),

    ThreadPool("threadPool"),

    ClientTimeout("clientTimeout"),

    ServerTimeout("serverTimeout"),

    LoadBalance("loadBalance"),

    FailOver("failover"),

    Compatible("compatible"),

    TimeOut("timeout"),

    ProcessTime("processTime"),

    Weight("weight");

    private final String value;

    ConfigKey(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ConfigKey findByValue(String value) {
        switch (value) {
            case "thread":
                return Thread;
            case "threadPool":
                return ThreadPool;
            case "clientTimeout":
                return ClientTimeout;
            case "serverTimeout":
                return ServerTimeout;
            case "loadBalance":
                return LoadBalance;
            case "failover":
                return FailOver;
            case "compatible":
                return Compatible;
            case "processTime":
                return ProcessTime;
            default:
                return null;
        }
    }
}
