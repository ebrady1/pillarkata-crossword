import java.io.IOException;

public interface PuzzleDataSrc
{
     void LoadPuzzleData(String puzzleDataSrc) throws PuzzleDataException;
     void ValidatePuzzleData() throws PuzzleDataException;
}
