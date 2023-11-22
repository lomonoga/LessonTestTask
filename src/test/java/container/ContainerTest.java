package container;

import example.container.Container;
import example.container.Item;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ContainerTest {
    private Container testContainer;
    private Item testFirstEmptyItem;
    private Item testSecondEmpthyItem;

    @Before
    public void createNoteLogic() {
        testContainer = new Container();
        testFirstEmptyItem = Mockito.mock(Item.class);
        testSecondEmpthyItem = Mockito.mock(Item.class);
    }

    @After
    public void recreateTestContainer() {
        testContainer = new Container();
    }

    @Test
    public void addOneItemTest() {
        var resultAdding = testContainer.add(testFirstEmptyItem);
        Assert.assertTrue(resultAdding);

        var sizeContainer = testContainer.size();
        Assert.assertEquals(sizeContainer, 1);
    }

    @Test
    public void addTwoItemTest() {
        var firstResultAdding = testContainer.add(testFirstEmptyItem);
        var secondResultAdding = testContainer.add(testSecondEmpthyItem);
        Assert.assertTrue(firstResultAdding);
        Assert.assertTrue(secondResultAdding);

        var sizeContainer = testContainer.size();
        Assert.assertEquals(sizeContainer, 2);
    }

    @Test
    public void deleteOneItemTest() {
       testContainer.add(testFirstEmptyItem);
        var resultDelete = testContainer.remove(testFirstEmptyItem);
        Assert.assertTrue(resultDelete);

       var sizeContainer = testContainer.size();
       Assert.assertEquals(sizeContainer, 0);
    }

    @Test
    public void deleteTwoItemTest() {
        testContainer.add(testFirstEmptyItem);
        testContainer.add(testSecondEmpthyItem);
        var firstResultDelete = testContainer.remove(testFirstEmptyItem);
        var secondResultDelete = testContainer.remove(testSecondEmpthyItem);
        Assert.assertTrue(firstResultDelete);
        Assert.assertTrue(secondResultDelete);

        var sizeContainer = testContainer.size();
        Assert.assertEquals(sizeContainer, 0);
    }

    @Test
    public void deleteOneItemFromTwoTest() {
        testContainer.add(testFirstEmptyItem);
        testContainer.add(testSecondEmpthyItem);
        var resultDelete = testContainer.remove(testSecondEmpthyItem);
        Assert.assertTrue(resultDelete);

        var sizeContainer = testContainer.size();
        Assert.assertEquals(sizeContainer, 1);
    }

    @Test
    public void deleteNonExistsItem() {
        testContainer.add(testFirstEmptyItem);
        var resultDelete = testContainer.remove(testSecondEmpthyItem);
        Assert.assertFalse(resultDelete);

        var sizeContainer = testContainer.size();
        Assert.assertEquals(sizeContainer, 1);
    }
}
