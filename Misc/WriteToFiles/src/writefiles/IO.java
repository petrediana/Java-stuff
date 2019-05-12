package writefiles;

public interface IO {

	public void readFromTxt(String fileName) throws Exception;

	public void writeToBinary(String fileName) throws Exception;

	public void readFromBinary(String fileName) throws Exception;
}
