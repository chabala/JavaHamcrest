package org.hamcrest.collection;

import static org.hamcrest.test.MatcherAssertions.*;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.test.AbstractMatcherTest;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class IsArrayContainingInAnyOrderTest extends AbstractMatcherTest {

    @SuppressWarnings("unchecked")
    @Override
    protected Matcher<?> createMatcher() {
        return arrayContainingInAnyOrder(equalTo(1), equalTo(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testHasAReadableDescription() {
        assertDescription("[<1>, <2>] in any order", arrayContainingInAnyOrder(equalTo(1), equalTo(2)));
        assertDescription("[<1>, <2>] in any order", arrayContainingInAnyOrder(1, 2));
    }

    @Test
    public void testMatchesItemsInAnyOrder() {
      assertMatches("in order", arrayContainingInAnyOrder(1, 2, 3), new Integer[] {1, 2, 3});
      assertMatches("out of order", arrayContainingInAnyOrder(1, 2, 3), new Integer[] {3, 2, 1});
      assertMatches("single", arrayContainingInAnyOrder(1), new Integer[] {1});
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testAppliesMatchersInAnyOrder() {
      assertMatches("in order", arrayContainingInAnyOrder(equalTo(1), equalTo(2), equalTo(3)), new Integer[] {1, 2, 3});
      assertMatches("out of order", arrayContainingInAnyOrder(equalTo(1), equalTo(2), equalTo(3)), new Integer[] {3, 2, 1});
      assertMatches("single", arrayContainingInAnyOrder(equalTo(1)), new Integer[] {1});
    }

    @Test
    public void testMismatchesItemsInAnyOrder() {
      Matcher<Integer[]> matcher = arrayContainingInAnyOrder(1, 2, 3);
      assertMismatchDescription("was null", matcher, null);
      assertMismatchDescription("no item matches: <1>, <2>, <3> in []", matcher, new Integer[] {});
      assertMismatchDescription("no item matches: <2>, <3> in [<1>]", matcher, new Integer[] {1});
      assertMismatchDescription("not matched: <4>", matcher, new Integer[] {4,3,2,1});
    }

}
