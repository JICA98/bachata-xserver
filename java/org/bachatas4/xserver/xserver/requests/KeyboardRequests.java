package org.bachatas4.xserver.xserver.requests;

import static org.bachatas4.xserver.xserver.Keyboard.KEYSYMS_PER_KEYCODE;
import static org.bachatas4.xserver.xserver.XClientRequestHandler.RESPONSE_CODE_SUCCESS;

import org.bachatas4.xserver.xconnector.XInputStream;
import org.bachatas4.xserver.xconnector.XOutputStream;
import org.bachatas4.xserver.xconnector.XStreamLock;
import org.bachatas4.xserver.xserver.Keyboard;
import org.bachatas4.xserver.xserver.XClient;
import org.bachatas4.xserver.xserver.errors.XRequestError;

import java.io.IOException;

public abstract class KeyboardRequests {
    public static void queryKeymap(XClient client, XOutputStream outputStream) throws IOException {
        try (XStreamLock lock = outputStream.lock()) {
            outputStream.writeByte(RESPONSE_CODE_SUCCESS);
            outputStream.writeByte((byte)0);
            outputStream.writeShort(client.getSequenceNumber());
            outputStream.writeInt(2);
            outputStream.write(client.xServer.keyboard.getKeymap());
        }
    }

    public static void getKeyboardMapping(XClient client, XInputStream inputStream, XOutputStream outputStream) throws IOException, XRequestError {
        byte firstKeycode = inputStream.readByte();
        int count = inputStream.readUnsignedByte();
        inputStream.skip(2);

        try (XStreamLock lock = outputStream.lock()) {
            outputStream.writeByte(RESPONSE_CODE_SUCCESS);
            outputStream.writeByte(KEYSYMS_PER_KEYCODE);
            outputStream.writeShort(client.getSequenceNumber());
            outputStream.writeInt(count);
            outputStream.writePad(24);

            int i = firstKeycode - Keyboard.MIN_KEYCODE;
            while (count != 0) {
                outputStream.writeInt(client.xServer.keyboard.keysyms[i]);
                count--;
                i++;
            }
        }
    }

    public static void getModifierMapping(XClient client, XInputStream inputStream, XOutputStream outputStream) throws IOException, XRequestError {
        try (XStreamLock lock = outputStream.lock()) {
            outputStream.writeByte(RESPONSE_CODE_SUCCESS);
            outputStream.writeByte((byte)1);
            outputStream.writeShort(client.getSequenceNumber());
            outputStream.writeInt(2);
            outputStream.writePad(24);
            outputStream.writePad(8);
        }
    }
}
