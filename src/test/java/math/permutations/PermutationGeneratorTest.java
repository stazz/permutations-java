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

import org.junit.Test;

/**
 * TODO test actual permutation sequences. TODO test with arrays with multiple same values.
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class PermutationGeneratorTest extends AbstractPermutationTest
{

    @Test
    public void genericComparableGeneratorTest1()
    {
        this.runGenericComparableTest1( STRING_PERMUTATIONS, STRING_ARRAY );
    }

    @Test
    public void genericComparableGeneratorTest2()
    {
        this.runGenericComparableTest2( STRING_PERMUTATIONS, String.class, STRING_ARRAY );
    }

    @Test
    public void genericComparableGeneratorTest3()
    {
        this.runGenericComparableTest3( STRING_PERMUTATIONS, String.class, STRING_ARRAY );
    }

    @Test
    public void genericGeneratorTest1()
    {
        this.runGenericTest1( WRAPPER_PERMUTATIONS, IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY );
    }

    @Test
    public void genericGeneratorTest2()
    {
        this.runGenericTest2( WRAPPER_PERMUTATIONS, IntWrapper.class, IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY );
    }

    @Test
    public void genericGeneratorTest3()
    {
        this.runGenericTest3( WRAPPER_PERMUTATIONS, IntWrapper.class, IntWrapperComparator.INSTANCE, INT_WRAPPER_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestByte()
    {
        this.runOptimizedTest( BYTE_PERMUTATIONS, BYTE_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestShort()
    {
        this.runOptimizedTest( SHORT_PERMUTATIONS, SHORT_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestInt()
    {
        this.runOptimizedTest( INT_PERMUTATIONS, INT_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestLong()
    {
        this.runOptimizedTest( LONG_PERMUTATIONS, LONG_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestFloat()
    {
        this.runOptimizedTest( FLOAT_PERMUTATIONS, FLOAT_ARRAY );
    }

    @Test
    public void optimizedGeneratorTestDouble()
    {
        this.runOptimizedTest( DOUBLE_PERMUTATIONS, DOUBLE_ARRAY );
    }

}
