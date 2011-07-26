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

package math.permutations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import math.permutations.impl.BytePermutationGenerator;
import math.permutations.impl.DoublePermutationGenerator;
import math.permutations.impl.FloatPermutationGenerator;
import math.permutations.impl.GenericComparablePermutationGenerator;
import math.permutations.impl.GenericPermutationGenerator;
import math.permutations.impl.IntegerPermutationGenerator;
import math.permutations.impl.LongPermutationGenerator;
import math.permutations.impl.ShortPermutationGenerator;

import org.junit.Assert;
import org.junit.Test;

/**
 * TODO test actual permutation sequences.
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class PermutationGeneratorTest
{

    public static class IntWrapper
    {
        private final Integer _int;

        public IntWrapper( Integer theInt )
        {
            this._int = theInt;
        }

        public Integer getInt()
        {
            return this._int;
        }
    }

    public static class IntWrapperComparator
        implements Comparator<IntWrapper>
    {
        @Override
        public int compare( IntWrapper o1, IntWrapper o2 )
        {
            return o1.getInt().compareTo( o2.getInt() );
        }
    }

    public static final IntWrapperComparator WRAPPER_COMPARATOR = new IntWrapperComparator();

    public static final String STRING1 = "item1";
    public static final String STRING2 = "item2";
    public static final String STRING3 = "item3";

    public static final IntWrapper INT_WRAPPER1 = new IntWrapper( 1 );
    public static final IntWrapper INT_WRAPPER2 = new IntWrapper( 2 );
    public static final IntWrapper INT_WRAPPER3 = new IntWrapper( 3 );

    public static final byte BYTE1 = 1;
    public static final byte BYTE2 = 2;
    public static final byte BYTE3 = 3;

    public static final int INT1 = 1;
    public static final int INT2 = 2;
    public static final int INT3 = 3;

    public static final short SHORT1 = 1;
    public static final short SHORT2 = 2;
    public static final short SHORT3 = 3;

    public static final long LONG1 = 1L;
    public static final long LONG2 = 2L;
    public static final long LONG3 = 3L;

    public static final double DOUBLE1 = 1.0;
    public static final double DOUBLE2 = 2.0;
    public static final double DOUBLE3 = 3.0;

    public static final float FLOAT1 = 1.0f;
    public static final float FLOAT2 = 2.0f;
    public static final float FLOAT3 = 3.0f;

    @Test
    public void genericComparableGeneratorTest1()
    {
        PermutationGenerator<String[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            String.class, STRING1, STRING2, STRING3 );
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class, stringGen.getClass() );
    }

    @Test
    public void genericComparableGeneratorTest2()
    {
        PermutationGenerator<String[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            String.class, Arrays.asList( STRING1, STRING2, STRING3 ) );
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class, stringGen.getClass() );
    }

    @Test
    public void genericComparableGeneratorTest3()
    {
        PermutationGenerator<String[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            String.class, new Iterable<String>()
            {
                @Override
                public Iterator<String> iterator()
                {
                    return Arrays.asList( STRING1, STRING2, STRING3 ).iterator();
                }
            } );
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class, stringGen.getClass() );
    }

    @Test
    public void genericGeneratorTest1()
    {
        PermutationGenerator<IntWrapper[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            IntWrapper.class, WRAPPER_COMPARATOR, INT_WRAPPER1, INT_WRAPPER2, INT_WRAPPER3 );
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class,
            stringGen.getClass() );
    }

    @Test
    public void genericGeneratorTest2()
    {
        PermutationGenerator<IntWrapper[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            IntWrapper.class, WRAPPER_COMPARATOR, Arrays.asList( INT_WRAPPER1, INT_WRAPPER2, INT_WRAPPER3 ) );
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class,
            stringGen.getClass() );
    }

    @Test
    public void genericGeneratorTest3()
    {
        PermutationGenerator<IntWrapper[]> stringGen = PermutationGeneratorProvider.createGenericPermutationGenerator(
            IntWrapper.class, WRAPPER_COMPARATOR, new Iterable<IntWrapper>()
            {
                @Override
                public Iterator<IntWrapper> iterator()
                {
                    return Arrays.asList( INT_WRAPPER1, INT_WRAPPER2, INT_WRAPPER3 ).iterator();
                }
            } );
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class,
            stringGen.getClass() );
    }

    @Test
    public void optimizedGeneratorTestByte()
    {
        PermutationGenerator<byte[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new byte[]
        {
            BYTE1, BYTE2, BYTE3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type", BytePermutationGenerator.class,
            generator.getClass() );
    }

    @Test
    public void optimizedGeneratorTestShort()
    {
        PermutationGenerator<short[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new short[]
        {
            SHORT1, SHORT2, SHORT3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            ShortPermutationGenerator.class, generator.getClass() );
    }

    @Test
    public void optimizedGeneratorTestInt()
    {
        PermutationGenerator<int[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new int[]
        {
            INT1, INT2, INT3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            IntegerPermutationGenerator.class, generator.getClass() );
    }

    @Test
    public void optimizedGeneratorTestLong()
    {
        PermutationGenerator<long[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new long[]
        {
            LONG1, LONG2, LONG3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type", LongPermutationGenerator.class,
            generator.getClass() );
    }

    @Test
    public void optimizedGeneratorTestFloat()
    {
        PermutationGenerator<float[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new float[]
        {
            FLOAT1, FLOAT2, FLOAT3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            FloatPermutationGenerator.class, generator.getClass() );
    }

    @Test
    public void optimizedGeneratorTestDouble()
    {
        PermutationGenerator<double[]> generator = PermutationGeneratorProvider.createOptimizedGenerator( new double[]
        {
            DOUBLE1, DOUBLE2, DOUBLE3
        } );
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            DoublePermutationGenerator.class, generator.getClass() );
    }

}
