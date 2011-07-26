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

import math.permutations.impl.BytePermutationGenerator;
import math.permutations.impl.DoublePermutationGenerator;
import math.permutations.impl.FloatPermutationGenerator;
import math.permutations.impl.GenericComparablePermutationGenerator;
import math.permutations.impl.GenericPermutationGenerator;
import math.permutations.impl.IntPermutationGenerator;
import math.permutations.impl.LongPermutationGenerator;
import math.permutations.impl.ShortPermutationGenerator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class PermutationGeneratorProviderTest extends AbstractPermutationTest
{
    @Test
    public void genericComparableGeneratorTest1()
    {
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class, this.createGenericComparableGenerator1( STRING_ARRAY )
                .getClass() );
    }

    @Test
    public void genericComparableGeneratorTest2()
    {
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class,
            this.createGenericComparableGenerator2( String.class, STRING_ARRAY ).getClass() );
    }

    @Test
    public void genericComparableGeneratorTest3()
    {
        Assert.assertEquals( "Permutation generator must be of generic type",
            GenericComparablePermutationGenerator.class,
            this.createGenericComparableGenerator3( String.class, STRING_ARRAY ).getClass() );
    }

    @Test
    public void genericGeneratorTest1()
    {
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class, this
            .createGenericGenerator1( IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY ).getClass() );
    }

    @Test
    public void genericGeneratorTest2()
    {
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class, this
            .createGenericGenerator2( IntWrapper.class, IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY ).getClass() );
    }

    @Test
    public void genericGeneratorTest3()
    {
        Assert.assertEquals( "Permutation generator must be of generic type", GenericPermutationGenerator.class, this
            .createGenericGenerator3( IntWrapper.class, IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestByte()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type", BytePermutationGenerator.class,
            this.createOptimizedGenerator( BYTE_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestShort()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            ShortPermutationGenerator.class, this.createOptimizedGenerator( SHORT_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestInt()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type", IntPermutationGenerator.class,
            this.createOptimizedGenerator( INT_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestLong()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type", LongPermutationGenerator.class,
            this.createOptimizedGenerator( LONG_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestFloat()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            FloatPermutationGenerator.class, this.createOptimizedGenerator( FLOAT_ARRAY ).getClass() );
    }

    @Test
    public void optimizedGeneratorTestDouble()
    {
        Assert.assertEquals( "Permutation generator must be of correct optimized type",
            DoublePermutationGenerator.class, this.createOptimizedGenerator( DOUBLE_ARRAY ).getClass() );
    }

}
