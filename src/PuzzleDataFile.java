import java.io.*;
import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PuzzleDataFile implements PuzzleDataSrc
{
    private boolean m_dataFileLoaded = false;
    private String m_dataSrc = "";
    private FileReader m_puzzleDataFileReader = null;
    private BufferedReader m_bufferedDataFileReader = null;
    private String m_wordsToFind[] = null;
    private ArrayList<String[]> m_playField = new ArrayList<>();

    public PuzzleDataFile()
    {

    }

    public void LoadPuzzleData(String dataSrc) throws PuzzleDataException
    {
        m_dataSrc = dataSrc;
        try
        {
            LoadPuzzleWordList();
            LoadPuzzlePlayField();
        } catch (NullPointerException | IOException ex)
        {
            throw new PuzzleDataIOException(ex.getMessage(), ex.getCause());
        }
    }

    public void ValidatePuzzleData() throws PuzzleDataException
    {
        ValidatePuzzleDataWordList();
        ValidatePuzzleDataPlayField();
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

        String puzzleWordsToFindLine = bufferedPuzzleDataFileReader.readLine();
        String puzzleMatrixRow = bufferedPuzzleDataFileReader.readLine();
        while (puzzleMatrixRow != null)
        {
            m_playField.add(puzzleMatrixRow.split(","));
            puzzleMatrixRow = bufferedPuzzleDataFileReader.readLine();
        }
    }

    private void ValidatePuzzleDataWordList() throws PuzzleDataWordListException
    {
        boolean validList = false;
        if (m_wordsToFind.length > 0)
        {
            for (String word : m_wordsToFind)
            {
                if (word.length() >= 2)
                {
                    IntStream wordChars = word.chars();
                    validList = wordChars.allMatch(Character::isLetter);
                }
                if (!validList)
                {
                    break;
                }
            }
        }

        if (!validList)
        {
            throw new PuzzleDataWordListException();
        }
    }

    private void ValidatePuzzleDataPlayField() throws PuzzleDataPlayfieldException
    {
        Integer maxWordSize = PuzzleDataMaxWordSize();
        if (m_playField != null)
        {
            Integer rows = m_playField.size();
            if (rows < maxWordSize)
            {
                throw new PuzzleDataPlayfieldException();
            }
            PuzzleDataPlayfieldRowValidate();
        }
    }

    private Integer PuzzleDataMaxWordSize()
    {
        boolean validPlayfield = false;
        Integer maxWordSize = 0;
        for (String testWord : m_wordsToFind)
        {
            Integer testWordSize = testWord.length();
            maxWordSize = (maxWordSize < testWordSize) ? testWordSize : maxWordSize;
        }

        return maxWordSize;

    }

    private void PuzzleDataPlayfieldRowValidate() throws PuzzleDataPlayfieldException
    {
        Integer rows = m_playField.size();
        for (String[] rowChars : m_playField)
        {
            if (rowChars.length != rows)
            {
                throw new PuzzleDataPlayfieldException();
            }

            for (String colChar : rowChars)
            {
                if ((colChar.length() != 1) &&
                        (!colChar.chars().allMatch(Character::isLetter)))
                {
                    throw new PuzzleDataPlayfieldException();
                }
            }
        }
    }
}

