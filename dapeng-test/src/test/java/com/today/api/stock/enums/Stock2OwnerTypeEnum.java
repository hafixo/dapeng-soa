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
package com.today.api.stock.enums;

        /**
         * Autogenerated by Dapeng-Code-Generator (2.2.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

        * 

 库存主体类型

        **/
        public enum Stock2OwnerTypeEnum implements com.github.dapeng.org.apache.thrift.TEnum{
        
            /**
            *

 门店

            **/
            STORE(1),
          
            /**
            *

 仓库

            **/
            WAREHOUSE(2),
          
        /*
        * 未定义的枚举类型
        */
        UNDEFINED(-1);
        private final int value;

        private Stock2OwnerTypeEnum(int value){
          this.value = value;
        }

        public int getValue(){
          return this.value;
        }

        public static Stock2OwnerTypeEnum findByValue(int value){
          switch(value){
            
                case 1:
                return STORE;
              
                case 2:
                return WAREHOUSE;
              
            case -1:
              return UNDEFINED;
            default:
            return null;
          }
        }
      }
      