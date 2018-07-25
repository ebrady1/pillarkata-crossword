import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleDataFileTest
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
}