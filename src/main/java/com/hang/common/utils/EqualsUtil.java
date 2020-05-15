package com.hang.common.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * the function of equals
 * 
 * @author hang.yu
 * @version 2013-07-28
 */
public final class EqualsUtil
{
    public static <TKey, TValue> boolean equals(Map<TKey, TValue> left, Map<TKey, TValue> right)
    {
        if (left == null && right == null)
        {
            return true;
        }

        if (left == null && right != null || left != null && right == null)
        {
            return false;
        }

        if (left.size() != right.size())
        {
            return false;
        }
        
        Iterator<Map.Entry<TKey, TValue>> iter = left.entrySet().iterator();
		while (iter.hasNext())
		{
			Map.Entry<TKey, TValue> entry = iter.next();
			TKey key = entry.getKey();
			TValue value = entry.getValue();
			
			if (!right.containsKey(key))
            {
                return false;
            }
			
            TValue rightValue = right.get(key);
            if (rightValue instanceof byte[])
            {
            	byte[] leftArray = (byte[])value;
            	byte[] rightArray = (byte[])rightValue;
                if (!EqualsUtil.equals(leftArray, rightArray))
                {
                    return false;
                }
            }
            else
            {
                // General type
                if (value != null && !value.equals(rightValue)
                || value == null && rightValue != null)
                {
                    return false;
                }
            }
		}
		
        return true;
    }

    public static <TKey> boolean equals(Set<TKey> left, Set<TKey> right)
    {
        if (left == null && right == null)
        {
            return true;
        }

        if (left == null && right != null || left != null && right == null)
        {
            return false;
        }

        if (left.size() != right.size())
        {
            return false;
        }

        for (TKey key : left)
        {
            if (!right.contains(key))
            {
                return false;
            }
        }
        return true;
    }

    public static <TKey> boolean equals(List<TKey> left, List<TKey> right)
    {
        if (left == null && right == null)
        {
            return true;
        }

        if (left == null && right != null || left != null && right == null)
        {
            return false;
        }

        if (left.size() != right.size())
        {
            return false;
        }

        for (int i = 0; i < left.size(); ++i)
        {
            if (!left.get(i).equals(right.get(i)))
            {
                return false;
            }
        }

        return true;
    }
    public static boolean equals(byte[] left, byte[] right)
    {
        if (left == null && right == null)
        {
            return true;
        }

        if (left == null && right != null || left != null && right == null)
        {
            return false;
        }

        if (left.length != right.length)
        {
            return false;
        }

        for (int i = 0; i < left.length; ++i)
        {
            if (left[i] != right[i])
            {
                return false;
            }
        }

        return true;
    }

    public static <TKey> boolean equals(TKey[] left, TKey[] right)
    {
        if (left == null && right == null)
        {
            return true;
        }

        if (left == null && right != null || left != null && right == null)
        {
            return false;
        }

        if (left.length != right.length)
        {
            return false;
        }

        for (int i = 0; i < left.length; ++i)
        {
            if (!left[i].equals(right[i]))
            {
                return false;
            }
        }

        return true;
    }

    public static <TKey> boolean equals(TKey left, TKey right){
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }
        if (!left.equals(right)) {
            return false;
        }
        return true;
    }
}