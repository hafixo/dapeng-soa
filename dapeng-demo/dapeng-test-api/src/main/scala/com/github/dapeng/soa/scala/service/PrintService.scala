
        package com.github.dapeng.soa.scala.service

        import com.github.dapeng.core.{Processor, Service}
        import com.github.dapeng.core.SoaGlobalTransactional

        /**
         * Autogenerated by Dapeng-Code-Generator (2.1.1-SNAPSHOT)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated

        * 
        **/
        @Service(name ="com.github.dapeng.soa.service.PrintService" , version = "1.0.0")
        @Processor(className = "com.github.dapeng.soa.scala.PrintServiceCodec$Processor")
        trait PrintService {
        
            /**
            * 
            **/
            
            @throws[com.github.dapeng.core.SoaException]
            def print(
            ): Unit

          
            /**
            * 
            **/
            
            @throws[com.github.dapeng.core.SoaException]
            def printInfo(
            info: com.github.dapeng.soa.scala.domain.Info ): String

          
            /**
            * 
            **/
            
            @throws[com.github.dapeng.core.SoaException]
            def printInfo2(
            name: String ): String

          
            /**
            * 
            **/
            
            @throws[com.github.dapeng.core.SoaException]
            def printInfo3(
            ): String

          
      }
      