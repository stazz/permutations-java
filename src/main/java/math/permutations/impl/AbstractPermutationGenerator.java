/*
 * Copyright (c) 2011, Stanislav Muhametsin. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package math.permutations.impl;

import java.math.BigInteger;
import java.util.Iterator;

import math.permutations.PermutationGenerator;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public abstract class AbstractPermutationGenerator<T>
    implements PermutationGenerator<T>
{
    public static interface ArrayInfo
    {
        public int getArrayLength();

        public int[] getMultiplicities();
    }

    private final BigInteger _total;

    public AbstractPermutationGenerator( ArrayInfo arrayInfo )
    {
        // Retrieved from http://en.wikipedia.org/wiki/Permutation on 26.07.2011
        // Amount of permutations of a multiset is
        //         n!
        // -----------------
        // m_1! m_2! ... m_s!
        //
        // Where n is the size of the multiset, s is a amount of distinct elements, and m_i is multiplicity of i:th distinct element.

        BigInteger upper = getFactorial( arrayInfo.getArrayLength() );
        BigInteger lower = BigInteger.ONE;
        for( int multiplicity : arrayInfo.getMultiplicities() )
        {
            BigInteger f = getFactorial( multiplicity );
            if( f != BigInteger.ONE )
            {
                lower = lower.multiply( f );
            }
        }
        if( lower != BigInteger.ONE )
        {
            this._total = upper.divide( lower );
        }
        else
        {
            this._total = upper;
        }
    }

    @Override
    public BigInteger getTotal()
    {
        return this._total;
    }

    @Override
    public Iterator<T> iterator()
    {
        return this.createIterator();
    }

    protected abstract Iterator<T> createIterator();

    private static BigInteger getFactorial( int n )
    {
        BigInteger fact = BigInteger.ONE;
        for( int i = n; i > 1; i-- )
        {
            fact = fact.multiply( BigInteger.valueOf( i ) );
        }
        return fact;
    }

}
