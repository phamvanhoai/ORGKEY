package com.hovait.orgx

import org.junit.Assert.*
import org.junit.Test

class ImportedFxValidationTest {
    @Test fun enabledUnknownModuleIsRejectedInsteadOfSilentlyBypassed(){
        val module=ImportedFxModule(999,true,false,emptyList())
        try{ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module)));fail("Unknown enabled FX was ignored")}
        catch(expected:IllegalArgumentException){assertEquals("Unsupported enabled SET FX module 999",expected.message)}
        ImportedFxChain(ImportedChannelSettings(fxEnabled=false,modules=listOf(module))).use{
            it.process(.25,-.125);assertEquals(.25,it.left,0.0);assertEquals(-.125,it.right,0.0)
        }
        ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module.copy(enabled=false)))).use{
            it.process(.25,-.125);assertEquals(.25,it.left,0.0)
        }
    }
    @Test fun malformedNewlySupportedPayloadsFailBeforeNativeAllocation(){
        for(module in listOf(ImportedFxModule(15,true,false,listOf(100)),
            ImportedFxModule(15,true,false,listOf(49,0,0,0)),
            ImportedFxModule(31,true,false,listOf(101,0,0,0)))){
            try{ImportedFxChain(ImportedChannelSettings(fxEnabled=true,modules=listOf(module)));fail("Invalid FX accepted")}
            catch(expected:IllegalArgumentException){}
        }
    }
}
