package jmri.jmrix;

import jmri.SystemConnectionMemo;
import jmri.jmrix.debugthrottle.DebugThrottle;
import jmri.util.JUnitUtil;
import jmri.DccLocoAddress;

import org.mockito.Mockito;
import org.junit.jupiter.api.*;

/**
 * @author Paul Bender Copyright (C) 2017
 */
public class AbstractThrottleManagerTest extends jmri.managers.AbstractThrottleManagerTestBase {

    private SystemConnectionMemo memo;
    private DebugThrottle throttle;

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        memo = Mockito.mock(SystemConnectionMemo.class);
        Mockito.when(memo.getUserName()).thenReturn("Test");
        Mockito.when(memo.getSystemPrefix()).thenReturn("T");
        tm = new AbstractThrottleManager(memo) {
            @Override
            public void requestThrottleSetup(jmri.LocoAddress a, boolean control) {
                if (!(a instanceof DccLocoAddress)){
                    Assertions.fail("DebugThrottle needs a dcclocoaddress : " + a );
                    return;
                }
                throttle = new DebugThrottle((DccLocoAddress) a, adapterMemo);
                notifyThrottleKnown(throttle, a);
            }

            @Override
            public boolean addressTypeUnique() {
                return false;
            }

            @Override
            public boolean canBeShortAddress(int address) {
                return true;
            }

            @Override
            public boolean canBeLongAddress(int address) {
                return true;
            }
        };
    }

    @AfterEach
    public void tearDown() {
        memo = null;
        tm = null;
        JUnitUtil.tearDown();
    }

    // private final static Logger log =
    // LoggerFactory.getLogger(AbstractThrottleManagerTest.class);

}
