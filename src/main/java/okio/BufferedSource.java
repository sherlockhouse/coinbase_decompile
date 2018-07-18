package okio;

import java.io.IOException;
import java.io.InputStream;

public interface BufferedSource extends Source {
    Buffer buffer();

    boolean exhausted() throws IOException;

    long indexOf(byte b) throws IOException;

    InputStream inputStream();

    long readAll(Sink sink) throws IOException;

    byte readByte() throws IOException;

    byte[] readByteArray() throws IOException;

    byte[] readByteArray(long j) throws IOException;

    ByteString readByteString(long j) throws IOException;

    long readDecimalLong() throws IOException;

    long readHexadecimalUnsignedLong() throws IOException;

    int readInt() throws IOException;

    int readIntLe() throws IOException;

    short readShort() throws IOException;

    short readShortLe() throws IOException;

    String readUtf8LineStrict() throws IOException;

    boolean request(long j) throws IOException;

    void require(long j) throws IOException;

    void skip(long j) throws IOException;
}
