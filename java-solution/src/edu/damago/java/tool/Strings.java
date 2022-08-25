package edu.damago.java.tool;

/**
 * Facade for additional String operations.
 */
public class Strings {

	/**
	 * Prevents external instantiation.
	 */
	private Strings () {}


	/**
	 * Returns a copy of the given text, resized to the given size. 
	 * @param text the text, or {@code null} for empty
	 * @param size the size
	 * @param leftHanded whether or not he modification shall be performed to the left of the given text
	 * @param pad the padding character
	 * @return the text created
	 * @throws IllegalArgumentException if the given size is strictly negative
	 */
	static public String resize (final String text, final int size, final boolean leftHanded, final char pad) throws IllegalArgumentException {
		if (size < 0) throw new IllegalArgumentException(String.valueOf(size));
		final StringBuilder factory = new StringBuilder(text == null ? "" : text);

		while (factory.length() < size) {
			if (leftHanded)
				factory.insert(0, pad);
			else
				factory.append(pad);
		}

		if (factory.length() > size) {
			if (leftHanded)
				factory.delete(0, factory.length() - size);
			else
				factory.delete(size, factory.length());
		}

		return factory.toString();
	}


	/**
	 * Returns how often the given character occurs within the given text. 
	 * @param text the text
	 * @param character the character
	 * @return the number of character occurrences
	 * @throws NullPointerException if the given text is {@code null}
	 */
	static public int occurrences (final String text, final char character) throws NullPointerException {
		int occurrences = 0;

		for (int index = 0; index < text.length(); ++index)
			if (text.charAt(index) == character) occurrences += 1;

		return occurrences;
	}


	/**
	 * Returns how often the given code-point occurs within the given text. 
	 * @param text the text
	 * @param codePoint the code-point
	 * @return the number of code-point occurrences
	 * @throws NullPointerException if the given text is {@code null}
	 */
	static public int occurrences (final String text, final int codePoint) throws NullPointerException {
		int occurrences = 0;

		for (int index = 0; index < text.length(); ++index)
			if (text.codePointAt(index) == codePoint) occurrences += 1;

		return occurrences;
	}
}