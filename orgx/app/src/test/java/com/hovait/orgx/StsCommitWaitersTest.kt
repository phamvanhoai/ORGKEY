package com.hovait.orgx

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class StsCommitWaitersTest {
    @Test fun shutdownFinishesAllPendingRequestsAndRejectsNewOnes()=runBlocking {
        val waiters=StsCommitWaiters()
        val first=waiters.register();val second=waiters.register()
        assertFalse(first.isCompleted)
        waiters.close()
        assertFalse(first.await());assertFalse(second.await())
        assertFalse(waiters.register().await())
        assertFalse(first.complete(true))
        waiters.close()
    }
    @Test fun shutdownPreservesAlreadyCommittedSuccessAndAcceptsCancelledRequests()=runBlocking {
        val waiters=StsCommitWaiters()
        val success=waiters.register();success.complete(true)
        val cancelled=waiters.register();cancelled.cancel()
        waiters.close()
        assertTrue(success.await());assertTrue(cancelled.isCancelled)
    }
}
