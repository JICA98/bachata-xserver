package org.bachatas4.xserver.sysvshm;

import org.bachatas4.xserver.xconnector.ConnectedClient;
import org.bachatas4.xserver.xconnector.ConnectionHandler;

public class SysVSHMConnectionHandler implements ConnectionHandler {
    private final SysVSharedMemory sysVSharedMemory;

    public SysVSHMConnectionHandler(SysVSharedMemory sysVSharedMemory) {
        this.sysVSharedMemory = sysVSharedMemory;
    }

    @Override
    public void handleNewConnection(ConnectedClient client) {
        client.setTag(sysVSharedMemory);
    }

    @Override
    public void handleConnectionShutdown(ConnectedClient client) {}
}
