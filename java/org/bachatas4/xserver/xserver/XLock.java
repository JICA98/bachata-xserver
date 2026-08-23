package org.bachatas4.xserver.xserver;

public interface XLock extends AutoCloseable {
    @Override
    void close();
}
