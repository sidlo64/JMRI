package jmri.jmrix.can.cbus.node;

import jmri.jmrix.can.CanSystemConnectionMemo;
import jmri.jmrix.can.TrafficController;
import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 * @author Steve Young Copyright (C) 2019
 */
public class CbusNodeTrickleFetchTest {

    @Test
    public void testCTor() {

        CanSystemConnectionMemo memo = Mockito.mock(CanSystemConnectionMemo.class);
        TrafficController tc = Mockito.mock(TrafficController.class);
        CbusNodeTableDataModel model = Mockito.mock(CbusNodeTableDataModel.class);
        Mockito.when(memo.getTrafficController()).thenReturn(tc);

        CbusNodeTrickleFetch t = new CbusNodeTrickleFetch(memo,model,5L);
        Assertions.assertNotNull(t);

        t.dispose();

    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}
