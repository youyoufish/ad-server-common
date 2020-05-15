package com.hang.common.uniquenumber;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generate unique id.
 * 
 * from high to low. machine id (9bits), timestamp (43bits), seq(10bits) only
 * use the first 62bits, leave the highest bit zero, and the 63rd bit one.
 * 
 * This can only works fine under QPS 1.024e6
 * 
 * @author quangang.sheng@adchina.com
 */
public class UniqueNumberGenerator2
{
	private static final long MACHINEID_MASK = 0x1ffL; // 9bits
	private static final long TIMESTAMP_MASK = 0x7ffffffffffL; // 43bits
	private static final long SEQUENCE_MASK = 0x3ffL; // 10bits

	private static final long INITIAL = 0x1L << 62;

	private static ConcurrentMap<Integer, UniqueNumberGenerator2> uniqueNumberGenerators = new ConcurrentHashMap<Integer, UniqueNumberGenerator2>();

	public static UniqueNumberGenerator2 getInstance(int machineId)
	{
		Integer key = Integer.valueOf(machineId);
		UniqueNumberGenerator2 rng = uniqueNumberGenerators.get(key);		
		if(rng == null)
		{
			rng = new UniqueNumberGenerator2(machineId);	
			uniqueNumberGenerators.put(key, rng);
		}
		return rng;
	}

	public static final class OriginalParts
	{
		int machineId;
		Date timestamp;
		int sequence;

		public OriginalParts(long id)
		{
			machineId = (int) ((id >> (43 + 10)) & MACHINEID_MASK);
			timestamp = new Date((id >> 10) & TIMESTAMP_MASK);
			sequence = (int) (id & SEQUENCE_MASK);
		}

		public int getMachineId()
		{
			return machineId;
		}

		public int getSequence()
		{
			return sequence;
		}

		public Date getTimestamp()
		{
			return timestamp;
		}

		@Override
		public String toString()
		{
			StringBuilder sb = new StringBuilder();
			sb.append("{machineId = ").append(machineId)
					.append(", timestamp = ").append(timestamp)
					.append(", sequence = ").append(sequence).append("}");
			return sb.toString();
		}
	}

	long machineId;
	long machineIdPart;
	AtomicInteger seq;

	public UniqueNumberGenerator2(int machineId)
	{
		if (machineId < 0 || machineId > 511)
			throw new IllegalArgumentException(
					"machineId should be in range of [0, 511]");
		this.machineId = machineId;
		this.machineIdPart = ((long) machineId & MACHINEID_MASK) << (43 + 10);
		seq = new AtomicInteger(-1);
	}

	public long next()
	{
		long seqPart = seq.incrementAndGet() & SEQUENCE_MASK;
		long timestampPart = (System.currentTimeMillis() & TIMESTAMP_MASK) << 10;
		return INITIAL | machineIdPart | timestampPart | seqPart;
	}

	public static OriginalParts decrypt(long uid)
	{
		return new OriginalParts(uid);
	}
}
