package prj00DB;

public interface IO {

	public void readFromTxtFile (String fileName) throws Exception;

	public void writeToBinaryFile (String fileName) throws Exception;

	public void readFromBinaryFile (String fileName) throws Exception;
}
