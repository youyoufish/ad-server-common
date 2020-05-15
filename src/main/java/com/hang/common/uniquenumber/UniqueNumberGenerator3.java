package com.hang.common.uniquenumber;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import com.hang.common.web.url.HexEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate unique number.
 * <p>
 * This generator will generate unique number based on machine id, MAC address,
 * {@link System#currentTimeMillis()} and sequence no.
 * </p>
 * <p>
 * ATTENTION: This class require access to {@link NetworkInterface}!!!
 * </p>
 *
 * @author quangang.sheng@adchina.com
 * @version 2015/6/1 14:03
 * @since 0.1.2
 */
public final class UniqueNumberGenerator3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(UniqueNumberGenerator3.class);

    private static final char[][] HEX_DIGITS_TABLE;

    private static final long FIRST_FOUND_MAC;

    private static final UniqueNumberGenerator3[] GENERATORS;

    static {
        final char[] digits = "0123456789abcdef".toCharArray();
        HEX_DIGITS_TABLE = new char[Byte.MAX_VALUE - Byte.MIN_VALUE + 1][2];
        // map signed bytes to unsigned bytes.
        for (int i = 0, n = Byte.MAX_VALUE - Byte.MIN_VALUE + 1; i < n; ++i) {
            HEX_DIGITS_TABLE[i][0] = digits[(i >> 4) & 0xf];
            HEX_DIGITS_TABLE[i][1] = digits[i & 0xf];
        }
        byte[] macAddress = null;
        // fix jdk on window 8 bug.
        UUID.randomUUID();
        try {
            for (NetworkInterface iff : Collections.list(NetworkInterface
                    .getNetworkInterfaces())) {
                macAddress = iff.getHardwareAddress();
                if (macAddress != null && macAddress.length == 6) {
                    if (LOGGER.isDebugEnabled())
                        LOGGER.debug("found network interface = "
                                + iff.getName() + ", MAC = "
                                + java.util.Arrays.toString(macAddress));
                    break;
                }
                macAddress = null;
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        if (macAddress == null)
            throw new RuntimeException("Can't get a valid MAC address");
        long first = 0;
        first |= (((long) macAddress[0]) & 0xffL) << 56;
        first |= (((long) macAddress[1]) & 0xffL) << 48;
        first |= (((long) macAddress[2]) & 0xffL) << 40;
        first |= (((long) macAddress[3]) & 0xffL) << 32;
        first |= (((long) macAddress[4]) & 0xffL) << 24;
        first |= (((long) macAddress[5]) & 0xffL) << 16;
        FIRST_FOUND_MAC = first;

        GENERATORS = new UniqueNumberGenerator3[(int) Short.MAX_VALUE
                - (int) Short.MIN_VALUE + 1];
        for (int i = 0, n = GENERATORS.length; i < n; ++i)
            GENERATORS[i] = new UniqueNumberGenerator3(i);
    }

    private final short _M_machineId;
    private final char[] _M_firstChars;
    private final AtomicInteger _M_counter;

    private UniqueNumberGenerator3(final int machineId) {
        _M_machineId = (short) (machineId & 0xffff);
        _M_counter = new AtomicInteger(0);
        _M_firstChars = new char[16];
        fillDigits(_M_firstChars, 0, FIRST_FOUND_MAC | _M_machineId);
    }

    private void fillDigits(final char[] buf, int offset, final long value) {
        char[] bytes = null;
        int index = offset - 1;

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 56) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 48) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 40) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 32) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 24) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 16) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 8) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];

        bytes = HEX_DIGITS_TABLE[(int) ((value >> 0) & 0xff)];
        buf[++index] = bytes[0];
        buf[++index] = bytes[1];
    }

    /**
     * The machine id associated with this {@link UniqueNumberGenerator3}.
     *
     * @return The machine id.
     */
    public int getMachineId() {
        return _M_machineId;
    }

    /**
     * Get an unique id.
     *
     * @return The unique id.
     */
    public String next() {
        return makeString(System.currentTimeMillis(),
                ((long) _M_counter.incrementAndGet()) & 0xffffffffL);
    }

    /**
     * Make a hex string combined by mostSignificantBits and
     * leastSignificanBits.
     *
     * @param mostSignificantBits
     *            As it says, these bits comes before any other bits.
     * @param leastSignificanBits
     *            As it says, these bits comes after any other bits.
     * @return The hex string.
     */
    private String makeString(long mostSignificantBits, long leastSignificanBits) {
        char[] cb = new char[48];
        System.arraycopy(_M_firstChars, 0, cb, 0, 16);
        fillDigits(cb, 16, mostSignificantBits);
        fillDigits(cb, 32, leastSignificanBits);
        return new String(cb);
    }

    /**
     * The decrypted unique id which is generated by
     * {@link UniqueNumberGenerator3}.
     * <p>
     * It contains all the fields, which are <b><code>MAC address</code></b>,
     * <b><code>machine id</code></b>, <b> <code>timestamp</code></b>(in
     * milliseconds) and <b><code>sequence
     * no</code></b>.
     * </p>
     *
     * @author quangang.sheng@adchina.com
     * @since 0.1.2
     */
    public static final class DecryptedUid {

        private static final long[][] BYTE_TABLE;

        static {
            final char[] LOWER_DIGITS = "0123456789abcdef".toCharArray();
            final char[] UPPER_DIGITS = "0123456789ABCDEF".toCharArray();
            BYTE_TABLE = new long[0xff][0xff];
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    BYTE_TABLE[LOWER_DIGITS[i]][LOWER_DIGITS[j]] = i << 4 | j;
                }
            }
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 16; ++j) {
                    BYTE_TABLE[UPPER_DIGITS[i]][UPPER_DIGITS[j]] = i << 4 | j;
                }
            }
        }

        private final long _M_macAddress;
        private final int _M_machineId;
        private final Date _M_timestamp;
        private final int _M_sequence;

        private DecryptedUid(final CharSequence uid) {
            if (uid == null || uid.length() != 48)
                throw new IllegalArgumentException();
            long firstBits = extractLong(uid, 0);
            long highBits = extractLong(uid, 16);
            long lowBits = extractLong(uid, 32);

            _M_macAddress = firstBits >>> 16;
            _M_machineId = (int) (firstBits & 0xffffL);
            _M_timestamp = new Date(highBits);
            _M_sequence = (int) (lowBits & 0xffffffffL);
        }

        private long extractLong(final CharSequence seq, final int offset) {
            long value = 0L;
            value |= BYTE_TABLE[seq.charAt(offset + 0)][seq.charAt(offset + 1)] << 56;
            value |= BYTE_TABLE[seq.charAt(offset + 2)][seq.charAt(offset + 3)] << 48;
            value |= BYTE_TABLE[seq.charAt(offset + 4)][seq.charAt(offset + 5)] << 40;
            value |= BYTE_TABLE[seq.charAt(offset + 6)][seq.charAt(offset + 7)] << 32;
            value |= BYTE_TABLE[seq.charAt(offset + 8)][seq.charAt(offset + 9)] << 24;
            value |= BYTE_TABLE[seq.charAt(offset + 10)][seq
                    .charAt(offset + 11)] << 16;
            value |= BYTE_TABLE[seq.charAt(offset + 12)][seq
                    .charAt(offset + 13)] << 8;
            value |= BYTE_TABLE[seq.charAt(offset + 14)][seq
                    .charAt(offset + 15)] << 0;
            return value;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return "{MAC = "
                    + HexEncoding.BIG_ENDIAN.toHexString(getMACAddressBytes())
                    + ", machineId = " + _M_machineId + ", timestamp = "
                    + _M_timestamp + ", sequence = " + _M_sequence + "}";
        }

        /**
         * The MAC address in bytes, which is always compound of 48bits.
         *
         * @return The 48-bit length byte array of MAC address.
         */
        public byte[] getMACAddressBytes() {
            return ByteBuffer.allocate(16).order(ByteOrder.BIG_ENDIAN)
                    .putLong(_M_macAddress).array();
        }

        /**
         * The MAC address in 64-bit long format, in which only the last 48 bits
         * is used.
         *
         * @return The MAC address 64bit integer.
         */
        public long getMACAddress() {
            return _M_macAddress;
        }

        /**
         * The machine id from which the unique number is generated.
         *
         * @return The machine id of the {@link UniqueNumberGenerator3}.
         */
        public int getMachineId() {
            return _M_machineId;
        }

        /**
         * The creation timestamp of this unique number.
         *
         * @return The creation timestamp.
         */
        public Date getTimestamp() {
            return _M_timestamp;
        }

        /**
         * The sequence no.
         * <p>
         * It may be negative due to overflow or underflow.
         * </p>
         *
         * @return The sequence no.
         */
        public int getSequenceNo() {
            return _M_sequence;
        }
    }

    /**
     * Decrypt the specified uid into their original parts.
     *
     * @param uid
     *            The uid.
     * @return The decrypted uid object.
     */
    public static DecryptedUid decrypt(final CharSequence uid) {
        return new DecryptedUid(uid);
    }

    /**
     * Initialize an {@link UniqueNumberGenerator3} instance with the specified
     * machine id.
     *
     * @param machineId
     *            The machine id is used in business logical only. Its value
     *            range is [0, 255]. It is used as unsigned 16-bit integer.
     * @return The required {@link UniqueNumberGenerator3} instance initialized
     *         with the specified machine id.
     */
    public static UniqueNumberGenerator3 getInstance(final int machineId) {
        return GENERATORS[machineId & 0xffff];
    }
}