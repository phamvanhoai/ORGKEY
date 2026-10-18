package com.hovait.orgx

import java.io.File
import org.junit.Assert.*
import org.junit.Assume.assumeNotNull
import org.junit.Test

class ImportedStsIntegrationTest {
    @Test fun legacySnapshotsKeepSlotIdentityAndValidInstrumentReferences() {
        val path=System.getProperty("orgx.legacySet")
        assumeNotNull(path)
        val index=ImportedSetInstruments.index(File(path!!))
        val snapshots=index.tracks.flatMap{it.sts}
        assertEquals("Snapshot count differs from the audited legacy SET",112,snapshots.size)
        index.tracks.forEach{track->
            assertEquals(track.sts.size,track.sts.map{it.slot}.distinct().size)
            assertTrue(track.sts.all{it.slot in 0..12})
        }
        snapshots.forEach{snapshot->
            assertTrue("Missing instrument ${snapshot.instrument} at slot ${snapshot.slot}",snapshot.instrument in index.instruments.indices)
            assertTrue("Missing system ${snapshot.system} at slot ${snapshot.slot}",snapshot.system in index.systems.indices)
            assertEquals(if(snapshot.chordType==0)0 else 12,snapshot.chordSingle.size)
        }
    }
}
