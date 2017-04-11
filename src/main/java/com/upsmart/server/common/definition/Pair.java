package com.upsmart.server.common.definition;

public class Pair<K, V>
{
    public K name;
    public V value;

    public Pair()
    {
    }

    public Pair(K name, V value)
    {
        this.name = name;
        this.value = value;
    }

    @SuppressWarnings("unchecked")
    @Override
	public boolean equals(Object obj)
	{
        if(obj == this)
            return true;
		return (obj instanceof Pair) ? equals((Pair<K, V>)obj) : super.equals(obj);
	}

    public boolean equals(Pair<K, V> other) {
        return this.name.equals(other.name) && this.value.equals(other.value);
    }
}
