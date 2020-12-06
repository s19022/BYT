public class CsvWriter {
	public CsvWriter() {
	}

	public void write(String[][] lines) {
		for (String[] line : lines){
			writeLine(line);
		}
	}

	private void writeLine(String[] fields) {
		if (fields.length == 0){
			System.out.println();
			return;
		}
		writeField(fields[0]);

		for (int i = 1; i < fields.length; i++) {
			System.out.print(",");
			writeField(fields[i]);
		}
		System.out.println();

	}

	private void writeField(String field) {
		if (field.indexOf(',') != -1 || field.indexOf('\"') != -1)
			writeQuoted(field);
		else
			System.out.print(field);
	}

	private void writeQuoted(String field) {
		System.out.print('\"');
		for (char item : field.toCharArray()){
			if (item == '\"')
				System.out.print("\"\"");
			else
				System.out.print(item);
		}
		System.out.print('\"');
	}
}