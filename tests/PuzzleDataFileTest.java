import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleDataFileTests
{
    @Test
    void LoadPuzzleDataSrcTest()
    {
        try
        {
            PuzzleDataSrc dataSrc = new PuzzleDataFile();
            dataSrc.LoadPuzzleData("tests/ValidPuzzleTest.txt");
        }
        catch (Exception exception)
        {
            assertTrue(false);
        }

    }

    @Test
    void LoadPuzzleDataWithInvalidWordInList()
    {
        boolean success = false;
        try
        {
            PuzzleDataSrc dataSrc = new PuzzleDataFile();
            dataSrc.LoadPuzzleData("tests/InvalidPuzzleTest_WordList.txt");
            dataSrc.ValidatePuzzleData();
        } catch (PuzzleDataWordListException e)
        {
            success = true;
        } catch (Exception e)
        {
            success = false;
        }

        assertTrue(success);

    }
}