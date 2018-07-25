import java.io.*;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class PuzzleDataFile implements PuzzleDataSrc
{
    private boolean m_dataFileLoaded = false;
    private String m_dataSrc = "";
    private FileReader m_puzzleDataFileReader = null;
    private BufferedReader m_bufferedDataFileReader = null;
    private String m_wordsToFind[] = null;
    private List<String[]> m_playField = new ArrayList<>();

    public PuzzleDataFile()
    {

    }
    public void LoadPuzzleData(String dataSrc) throws PuzzleDataIOException
    {
        m_dataSrc = dataSrc;
        try
        {
            LoadPuzzleWordList();
            LoadPuzzlePlayField();
        }
        catch(IOException ex)
        {
            throw new PuzzleDataIOException(ex.getMessage(),ex.getCause());
        }
    }

    private void LoadPuzzleWordList() throws IOException
    {
        FileReader puzzleDataFileReader = new FileReader(m_dataSrc);
        BufferedReader bufferedPuzzleDataFileReader = new BufferedReader(puzzleDataFileReader);

        String puzzleWordLine = bufferedPuzzleDataFileReader.readLine();
        m_wordsToFind = puzzleWordLine.split(",");
    }

    private void LoadPuzzlePlayField() throws IOException
    {
        FileReader puzzleDataFileReader = new FileReader(m_dataSrc);
        BufferedReader bufferedPuzzleDataFileReader = new BufferedReader(puzzleDataFileReader);

        int row = 0;
        int columns = 0;
        String puzzleWordsToFindLine = bufferedPuzzleDataFileReader.readLine();
        String puzzleMatrixRow = bufferedPuzzleDataFileReader.readLine();
        while (puzzleMatrixRow != null)
        {
            m_playField.add(puzzleMatrixRow.split(","));
            puzzleMatrixRow = bufferedPuzzleDataFileReader.readLine();
        }
    }
}
