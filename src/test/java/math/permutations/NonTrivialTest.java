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
 * 
 * @author 2011 Stanislav Muhametsin
 */
public class NonTrivialTest extends AbstractPermutationTest
{

    @Test
    public void testPermutationsWithArrayOfSameStrings()
    {
        this.runGenericComparableTest1( new String[][]
        {
            new String[]
            {
                STRING1, STRING1, STRING1
            }
        }, STRING1, STRING1, STRING1 );
    }

    @Test
    public void testPermutationsWithArrayOfSameIntWrappers()
    {
        this.runGenericTest1( new IntWrapper[][]
        {
            new IntWrapper[]
            {
                INT_WRAPPER1, INT_WRAPPER1, INT_WRAPPER1
            }
        }, IntWrapperComparator.INSTANCE, INT_WRAPPER1, INT_WRAPPER1, INT_WRAPPER1 );
    }

    @Test
    public void testPermutationsWithArrayOfSameBytes()
    {
        this.runOptimizedTest( new byte[][]
        {
            new byte[]
            {
                BYTE1, BYTE1, BYTE1
            }
        }, new byte[]
        {
            BYTE1, BYTE1, BYTE1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfSameShorts()
    {
        this.runOptimizedTest( new short[][]
        {
            new short[]
            {
                SHORT1, SHORT1, SHORT1
            }
        }, new short[]
        {
            SHORT1, SHORT1, SHORT1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfSameInts()
    {
        this.runOptimizedTest( new int[][]
        {
            new int[]
            {
                INT1, INT1, INT1
            }
        }, new int[]
        {
            INT1, INT1, INT1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfSameLongs()
    {
        this.runOptimizedTest( new long[][]
        {
            new long[]
            {
                LONG1, LONG1, LONG1
            }
        }, new long[]
        {
            LONG1, LONG1, LONG1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfSameFloats()
    {
        this.runOptimizedTest( new float[][]
        {
            new float[]
            {
                FLOAT1, FLOAT1, FLOAT1
            }
        }, new float[]
        {
            FLOAT1, FLOAT1, FLOAT1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfSameDoubles()
    {
        this.runOptimizedTest( new double[][]
        {
            new double[]
            {
                DOUBLE1, DOUBLE1, DOUBLE1
            }
        }, new double[]
        {
            DOUBLE1, DOUBLE1, DOUBLE1
        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyStrings()
    {
        this.runGenericComparableTest1( new String[][]
        {
            new String[] {

            }
        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyIntWrappers()
    {
        this.runGenericTest1( new IntWrapper[][]
        {
            new IntWrapper[] {

            }
        }, IntWrapperComparator.INSTANCE );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyBytes()
    {
        this.runOptimizedTest( new byte[][]
        {
            new byte[] {

            }
        }, new byte[] {

        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyShorts()
    {
        this.runOptimizedTest( new short[][]
        {
            new short[] {

            }
        }, new short[] {

        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyInts()
    {
        this.runOptimizedTest( new int[][]
        {
            new int[] {

            }
        }, new int[] {

        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyLongs()
    {
        this.runOptimizedTest( new long[][]
        {
            new long[] {

            }
        }, new long[] {

        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyFloats()
    {
        this.runOptimizedTest( new float[][]
        {
            new float[] {

            }
        }, new float[] {

        } );
    }

    @Test
    public void testPermutationsWithArrayOfEmptyDoubles()
    {
        this.runOptimizedTest( new double[][]
        {
            new double[] {

            }
        }, new double[] {

        } );
    }

    @Test
    public void testPermutationsWithTrickyDoubles()
    {
        double first = -0.0d;
        double second = 0.0d;
        double third = Double.NaN;

        this.runOptimizedTest( new double[][]
        {
            new double[]
            {
                first, second, third, third
            }, new double[]
            {
                first, third, second, third,
            }, new double[]
            {
                first, third, third, second
            }, new double[]
            {
                second, first, third, third
            }, new double[]
            {
                second, third, first, third,
            }, new double[]
            {
                second, third, third, first
            }, new double[]
            {
                third, first, second, third
            }, new double[]
            {
                third, first, third, second
            }, new double[]
            {
                third, second, first, third
            }, new double[]
            {
                third, second, third, first
            }, new double[]
            {
                third, third, first, second
            }, new double[]
            {
                third, third, second, first
            }
        }, new double[]
        {
            second, third, first, third
        } );
    }
}
