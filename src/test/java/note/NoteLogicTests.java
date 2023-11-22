package note;

import example.note.NoteLogic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NoteLogicTests {
    private NoteLogic noteLogic;
    private String handleMassage;

    @Before
    public void createNoteLogic() {
        noteLogic = new NoteLogic();
        handleMassage = "HomoSap";
    }

    @Test
    public void addMessageTest() {
        var request = noteLogic.handleMessage("/add " + handleMassage);
        Assert.assertEquals(request, handleMassage + " added!");
    }

    @Test
    public void editMessageTest() {
        noteLogic.handleMessage("/add " + handleMassage);
        var request = noteLogic.handleMessage("/edit " + handleMassage);
        Assert.assertEquals(request, handleMassage + " added!");
    }

    @Test
    public void deleteMessageTest() {
        noteLogic.handleMessage("/add " + handleMassage);
        var request = noteLogic.handleMessage("/del " + handleMassage);
        Assert.assertEquals(request, handleMassage + " deleted!");
    }

    @Test
    public void getMessageTest() {
        var request = noteLogic.handleMessage("/notes " + handleMassage);
        Assert.assertEquals(request, "Your note: " + handleMassage);
    }


    @Test
    public void unknownMessageTest() {
        var request = noteLogic.handleMessage("/addRange " + handleMassage);
        Assert.assertEquals(request, "Unknown command");
    }
}
